package com.cambiomaster.web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cambiomaster.web.modelo.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
	
	@Query(value="SELECT * FROM (SELECT * FROM SOLICITUD WHERE PRODUCTO_MANDA IS NULL ORDER BY ID_SOLICITUD) WHERE ROWNUM < 2", nativeQuery=true)
	Solicitud encontrarSolicitudPendiente();
	
	@Query(value="SELECT * FROM SOLICITUD WHERE USUARIO_RECIBE = ?1 && PRODUCTO_MANDA IS NOT NULL", nativeQuery=true)
	List<Solicitud> encontrarSolicitudesEnviaUsuario(long id);
	
	@Query(value="SELECT * FROM SOLICITUD WHERE USUARIO_SOLICITA = ?1 && PRODUCTO_MANDA IS NOT NULL", nativeQuery=true)
	List<Solicitud> encontrarSolicitudesMandaUsuario(long id);
	

}
