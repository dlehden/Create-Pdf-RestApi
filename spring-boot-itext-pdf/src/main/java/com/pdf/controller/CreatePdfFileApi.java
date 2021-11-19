package com.pdf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.component.PdfTable;
import com.pdf.service.PdfMakeFileServiceImp;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pdf")
@Slf4j
public class CreatePdfFileApi {

	private PdfTable pdfTable;
	private PdfMakeFileServiceImp pdfMakeFile;

	public CreatePdfFileApi(PdfTable pdfTable, PdfMakeFileServiceImp pdfMakeFile) {
		this.pdfTable = pdfTable;
		this.pdfMakeFile = pdfMakeFile;
	}

	@GetMapping("/linerbl")
	public String createItextPdfFile() throws Exception {
		String checkPdfData ="";
		for(int i=0 ; i < 3; i ++) {
			 checkPdfData  = pdfMakeFile.pdfMakeFile("blno"+"_"+i);
		}
		return checkPdfData;
	}

	@GetMapping("/images/{filename}")
	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		// root path for image files
		String FILE_PATH_ROOT = "/Users/SINO/Desktop/demo.png";

		byte[] image = new byte[0];
		try {
			image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);

	}

	@GetMapping("/viewer")
	public ResponseEntity<InputStreamResource> getBlPdfFile() throws FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline;filename=" + "[2021111849]_BL.pdf");
		File file = new File("/Users/SINO/Desktop/createpdf/" + "[2021111849]_BL.pdf");

		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
	}
}
