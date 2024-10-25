package com.generic.exporter.api.infrastructure.model.enums;

import com.lowagie.text.Element;
import lombok.Getter;

@Getter
public enum EnumAlignment {

    CENTER("CENTER", Element.ALIGN_CENTER),
    LEFT("LEFT", Element.ALIGN_LEFT),
    RIGHT("RIGHT", Element.ALIGN_RIGHT);

    private String align;

    private Integer element;

    EnumAlignment(String align, Integer element) {
        this.align = align;
        this.element = element;
    }

}
