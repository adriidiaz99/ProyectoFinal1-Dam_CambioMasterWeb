package com.cambiomaster.web.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String username;

	@NotEmpty
	private String nombre;

	@Column(nullable = false)
	private String telefono;

	@Column(unique = true)
	private String email;

	@NotEmpty
	private String direccion;
	
	@NotEmpty
	private String password;
	
	private String valoracion;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "usuario")
	private List<Producto> listaProductos = new ArrayList<>();

	@OneToMany(mappedBy = "usuarioRecibe")
	private List<Cambio> listaCambiosRecibe = new ArrayList<>();

	@OneToMany(mappedBy = "usuarioManda")
	private List<Cambio> listaCambiosManda = new ArrayList<>();
	
	public Usuario(String username, @NotEmpty String nombre, String telefono, String email, @NotEmpty String direccion,
			@NotEmpty String password, String valoracion) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.password = password;
		this.valoracion = valoracion;
	}	

	

	// Métodos helper
	public void addProducto(Producto p) {
		this.listaProductos.add(p);
		p.setUsuario(this);
	}

	public void removeProducto(Producto p) {
		this.listaProductos.remove(p);
		p.setUsuario(null);
	}

	public void addCambioRecibe(Cambio c) {
		this.listaCambiosRecibe.add(c);
		c.setUsuarioRecibe(this);
	}

	public void removeCambioRecibe(Cambio c) {
		this.listaCambiosRecibe.remove(c);
		c.setUsuarioRecibe(null);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
}