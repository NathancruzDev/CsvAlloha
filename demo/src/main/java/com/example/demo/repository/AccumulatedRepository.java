package com.example.demo.repository;

import com.example.demo.model.entitys.AccumulatedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccumulatedRepository extends JpaRepository<AccumulatedExpenseEntity,Integer> {

}
