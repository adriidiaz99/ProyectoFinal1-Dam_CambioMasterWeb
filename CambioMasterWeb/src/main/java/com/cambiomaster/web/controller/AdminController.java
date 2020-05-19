package com.cambiomaster.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/principal")
	public String principalAdmin() {
		return "/admin/principalAdministrador";
	}
	
	@GetMapping("/gestionUsuarios")
	public String gestionUsuarios() {
		return "/admin/principalAdministrador";
	}
	
	@GetMapping("/configuracion/cambiarContrasenya")
	public String cambioContrasenya() {
		return "/admin/cambiarContrasenaAdmin";
	}
	
	@GetMapping("/configuracion")
	public String configuracionAdmin() {
		return "/admin/configuracionAdministrador";
	}
	
	@GetMapping("/ultimosCambios")
	public String ultimosCambios() {
		return "/admin/ultimosCambiosAdministrador";
	}
	
	@GetMapping("/registroEmpresa")
	public String registroEmpresa() {
		return "/admin/registroEmpresa";
	}
	
	@GetMapping("/gestionProductos")
	public String gestionProductos() {
		return "/admin/gestionProductos";
	}
	
	@GetMapping("/gestionEmpresas")
	public String gestionEmpresa() {
		return "/admin/gestionEmpresas";
	}
	
	@GetMapping("/gestionEnvios")
	public String gestionEnvios() {
		return "/admin/gestionEnvios";
	}
	
	@GetMapping("/historialCambios")
	public String historialCambios() {
		return "/admin/historialCambios";
	}
}
