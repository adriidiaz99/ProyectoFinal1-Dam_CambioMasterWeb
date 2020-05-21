package com.cambiomaster.web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cambiomaster.web.modelo.Alimentacion;
import com.cambiomaster.web.modelo.Calzado;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	@Query(value="SELECT * FROM ELECTRONICA JOIN PRODUCTO ON (ELECTRONICA.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Electronica> filtrarAllElectronica(long id);
	
	@Query(value="SELECT * FROM MODA JOIN PRODUCTO ON (MODA.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Moda> filtrarAllModa(long id);
	
	@Query(value="SELECT * FROM LIBRO JOIN PRODUCTO ON (LIBRO.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Libro> filtrarAllLibro(long id);
	
	@Query(value="SELECT * FROM MUSICA JOIN PRODUCTO ON (MUSICA.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Musica> filtrarAllMusica(long id);
	
	@Query(value="SELECT * FROM CALZADO JOIN PRODUCTO ON (CALZADO.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Calzado> filtrarAllCalzado(long id);
	
	@Query(value="SELECT * FROM ALIMENTACION JOIN PRODUCTO ON (ALIMENTACION.ID = PRODUCTO.ID) JOIN USUARIO ON(USUARIO.ID = PRODUCTO.USUARIO_ID) WHERE PRODUCTO.USUARIO_ID != ?1", nativeQuery = true)
	List<Alimentacion> filtrarAllAlimentacion(long id);
	
	List<Producto> findByUsuario(Usuario usuario);

}
