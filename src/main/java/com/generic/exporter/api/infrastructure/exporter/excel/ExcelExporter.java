package com.generic.exporter.api.infrastructure.exporter.excel;

import com.generic.exporter.api.core.domain.dto.ExcelExportDTO;
import com.generic.exporter.api.infrastructure.exception.GenericsException;
import com.generic.exporter.api.infrastructure.util.Util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ExcelExporter {

    private final ExcelExportDTO excelExportDTO;

    public ExcelExporter(ExcelExportDTO excelExportDTO) {
        this.excelExportDTO = excelExportDTO;
    }

    public byte[] export() {
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        String tab = excelExportDTO.getTabTitle();

        byte[] excelReturn;
        try {
            if (Util.isEmpty(tab)) tab = "Plan1";

            Sheet sheet = wb.createSheet(tab);

            getHeaderRow(excelExportDTO.getHeaders(), sheet, wb);

            Row row;

            int rowIdx = 1;
            int lineIdx = 0;
            int totalRows = new BigDecimal(excelExportDTO.getData().size())
                    .divide(new BigDecimal(excelExportDTO.getHeaders().size()), 0, RoundingMode.UP)
                    .intValue();

            while (rowIdx <= totalRows) {
                int colCount = 0;
                row = sheet.createRow(rowIdx);

                while (colCount < excelExportDTO.getHeaders().size()) {
                    Cell cell = row.createCell(colCount++);

                    setCellValue(cell, excelExportDTO.getData().get(lineIdx));
                    setCellStyleDefault(wb, cell);

                    if (lineIdx < excelExportDTO.getData().size() - 1) {
                        lineIdx++;
                    } else {
                        break;
                    }
                }

                rowIdx++;
            }

            sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, excelExportDTO.getHeaders().size() - 1));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            wb.setForceFormulaRecalculation(true);
            wb.write(baos);
            wb.dispose();
            wb.close();

            excelReturn = baos.toByteArray();

            baos.close();
        } catch (Exception e) {
            throw new GenericsException("Error on export excel: " + e.getMessage());
        }

        return excelReturn;
    }

    private void getHeaderRow(List<String> headers, Sheet sheet, SXSSFWorkbook wb) {
        int columnCount = 0;
        Row row = sheet.createRow(0);
        CellStyle cellStyleHeader = getHeaderCellStyle(wb);

        for (String headerCell : headers) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(headerCell);
            cell.setCellStyle(cellStyleHeader);
        }
    }

    private CellStyle getHeaderCellStyle(SXSSFWorkbook wb) {
        Font headerFont = wb.createFont();
        CellStyle csHeader = wb.createCellStyle();

        headerFont.setBold(true);
        csHeader.setFont(headerFont);
        csHeader.setBorderTop(BorderStyle.MEDIUM);
        csHeader.setBorderBottom(BorderStyle.MEDIUM);
        csHeader.setBorderLeft(BorderStyle.THIN);
        csHeader.setBorderRight(BorderStyle.THIN);

        return csHeader;
    }

    private CellStyle createCellStyle(Workbook wb) {
        Font lineFont = wb.createFont();
        CellStyle cell = wb.createCellStyle();

        cell.setFont(lineFont);
        cell.setBorderTop(BorderStyle.THIN);
        cell.setBorderBottom(BorderStyle.THIN);
        cell.setBorderLeft(BorderStyle.THIN);
        cell.setBorderRight(BorderStyle.THIN);

        return cell;
    }

    private void setCellValue(Cell cell, Object value) {
        cell.setCellValue(value.toString());
    }

    private void setCellStyleDefault(SXSSFWorkbook wb, Cell cell) {
        cell.setCellStyle(createCellStyle(wb));
    }

}
