package com.generic.exporter.api.core.domain.dto;

public class ExcelExportResponseDTO {

    private byte[] bytes;

    private String filename;

    public ExcelExportResponseDTO(byte[] bytes, String filename) {
        this.bytes = bytes;
        this.filename = filename;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getFilename() {
        return filename;
    }

}
