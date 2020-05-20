package com.cambiomaster.web.modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
public class Empresa extends Usuario {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFundada;

	public Empresa(String username, @NotEmpty String nombre, String telefono, String email, @NotEmpty String direccion,
			@NotEmpty String password, String valoracion, @NotEmpty LocalDate fechaFundada) {
		super(username, nombre, telefono, email, direccion, password, valoracion);
		this.fechaFundada = fechaFundada;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority("EMP"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
