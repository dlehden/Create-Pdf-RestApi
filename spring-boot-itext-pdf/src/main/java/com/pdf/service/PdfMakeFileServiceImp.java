package com.pdf.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.component.PdfTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PdfMakeFileServiceImp implements PdfMakeFile {
	
	private PdfTable pdfTable;

	public PdfMakeFileServiceImp(PdfTable pdfTable) {
		this.pdfTable = pdfTable;
	}

	@Override
	public String pdfMakeFile(String blno) throws Exception {
		String reportName, reportFilePath;
		/* 보고서 이름 PDF 생성 시 마다 초기화 */
		reportName = new SimpleDateFormat("[yyyyMMddss]").format(new Date()) + "_BL";
		reportFilePath = "/Users/SINO/Desktop/createpdf/" + blno + ".pdf";

		Document document = new Document(PageSize.A4);
		FileOutputStream fos = new FileOutputStream(reportFilePath);
		// 출력할 pdf 파일
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		document.open();
		PdfContentByte canvas = writer.getDirectContent();
		// 원본 pdf 파일(클래스패스에서 가져옴)
		PdfReader mainPage = new PdfReader("/Users/SINO/Desktop/createpdf/samplepdf.pdf");
		
		// 원본 pdf 에서 페이지를 하나씩 읽어서 출력 pdf 에 추가한다.
		for (int num = 1, size = mainPage.getNumberOfPages(); num <= size; num++) {
			PdfImportedPage page = writer.getImportedPage(mainPage, num);
			document.newPage();
			// 0, 0 위치에 페이지 추가(참고로 itext에서 좌표 체계는 왼쪽 아래에서 부터 0, 0으로 시작함, 그래프와 비슷함)
			canvas.addTemplate(page, 0, 0);
			// 첫번째 페이지만 상단에 테이블 추가
			if (num == 1) {
				// (0, -1) : 전체 row, 뒤에 숫자 두개는 추가할 위치(x, y) = 왼쪽에서 20, top+20 위치에 테이블 추가
				pdfTable.makeBlNo("1234567").writeSelectedRows(0, -1, 430, document.top() - 15, canvas);
				pdfTable.makeShipper("shipper").writeSelectedRows(0, -1, 20, document.top() - 20, canvas);
				//pdfTable.makeShipper("shipper").writeSelectedRows(0, -1, 20, document.top() - 80, canvas); //consignee part
				pdfTable.makeShipper("shipper").writeSelectedRows(0, -1, 20, document.top() - 340, canvas);   //container part
			}
		}

        //attatchPage
		attPdfPage( document ,  writer);
		
		document.close();
		fos.close();
		return "abc";
	}
	
	public void attPdfPage(Document document , PdfWriter writer) throws Exception{
		 PdfReader attPage = new PdfReader("/Users/SINO/Desktop/createpdf/samplepdf.pdf");
		 PdfContentByte canvas = writer.getDirectContent();
		// 원본 pdf 에서 페이지를 하나씩 읽어서 출력 pdf 에 추가한다.
		for (int num = 1, size = attPage.getNumberOfPages(); num <= size; num++) {

			PdfImportedPage page = writer.getImportedPage(attPage, num);
			document.newPage();
			// 0, 0 위치에 페이지 추가(참고로 itext에서 좌표 체계는 왼쪽 아래에서 부터 0, 0으로 시작함, 그래프와 비슷함)
			canvas.addTemplate(page, 0, 0);
			// 첫번째 페이지만 상단에 테이블 추가
			if (num == 1) {
				// (0, -1) : 전체 row, 뒤에 숫자 두개는 추가할 위치(x, y) = 왼쪽에서 20, top+20 위치에 테이블 추가
				pdfTable.makeBlNo("1234567").writeSelectedRows(0, -1, 430, document.top() - 15, canvas);
				pdfTable.makeShipper("shipper").writeSelectedRows(0, -1, 20, document.top() - 20, canvas);
			}

			// 워터마크 이미지(클래스패스에서 가져옴)
			Image img = Image.getInstance("/Users/SINO/Desktop/sinotrans/business/SNO_LIN/PrtFrm/PrtFrm/SNTX.bmp");
			img.setAbsolutePosition(200, 300);
			img.scalePercent(20);

			// 이미지 투명하게 처리
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.2f);
			canvas.setGState(state);

			canvas.addImage(img);
		}
		
	}

}
