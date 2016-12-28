package com.eficacia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "agendaId")
	private int id;
	@Column(name = "agendaCodigoTransaccion")
	private String codigoTransaccion;
	@Column(name = "agendaFechaTransaccion")
	//@Temporal(TemporalType.TIMESTAMP)
	//@Type(type = "date")
	private String fechaTransaccion;
	@Column(name = "agendaFechaCiere")
    //@Temporal(TemporalType.TIMESTAMP)
	//@Type(type = "date")
	private String fechaCierre;
	@Column(name = "agendaNumeroCliente")
	private String numeroCliente;
	@Column(name = "agendaRazonSocial")
	private String razonSocial;
	@Column(name = "agendaNombreRepresentante")
	private String nombreRepresentante;
	@Column(name = "agendaNumeroTelefono")
	private String numeroTelefono;
	@Column(name = "agendaSoeid")
	private String soeid;
	@Column(name = "agendaEjecutivo")
	private String ejecutivo;
	@Column(name = "agendaSede")
	private String sede;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public String getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	public String getSoeid() {
		return soeid;
	}
	public void setSoeid(String soeid) {
		this.soeid = soeid;
	}
	
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
	
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	
	
}
