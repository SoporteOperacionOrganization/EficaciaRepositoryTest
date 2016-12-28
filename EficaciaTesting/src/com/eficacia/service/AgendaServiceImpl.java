package com.eficacia.service;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.eficacia.dao.AgendaDao;
import com.eficacia.model.Agenda;

@Service
@Transactional
public class AgendaServiceImpl implements AgendaService {

	Calendar date = new GregorianCalendar();
	Random randomGenerator = new Random();
	
	@Autowired
	private AgendaDao agendaDao;
	
	@Override
	public Agenda obtenerAgenda(String codigoTransaccion){
		return agendaDao.obtenerAgenda(codigoTransaccion);
	}
	
	@Override
	public List<Agenda> obtenerAgendas(String razonSocial) {
		return agendaDao.obtenerAgendas(razonSocial);
	}

	@Override
	public Long contarRegistros() {
		return agendaDao.contarRegistros();
	}
	
	@Override
	public Long contarRegistrosPag(String razonSocial) {
		return agendaDao.contarRegistrosPag(razonSocial);
	}

	@Override
	public void agregarAgenda(Agenda agenda) {
		String fecha = date.get(Calendar.YEAR) + "" + (date.get(Calendar.MONTH)+1) + "" + date.get(Calendar.DAY_OF_MONTH);
		String hora = String.format("%02d%02d%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
		String noCliente = agenda.getNumeroCliente();
		int cadenaVerificadora = randomGenerator.nextInt(900) + 100;
		String codigoTransaccion = fecha + hora + noCliente + cadenaVerificadora;
	agenda.setCodigoTransaccion(codigoTransaccion);
		agendaDao.agregarAgenda(agenda);
	}

	@Override
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite) {
		return agendaDao.filtrarAgendas(razonSocial, offset, limite);
	}

	@Override
	public void eliminarAgenda(String codigoTransaccion){
		agendaDao.eliminarAgenda(codigoTransaccion);
	}
	
	
	@Override
	public void modificarAgenda(Agenda agenda) {
			agendaDao.modificarAgenda(agenda);
	}
	
