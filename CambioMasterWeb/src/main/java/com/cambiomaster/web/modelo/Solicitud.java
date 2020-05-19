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
    @JoinColumn(name = "Producto_Manda", updatable = false, nullable = false)
	private Producto productoManda;
	
	@OneToOne
    @JoinColumn(name = "Producto_Recibe", updatable = false, nullable = false)
	private Producto productoRecibe;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_Solicita", updatable = false, nullable = false)
	private Usuario usuarioSolicita;
	
	@ManyToOne
	@JoinColumn(name = "Buzon_Entrega", updatable = false, nullable = false)
	private Buzon buzonEntrega;

}
