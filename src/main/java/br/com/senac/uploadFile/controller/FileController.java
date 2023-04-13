package br.com.senac.uploadFile.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.uploadFile.model.FileModel;
import br.com.senac.uploadFile.service.FileService;

@Controller
public class fileController {

	@Autowired
	FileService fileService = new FileService();

	@GetMapping("/")
	public String index() {
		return "uploadForm";
	}

	@GetMapping("/files")
	public ModelAndView uploadFile(@RequestParam("files") MultipartFile[] files) {
		ModelAndView modelAndView = new ModelAndView("uploadForm");

		List<String> filesNames = new ArrayList<String>();
		List<FileModel> storedFile = new ArrayList<FileModel>();

		try {
			for (MultipartFile file : files) {
				FileModel fileModel = fileService.findNameFileModel(file.getOriginalFilename());
				if (fileModel != null) {
					fileModel.setPic(file.getBytes());
				} else {
					fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
				}

				filesNames.add(file.getOriginalFilename());
				storedFile.add(fileModel);
			}

			fileService.saveFiles(storedFile);

			modelAndView.addObject("message", "Files uploaded successfully!");
			modelAndView.addObject("files: ", filesNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			modelAndView.addObject("message", "Files uploaded failed!");
			modelAndView.addObject("files: ", filesNames);
			e.printStackTrace();
		}
		return modelAndView;
	}
}
