package com.example.demo.repository;

import com.example.demo.model.entitys.TechnicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository <TechnicalEntity,Integer> {

}
