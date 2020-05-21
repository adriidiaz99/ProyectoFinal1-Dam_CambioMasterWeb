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
import com.cambiomaster.web.modelo.Cambio;
import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Libro;
import com.cambiomaster.web.modelo.Moda;
import com.cambiomaster.web.modelo.Musica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Solicitud;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.CambioService;
import com.cambiomaster.web.servicio.ProductoService;
import com.cambiomaster.web.servicio.SolicitudService;
import com.cambiomaster.web.servicio.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private ProductoService servicioProducto;
	@Autowired
	private SolicitudService servicioSolicitud;
	@Autowired
	private CambioService servicioCambio;

	@GetMapping("/alimentacion")
	public String alimentacion(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("productos", servicioProducto.filtrarAlimentacion(usuario.getId()));
		model.addAttribute("usuario", usuario);
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
		model.addAttribute("usuario", usuario);

		model.addAttribute("productos", servicioProducto.filtrarCalzado(usuario.getId()));
		return "calzado";
	}

	@GetMapping("/configuracion")
	public String configuracion(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);

		return "configuracion";
	}

	@GetMapping("/configuracion/cambiarContrasenya")
	public String cambiarContrasena(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);

		return "cambiarContrasena";
	}

	@GetMapping("/misSolicitudes/confirmarCambios")
	public String confirmarCambios(Model model) {

		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);

		return "confirmarCambios";
	}

	@GetMapping("/electronica")
	public String electronica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("productos", servicioProducto.filtrarElectronica(usuario.getId()));
		model.addAttribute("usuario", usuario);

		return "electronica";
	}

	@GetMapping("/libros")
	public String libros(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("productos", servicioProducto.filtrarLibros(usuario.getId()));
		model.addAttribute("usuario", usuario);

		return "libros";
	}

	@GetMapping("/misProductos")
	public String misProductos(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("productos", servicioProducto.filtrarMisProductos(usuario));
		model.addAttribute("usuario", usuario);

		return "misProductos";
	}

	@GetMapping("/misProductos/agregarProducto")
	public String agregarProductos(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Producto());

		return "agregarProducto";
	}

	@GetMapping("/misProductos/agregarElectronica")
	public String agregarElectronica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Electronica());

		return "agregarElectronica";
	}

	@GetMapping("/misProductos/agregarLibro")
	public String agregarLibro(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Libro());
		return "agregarLibro";
	}

	@GetMapping("/misProductos/agregarModa")
	public String agregarModa(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Moda());
		return "agregarModa";
	}

	@GetMapping("/misProductos/agregarCalzado")
	public String agregarCalzado(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Calzado());
		return "agregarCalzado";
	}

	@GetMapping("/misProductos/agregarAlimentacion")
	public String agregarAlimentacion(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Alimentacion());
		return "agregarAlimentacion";
	}

	@GetMapping("/misProductos/agregarMusica")
	public String agregarMusica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("producto", new Musica());
		return "agregarMusica";
	}

	@GetMapping("/misProductos/seleccionarCategoria")
	public String seleccionarCategoria(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		return "seleccionarCategoria";
	}

	@PostMapping("/misProductos/agregarProducto/submit")
	public String registroProductos(@ModelAttribute("producto") Producto producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		producto.setCategoria("Otros..");
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

		producto.setCategoria("Electrónica");
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

		producto.setCategoria("Moda");
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

		producto.setCategoria("Calzado");
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

		producto.setCategoria("Música");
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

		producto.setCategoria("Alimentación");
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

		producto.setCategoria("Libros");
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

		model.addAttribute("usuario", usuario);
		model.addAttribute("productos", servicioProducto.filtrarModa(usuario.getId()));

		return "moda";
	}

	@GetMapping("/musica")
	public String musica(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("productos", servicioProducto.filtrarMusica(usuario.getId()));

		return "musica";
	}

	@GetMapping("/principal")
	public String principal(Model model) {
		List<Producto> listaPrincipal = new ArrayList<Producto>();
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);

		for (Producto p1 : usuario.getListaProductos()) {
			for (Producto p : servicioProducto.findAll()) {
				if (!p.equals(p1)) {
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

		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);

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

		if (p instanceof Musica) {
			model.addAttribute("producto", p);
			return "productoMusica";
		}

		if (p instanceof Producto) {
			model.addAttribute("producto", p);
			return "producto";
		}

		if (p instanceof Libro) {
			model.addAttribute("producto", p);
			return "productoLibro";
		}

		return "redirect:/usuario/principal";
	}

	@GetMapping("/editarProducto/{id}")
	public String editarProducto(@PathVariable long id, Model model) {
		Producto p = servicioProducto.findById(id);

		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		usuario.removeProducto(p);

		if (p instanceof Electronica) {
			model.addAttribute("producto", p);
			return "agregarElectronica";
		}

		if (p instanceof Moda) {
			model.addAttribute("producto", p);
			return "agregarModa";
		}

		if (p instanceof Alimentacion) {
			model.addAttribute("producto", p);
			return "agregarAlimentacion";
		}

		if (p instanceof Calzado) {
			model.addAttribute("producto", p);
			return "agregarCalzado";
		}

		if (p instanceof Musica) {
			model.addAttribute("producto", p);
			return "agregarMusica";
		}

		if (p instanceof Producto) {
			model.addAttribute("producto", p);
			return "agregarProducto";
		}

		if (p instanceof Libro) {
			model.addAttribute("producto", p);
			return "agregarLibros";
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

		return "miPerfil";
	}

	@GetMapping("/ultimosCambios")
	public String ultimosCambios(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("usuario", usuario);
		model.addAttribute("listaCambios", servicioCambio.findAll());
		return "ultimosCambios";
	}

	@GetMapping("/misProductos/eliminar/{id}")
	public String eliminarProducto(@PathVariable long id) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		Producto p = servicioProducto.findById(id);

		usuario.removeProducto(p);

		servicioProducto.delete(p);

		return "redirect:/usuario/misProductos";
	}

	@PostMapping("/misProductos/editarElectronica/submit")
	public String editarElectronica(@ModelAttribute("producto") Electronica producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarModa/submit")
	public String editarModa(@ModelAttribute("producto") Moda producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarCalzado/submit")
	public String editarCalzado(@ModelAttribute("producto") Calzado producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarMusica/submit")
	public String editarMusica(@ModelAttribute("producto") Musica producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarProducto/submit")
	public String editarProducto(@ModelAttribute("producto") Producto producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarLibro/submit")
	public String editarLibro(@ModelAttribute("producto") Libro producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@PostMapping("/misProductos/editarAlimentacion/submit")
	public String editarAlimentacion(@ModelAttribute("producto") Alimentacion producto) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		System.out.println(servicioProducto.findAll());

		usuario.addProducto(producto);
		servicioProducto.edit(producto);
		System.out.println(servicioProducto.findAll());
		return "redirect:/usuario/misProductos";

	}

	@GetMapping("/seleccionarProducto/{id}")
	public String seleccionarProducto(@PathVariable long id, Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		Solicitud s1 = new Solicitud(null, servicioProducto.findById(id), usuario,
				servicioProducto.findById(id).getUsuario());
		servicioSolicitud.save(s1);

		model.addAttribute("solicitud", s1);
		model.addAttribute("productos", servicioProducto.filtrarMisProductos(usuario));
		model.addAttribute("usuario", usuario);
		return "seleccionarProducto";

	}

	@GetMapping("/enviarSolicitud/{id}")
	public String enviarSolicitud(@PathVariable long id) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		Solicitud solicitud = servicioSolicitud.solicitudPendiente();

		Producto p = servicioProducto.findById(id);

		solicitud.setProductoManda(p);

		servicioSolicitud.edit(solicitud);
		
		System.out.println(servicioSolicitud.findAll());

		if (servicioSolicitud.findById(solicitud.getIdSolicitud()).getProductoManda() == null)
			servicioSolicitud.delete(servicioSolicitud.solicitudPendiente());

		return "redirect:/usuario/principal";

	}

	@GetMapping("/cancelarCambio")
	public String cancelarCambio() {

		Solicitud solicitud = servicioSolicitud.solicitudPendiente();

		servicioSolicitud.delete(solicitud);

		return "redirect:/usuario/principal";

	}

	@GetMapping("/misCambios")
	public String misCambios(Model model) {
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());

		model.addAttribute("misSolicitudes", servicioSolicitud.encontrarSolicitudRecibidas(usuario.getId()));
		model.addAttribute("misSolicitudesEnviadas", servicioSolicitud.encontrarSolicitudMandadas(usuario.getId()));
		model.addAttribute("usuario", usuario);

		return "misCambios";

	}
	
	@GetMapping("/cancelarCambio/{id}")
	public String cancelarMisCambios(@PathVariable long id) {
		
		servicioSolicitud.deleteById(id);
		
		return "redirect:/usuario/misCambios";	
	}
	
	@GetMapping("/verificarCambio/{id}")
	public String verificarCambio(@PathVariable long id, Model model){
		
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("solicitud", servicioSolicitud.findById(id));
		model.addAttribute("usuario", usuario);
		
		return "confirmarCambios";
		
	}
	
	@GetMapping("/cambiar/{id}")
	public String cambiarProductos(@PathVariable long id, Model model) {
		
		UsuarioGeneral usuario;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		usuario = (UsuarioGeneral) this.servicioUsuario.buscarPorUserName(userDetail.getUsername());
		
		model.addAttribute("usuario", usuario);
		
		Solicitud s1 = servicioSolicitud.findById(id);
		
		Cambio c1 = new Cambio(s1.getUsuarioRecibe(), s1.getUsuarioSolicita(), s1.getProductoRecibe(), s1.getProductoManda());
		
		c1.getUsuarioRecibe().addCambioRecibe(c1);
		
		c1.getUsuarioManda().addCambioManda(c1);
		
		c1.getUsuarioRecibe().removeProducto(s1.getProductoRecibe());
		
		c1.getUsuarioManda().removeProducto(s1.getProductoManda());
		
		c1.getUsuarioRecibe().addProducto(s1.getProductoManda());
		
		c1.getUsuarioManda().addProducto(s1.getProductoRecibe());
		
		servicioUsuario.edit(c1.getUsuarioRecibe());
		
		servicioUsuario.edit(c1.getUsuarioManda());
		
		servicioCambio.edit(c1);
		
		for (Solicitud s2 : servicioSolicitud.encontrarSolicitudesRestantes(c1.getProducto1().getId())) {
			
			servicioSolicitud.delete(servicioSolicitud.findById(s2.getIdSolicitud()));
			
		}
		
		for (Solicitud s2 : servicioSolicitud.encontrarSolicitudesRestantes(c1.getProducto2().getId())) {
			
			servicioSolicitud.delete(servicioSolicitud.findById(s2.getIdSolicitud()));
			
		}
		
		return "redirect:/usuario/principal";
		
	}
	

}
