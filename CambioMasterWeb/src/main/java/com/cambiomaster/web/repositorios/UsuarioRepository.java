package com.cambiomaster.web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findFirstByUsername(String email);
	
	@Query(value = "SELECT * FROM EMPRESA JOIN USUARIO USING (ID)", nativeQuery= true)
	List<Empresa> findAllEmpresas();
	
	@Query(value = "SELECT * "
				 + "FROM USUARIO_GENERAL "
				 + "JOIN USUARIO USING (ID) "
				 + "WHERE ADMIN = FALSE", nativeQuery= true)
	List<UsuarioGeneral> findAllUsuarios();
	
}
