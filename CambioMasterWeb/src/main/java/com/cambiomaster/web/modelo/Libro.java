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
public class Libro extends Producto{
	
	private String autor;
	private String genero;
	
	public Libro(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, String categoria, String autor, String genero) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor, categoria);
		this.autor = autor;
		this.genero = genero;
	}
	
	
	
}
