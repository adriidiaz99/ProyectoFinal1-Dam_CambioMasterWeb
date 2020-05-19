package com.cambiomaster.web.servicio;

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

}
