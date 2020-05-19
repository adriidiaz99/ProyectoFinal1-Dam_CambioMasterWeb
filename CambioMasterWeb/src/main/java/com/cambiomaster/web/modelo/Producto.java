package com.cambiomaster.web.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty
	private String nombre;
	private double precioCompra;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCompra;
	private String descripcion;
	private String productor;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	private Usuario usuario;
	

	public Producto(@NotEmpty String nombre, double precioCompra, LocalDate fechaCompra, String descripcion,
			String productor) {
		super();
		this.nombre = nombre;
		this.precioCompra = precioCompra;
		this.fechaCompra = fechaCompra;
		this.descripcion = descripcion;
		this.productor = productor;
	}


}
