package com.eficacia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eficacia.model.Agenda;

public interface AgendaService {

	public Agenda obtenerAgenda(String codigoTransaccion);
	
	public List<Agenda> obtenerAgendas(String razonSocial);

	public Long contarRegistros();
	
	public Long contarRegistrosPag(String razonSocial);

	public void agregarAgenda(Agenda agenda);
	
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite);
	
	public String validarExcel(MultipartFile excelFile);
	
	public void modificarAgenda(Agenda agenda);
	
	public void eliminarAgenda(String codigoTransaccion);
	
	public List<String> validarExcelEliminacion(MultipartFile excelFile);
	
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite);
	
	public ArrayList<String> registrosNoEncontrados(MultipartFile excelFile);
	
}
