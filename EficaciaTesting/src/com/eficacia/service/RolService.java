package com.eficacia.service;

import java.util.List;

import com.eficacia.model.Rol;

public interface RolService {

	public List<Rol> obtenerRoles(); 
	
	public Rol obtenerRol(int id);
	
}
