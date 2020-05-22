package com.cambiomaster.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private UsuarioService servicioUsuario;
	private final String BASE_IMAGE_PATH;

	public MainController(UsuarioService servicioUsuario, @Value("${image.base-path:/files}") String path) {
		super();
		this.servicioUsuario = servicioUsuario;
		BASE_IMAGE_PATH = path;
	}
	
	@ModelAttribute("base_image_path")
	public String getBASE_IMAGE_PATH() {
		return BASE_IMAGE_PATH;
	}

	@GetMapping("/login")
	public String index() {
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
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
		Usuario c1 = new UsuarioGeneral();
		model.addAttribute("usuario", c1);
		return "registro";
	}
	
	@PostMapping("/registro/submit")
	public String registerUser(@ModelAttribute UsuarioGeneral usuario, MultipartFile file) {
		
		if (!file.isEmpty())
			servicioUsuario.registerConImagen(usuario, file);
		else 
			servicioUsuario.register(usuario);
		
		
		return "redirect:/login";
	}
	
}
