package com.pdf.component;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@Component
public class PdfTable {
	public PdfPTable makeBlNo(String blno) throws DocumentException, IOException {
	    // 클래스패스 루트에 나눔고딕 폰트 추가(한글을 쓰기 위해서 한글폰트를 설정해 줘야됨)
	    BaseFont base_font = BaseFont.createFont();

	    Font header_font = new Font(base_font, 10);
	    header_font.setColor(BaseColor.BLACK);

	    // 컬럼 4개 짜리 테이블
	    PdfPTable table = new PdfPTable(1);
	    table.setTotalWidth(new float[] { 150 });

	    // 컬럼이 4개니까 addCell 4번 해주면 됨
	    PdfPCell cell = new PdfPCell(new Phrase("BL"+blno, header_font));
	    cell.setFixedHeight(18);
	    cell.setBorder(0);
//	    cell.setUseVariableBorders(true);
//	    cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP | Rectangle.RIGHT );
//	    cell.setBorderColorLeft(BaseColor.WHITE);
//	    cell.setBorderColorBottom(BaseColor.WHITE);
//	    cell.setBorderColorTop(BaseColor.WHITE);
//	    cell.setBorderColorRight(BaseColor.WHITE);
	   // cell.setBackgroundColor(BaseColor.GRAY);
	    table.addCell(cell);

	    return table;
	}

	public PdfPTable makeShipper(String Shipper) throws DocumentException, IOException {
	    // 클래스패스 루트에 나눔고딕 폰트 추가(한글을 쓰기 위해서 한글폰트를 설정해 줘야됨)
	    BaseFont base_font = BaseFont.createFont();

	    Font header_font = new Font(base_font, 7);
	    header_font.setColor(BaseColor.BLACK);

	    // 컬럼 4개 짜리 테이블
	    PdfPTable table = new PdfPTable(1);
	    table.setTotalWidth(new float[] { 300 });

	    // 컬럼이 4개니까 addCell 4번 해주면 됨
	    PdfPCell cell = new PdfPCell(new Phrase("HANIL PRECISION INDUSTRY CO.,LTD", header_font));
	   // cell.setFixedHeight(11);
	    cell.setBorder(0);
	    table.addCell(cell);
	    
	    cell = new PdfPCell(new Phrase("419-2, KEUMCHON 1-DONG, PAJU-SI,", header_font));
	   // cell.setFixedHeight(11);
	    cell.setBorder(0);
	    table.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase("TEL : +82-41-421-5305 FAX : +82-41-421-5009", header_font));
	  //  cell.setFixedHeight(11);
	    cell.setBorder(0);
	    table.addCell(cell);
	    
	    
	    cell = new PdfPCell(new Phrase("SENDER: LEE DAE HOON CORPORATE VAT NUMBER : 173850", header_font));
	  //  cell.setFixedHeight(11);
	    cell.setBorder(0);
	    table.addCell(cell);

	    return table;
	}

}
