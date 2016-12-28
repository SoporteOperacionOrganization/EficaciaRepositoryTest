package com.eficacia.dao;

import java.util.List;

import com.eficacia.model.Rol;

public interface RolDao {

	public List<Rol> obtenerRoles();
	
	public Rol obtenerRol(int id);
	
}
