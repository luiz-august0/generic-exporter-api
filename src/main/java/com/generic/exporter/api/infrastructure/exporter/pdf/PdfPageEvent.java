package com.generic.exporter.api.infrastructure.exporter.pdf;

import com.generic.exporter.api.core.domain.bean.PdfLineBean;
import com.generic.exporter.api.infrastructure.exception.GenericsException;
import com.generic.exporter.api.infrastructure.exporter.pdf.util.PdfComponentUtil;
import com.generic.exporter.api.infrastructure.util.DateUtil;
import com.generic.exporter.api.infrastructure.util.ListUtil;
import com.generic.exporter.api.infrastructure.util.Util;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class PdfPageEvent extends PdfPageEventHelper {

    private final List<PdfLineBean> headers;

    private final String title;

    private final int headerTableFontSize;

    private final List<PdfLineBean> totalizers;

    public PdfPageEvent(List<PdfLineBean> headers, String title, int headerTableFontSize, List<PdfLineBean> totalizers) {
        this.headers = headers;
        this.title = title;
        this.headerTableFontSize = headerTableFontSize;
        this.totalizers = totalizers;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        boolean isFirstPage = Util.equals(writer.getPageNumber(), 1);

        PdfPTable table = mountTable();

        if (isFirstPage) {
            table.setSpacingAfter(-10);
        }

        mountMainHeader(document);

        if (isFirstPage && ListUtil.isNotNullOrNotEmpty(totalizers)) {
            mountTotalizers(document);
        }

        mountTableHeader(document, table);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Font font = FontFactory.getFont("sansserif", 10);
        int pageNumber = writer.getPageNumber();

        Phrase phraseDate = new Phrase("Relatório gerado em " + DateUtil.formatDDMMYYYYHHMM(new Date()), font);
        Phrase phrasepage = new Phrase("Página " + pageNumber, font);

        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, phraseDate, (document.left()), document.bottom() - 20, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, phrasepage, (document.right()), document.bottom() - 20, 0);
    }

    private void mountHeaderTitle(Document document, float spacingBefore, Integer size, String text) {
        try {
            Font font = FontFactory.getFont("sansserif", size, Font.BOLD);

            Paragraph paragraph = new Paragraph(text, font);

            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            paragraph.setSpacingBefore(spacingBefore);

            document.add(paragraph);
        } catch (Exception e) {
            throw new GenericsException(e.getMessage());
        }
    }

    private void mountMainHeader(Document document) {
        try {
            /*TODO implements image logo header
            InputStream inputStream = new ClassPathResource(BASE_PATH_LOGO).getInputStream();
            Image image = Image.getInstance(IOUtils.toByteArray(inputStream));

            image.scaleAbsolute(90, 20);

            float yPosition = document.getPageSize().getHeight() - image.getHeight();

            image.setAbsolutePosition(document.left(), yPosition);

            document.add(image);
            */

            mountHeaderTitle(document, 20, 10, title);
        } catch (Exception e) {
            throw new GenericsException(e.getMessage());
        }
    }

    private void mountTableHeader(Document document, PdfPTable table) {
        try {
            PdfPCell cell = new PdfPCell();

            PdfComponentUtil.addHeaderStyle(cell);

            headers.forEach(header -> {
                cell.setPhrase(new Phrase(header.getValue(), PdfComponentUtil.mountFont(headerTableFontSize, Color.GRAY)));
                cell.setHorizontalAlignment(header.getAlign());

                table.addCell(cell);
            });

            document.add(table);
        } catch (Exception e) {
            throw new GenericsException(e.getMessage());
        }
    }

    private void mountTotalizers(Document document) {
        try {
            PdfPTable table = PdfComponentUtil.mountTable(totalizers.size());
            table.setSpacingBefore(10);

            PdfPCell cell = new PdfPCell();
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            PdfComponentUtil.addHeaderStyle(cell);

            Font font = PdfComponentUtil.mountFont(8, Color.BLACK);

            totalizers.forEach(totalizer -> {
                cell.setPhrase(new Phrase(totalizer.getValue().substring(0, totalizer.getValue().indexOf(":")).trim(), font));

                table.addCell(cell);
            });

            document.add(table);

            PdfPTable tableContent = PdfComponentUtil.mountTable(totalizers.size());

            PdfPCell cellContent = new PdfPCell();
            cellContent.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

            PdfComponentUtil.addCellStyle(cellContent);

            totalizers.forEach(totalizer -> {
                cellContent.setPhrase(new Phrase(totalizer.getValue().substring(totalizer.getValue().indexOf(":") + 1).trim(), font));

                tableContent.addCell(cellContent);
            });

            document.add(tableContent);
        } catch (Exception e) {
            throw new GenericsException(e.getMessage());
        }
    }

    protected PdfPTable mountTable() {
        PdfPTable table = PdfComponentUtil.mountTable(headers.size());
        table.setSpacingBefore(10);

        return table;
    }

}