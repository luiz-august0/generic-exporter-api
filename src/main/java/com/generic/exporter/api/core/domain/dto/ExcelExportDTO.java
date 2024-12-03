package com.generic.exporter.api.core.domain.dto;

import java.io.Serializable;
import java.util.List;

public class ExcelExportDTO implements Serializable {

    private String tabTitle;

    private List<String> headers;

    private List<Object> data;

    private String filename;

    public ExcelExportDTO() {
    }

    public ExcelExportDTO(String tabTitle, List<String> headers, List<Object> data, String filename) {
        this.tabTitle = tabTitle;
        this.headers = headers;
        this.data = data;
        this.filename = filename;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Object> getData() {
        return data;
    }

    public String getFilename() {
        return filename;
    }

}
