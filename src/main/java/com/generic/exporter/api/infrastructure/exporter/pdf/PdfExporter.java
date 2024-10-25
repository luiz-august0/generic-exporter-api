package com.generic.exporter.api.infrastructure.exporter.pdf;

import com.generic.exporter.api.core.domain.dto.PdfExportDTO;
import com.generic.exporter.api.infrastructure.exception.GenericsException;
import com.generic.exporter.api.infrastructure.exporter.pdf.util.PdfComponentUtil;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@RequiredArgsConstructor
public class PdfExporter {

    private final PdfExportDTO pdfExportDTO;

    public ByteArrayOutputStream export() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
            PdfPageEvent event = new PdfPageEvent(
                    pdfExportDTO.getHeaders(),
                    pdfExportDTO.getTitle(),
                    pdfExportDTO.getHeaderTableFontSize(),
                    pdfExportDTO.getTotalizers()
            );

            pdfWriter.setPageEvent(event);

            document.open();

            PdfPTable table = event.mountTable();
            mountTableData(table);

            document.add(table);
            document.close();

            return baos;
        } catch (Exception e) {
            throw new GenericsException(e.getMessage());
        }
    }

    private void mountTableData(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        PdfComponentUtil.addCellStyle(cell);

        pdfExportDTO.getData().forEach(dataValue -> {
            cell.setPhrase(new Phrase(dataValue.getValue(), PdfComponentUtil.mountFont(pdfExportDTO.getDataTableFontSize(), Color.BLACK)));
            cell.setHorizontalAlignment(dataValue.getAlign());

            table.addCell(cell);
        });
    }

}