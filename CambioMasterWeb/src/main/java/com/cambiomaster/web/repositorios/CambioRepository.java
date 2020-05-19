package com.cambiomaster.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambiomaster.web.modelo.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

}
