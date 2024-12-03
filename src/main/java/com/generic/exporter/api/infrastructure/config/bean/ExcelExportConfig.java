package com.generic.exporter.api.infrastructure.config.bean;

import com.generic.exporter.api.core.gateway.ExcelExportGateway;
import com.generic.exporter.api.core.service.ExcelExportService;
import com.generic.exporter.api.infrastructure.service.ExcelExportGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelExportConfig {

    @Bean
    ExcelExportService excelExportService(ExcelExportGateway excelExportGateway) {
        return new ExcelExportService(excelExportGateway);
    }

    @Bean
    ExcelExportGateway excelExportGateway() {
        return new ExcelExportGatewayImpl();
    }

}
