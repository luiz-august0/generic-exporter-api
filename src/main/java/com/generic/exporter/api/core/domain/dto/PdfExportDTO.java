package com.generic.exporter.api.core.domain.dto;

import com.generic.exporter.api.core.domain.bean.PdfLineBean;

import java.util.List;

public class PdfExportDTO {
    private String title;

    private List<PdfLineBean> headers;

    private List<PdfLineBean> data;

    private List<PdfLineBean> totalizers;

    private Integer headerTableFontSize;

    private Integer dataTableFontSize;

    private String filename;

    public PdfExportDTO(String title, List<PdfLineBean> totalizers, List<PdfLineBean> headers, List<PdfLineBean> data, int headerTableFontSize, int dataTableFontSize, String filename) {
        this.title = title;
        this.headers = headers;
        this.data = data;
        this.headerTableFontSize = headerTableFontSize;
        this.dataTableFontSize = dataTableFontSize;
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public List<PdfLineBean> getHeaders() {
        return headers;
    }

    public List<PdfLineBean> getData() {
        return data;
    }

    public List<PdfLineBean> getTotalizers() {
        return totalizers;
    }

    public Integer getHeaderTableFontSize() {
        return headerTableFontSize;
    }

    public Integer getDataTableFontSize() {
        return dataTableFontSize;
    }

    public String getFilename() {
        return filename;
    }

}
