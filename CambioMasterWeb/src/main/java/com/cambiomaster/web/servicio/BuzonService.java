package com.cambiomaster.web.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Buzon;
import com.cambiomaster.web.repositorios.BuzonRepository;
import com.cambiomaster.web.servicio.base.BaseService;

@Service
public class BuzonService extends BaseService<Buzon, Long, BuzonRepository> {
	
	@Autowired
	private SolicitudService solicitudesUsuario;

	public BuzonService(BuzonRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
}
