package com.generic.exporter.api.core.service;

import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import com.generic.exporter.api.core.domain.dto.ExcelExportResponseDTO;
import com.generic.exporter.api.core.gateway.ExcelExportGateway;

public class ExcelExportService {

    private final ExcelExportGateway excelExportGateway;

    public ExcelExportService(ExcelExportGateway excelExportGateway) {
        this.excelExportGateway = excelExportGateway;
    }

    public ExcelExportResponseDTO exportExcel(ExcelExportDTO excelExportDTO) {
        return excelExportGateway.exportExcel(excelExportDTO);
    }

}
