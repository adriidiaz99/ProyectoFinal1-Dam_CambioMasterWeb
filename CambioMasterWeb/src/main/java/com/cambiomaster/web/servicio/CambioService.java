package com.cambiomaster.web.servicio;

import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Cambio;
import com.cambiomaster.web.repositorios.CambioRepository;
import com.cambiomaster.web.servicio.base.BaseService;

@Service
public class CambioService extends BaseService<Cambio, Long, CambioRepository> {

	public CambioService(CambioRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
}
