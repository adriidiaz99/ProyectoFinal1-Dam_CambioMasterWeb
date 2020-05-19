package com.cambiomaster.web.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Alimentacion;
import com.cambiomaster.web.modelo.Calzado;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.repositorios.ProductoRepository;
import com.cambiomaster.web.servicio.base.BaseService;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {

	public ProductoService(ProductoRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	public void generarPrima(Electronica e1) {
		int max = 100, min = 9;
		Random rnd = new Random(System.nanoTime());
		double primaSeguro = (min + (max - min) * rnd.nextDouble());
		double roundedDouble = Precision.round(primaSeguro,2);
		if (e1.isSeguro()) {
			e1.setPrima(roundedDouble);
		}
	}
	
	public List<Producto> filtrarElectronica(List<Producto> listaUsuario){
		List <Producto> electronica = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Electronica)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						electronica.add(p);
				}
		}
		return electronica;
	}
	
	public List<Producto> filtrarModa(List<Producto> listaUsuario){
		List <Producto> moda = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Moda)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						moda.add(p);
				}
		}
		return moda;
	}
	
	public List<Producto> filtrarLibros(List<Producto> listaUsuario){
		List <Producto> libros = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Libro)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						libros.add(p);
				}
		}
		return libros;
	}
	
	public List<Producto> filtrarMusica(List<Producto> listaUsuario){
		List <Producto> musica = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Musica)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						musica.add(p);
				}
		}
		return musica;
	}
	
	public List<Producto> filtrarAlimentacion(List<Producto> listaUsuario){
		List <Producto> alimentacion = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Alimentacion)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						alimentacion.add(p);
				}
		}
		return alimentacion;
	}
	
	public List<Producto> filtrarCalzado(List<Producto> listaUsuario){
		List <Producto> calzados = new ArrayList<Producto>();
		
		for(Producto p : repositorio.findAll()) {
			if(p instanceof Calzado)
				for(Producto p1 : listaUsuario) {
					if(!p1.equals(p))
						calzados.add(p);
				}
		}
		return calzados;
	}
	
	

}
