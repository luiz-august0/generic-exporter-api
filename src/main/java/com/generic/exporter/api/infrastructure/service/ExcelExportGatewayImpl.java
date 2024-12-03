package com.generic.exporter.api.infrastructure.service;

import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import com.generic.exporter.api.core.domain.dto.ExcelExportResponseDTO;
import com.generic.exporter.api.core.gateway.ExcelExportGateway;
import com.generic.exporter.api.infrastructure.exporter.excel.ExcelExporter;

public class ExcelExportGatewayImpl implements ExcelExportGateway {

    @Override
    public ExcelExportResponseDTO exportExcel(ExcelExportDTO excelExportDTO) {
        ExcelExporter excelExporter = new ExcelExporter(excelExportDTO);

        return new ExcelExportResponseDTO(
                excelExporter.export(),
                excelExportDTO.getFilename()
        );
    }

}
