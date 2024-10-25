package com.generic.exporter.api.controller;

import com.generic.exporter.api.controller.interfaces.IPdfExportController;
import com.generic.exporter.api.core.service.PdfExportService;
import com.generic.exporter.api.infrastructure.mapper.PdfExportMapper;
import com.generic.exporter.api.infrastructure.model.dto.PdfExportRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PdfExportController implements IPdfExportController {

    private final PdfExportService pdfExportService;

    private final PdfExportMapper pdfExportMapper = new PdfExportMapper();

    @Override
    public ResponseEntity<ByteArrayResource> exportPdf(PdfExportRequestDTO pdfExportRequestDTO) {
        return pdfExportMapper.toResponse(pdfExportService.exportPdf(pdfExportMapper.toDomain(pdfExportRequestDTO)));
    }

}
