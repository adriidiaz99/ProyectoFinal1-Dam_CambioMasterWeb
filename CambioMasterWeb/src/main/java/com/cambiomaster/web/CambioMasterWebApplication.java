package com.cambiomaster.web;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cambiomaster.web.modelo.Electronica;
import com.cambiomaster.web.modelo.Producto;
import com.cambiomaster.web.modelo.Usuario;
import com.cambiomaster.web.modelo.UsuarioGeneral;
import com.cambiomaster.web.servicio.ProductoService;
import com.cambiomaster.web.servicio.UsuarioService;

@SpringBootApplication
public class CambioMasterWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioMasterWebApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioService usuarioService, ProductoService listaProductos) {

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

				Usuario ug1 = new UsuarioGeneral("admin", "Admin", "123456789", "admin@admin.com", "Sevilla", encoder.encode("admin"), 0.0 , LocalDate.parse("2019-11-01"),
						"a", "a", true);
				Usuario ug2 = new UsuarioGeneral("Usuario", "b", "a", "a", "a", encoder.encode("1234"),0.0 , LocalDate.parse("2019-11-01"),
						"a", "a", false);
				
				Producto e2 = new Electronica("e", 4.2, LocalDate.now(), "o", "o", "Electrónica", false, 0.0, "o");
				
				usuarioService.edit(ug2);
				
				ug2.addProducto(e2);
				
				usuarioService.edit(ug1);
				usuarioService.edit(ug2);
				
				listaProductos.save(e2);
				
			}

		};

	}

}