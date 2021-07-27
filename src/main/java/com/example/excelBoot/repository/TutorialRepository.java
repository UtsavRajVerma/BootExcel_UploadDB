package com.example.excelBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.excelBoot.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

}

//public interface TutorialRepository extends CrudRepository<Tutorial, Long>{
//
//}
