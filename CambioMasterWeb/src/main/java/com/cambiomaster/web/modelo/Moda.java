package com.cambiomaster.web.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=true)
public class Moda extends Producto {
	
	private String talla;
	private String seccion;
	
	public Moda(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, String categoria, String talla, String seccion) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor, categoria);
		this.talla = talla;
		this.seccion = seccion;
	}
	
}
