package com.generic.exporter.api.controller;

import com.generic.exporter.api.controller.interfaces.IExcelExportController;
import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import com.generic.exporter.api.core.service.ExcelExportService;
import com.generic.exporter.api.infrastructure.mapper.ExcelExportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExcelExportController implements IExcelExportController {

    private final ExcelExportService excelExportService;

    private final ExcelExportMapper excelExportMapper = new ExcelExportMapper();

    @Override
    public ResponseEntity<byte[]> exportExcel(ExcelExportDTO excelExportDTO) {
        return excelExportMapper.toResponse(excelExportService.exportExcel(excelExportDTO));
    }

}
