package com.cambiomaster.web.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Buzon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idBuzon;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "buzonEntrega")
	private List<Solicitud> listaSolicitud;

	public void addSolicitud(Solicitud s) {
		this.listaSolicitud.add(s);
		s.setBuzonEntrega(this);
	}

	public void removeSolicitud(Solicitud s) {
		this.listaSolicitud.remove(s);
		s.setBuzonEntrega(null);
	}

}
