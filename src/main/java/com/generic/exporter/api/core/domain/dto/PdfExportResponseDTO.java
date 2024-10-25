package com.generic.exporter.api.core.domain.dto;

import java.io.ByteArrayOutputStream;

public class PdfExportResponseDTO {

    private ByteArrayOutputStream stream;

    private String filename;

    public PdfExportResponseDTO(ByteArrayOutputStream stream, String filename) {
        this.stream = stream;
        this.filename = filename;
    }

    public ByteArrayOutputStream getStream() {
        return stream;
    }

    public String getFilename() {
        return filename;
    }

}
