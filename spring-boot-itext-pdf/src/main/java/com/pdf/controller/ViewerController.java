package com.pdf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewerController {
	
	@RequestMapping(value = "show", method=RequestMethod.GET)
	public String viewerPdf() {
		return "viewer";
	}

}
