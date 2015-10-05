package com.ahana.api.system.security.error;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;

public class ErrorConstantsGenerator extends MatchingTask {

    private String xlsTemplatePath;

    private String xmlTemplatePath;

    private FileSet errorConstantFile;
    
    public void execute() {
        try {
        	System.out.println("xlsTemplatePath " +xlsTemplatePath);
        	System.out.println("xmlTemplatePath " +xmlTemplatePath);
        	System.out.println("errorConstantFile " +errorConstantFile);
            if (xlsTemplatePath == null) {
                throw new FileNotFoundException("XLS template should not be null..");
            }
            if (xmlTemplatePath == null) {
                throw new FileNotFoundException("XML template should not be null..");
            }
            if (errorConstantFile == null) {
                throw new FileNotFoundException("No java file Available to Transform..");
            }
            parseFile();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void parseFile() throws Exception {
        File errorTokenFile = null;
        FileSet fs = getErrorConstantFile();
        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
        String[] includedFiles = ds.getIncludedFiles();
        if (includedFiles != null && includedFiles.length > 0) {
            errorTokenFile = new File(ds.getBasedir(), includedFiles[0]);
        }
        XLSDocReader docReader = new XLSDocReader();
        File xmlFile = new File(ds.getBasedir(), xmlTemplatePath);
        File xlsFile = new File(ds.getBasedir(), xlsTemplatePath);
        ParserData parserData = docReader.readXLS(xmlFile, xlsFile);
        docReader.writeErrorTokens(errorTokenFile, parserData);
    }

    public String getXlsTemplatePath() {
        return xlsTemplatePath;
    }

    public void setXlsTemplatePath(String xlsTemplatePath) {
        this.xlsTemplatePath = xlsTemplatePath;
    }

    public String getXmlTemplatePath() {
        return xmlTemplatePath;
    }

    public void setXmlTemplatePath(String xmlTemplatePath) {
        this.xmlTemplatePath = xmlTemplatePath;
    }

    public FileSet getErrorConstantFile() {
        return errorConstantFile;
    }

    public void setErrorConstantFile(FileSet errorConstantFile) {
        this.errorConstantFile = errorConstantFile;
    }

    public void addErrorConstantFile(FileSet errorConstantFile) {
        this.errorConstantFile = errorConstantFile;
    }
    
    public static void main(String[] arg){
    	ErrorConstantsGenerator errorConstantsGenerator=new ErrorConstantsGenerator();
    	errorConstantsGenerator.execute();
    }
}