package com.example.demo.repository;

import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.entitys.OsEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface OsInterface extends JpaRepository<OsEntity,Integer> {


    boolean existsByOsNumber(Integer osNumber);

    Optional<OsEntity> findByOsNumber(String osNumber);

    void save(OsEntity osEntity);
}
