package br.com.senac.uploadFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.uploadFile.model.FileModel;

public interface FileRepository extends JpaRepository<FileModel, Long> {
	
	public FileModel findByName(String name);
}
