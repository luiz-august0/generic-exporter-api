package com.generic.exporter.api.controller.interfaces;

import com.generic.exporter.api.infrastructure.model.dto.PdfExportRequestDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.generic.exporter.api.infrastructure.constant.Path.prefixPath;

@RequestMapping(IPdfExportController.PATH)
public interface IPdfExportController {

    String PATH = prefixPath + "/pdf";

    @GetMapping("/export")
    ResponseEntity<ByteArrayResource> exportPdf(@RequestBody PdfExportRequestDTO pdfExportRequestDTO);

}