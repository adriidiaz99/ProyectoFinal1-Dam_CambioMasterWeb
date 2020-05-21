package com.cambiomaster.web.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Solicitud;
import com.cambiomaster.web.repositorios.SolicitudRepository;
import com.cambiomaster.web.servicio.base.BaseService;

@Service
public class SolicitudService extends BaseService<Solicitud, Long, SolicitudRepository>{

	public SolicitudService(SolicitudRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
	public Solicitud solicitudPendiente() {
		return repositorio.encontrarSolicitudPendiente();
	}
	
	public List<Solicitud> encontrarSolicitudRecibidas(long id) {
		return repositorio.encontrarSolicitudesEnviaUsuario(id);
	}
	
	public List<Solicitud> encontrarSolicitudMandadas(long id) {
		return repositorio.encontrarSolicitudesMandaUsuario(id);
	}

}
