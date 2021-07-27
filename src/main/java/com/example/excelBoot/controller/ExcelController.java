package com.example.excelBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.excelBoot.entity.Tutorial;
import com.example.excelBoot.helper.ExcelHelper;
import com.example.excelBoot.message.ResponseMessage;
import com.example.excelBoot.service.ExcelService;

@CrossOrigin("http://localhost:6326")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

  @Autowired
  private ExcelService fileService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(value="file", required=true) MultipartFile file) {
    String message = "";

    if (ExcelHelper.hasExcelFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }
    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }
  
//  @PostMapping(value="/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//  public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
//    String message = "";
//
////    if (ExcelHelper.hasExcelFormat(file)) {
////      try {
//        fileService.save(file);
//
////        message = "Uploaded the file successfully: " + file.getOriginalFilename();
////        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//        
//        ModelAndView mv=new ModelAndView();
//    	mv.addObject("file",file);
//    	mv.setViewName("success");
//    	return mv;
////        
////       return "success";
//        
////        return "home";
////      } catch (Exception e) {
////        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
////        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
////      }
////    }
////    message = "Please upload an excel file!";
////    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials() {
    try {
      List<Tutorial> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/download")
  public ResponseEntity<Resource> getFile() {
    String filename = "tutorials.xlsx";
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
  }
}

