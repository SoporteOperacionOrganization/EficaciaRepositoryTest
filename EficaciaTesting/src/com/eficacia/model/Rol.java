package com.eficacia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue
	@Column(name = "rolid")
	private int id;
	@Column(name = "rolnombre")
	private String nombre;
	
	public Rol(){
		
		
	}
    public Rol(int id, String nombre ){
    	this.nombre = nombre;
    	this.id = id;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
