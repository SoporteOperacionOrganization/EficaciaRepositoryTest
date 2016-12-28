package com.eficacia.utilities;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.eficacia.model.Agenda;

public class AgendaExcelBuilder extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
									  Workbook workbook, 
									  HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		List<Agenda> agendas = (List<Agenda>) model.get("agendas");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"agendas.xlsx\"");

		//Crear hoja excel
		Sheet sheet = workbook.createSheet("Agendas");
        sheet.setDefaultColumnWidth(30);
        sheet.protectSheet("12345");
        
        //crear estilo para cabecera
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
      
        
        //crear cabecera
        Row header = sheet.createRow(2);
        
         
        header.createCell(0).setCellValue("CÓDIGO TRANSACCION");
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue("FECHA TRANSACCION");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("FECHA CIERRE");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("NÚMERO CLIENTE");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("RAZÓN SOCIAL");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("NOMBRE REPRESENTANTE");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("NÚMERO DE TELÉFONO");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("SOEID");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("EJECUTIVO");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("SEDE");
        header.getCell(9).setCellStyle(style);
        
        //Crear filas
        int rowCount = 3;
         
        for (Agenda aAgenda : agendas) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aAgenda.getCodigoTransaccion());
            aRow.createCell(1).setCellValue(aAgenda.getFechaTransaccion());
            aRow.createCell(2).setCellValue(aAgenda.getFechaCierre());
            aRow.createCell(3).setCellValue(aAgenda.getNumeroCliente());
            aRow.createCell(4).setCellValue(aAgenda.getRazonSocial());
            aRow.createCell(5).setCellValue(aAgenda.getNombreRepresentante());
            aRow.createCell(6).setCellValue(aAgenda.getNumeroTelefono());
            aRow.createCell(7).setCellValue(aAgenda.getSoeid());
            aRow.createCell(8).setCellValue(aAgenda.getEjecutivo());
            aRow.createCell(9).setCellValue(aAgenda.getSede());
        }
	}
	
}
