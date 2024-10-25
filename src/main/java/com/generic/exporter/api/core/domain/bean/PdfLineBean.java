package com.generic.exporter.api.core.domain.bean;

public class PdfLineBean {

    private String value;

    private Integer align;

    public PdfLineBean(String value, Integer align) {
        this.value = value;
        this.align = align;
    }

    public String getValue() {
        return value;
    }

    public Integer getAlign() {
        return align;
    }

}