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
import org.springframework.web.multipart.MultipartFile;

import com.cambiomaster.web.modelo.Alimentacion;
import com.cambiomaster.web.modelo.Calzado;
import com.cambiomaster.web.modelo.Cambio;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Empresa;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Solicitud;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.CambioService;
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
	private CambioService servicioCambio;

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
	
	@GetMapping("/logout")
	public String cerrarSesion() {
		return "redirect:/logout";
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
	public String historialCambios(Model model) {

		model.addAttribute("cambios", servicioCambio.findAll());

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
	public String registrandoEmpresa(@ModelAttribute Empresa empresa, MultipartFile file) {

		if (!file.isEmpty())
			servicioUsuario.registerConImagen(empresa, file);
		else
			servicioUsuario.register(empresa);
		
		return "redirect:/admin/gestionEmpresas";
	}

	@PostMapping("/editarEmpresa/submit")
	public String editandoEmpresa(@ModelAttribute Empresa usuario, MultipartFile file) {
		if (usuario.getPassword().equals("")) {
			usuario.setPassword(servicioUsuario.findById(usuario.getId()).getPassword());
			if (file.isEmpty()) {
				usuario.setImagen(servicioUsuario.findById(usuario.getId()).getImagen());
				servicioUsuario.edit(usuario);
			} else {
				servicioUsuario.edit(usuario, file);
			}
		} else {
			if (file.isEmpty()) {
				usuario.setImagen(servicioUsuario.findById(usuario.getId()).getImagen());
				servicioUsuario.register(usuario);
			} else {
				servicioUsuario.registerConImagen(usuario, file);
			}

		}
		return "redirect:/admin/gestionEmpresas";
	}

	@PostMapping("/editarUsuario/submit")
	public String editandoUsuario(@ModelAttribute UsuarioGeneral usuario, MultipartFile file) {
		if (usuario.getPassword().equals("")) {
			usuario.setPassword(servicioUsuario.findById(usuario.getId()).getPassword());
			if (file.isEmpty()) {
				usuario.setImagen(servicioUsuario.findById(usuario.getId()).getImagen());
				servicioUsuario.edit(usuario);
			} else {
				servicioUsuario.edit(usuario, file);
			}
		} else {
			if (file.isEmpty()) {
				usuario.setImagen(servicioUsuario.findById(usuario.getId()).getImagen());
				servicioUsuario.register(usuario);
			} else {
				servicioUsuario.registerConImagen(usuario, file);
			}

		}

		return "redirect:/admin/principal";
	}

	@GetMapping("/gestionUsuario/registroUsuario")
	public String registroUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioGeneral());
		return "/admin/registroUsuario";
	}

	@PostMapping("/registroUsuario/submit")
	public String registrandoUsuario(@ModelAttribute UsuarioGeneral usuario, MultipartFile file) {

		if (!file.isEmpty())
			servicioUsuario.registerConImagen(usuario, file);
		else
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

	@GetMapping("/eliminarEmpresa/{id}")
	public String eliminarEmpresa(@PathVariable long id) {
		Usuario u1 = servicioUsuario.findById(id);

		for (Producto producto : u1.getListaProductos()) {
			servicioProducto.delete(producto);
		}

		servicioUsuario.deleteById(id);

		return "redirect:/admin/gestionEmpresas";
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
			return "/admin/agregarLibro";
		}

		return "redirect:/admin/principal";
	}

	@PostMapping("/editarElectronica/submit")
	public String editarElectronica(@ModelAttribute Electronica producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);
		
		producto.setCategoria("Electrónica");
		

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Electronica) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarModa/submit")
	public String editarModa(@ModelAttribute Moda producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Moda) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarCalzado/submit")
	public String editarCalzado(@ModelAttribute Calzado producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Calzado) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarMusica/submit")
	public String editarMusica(@ModelAttribute Musica producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Musica) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarProducto/submit")
	public String editarProducto(@ModelAttribute Producto producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarLibro/submit")
	public String editarLibro(@ModelAttribute Libro producto, MultipartFile file) {
		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Libro) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@PostMapping("/editarAlimentacion/submit")
	public String editarAlimentacion(@ModelAttribute Alimentacion producto, MultipartFile file) {

		Producto p1 = servicioProducto.findById(producto.getId());

		Usuario usuario = servicioProducto.findById(producto.getId()).getUsuario();

		p1.getUsuario().removeProducto(p1);

		producto.setCategoria("Electrónica");

		if (file.isEmpty()) {
			producto.setImagen(servicioProducto.findById(producto.getId()).getImagen());
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		} else {
			producto = (Alimentacion) servicioProducto.edit(producto, file);
			usuario.addProducto(producto);
			servicioProducto.edit(producto);
		}

		return "redirect:/admin/gestionProductos";

	}

	@GetMapping("/ultimosCambios")
	public String ultimosCambios(Model model) {

		model.addAttribute("cambios", servicioCambio.findAll());
		return "/admin/ultimosCambiosAdministrador";
	}

	@GetMapping("/deshacerCambio/{id}")
	public String deshacerCambio(@PathVariable long id) {

		Cambio c1 = servicioCambio.findById(id);

		c1.getUsuarioRecibe().addCambioRecibe(c1);

		c1.getUsuarioManda().addCambioManda(c1);

		c1.getUsuarioRecibe().removeProducto(c1.getProducto2());

		c1.getUsuarioManda().removeProducto(c1.getProducto1());

		c1.getUsuarioRecibe().addProducto(c1.getProducto1());

		c1.getUsuarioManda().addProducto(c1.getProducto2());

		servicioUsuario.edit(c1.getUsuarioRecibe());

		servicioUsuario.edit(c1.getUsuarioManda());

		servicioCambio.delete(servicioCambio.findById(c1.getIdCompra()));

		return "redirect:/admin/historialCambios";
	}

}
