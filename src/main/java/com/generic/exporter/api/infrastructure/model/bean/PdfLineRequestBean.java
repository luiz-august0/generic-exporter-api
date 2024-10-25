package com.generic.exporter.api.infrastructure.model.bean;

import com.generic.exporter.api.infrastructure.model.enums.EnumAlignment;
import lombok.Data;

import java.io.Serializable;

@Data
public class PdfLineRequestBean implements Serializable {

    private String value;

    private EnumAlignment alignment;

}