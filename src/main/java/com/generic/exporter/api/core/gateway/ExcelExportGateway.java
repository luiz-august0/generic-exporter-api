package com.generic.exporter.api.core.gateway;

import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import com.generic.exporter.api.core.domain.dto.ExcelExportResponseDTO;

public interface ExcelExportGateway {

    ExcelExportResponseDTO exportExcel(ExcelExportDTO excelExportDTO);

}
