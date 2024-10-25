package com.generic.exporter.api.core.service;

import com.generic.exporter.api.core.domain.dto.PdfExportDTO;
import com.generic.exporter.api.core.domain.dto.PdfExportResponseDTO;
import com.generic.exporter.api.core.gateway.PdfExportGateway;

public class PdfExportService {

    private final PdfExportGateway pdfExportGateway;

    public PdfExportService(PdfExportGateway pdfExportGateway) {
        this.pdfExportGateway = pdfExportGateway;
    }

    public PdfExportResponseDTO exportPdf(PdfExportDTO pdfExportDTO) {
        return pdfExportGateway.exportPdf(pdfExportDTO);
    }

}
