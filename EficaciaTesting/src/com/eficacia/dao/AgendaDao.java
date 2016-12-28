package com.eficacia.dao;

import java.util.ArrayList;
import java.util.List;

import com.eficacia.model.Agenda;

public interface AgendaDao {

	public Agenda obtenerAgenda(String codigoTransaccion);
	
	public List<Agenda> obtenerAgendas(String razonSocial);
	 
	public Long contarRegistros();
	
	public Long contarRegistrosPag(String razonSocial);
	
	public void agregarAgenda(Agenda agenda);
	
	public void eliminarAgenda(String codigoTransaccion);
	
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite);
	 
	public String cargaMasiva(List<Agenda> agendas);
	
	public void modificarAgenda(Agenda agenda);
	
	public List<String> eliminacionMasiva(List<String> agendas);
	
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite);

	public ArrayList<String> registrosNoEncontrados (List<Agenda> agendas);

	
}
