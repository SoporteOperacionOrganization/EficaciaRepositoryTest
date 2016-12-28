package com.eficacia.custommodel;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUsuario extends org.springframework.security.core.userdetails.User {
	
    private String nombre;
    private String apellidoPaterno;
    private boolean credencialesNoExpiradas;
    private String rol;
    public CustomUsuario(String username, 
    					 String password, 
    					 boolean enabled, 
    					 boolean accountNonExpired,
    					 boolean credencialesExpiradas, 
    					 boolean accountNonLocked,
    					 String rol,
    					 Collection<? extends GrantedAuthority> authorities, 
    					 String nombre, 
    					 String apellidoPaterno) {
    				super(username, password, enabled, accountNonExpired, credencialesExpiradas,
    				accountNonLocked, authorities);

		this.setNombre(nombre);
		this.setApellidoPaterno(apellidoPaterno);
		this.setRol(rol);
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public boolean isCredencialesNoExpiradas() {
		return credencialesNoExpiradas;
	}

	public void setCredencialesNoExpiradas(boolean credencialesNoExpiradas) {
		this.credencialesNoExpiradas = credencialesNoExpiradas;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
    
    
    
}
