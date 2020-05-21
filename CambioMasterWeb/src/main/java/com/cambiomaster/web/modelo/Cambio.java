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
public class Cambio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCompra;

	@ManyToOne
    @JoinColumn(name = "Usuario_Recibe", updatable = false, nullable = false)
	private Usuario usuarioRecibe;
	
	@ManyToOne
    @JoinColumn(name = "Usuario_Manda", updatable = false, nullable = false)
	private Usuario usuarioManda;

	@OneToOne
    @JoinColumn(name = "Producto_Recibe", updatable = false, nullable = false)
	private Producto producto1;
	
	@OneToOne
    @JoinColumn(name = "Producto_Manda", updatable = false, nullable = false)
	private Producto producto2;

	public Cambio(Usuario usuarioRecibe, Usuario usuarioManda, Producto producto1, Producto producto2) {
		super();
		this.usuarioRecibe = usuarioRecibe;
		this.usuarioManda = usuarioManda;
		this.producto1 = producto1;
		this.producto2 = producto2;
	}

}
