package com.example.demo.repository;

import com.example.demo.model.dtos.OsActiveDto;
import com.example.demo.model.entitys.AccumulatedExpenseEntity;
import com.example.demo.model.entitys.OsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OsRepository extends JpaRepository<OsEntity,Integer> {


    boolean existsByOsNumber(Integer osNumber);

    Optional<OsEntity> findByOsNumber(Integer osNumber);

    Optional<List<OsEntity>> findByIsEnable(boolean isEnable);

}