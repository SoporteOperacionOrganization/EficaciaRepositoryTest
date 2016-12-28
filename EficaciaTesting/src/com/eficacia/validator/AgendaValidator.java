package com.eficacia.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Agenda;
import com.eficacia.model.Usuario;

@Component
public class AgendaValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
        return Agenda.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        Agenda agenda = (Agenda) obj;
        
        String patronSoloNumeros = "\\d+";
    	String patronSoloLetras = "^[\\p{L} .'-]+$";
    	String patronLetrasYCaracteres = "[a-zA-Z . ]+";
             
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaTransaccion", "NotEmpty.agenda.fechaTransaccion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaCierre", "NotEmpty.agenda.fechaCierre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sede", "NotEmpty.agenda.sede");
        
        if(agenda.getRazonSocial().equals("")){
        	errors.rejectValue("razonSocial", "NotEmpty.agenda.razonSocial");
        }else if(!agenda.getRazonSocial().matches(patronLetrasYCaracteres)){
        	errors.rejectValue("razonSocial", "NotFormat.agenda.razonSocial");
        }
        
        if(agenda.getNumeroCliente().length() != 5){
        	errors.rejectValue("numeroCliente", "NotLenght.agenda.numeroCliente");
        }else if(!agenda.getNumeroCliente().matches(patronSoloNumeros)){
        	errors.rejectValue("numeroCliente", "NotNumber.agenda.notnumber");
        }
        
        if(agenda.getNumeroTelefono().equals("")){
        	errors.rejectValue("numeroTelefono", "NotEmpty.agenda.numeroTelefono");
        }else if(!agenda.getNumeroTelefono().matches(patronSoloNumeros)){
        	errors.rejectValue("numeroTelefono", "NotNumber.agenda.notnumber");
        }
        
        if(agenda.getNombreRepresentante().equals("")){
        	errors.rejectValue("nombreRepresentante", "NotEmpty.agenda.nombreRepresentante");
        }else if(!agenda.getNombreRepresentante().matches(patronSoloLetras)){
        	errors.rejectValue("nombreRepresentante", "NotMatch.agenda.soloLetras");
        }
        
        if(agenda.getSoeid().length() != 7){
        	errors.rejectValue("soeid", "NotLenght.agenda.soeid");
        }else if(!agenda.getSoeid().substring(0, 2).matches(patronSoloLetras) || !agenda.getSoeid().substring(2, 7).matches(patronSoloNumeros) ){
        	errors.rejectValue("soeid", "NotPattern.agenda.soeid");
        }
        
        if(agenda.getEjecutivo().equals("")){
        	errors.rejectValue("ejecutivo", "NotEmpty.agenda.ejecutivo");
        }else if(!agenda.getEjecutivo().matches(patronSoloLetras)){
        	errors.rejectValue("ejecutivo", "NotMatch.agenda.soloLetras");
        }
        
        
    }
	
}
