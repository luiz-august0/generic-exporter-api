package com.generic.exporter.api.infrastructure.mapper;

import com.generic.exporter.api.core.domain.dto.ExcelExportResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@NoArgsConstructor
public class ExcelExportMapper {

    public ResponseEntity<byte[]> toResponse(ExcelExportResponseDTO excelExportResponseDTO) {
        byte[] encode = Base64.getEncoder().encode(excelExportResponseDTO.getBytes());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=" + excelExportResponseDTO.getFilename())
                .body(encode);
    }

}
