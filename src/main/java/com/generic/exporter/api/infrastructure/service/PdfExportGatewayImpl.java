package com.generic.exporter.api.infrastructure.service;

import com.generic.exporter.api.core.domain.dto.PdfExportDTO;
import com.generic.exporter.api.core.domain.dto.PdfExportResponseDTO;
import com.generic.exporter.api.core.gateway.PdfExportGateway;
import com.generic.exporter.api.infrastructure.exporter.pdf.PdfExporter;

public class PdfExportGatewayImpl implements PdfExportGateway {

    @Override
    public PdfExportResponseDTO exportPdf(PdfExportDTO pdfExportDTO) {
        PdfExporter pdfExporter = new PdfExporter(pdfExportDTO);

        return new PdfExportResponseDTO(
                pdfExporter.export(),
                pdfExportDTO.getFilename()
        );
    }

}
