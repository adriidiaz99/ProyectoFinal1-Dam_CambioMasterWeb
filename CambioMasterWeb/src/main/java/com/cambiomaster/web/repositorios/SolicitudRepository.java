package com.cambiomaster.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambiomaster.web.modelo.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

}
