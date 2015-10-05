package com.ahana.api.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;

public class IdGenerator implements PersistentIdentifierGenerator,
		Configurable {
	private static final Log log = LogFactory.getLog(IdGenerator.class);
	
	public static final String COLUMN = "column";

	public static final String TABLE = "table";
	
	public static final String INSTALL_ID = "install_id";
	
	public static final String MAX_LO = "max_lo";
	
	public static final String ID_TYPE = "type";

	private String seedContainerTableName;

	private String highOidColName;

	private String installIdColName;

	private String query;

	private String update;

	private long hi;

	private int lo;

	private int maxLo;

	private String installId = null;
	
	private String idType = null;

	public void configure(Type type, Properties params, Dialect dialect) {
		
		this.seedContainerTableName = ConfigurationHelper.getString(TABLE, params,"ahana.seed_container");
		this.highOidColName = ConfigurationHelper.getString(COLUMN, params, "high_oid");
		this.installIdColName = ConfigurationHelper.getString(INSTALL_ID, params,"seed_id");
		this.idType=ConfigurationHelper.getString(ID_TYPE, params,Constants.ID_REST);
		
		String schemaName = params.getProperty(SCHEMA);
		if (schemaName != null && seedContainerTableName.indexOf('.') < 0)
			seedContainerTableName = schemaName + '.' + seedContainerTableName;

		query = "select " + highOidColName + "," + installIdColName + " from " + seedContainerTableName + " where type='"+this.idType+"'";
		update = "update " + seedContainerTableName + " set " + highOidColName + " = ? where " + installIdColName + " = ? and type = ?";

		maxLo = ConfigurationHelper.getInt(MAX_LO, params, Short.MAX_VALUE);
		lo = maxLo + 1; // so we "clock over" on the first invocation
	}

	public synchronized Serializable generate(SessionImplementor session,Object obj) throws HibernateException {
		if (lo > maxLo) {
			try {
				hi = ((Integer) realGenerate(session, obj)).intValue();
				lo = 1;
			} catch (Exception e1) {

				if(!retryRealGenerate(session, obj)) {
						e1.printStackTrace();
						throw new HibernateException(e1);
				}
			}
			log.debug("new hi value: " + hi);
		}
		String nextOid=null;
		if(idType.equalsIgnoreCase(Constants.ID_STAFF) || idType.equalsIgnoreCase(Constants.ID_PATIENT)){
			nextOid= installId+hi;
		}else{
			nextOid= IdGenerator.getNextOid(installId,hi,(hi + lo++));
		}
		return nextOid;
	}

	public synchronized Serializable realGenerate(SessionImplementor session,
			Object object) throws Exception {
		Connection conn = null;
		int currentHiVal;
		int rows;
		int tx = Connection.TRANSACTION_READ_COMMITTED;
		boolean autoCommit = false;
		try {
			conn = session.connection();
			autoCommit = conn.getAutoCommit();
			if (autoCommit ){
				conn.setAutoCommit( false );
			}
			do {
				tx = conn.getTransactionIsolation();
				conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				PreparedStatement qps = conn.prepareStatement(query);
				try {
					ResultSet rs = qps.executeQuery();
					if (!rs.next()) {
						String err = "could not read a hi value - you need to populate the table: " + seedContainerTableName;
						log.error(err);
						throw new IdentifierGenerationException(err);
					}
					currentHiVal = rs.getInt(1);
					installId = rs.getString(2);
					rs.close();
				} catch (Exception sqle) {
					log.error("could not read a hi value", sqle);
					throw sqle;
				} finally {
					qps.close();
				}

				PreparedStatement ups = conn.prepareStatement(update);
				try {
					ups.setInt(1,(currentHiVal + 1));
					ups.setString(2, installId);
					ups.setString(3, idType);
					rows = ups.executeUpdate();
				} catch (Exception sqle) {
					log.error("could not update hi value in: " + seedContainerTableName,sqle);
					throw sqle;
				} finally {
					ups.close();
				}
			} while (rows == 0);
			
		} finally {
			if(conn != null){
				conn.commit();
				conn.setAutoCommit(autoCommit);
				conn.setTransactionIsolation(tx);
				conn.close();	
			}
		}
		return new Integer(currentHiVal);
	}

	public synchronized boolean retryRealGenerate(SessionImplementor session,Object obj) {
		try{
				hi = ((Integer) realGenerate(session, obj)).intValue();
				lo = 1;
		}catch(Exception e) {
			try{
					hi = ((Integer) realGenerate(session, obj)).intValue();
					lo = 1;
				}catch(Exception e1) {
                  return false;
		        }
		}
		return true;
	}

	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		return new String[] {
				"create table " + seedContainerTableName + " ( " + highOidColName + " "
						+ dialect.getTypeName(Types.INTEGER) + " )",
				"insert into " + seedContainerTableName + " values ( 0 )" };
	}

	public String sqlDropString(Dialect dialect) {
		StringBuffer sqlDropString = new StringBuffer().append("drop table ");
		if (dialect.supportsIfExistsBeforeTableName())
			sqlDropString.append("if exists ");
		sqlDropString.append(seedContainerTableName).append(
				dialect.getCascadeConstraintsString());
		if (dialect.supportsIfExistsAfterTableName())
			sqlDropString.append(" if exists");
		return sqlDropString.toString();
	}

	public Object generatorKey() {
		return seedContainerTableName;
	}

	public static String getNextOid(String installationId,long hiValue, long hiPlusLow) {
		return
		installationId + //Strictly limited to 3 chars
		pad(Long.toHexString(hiValue), 8, '0')+ // Range of 4294967295 (429Million), and after that change the install ID and reset the HI to 1
		pad(Long.toHexString(hiPlusLow), 9, '0');// wITH MAX LOW AT 100k this gives us a range of 429M s Hi Value, before we start overflowing hiPlus Low in Hex. Note we just pad and not truncate (the reason we do this so that we will get and error and we will know, else it will mess up the DB)
	}

	public static String pad(String str, int length, char c) {
		StringBuffer sb = new StringBuffer();
		int num = length - str.length();
		for (int i = 0; i < num; i++)
			sb.append(c);
		sb.append(str);
		return sb.toString();
	}

	public String[] sqlDropStrings(Dialect arg0) throws HibernateException {
		return null;
	}
}