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
public class Electronica extends Producto {
	
	private boolean seguro;
	private double prima;
	private String tipo;
	
	public Electronica(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, String categoria, boolean seguro, double prima, String tipo) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor, categoria);
		this.seguro = seguro;
		this.prima = prima;
		this.tipo = tipo;
	}
	
	
	
	
	
}
