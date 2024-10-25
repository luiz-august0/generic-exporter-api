package com.generic.exporter.api.infrastructure.config.bean;

import com.generic.exporter.api.core.gateway.PdfExportGateway;
import com.generic.exporter.api.core.service.PdfExportService;
import com.generic.exporter.api.infrastructure.service.PdfExportGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfExportConfig {

    @Bean
    PdfExportService pdfExportService(PdfExportGateway pdfExportGateway) {
        return new PdfExportService(pdfExportGateway);
    }

    @Bean
    PdfExportGateway exportGateway() {
        return new PdfExportGatewayImpl();
    }

}
