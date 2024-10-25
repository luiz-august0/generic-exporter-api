package com.generic.exporter.api.infrastructure.mapper;

import com.generic.exporter.api.core.domain.bean.PdfLineBean;
import com.generic.exporter.api.core.domain.dto.PdfExportDTO;
import com.generic.exporter.api.core.domain.dto.PdfExportResponseDTO;
import com.generic.exporter.api.infrastructure.model.bean.PdfLineRequestBean;
import com.generic.exporter.api.infrastructure.model.dto.PdfExportRequestDTO;
import com.generic.exporter.api.infrastructure.model.enums.EnumAlignment;
import com.generic.exporter.api.infrastructure.util.Util;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PdfExportMapper {

    public PdfExportDTO toDomain(PdfExportRequestDTO pdfExportRequestDTO) {
        return new PdfExportDTO(
                pdfExportRequestDTO.getTitle(),
                toDomainPdfLines(pdfExportRequestDTO.getTotalizers()),
                toDomainPdfLines(pdfExportRequestDTO.getHeaders()),
                toDomainPdfLines(pdfExportRequestDTO.getData()),
                Util.nvl(pdfExportRequestDTO.getHeaderTableFontSize(), 10),
                Util.nvl(pdfExportRequestDTO.getDataTableFontSize(), 10),
                pdfExportRequestDTO.getFilename()
        );
    }

    public ResponseEntity<ByteArrayResource> toResponse(PdfExportResponseDTO pdfExportResponseDTO) {
        ByteArrayOutputStream pdf = pdfExportResponseDTO.getStream();
        ByteArrayResource resource = new ByteArrayResource(pdf.toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "attachment; filename=" + pdfExportResponseDTO.getFilename())
                .body(resource);
    }

    private List<PdfLineBean> toDomainPdfLines(List<PdfLineRequestBean> pdfLineRequestBeans) {
        return Util.nvl(pdfLineRequestBeans, new ArrayList<PdfLineRequestBean>()).stream()
                .map((pdfLineRequestBean -> (
                        new PdfLineBean(
                                pdfLineRequestBean.getValue(),
                                Util.nvl(pdfLineRequestBean.getAlignment(), EnumAlignment.CENTER).getElement()
                        )
                ))).collect(Collectors.toList());
    }

}