	@Override
    public String validarExcel(MultipartFile excelFile) {
		  List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
          String estatusCarga = "";
          int i = 3;
          try{
                 DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                 
                 List<Agenda> agendas = new ArrayList<>();
                 XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());          
                 XSSFSheet worksheet = workbook.getSheetAt(0);
                 while (i <= worksheet.getLastRowNum()) {
                        Agenda agenda = new Agenda();
                        XSSFRow row = worksheet.getRow(i++);
                       
                        if(validarCamposFecha(row,0)){agenda.setFechaTransaccion(df.format(row.getCell(0).getDateCellValue()));}
                        else{estatusCarga = "Error en linea " + i + " celda " + letras.get(0);break;}
                        
                        if(validarCamposFecha(row,1)){agenda.setFechaCierre(df.format(row.getCell(1).getDateCellValue()));}
                        else{estatusCarga = "Error en linea " + i + " celda " + letras.get(1);break;}                                             
                        
                        String noCliente=row.getCell(2, row.RETURN_BLANK_AS_NULL).getStringCellValue();
                        agenda.setNumeroCliente(noCliente);
   
                        String fecha = date.get(Calendar.YEAR) + "" + (date.get(Calendar.MONTH)+1) + "" + date.get(Calendar.DAY_OF_MONTH);
                        String hora = String.format("%02d%02d%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));
                      
                        int cadenaVerificadora = randomGenerator.nextInt(900) + 100;
                        String codigoTransaccion = fecha + hora + noCliente + cadenaVerificadora;
                 
                        agenda.setCodigoTransaccion(codigoTransaccion);
                       
                        agenda.setRazonSocial(row.getCell(3).getStringCellValue());
                        agenda.setNombreRepresentante(row.getCell(4).getStringCellValue());
                        String noTelefono=row.getCell(5,row.RETURN_BLANK_AS_NULL).getStringCellValue();
                        agenda.setNumeroTelefono(noTelefono);
                                
                        agenda.setSoeid(String.valueOf(row.getCell(6).getStringCellValue()));                
                        agenda.setEjecutivo(row.getCell(7).getStringCellValue());
                        agenda.setSede(row.getCell(8).getStringCellValue());
                        
                        agendas.add(agenda);
                 
                 		
		                 /*CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) excelFile;
		                 FileItem fileItem = commonsMultipartFile.getFileItem();
		                 DiskFileItem diskFileItem = (DiskFileItem) fileItem;
		                 String absPath = diskFileItem.getStoreLocation().getAbsolutePath();
		                 File inputWorkbook = new File(absPath);*/
                 
                 		
                 		/*File inputWorkbook = new File( excelFile.getOriginalFilename());
                 		excelFile.transferTo(inputWorkbook);
                 		
                 		System.out.println("Nombre " + inputWorkbook.getAbsolutePath());
                 		List<Agenda> agendas = new ArrayList<>();
                 		Workbook w;
                 		w = Workbook.getWorkbook(inputWorkbook);
                 		Sheet sheet = w.getSheet(0);
                 		for (int j = 0; j < sheet.getColumns(); j++) {
                 			for (int i = 3; i < sheet.getRows(); i++) {
                 				Cell cell = sheet.getCell(j, i);
                 				System.out.println("Contenido " + cell.getContents());
                 			}
                 		}
                 		*/
                 
                 }         
                 if(estatusCarga.equals("")){
                        agendaDao.cargaMasiva(agendas);
                 }
                        workbook.close();
          } catch (Exception e) {
                 e.printStackTrace();
          }
          return estatusCarga;
    }

	
	public ArrayList<String> registrosNoEncontrados(MultipartFile excelFile){
		ArrayList<String> noEncontrados= new ArrayList<>();
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		int i = 3;
		
		try{
			List<Agenda> agendas = new ArrayList<>();
			
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Agenda agenda = new Agenda();
				XSSFRow row = worksheet.getRow(i++);
				
				agenda.setCodigoTransaccion(row.getCell(0).getStringCellValue());
				agendas.add(agenda);
			}		
			if(estatusCarga.equals("")){
					noEncontrados=agendaDao.registrosNoEncontrados(agendas);
			}
				workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return noEncontrados;
	}
	
	
	public List<String> validarExcelEliminacion(MultipartFile excelFile) {
		List<String> letras = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		String estatusCarga = "";
		List<String> codigosNoEncontrados = new ArrayList<String>();
		int i = 3;
		try{
			List<String> codigos = new ArrayList<>();
		
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				XSSFRow row = worksheet.getRow(i++);
				codigos.add(row.getCell(0).getStringCellValue());
			}		
			if(estatusCarga.equals("")){
				codigosNoEncontrados = agendaDao.eliminacionMasiva(codigos);
			}
				workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codigosNoEncontrados;
	}
	
	
	
	public boolean validarCamposFecha(XSSFRow row, int cell){
		boolean estatus = true;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		if(row.getCell(cell) == null){
			estatus = false;
		}
		if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			estatus = false;
		}
		if(row.getCell(cell).getCellType() != Cell.CELL_TYPE_NUMERIC){
			estatus = false;
		}
		return estatus;
	}

	public boolean validarCamposNumericos(XSSFRow row, int cell){
		boolean estatus = true;
				if(row.getCell(cell) == null){
			
			estatus = false;
		}else if(row.getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK){
			
			estatus = false;
		}
		return estatus;
	}
	
	public boolean validarCamposNoNumericos(XSSFRow row, int cell){
		boolean estatus = true;
		if(row.getCell(cell) == null){
			estatus = false;
		}
		return estatus;
	}
	


	@Override
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite) {
		return agendaDao.obtenerAgendasPaginacion(offset, limite);
	}
	
}
