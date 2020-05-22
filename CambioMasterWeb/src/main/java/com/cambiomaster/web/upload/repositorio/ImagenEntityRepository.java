package com.cambiomaster.web.upload.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambiomaster.web.upload.modelo.ImagenEntity;

public interface ImagenEntityRepository extends JpaRepository<ImagenEntity, Long> {

}
