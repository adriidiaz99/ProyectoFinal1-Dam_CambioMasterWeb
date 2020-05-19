package com.cambiomaster.web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cambiomaster.web.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findFirstByUsername(String email);
	
}
