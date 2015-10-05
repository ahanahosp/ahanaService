package com.ahana.api.system.security.error;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("ExportContent")
public class ExportContent implements AhanaModelObject  {

    @XStreamAlias("Content")
    private String content;

    @XStreamAlias("ReportFileName")
    private String reportFileName;
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(!content.contains("CDATA")) {
            content = "<![CDATA[" + content + "]]>";
        }
        this.content = content;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}