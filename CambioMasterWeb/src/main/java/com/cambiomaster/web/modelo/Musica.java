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
public class Musica extends Producto{
	
	private String artista;
	private String genero;
	
	public Musica(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, String artista, String genero) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor);
		this.artista = artista;
		this.genero = genero;
	}
	
	
	
	
	
	
	
	
	
	
}
