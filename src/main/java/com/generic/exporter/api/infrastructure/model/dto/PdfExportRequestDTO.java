package com.generic.exporter.api.infrastructure.model.dto;

import com.generic.exporter.api.infrastructure.model.bean.PdfLineRequestBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PdfExportRequestDTO implements Serializable {

    private String title;

    private List<PdfLineRequestBean> headers;

    private List<PdfLineRequestBean> data;

    private List<PdfLineRequestBean> totalizers;

    private Integer headerTableFontSize;

    private Integer dataTableFontSize;

    private String filename;

}
