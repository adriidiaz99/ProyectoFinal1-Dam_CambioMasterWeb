package com.cambiomaster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UsuarioService servicioUsuario;

	@GetMapping("/login")
	public String index() {
		return "index";
	}
	
	@GetMapping("/avisoLegal")
	public String avisoLegal() {
		return "avisoLegal";
	}
	
	@GetMapping("/politicaDeDatos")
	public String politicaDeDatos() {
		return "politicaDeDatos";
	}
	

	@GetMapping("/registro")
	public String registroManda(Model model) {
		UsuarioGeneral c1 = new UsuarioGeneral();
		model.addAttribute("usuario", c1);
		return "registro";
	}
	
	@PostMapping("/registro/submit")
	public String registerUser(@ModelAttribute("usuario") UsuarioGeneral usuario) {
		servicioUsuario.register(usuario);
		return "redirect:/login";
	}
	
}
