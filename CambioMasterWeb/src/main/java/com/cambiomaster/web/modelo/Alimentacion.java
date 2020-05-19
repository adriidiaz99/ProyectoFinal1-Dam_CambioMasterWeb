package com.cambiomaster.web.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Alimentacion extends Producto {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCaducidad;
	private double peso;
	
	public Alimentacion(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor, LocalDate fechaCaducidad, double peso) {
		super(nombre, precioCompra, fechaCompra, descripcion, productor);
		this.fechaCaducidad = fechaCaducidad;
		this.peso = peso;
	}

}
