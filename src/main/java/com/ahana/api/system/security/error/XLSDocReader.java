package com.ahana.api.system.security.error;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

public class XLSDocReader {

    ParserData readXLS(File xmlFile, File xlsFile) throws IOException,SAXException {
        InputStream inputXML = null;
        InputStream inputXLS = null;

        if (xmlFile.exists()) {
            inputXML = new BufferedInputStream(new FileInputStream(xmlFile));
        }
        if (xlsFile.exists()) {
            inputXLS = new BufferedInputStream(new FileInputStream(xlsFile));
        }

        // Returns an interface to read and parse excel file.
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

        Map<String, ParserData> beans = new HashMap<String, ParserData>();
        ParserData parserData = new ParserData();
        beans.put("parserData", parserData);
        //Reads the excel file and puts the results in the given Map.
        try {
			mainReader.read(inputXLS, beans);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
        ParserData data = (ParserData) beans.get("parserData");
        return data;
    }

    public ParserData readXLS(String xmlFileName, String xlsFileName)throws IOException, SAXException {
		InputStream inputXML = null;
		InputStream inputXLS = null;
		ParserData data = null;
		try {
			inputXML = new BufferedInputStream(getClass().getResourceAsStream(xmlFileName));
			inputXLS = new BufferedInputStream(getClass().getResourceAsStream(xlsFileName));
			// Returns an interface to read and parse excel file.
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			Map<String, ParserData> beans = new HashMap<String, ParserData>();
			ParserData parserData = new ParserData();
			beans.put("parserData", parserData);
			// Reads the excel file and puts the results in the given Map.
			try {
				mainReader.read(inputXLS, beans);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			data = (ParserData) beans.get("parserData");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (SAXException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			inputXML.close();
			inputXLS.close();
		}
		return data;
	}

    void writeErrorTokens(File errorTokenFile, ParserData parserData) {
        String fileLine;
        BufferedReader errorTokenFileBuffer = null;
        StringBuffer outputFileBuffer = new StringBuffer();
        boolean systemError = false;
        boolean applicationError = false;
        boolean businessError = false;
        try {
        	System.out.println("errorTokenFile : "+errorTokenFile);
            if (errorTokenFile.exists()) {
                errorTokenFileBuffer = new BufferedReader(new FileReader(errorTokenFile));
            }
            if (errorTokenFileBuffer != null) {
                while ((fileLine = errorTokenFileBuffer.readLine()) != null) {
                    //Checks whether system error constants exists or not.
                    if (fileLine.trim().contains("//SYSTEM ERROR START//")) {
                        systemError = true;
                    } else {
                        if (systemError && (fileLine.trim().contains("//SYSTEM ERROR END//") || (fileLine.trim().trim().length() == 0))) {
                            systemError = false;
                            outputFileBuffer.append(covertErrorTokenToJavaVariable(parserData.getSystemErrorConstants(),"//SYSTEM ERROR START//"));
                        }
                    }
                    //Checks whether application error constants exists or not.
                    if (fileLine.trim().contains("//APPLICATION ERROR START//")) {
                        applicationError = true;
                    } else {
                        if (applicationError && (fileLine.trim().contains("//APPLICATION ERROR END//") || (fileLine.trim().trim().length() == 0))) {
                                applicationError = false;
                                outputFileBuffer.append(covertErrorTokenToJavaVariable(parserData.getApplicationErrorConstants(),"//APPLICATION ERROR START//"));
                        }
                    }
                    //Checks whether business error constants exists or not.
                    if (fileLine.trim().contains("//BUSINESS ERROR START//")) {
                        businessError = true;
                    } else {
                        if (businessError && (fileLine.trim().contains("//BUSINESS ERROR END//") || (fileLine.trim().trim().length() == 0))) {
                        	businessError = false;
                            outputFileBuffer.append(covertErrorTokenToJavaVariable(parserData.getBusinessErrorConstants(),"//BUSINESS ERROR START//"));
                        }
                    }
                    if (!systemError && !applicationError && !businessError) {
                        outputFileBuffer.append(fileLine + "\n");
                    }
                }
                errorTokenFileBuffer.close();
                BufferedWriter out = new BufferedWriter(new FileWriter(errorTokenFile));
                out.write(outputFileBuffer.toString());
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String covertErrorTokenToJavaVariable(List<ResponseError> tokens,
            String header) {
        String temp = "";
        temp = header + "\n";
        if (tokens != null && tokens.size() > 0) {
            for (ResponseError str : tokens) {
                if (str.getShortMessage() != null
                        && str.getShortMessage().length() > 0) {
                    temp += "public static final String "
                            + str.getShortMessage().trim().replace(" ", "_")
                                    .toUpperCase() + " = \""
                            + str.getShortMessage() + "\"; \n";
                }
            }
        }
        return temp;
    }
}