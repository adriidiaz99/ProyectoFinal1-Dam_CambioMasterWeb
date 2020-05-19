package com.cambiomaster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cambiomaster.web.modelo.Buzon;
import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.BuzonService;
import com.cambiomaster.web.servicio.ProductoService;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private ProductoService servicioProducto;
	@Autowired
	private BuzonService servicioBuzon;
	
	@GetMapping("/principal")
	public String principalAdmin(Model model) {
		model.addAttribute("listaUsuarios", servicioUsuario.findAllUsuarios());
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
	public String registroEmpresa(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "/admin/registroEmpresa";
	}
	
	@GetMapping("/editarEmpresa/{id}")
	public String editarEmpresa(@PathVariable long id, Model model) {
		model.addAttribute("empresa", servicioUsuario.findById(id));
		return "/admin/registroEmpresa";
	}
	
	@GetMapping("/gestionProductos")
	public String gestionProductos(Model model) {
		
		model.addAttribute("listaProducto", servicioProducto.findAll());
		
		return "/admin/gestionProductos";
	}
	
	@GetMapping("/gestionEmpresas")
	public String gestionEmpresa(Model model) {
		model.addAttribute("listaEmpresas", servicioUsuario.findAllEmpresas());
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
	
	@GetMapping("/miPerfil")
	public String miPerfil(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("usuario", usuario);
		
		
		return "miPerfil";
	}
	
	@PostMapping("/registroEmpresa/submit")
	public String registrandoEmpresa(@ModelAttribute("empresa") Empresa empresa) {
		Buzon b1 = new Buzon();
		
		servicioBuzon.edit(b1);
		empresa.setBuzon(b1);
		empresa.setValoracion("0.0");
		
		servicioUsuario.register(empresa);
		return "redirect:/admin/registroEmpresa";
	}
	
	@PostMapping("/editarEmpresa/submit")
	public String editandoEmpresa(@ModelAttribute("empresa") Empresa empresa) {
		servicioUsuario.edit(empresa);
		return "redirect:/admin/gestionEmpresa";
	}
}
