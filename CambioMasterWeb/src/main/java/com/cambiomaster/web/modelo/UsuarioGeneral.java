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
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UsuarioGeneral extends Usuario {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	@NotEmpty
	private String apellido1;
	private String apellido2;
	private boolean admin;

	public UsuarioGeneral(String username, @NotEmpty String nombre, String telefono, String email,
			@NotEmpty String direccion, @NotEmpty String password, String valoracion, LocalDate fechaNacimiento,
			@NotEmpty String apellido1, String apellido2, boolean admin) {
		super(username, nombre, telefono, email, direccion, password, valoracion);
		this.fechaNacimiento = fechaNacimiento;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.admin = admin;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        String role = "ROLE_";
        if (admin) {
            role += "ADMIN";
        } else {
            role += "USER";
        }
        return Arrays.asList(new SimpleGrantedAuthority(role));
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
