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

import com.cambiomaster.web.modelo.Alimentacion;
import com.cambiomaster.web.modelo.Calzado;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.ProductoService;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private ProductoService servicioProducto;

	@GetMapping("/principal")
	public String principalAdmin(Model model) {
		model.addAttribute("listaUsuarios", servicioUsuario.findAllUsuarios());
		return "/admin/principalAdministrador";
	}

	@GetMapping("/gestionUsuarios")
	public String gestionUsuarios(Model model) {
		model.addAttribute("listaUsuarios", servicioUsuario.findAllUsuarios());
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

	@GetMapping("/gestionEmpresa/registroEmpresa")
	public String registroEmpresa(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "/admin/registroEmpresa";
	}

	@GetMapping("/editarEmpresa/{id}")
	public String editarEmpresa(@PathVariable long id, Model model) {
		model.addAttribute("empresa", servicioUsuario.findById(id));
		return "/admin/registroEmpresa";
	}

	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable long id, Model model) {
		model.addAttribute("usuario", servicioUsuario.findById(id));
		return "/admin/registroUsuario";
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
	

		System.out.println(empresa);

		empresa.setValoracion("0.0");

		servicioUsuario.register(empresa);
		return "redirect:/admin/gestionEmpresas";
	}

	@PostMapping("/editarEmpresa/submit")
	public String editandoEmpresa(@ModelAttribute("empresa") Empresa empresa) {
		if (empresa.getPassword() == null) {
			empresa.setPassword(servicioUsuario.findById(empresa.getId()).getPassword());
			servicioUsuario.edit(empresa);
		} else {
			servicioUsuario.register(empresa);
		}
		return "redirect:/admin/gestionEmpresa";
	}

	@PostMapping("/editarUsuario/submit")
	public String editandoEmpresa(@ModelAttribute("usuario") UsuarioGeneral usuario) {

		Usuario u2 = servicioUsuario.findById(usuario.getId());

		if (usuario.getPassword() == null) {
			usuario.setPassword(servicioUsuario.findById(usuario.getId()).getPassword());
			servicioUsuario.edit(usuario);
		} else {
			servicioUsuario.register(usuario);
		}

		return "redirect:/admin/principal";
	}

	@GetMapping("/gestionUsuario/registroUsuario")
	public String registroUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioGeneral());
		return "/admin/registroUsuario";
	}

	@PostMapping("/registroUsuario/submit")
	public String registrandoUsuario(@ModelAttribute("usuario") UsuarioGeneral usuario) {

		System.out.println(usuario);

		usuario.setValoracion("0.0");

		servicioUsuario.register(usuario);
		return "redirect:/admin/principal";
	}

	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable long id) {
		Usuario u1 = servicioUsuario.findById(id);

		for (Producto producto : u1.getListaProductos()) {
			servicioProducto.delete(producto);
		}

		servicioUsuario.deleteById(id);

		return "redirect:/admin/principal";
	}

	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable long id) {
		Producto p1 = servicioProducto.findById(id);
		Usuario u1 = servicioUsuario.findById(p1.getUsuario().getId());

		u1.removeProducto(p1);

		servicioProducto.delete(p1);

		return "redirect:/admin/gestionProductos";
	}

	@GetMapping("/editarProducto/{id}")
	public String editarProducto(@PathVariable long id, Model model) {
		Producto p = servicioProducto.findById(id);

		p.getUsuario().removeProducto(p);

		if (p instanceof Electronica) {
			model.addAttribute("producto", p);
			return "/admin/agregarElectronica";
		}

		if (p instanceof Moda) {
			model.addAttribute("producto", p);
			return "/admin/agregarModa";
		}

		if (p instanceof Alimentacion) {
			model.addAttribute("producto", p);
			return "/admin/agregarAlimentacion";
		}

		if (p instanceof Calzado) {
			model.addAttribute("producto", p);
			return "/admin/agregarCalzado";
		}

		if (p instanceof Musica) {
			model.addAttribute("producto", p);
			return "/admin/agregarMusica";
		}

		if (p instanceof Producto) {
			model.addAttribute("producto", p);
			return "/admin/agregarProducto";
		}

		if (p instanceof Libro) {
			model.addAttribute("producto", p);
			return "/admin/agregarLibros";
		}

		return "redirect:/admin/principal";
	}

	@PostMapping("/editarElectronica/submit")
	public String editarElectronica(@ModelAttribute("producto") Electronica producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarModa/submit")
	public String editarModa(@ModelAttribute("producto") Moda producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarCalzado/submit")
	public String editarCalzado(@ModelAttribute("producto") Calzado producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarMusica/submit")
	public String editarMusica(@ModelAttribute("producto") Musica producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarProducto/submit")
	public String editarProducto(@ModelAttribute("producto") Producto producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarLibro/submit")
	public String editarLibro(@ModelAttribute("producto") Libro producto) {
		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarAlimentacion/submit")
	public String editarAlimentacion(@ModelAttribute("producto") Alimentacion producto) {

		Producto p1 = servicioProducto.findById(producto.getId());

		producto.setUsuario(p1.getUsuario());

		System.out.println(servicioProducto.findAll());

		producto.getUsuario().addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/admin/gestionProductos";

	}

}
