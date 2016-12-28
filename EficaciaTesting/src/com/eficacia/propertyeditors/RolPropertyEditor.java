package com.eficacia.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eficacia.model.Rol;
import com.eficacia.service.RolService;

@Component
public class RolPropertyEditor extends PropertyEditorSupport{

	@Autowired
	private RolService rolService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		Integer id = Integer.parseInt(text);
		Rol rol = rolService.obtenerRol(id);
		setValue(rol);
		//super.setAsText(text);
	}
	
}
