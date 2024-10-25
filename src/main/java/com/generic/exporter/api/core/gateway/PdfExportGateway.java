package com.generic.exporter.api.core.gateway;

import com.generic.exporter.api.core.domain.dto.PdfExportDTO;
import com.generic.exporter.api.core.domain.dto.PdfExportResponseDTO;

public interface PdfExportGateway {

    PdfExportResponseDTO exportPdf(PdfExportDTO pdfExportDTO);

}
