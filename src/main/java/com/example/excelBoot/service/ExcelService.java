package com.example.excelBoot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.excelBoot.entity.Tutorial;
import com.example.excelBoot.helper.ExcelHelper;
import com.example.excelBoot.repository.TutorialRepository;

@Service
public class ExcelService {
	
	@Autowired
	private TutorialRepository repository;
	
	public void save(MultipartFile file) {
	    try {
	      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
	      repository.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("Failed to store excel data: " + e.getMessage());
	    }
	}

	  public ByteArrayInputStream load() {
		    List<Tutorial> tutorials = repository.findAll();

		    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
		    return in;
	  }
	
	  public List<Tutorial> getAllTutorials() {
	    return repository.findAll();
	  }
}
