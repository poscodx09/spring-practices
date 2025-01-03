package fileupload.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fileupload.service.FileUploadService;


@Controller
public class FileUploadController {
	
	private final FileUploadService fileUploadService;
	
	@Autowired
	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	@RequestMapping({"/", "/form"})
	public String form() {
		return "form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("email") String email,
			@RequestParam("file") MultipartFile file,
			Model model) {
		
		String url = fileUploadService.restore(file);
		model.addAttribute("url", url);
		
		return "result";
		
	}
}
