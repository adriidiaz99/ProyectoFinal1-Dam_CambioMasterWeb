package com.cambiomaster.web.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idSolicitud;
	
	@OneToOne
    @JoinColumn(name = "Producto_Manda", updatable = true, nullable = true)
	private Producto productoManda;
	
	@OneToOne
    @JoinColumn(name = "Producto_Recibe", updatable = false, nullable = true)
	private Producto productoRecibe;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_Solicita", updatable = false, nullable = true)
	private Usuario usuarioSolicita;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_Recibe", updatable = false, nullable = true)
	private Usuario usuarioRecibe;


	public Solicitud(Producto productoManda, Producto productoRecibe, Usuario usuarioSolicita, Usuario usuarioRecibe) {
		super();
		this.productoManda = productoManda;
		this.productoRecibe = productoRecibe;
		this.usuarioSolicita = usuarioSolicita;
		this.usuarioRecibe = usuarioRecibe;
	}

}
