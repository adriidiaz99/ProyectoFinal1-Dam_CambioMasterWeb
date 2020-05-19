package com.cambiomaster.web.seguridad;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.servicio.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UsuarioService usuarioServicio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u1 = null;
		
		u1 = usuarioServicio.buscarPorUserName(username);
		
		if(u1 == null)
			throw new UsernameNotFoundException("Usuario no encontrado");
		else
			return u1;
		
		
	}
	

}
