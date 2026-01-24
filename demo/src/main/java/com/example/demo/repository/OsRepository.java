package com.example.demo.repository;

import com.example.demo.model.entitys.OsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OsRepository extends JpaRepository<OsEntity,Integer> {


    boolean existsByOsNumber(Integer osNumber);

    Optional<OsEntity> findByOsNumber(Integer osNumber);

    OsEntity saveOs(OsEntity osEntity);
}
