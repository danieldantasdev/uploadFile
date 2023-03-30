package br.com.senac.uploadFile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.uploadFile.model.FileModel;
import br.com.senac.uploadFile.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;

	public FileModel findNameFileModel(String name) {
		return fileRepository.findByName(name);
	}
	
	public void saveFiles(List<FileModel> fileModels) {
		fileRepository.saveAll(fileModels);
	}
}
