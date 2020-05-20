package com.cambiomaster.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.repositorios.UsuarioRepository;
import com.cambiomaster.web.servicio.base.BaseService;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {
	
	@Autowired
	private ProductoService productosDeUsuario;
	@Autowired
	private BuzonService buzonDeUsuario;

	public UsuarioService(UsuarioRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
	public Usuario buscarPorUserName(String usuario) {
		return repositorio.findFirstByUsername(usuario);
	}
	
	public Usuario register(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return edit(usuario);
	}
	
	public List<Empresa> findAllEmpresas() {
		return repositorio.findAllEmpresas();
	}
	
	public List<UsuarioGeneral> findAllUsuarios() {
		return repositorio.findAllUsuarios();
	}

}
