package com.generic.exporter.api.controller.interfaces;

import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.generic.exporter.api.infrastructure.constant.Path.prefixPath;

@RequestMapping(IExcelExportController.PATH)
public interface IExcelExportController {

    String PATH = prefixPath + "/excel";

    @GetMapping("/export")
    ResponseEntity<byte[]> exportExcel(@RequestBody ExcelExportDTO excelExportDTO);

}