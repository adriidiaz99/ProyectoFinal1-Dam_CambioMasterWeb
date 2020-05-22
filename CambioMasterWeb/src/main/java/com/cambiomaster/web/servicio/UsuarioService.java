package com.cambiomaster.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.repositorios.UsuarioRepository;
import com.cambiomaster.web.servicio.base.BaseService;
import com.cambiomaster.web.upload.DBStorageService;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {

	@Autowired
	private ProductoService productosDeUsuario;

	private final DBStorageService dbStorageService;

	public UsuarioService(UsuarioRepository repo, ProductoService productosDeUsuario,
			DBStorageService dbStorageService) {
		super(repo);
		this.productosDeUsuario = productosDeUsuario;
		this.dbStorageService = dbStorageService;
	}

	public Usuario buscarPorUserName(String usuario) {
		return repositorio.findFirstByUsername(usuario);
	}

	public Usuario register(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return edit(usuario);
	}
	
	public Usuario registerConImagen(Usuario usuario, MultipartFile file) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return edit(usuario, file);
	}

	public List<Empresa> findAllEmpresas() {
		return repositorio.findAllEmpresas();
	}

	public List<UsuarioGeneral> findAllUsuarios() {
		return repositorio.findAllUsuarios();
	}

	public void enviarSolicitud(Producto productoEnvia, Producto productoRecoge) {

	}

	public Usuario save(Usuario c, MultipartFile imagen) {

		String pathImagen = dbStorageService.store(imagen);
		c.setImagen(pathImagen);
		return this.save(c);
	}
	
	public Usuario edit(Usuario c, MultipartFile imagen) {

		String pathImagen = dbStorageService.store(imagen);
		c.setImagen(pathImagen);
		return this.edit(c);
	}

	@Override
	public void delete(Usuario usuario) {
		String idImagen = usuario.getImagen();
		dbStorageService.delete(Long.valueOf(idImagen));
		super.delete(usuario);
	}

	@Override
	public void deleteById(Long id) {
		Usuario usuario = findById(id);
		if (usuario != null)
			delete(usuario);
	}

}
