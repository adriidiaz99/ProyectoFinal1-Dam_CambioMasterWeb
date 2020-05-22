package com.cambiomaster.web.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cambiomaster.web.modelo.Alimentacion;
import com.cambiomaster.web.modelo.Calzado;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.repositorios.ProductoRepository;
import com.cambiomaster.web.servicio.base.BaseService;
import com.cambiomaster.web.upload.DBStorageService;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {

	private final DBStorageService dbStorageService;

	public ProductoService(ProductoRepository repo, DBStorageService dbStorageService) {
		super(repo);
		this.dbStorageService = dbStorageService;
	}

	public void generarPrima(Electronica e1) {
		int max = 100, min = 9;
		Random rnd = new Random(System.nanoTime());
		double primaSeguro = (min + (max - min) * rnd.nextDouble());
		double roundedDouble = Precision.round(primaSeguro, 2);
		if (e1.isSeguro()) {
			e1.setPrima(roundedDouble);
		}
	}

	/*
	 * public List<Producto> filtrarElectronica(long id) { return
	 * repositorio.filtrarElectronica(id); }
	 */

	public List<Electronica> filtrarElectronica(long id) {
		return repositorio.filtrarAllElectronica(id);
	}

	public List<Moda> filtrarModa(long id) {
		return repositorio.filtrarAllModa(id);
	}

	public List<Musica> filtrarMusica(long id) {
		return repositorio.filtrarAllMusica(id);
	}

	public List<Calzado> filtrarCalzado(long id) {
		return repositorio.filtrarAllCalzado(id);
	}

	public List<Alimentacion> filtrarAlimentacion(long id) {
		return repositorio.filtrarAllAlimentacion(id);
	}

	public List<Libro> filtrarLibros(long id) {
		return repositorio.filtrarAllLibro(id);
	}

	public List<Producto> filtrarMisProductos(Usuario usuario) {
		return repositorio.findByUsuario(usuario);
	}

	public List<Producto> filtrarAllPrincipal(Usuario usuario) {

		List<Producto> lista = new ArrayList<>();
		boolean b1 = false;

		for (Producto p1 : repositorio.findAll()) {
			b1 = false;
			for (Producto p2 : repositorio.findByUsuario(usuario)) {
				if (p1.equals(p2)) {
					b1 = true;
				}
			}

			if (!b1)
				lista.add(p1);
		}

		return lista;
	}

	public Producto save(Producto c, MultipartFile imagen) {

		String pathImagen = dbStorageService.store(imagen);
		c.setImagen(pathImagen);
		return this.save(c);
	}

	public Producto edit(Producto c, MultipartFile imagen) {

		String pathImagen = dbStorageService.store(imagen);
		c.setImagen(pathImagen);
		return this.edit(c);

	}

	@Override
	public void delete(Producto c) {
		if (c.getImagen() == null) {
			super.delete(c);
		} else {
			String idImagen = c.getImagen();
			dbStorageService.delete(Long.valueOf(idImagen));
		}

		super.delete(c);
	}

	@Override
	public void deleteById(Long id) {
		Producto c = findById(id);
		if (c != null)
			delete(c);
	}

}
