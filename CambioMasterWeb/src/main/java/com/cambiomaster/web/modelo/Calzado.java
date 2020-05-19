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
public class Calzado extends Producto {
	
	private Double talla;
	private String seccion;
	
	public Calzado(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, String categoria, Double talla, String seccion) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor, categoria);
		this.talla = talla;
		this.seccion = seccion;
	}
	
	
	
}
