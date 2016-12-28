package com.eficacia.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eficacia.model.Agenda;
import com.eficacia.service.AgendaService;
import com.eficacia.validator.AgendaValidator;
import com.eficacia.validator.FileValidator;


@Controller
public class AgendaController {

	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private AgendaValidator agendaValidator;
	
	@Autowired
	private FileValidator fileValidator;
	
	@InitBinder("agenda")
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(agendaValidator);
	}
	
	@InitBinder("file")
	public void initFileBinder(WebDataBinder webDataBinder){
		webDataBinder.setValidator(fileValidator);
	}
	
	@RequestMapping(value = "/listarAgendas", method = RequestMethod.GET)
	public String listarAgendasPaginacion(Model model, Integer offset, Integer limite){
		List<Agenda> agendas = agendaService.obtenerAgendasPaginacion(offset, limite);
		model.addAttribute("agendas", agendas);
		model.addAttribute("count", agendaService.contarRegistros());
		model.addAttribute("offset", offset);
		return "agendas/listarAgendas";
	}
	
	@RequestMapping(value = "/filtrarAgendas", method = RequestMethod.GET)
	public String filtrarAgendas(Model model, String criterio,  Integer offset, Integer limite){
		List<Agenda> agendas = agendaService.filtrarAgendas(criterio, offset, limite);
		model.addAttribute("criterio", criterio);
		model.addAttribute("count", agendaService.contarRegistrosPag(criterio));
		model.addAttribute("agendas", agendas);
		model.addAttribute("offset", offset);
		model.addAttribute("filtro", true);
		model.addAttribute("limite", limite);
		return "agendas/listarAgendas";
	}
	
	@RequestMapping(value = "/agregarAgenda", method = RequestMethod.GET)
	public String agregarAgenda(Model model){
		Calendar fecha = new GregorianCalendar();
		String fechaTransaccion= fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		model.addAttribute("agenda", new Agenda());
		model.addAttribute("fechaTransaccion", fechaTransaccion);
		return "agendas/formularioAgenda";
	}
	
	@RequestMapping(value = "/agregarAgenda", method = RequestMethod.POST)
	public String insertarAgenda(Model model, @Valid Agenda agenda, BindingResult result){
		if(result.hasErrors()){
			return "agendas/formularioAgenda";
		}
		agendaService.agregarAgenda(agenda);
		return "redirect:/listarAgendas";
	}
	
	@RequestMapping(value = "/editarAgenda/{codigoTransaccion}", method = RequestMethod.GET)
	public String editarAgenda(Model model, @PathVariable String codigoTransaccion){
		Agenda agenda = agendaService.obtenerAgenda(codigoTransaccion);
		model.addAttribute("agenda",agenda);
		
		return "agendas/formularioAgendaModificar";
	}
	
	@RequestMapping(value = "/editarAgenda/{codigoTransaccion1}", method = RequestMethod.POST)
	public String modificarAgenda(@Valid Agenda agenda, BindingResult result,@PathVariable("codigoTransaccion1") String codigoTransaccion, Model model){
		if(result.hasErrors()){
			return "agendas/formularioAgendaModificar";
		}
		agendaService.modificarAgenda(agenda);
		return "redirect:/listarAgendas";
	}
	
	@RequestMapping(value="/eliminarAgenda/{codigoTransaccion}", method = RequestMethod.GET)
	public String eliminarAgenda(Model model, @PathVariable String codigoTransaccion){
		agendaService.eliminarAgenda(codigoTransaccion);
		return "redirect:/listarAgendas";
	}	
	
	@RequestMapping(value = "/exportarAgendas/{razonSocial}", method = RequestMethod.GET)
	public String exportarAgendas(Model model, @PathVariable String razonSocial){
		List<Agenda> agendas = agendaService.obtenerAgendas(razonSocial);
		model.addAttribute("agendas", agendas);
		return "VistaExcel";
	}
	
	@RequestMapping(value = "/cargaMasiva", method = RequestMethod.GET)
	public String cargaMasiva(Model model){
		model.addAttribute("file", new com.eficacia.model.File());
		return "agendas/cargaMasiva";
	}
	
	@RequestMapping(value = "/cargarExcel", method = RequestMethod.POST)
    public String cargarExcel(@RequestParam("archivo") MultipartFile excelfile, @Valid com.eficacia.model.File file, BindingResult result, final RedirectAttributes redirectAttributes , Model model){
          if(result.hasErrors()){
                 return "agendas/cargaMasiva";
          }
          String extension = excelfile.getOriginalFilename().substring(excelfile.getOriginalFilename().lastIndexOf(".") + 1);
       
          if(!"xlsx".equals(extension) && !"xlsm".equals(extension) && !"xls".equals(extension)){
                 redirectAttributes.addFlashAttribute("extensionError","Verifica que el archivo tenga la extensión solicitada xlsx, xls. , xlsm");
          }else{
              String estatus = agendaService.validarExcel(excelfile);
                 if("".equals(estatus)){
                        redirectAttributes.addFlashAttribute("procesoCorrecto", "El proceso ha terminado exitosamente");
                        
                        
                 }
                 redirectAttributes.addFlashAttribute("estatus", estatus);
          }
          return "redirect:/cargaMasiva";
    }

	
	@RequestMapping(value = "/eliminacionMasiva", method = RequestMethod.GET)
	public String eliminacionMasiva(Model model){
		model.addAttribute("file", new com.eficacia.model.File());
		return "agendas/eliminacionMasiva";
	}
	
	@RequestMapping(value = "/cargarExcelEliminacion", method = RequestMethod.POST)
	public String cargarExcelEliminacion(@RequestParam("archivo") MultipartFile excelfile,  @Valid com.eficacia.model.File file, BindingResult result, Model model){
		if(result.hasErrors()){	
			return "agendas/eliminacionMasiva";
		}
		List<String> noEncontrados =new ArrayList<String>();

		noEncontrados = agendaService.validarExcelEliminacion(excelfile);
		model.addAttribute("noEncontrados", noEncontrados);
		model.addAttribute("procesoCorrecto", "El proceso ha terminado exitosamente");
		
		return "agendas/eliminacionMasiva";
	}

	@RequestMapping(value = "/descargarArchivo/{archivo}", method = RequestMethod.GET)
    public String descargarArchivo(Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable int archivo) throws IOException{
		String rutaArchivo = "";
		switch(archivo){
        	case 1:
        		rutaArchivo = "/WEB-INF/files/LayoutCargaMasiva.xlsm";
        	break;
        	
        	case 2:
        		rutaArchivo = "/WEB-INF/files/LayoutEliminacionMasiva.xlsm";
        	break;
        }
       
		
		
		int BUFFER_SIZE = 4096;    
        ServletContext context = request.getServletContext();
        String rutaAplicacion = context.getRealPath("");
        String rutaCompleta = rutaAplicacion + rutaArchivo;      
        File downloadFile = new File(rutaCompleta);
        FileInputStream inputStream = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(rutaCompleta);
        if (mimeType == null) {
        	mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
        downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
        	outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
        
        
        return null;
    }
	
	/*
	@RequestMapping(value = "/descargarLayoutCargaMasiva", method = RequestMethod.GET)
	public ModelAndView descargarLayoutCargaMasiva(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
            // Suponemos que es un zip lo que se quiere descargar el usuario.
            // Aqui se hace a piñón fijo, pero podría obtenerse el fichero
            // pedido por el usuario a partir de algún parámetro del request
            // o de la URL con la que nos han llamado.
            String nombreFichero = "LayoutCargaMasiva.xlsm";
            String unPath = "C:/Users/JG77991/Desktop/";

            response.setContentType("application/xlsm");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + nombreFichero+ "\"");

            InputStream is = new FileInputStream(unPath+nombreFichero);
            
            IOUtils.copy(is, response.getOutputStream());

            response.flushBuffer();
            
        } catch (IOException ex) {
            // Sacar log de error.
            throw ex;
        }
        
        return null;
	}
	*/
	
}
