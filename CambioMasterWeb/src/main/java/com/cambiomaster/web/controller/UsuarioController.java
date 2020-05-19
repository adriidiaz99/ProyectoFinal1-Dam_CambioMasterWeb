package com.cambiomaster.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.ProductoService;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private ProductoService servicioProducto;

	@GetMapping("/alimentacion")
	public String alimentacion(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarAlimentacion(usuario.getListaProductos()));
		return "alimentacion";
	}

	@GetMapping("/misSolicitudes")
	public String buzon() {
		return "buzon";
	}

	@GetMapping("/calzado")
	public String calzado(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarCalzado(usuario.getListaProductos()));
		return "calzado";
	}

	@GetMapping("/configuracion")
	public String configuracion() {
		return "configuracion";
	}

	@GetMapping("/configuracion/cambiarContrasenya")
	public String cambiarContrasena() {
		return "cambiarContrasena";
	}

	@GetMapping("/misSolicitudes/confirmarCambios")
	public String confirmarCambios() {
		return "confirmarCambios";
	}

	@GetMapping("/electronica")
	public String electronica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarElectronica(usuario.getListaProductos()));
		return "electronica";
	}

	@GetMapping("/libros")
	public String libros(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarLibros(usuario.getListaProductos()));
		return "libros";
	}

	@GetMapping("/misCambios")
	public String misCambios() {
		return "misCambios";
	}

	@GetMapping("/misProductos")
	public String misProductos(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		System.out.println(usuario);
		System.out.println(usuario.getNombre());
		model.addAttribute("productos", usuario.getListaProductos());
		return "misProductos";
	}

	@GetMapping("/misProductos/agregarProducto")
	public String agregarProductos(Model model) {
		model.addAttribute("producto", new Producto());
		return "agregarProducto";
	}

	@GetMapping("/misProductos/agregarElectronica")
	public String agregarElectronica(Model model) {
		model.addAttribute("producto", new Electronica());
		return "agregarElectronica";
	}

	@GetMapping("/misProductos/agregarLibro")
	public String agregarLibro(Model model) {
		model.addAttribute("producto", new Libro());
		return "agregarLibro";
	}

	@GetMapping("/misProductos/agregarModa")
	public String agregarModa(Model model) {
		model.addAttribute("producto", new Moda());
		return "agregarModa";
	}

	@GetMapping("/misProductos/agregarCalzado")
	public String agregarCalzado(Model model) {
		model.addAttribute("producto", new Calzado());
		return "agregarCalzado";
	}

	@GetMapping("/misProductos/agregarAlimentacion")
	public String agregarAlimentacion(Model model) {
		model.addAttribute("producto", new Alimentacion());
		return "agregarAlimentacion";
	}

	@GetMapping("/misProductos/agregarMusica")
	public String agregarMusica(Model model) {
		model.addAttribute("producto", new Musica());
		return "agregarMusica";
	}

	@GetMapping("/misProductos/seleccionarCategoria")
	public String seleccionarCategoria() {
		return "seleccionarCategoria";
	}

	@PostMapping("/misProductos/agregarProducto/submit")
	public String registroProductos(@ModelAttribute("producto") Producto producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarElectronica/submit")
	public String registroElectronica(@ModelAttribute("producto") Electronica producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		servicioProducto.generarPrima(producto);

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarModa/submit")
	public String registroModa(@ModelAttribute("producto") Moda producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarCalzado/submit")
	public String registroCalzado(@ModelAttribute("producto") Calzado producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarMusica/submit")
	public String registroMusica(@ModelAttribute("producto") Musica producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarAlimentacion/submit")
	public String registroAlimentacion(@ModelAttribute("producto") Alimentacion producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/agregarLibro/submit")
	public String registroLibros(@ModelAttribute("producto") Libro producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		usuario.addProducto(producto);
		servicioUsuario.edit(usuario);

		servicioProducto.edit(producto);

		return "redirect:/usuario/misProductos";
	}

	@GetMapping("/moda")
	public String moda(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarModa(usuario.getListaProductos()));
		return "moda";
	}

	@GetMapping("/musica")
	public String musica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("productos", servicioProducto.filtrarMusica(usuario.getListaProductos()));
		return "musica";
	}

	@GetMapping("/principal")
	public String principal(Model model) {
		List<Producto> listaPrincipal = new ArrayList<Producto>();
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		for(Producto p1: usuario.getListaProductos()) {
			for(Producto p: servicioProducto.findAll()) {
				if(!p.equals(p1)) {
					listaPrincipal.add(p);
				}
			}
			
		}
		model.addAttribute("productos", listaPrincipal);
		return "principal";
	}

	@GetMapping("/producto/{id}")
	public String producto(@PathVariable long id, Model model) {
		Producto p = servicioProducto.findById(id);

		if (p instanceof Electronica) {
			model.addAttribute("producto", p);
			return "productoElectronica";
		}

		if (p instanceof Moda) {
			model.addAttribute("producto", p);
			return "productoModa";
		}

		if (p instanceof Alimentacion) {
			model.addAttribute("producto", p);
			return "productoAlimentacion";
		}

		if (p instanceof Calzado) {
			model.addAttribute("producto", p);
			return "productoCalzado";
		}
		
		if(p instanceof Musica) {
			model.addAttribute("producto", p);
			return "productoMusica";
		}
		
		if(p instanceof Producto) {
			model.addAttribute("producto", p);
			return "producto";
		}
		
		if(p instanceof Libro) {
			model.addAttribute("producto", p);
			return "productoLibro";
		}
		
		return "redirect:/usuario/principal";
	}
	
	@GetMapping("/miPerfil")
	public String miPerfil(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("usuario", usuario);
		
		
		return "redirect:/usuario/miPerfil";
	}

	@GetMapping("/ultimosCambios")
	public String ultimosCambios() {
		return "ultimosCambios";
	}
}
