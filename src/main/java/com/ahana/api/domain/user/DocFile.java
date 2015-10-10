package com.ahana.api.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.ahana.api.common.AhanaVO;

@SuppressWarnings("serial")
@Entity
@Table(name = "doc_files")
@NamedQueries({
		@NamedQuery(name = "getUserPhotoByUserOid", query = "from DocFile docFile where docFile.userOid= :userOid")})
public class DocFile implements AhanaVO {

	@Id
	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator", strategy = "com.ahana.api.common.IdGenerator", parameters = {
			@Parameter(name = "table", value = "seed_container"),
			@Parameter(name = "column", value = "high_oid"),
			@Parameter(name = "install_id", value = "seed_id"),
			@Parameter(name = "max_lo", value = "100000") })
	@Column(name = "oid")
	private String oid;

	@Column(name = "user_oid")
	private String userOid;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "data")
	private byte[] data;

	@Column(name = "mime_type")
	private String mimeType;

	public DocFile() {
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUserOid() {
		return userOid;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
