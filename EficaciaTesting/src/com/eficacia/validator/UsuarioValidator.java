package com.eficacia.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.eficacia.model.Usuario;

import com.eficacia.service.UsuarioService;

@Component
public class UsuarioValidator implements Validator{

	@Autowired
	private UsuarioService usuarioService;
	
	public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        Usuario usuario = (Usuario) obj;
             
        String patronContraseñaSeguridad = "^(?=.[a-z])(?=.[A-Z])(?=.\\d).+$";
        String patronContraseñaSegura =
                "^[a-zA-Z0-9]*$";
        /*String patronContraseñaSegura =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,100})";*/
        String patronSoloNumeros = "\\d+";
        String patronSoloLetras = "^[\\p{L} .'-]+$";
        
        if(usuario.getSoeid().length() != 7){
        	errors.rejectValue("soeid", "NotLenght.usuario.soeid");
        }else if(!usuario.getSoeid().substring(0, 2).matches(patronSoloLetras) || !usuario.getSoeid().substring(2, 7).matches(patronSoloNumeros) ){
        	errors.rejectValue("soeid", "NotPattern.usuario.soeid");
        }
        
        if(usuario.getNombre().equals("")){
        	errors.rejectValue("nombre", "NotEmpty.usuario.nombre");
        }else if(!usuario.getNombre().matches(patronSoloLetras)){
        	errors.rejectValue("nombre", "NotMatch.usuario.soloLetras");
        }
        
        if(usuario.getApellidoPaterno().equals("")){
        	errors.rejectValue("apellidoPaterno", "NotEmpty.usuario.apellidoPaterno");
        }else if(!usuario.getApellidoPaterno().matches(patronSoloLetras)){
        	errors.rejectValue("apellidoPaterno", "NotMatch.usuario.soloLetras");
        }
        
        if(!usuario.getApellidoMaterno().equals(""))
        	if(!usuario.getApellidoMaterno().matches(patronSoloLetras)){
        		errors.rejectValue("apellidoMaterno", "NotMatch.usuario.soloLetras");
        	}
        
        if(usuario.getTelefono().equals("")){
        	errors.rejectValue("telefono", "NotEmpty.usuario.telefono");
        }else if(!usuario.getTelefono().matches(patronSoloNumeros)){
        	errors.rejectValue("telefono", "NotNumber.usuario.notnumber");
        }
        
        if(usuario.getPassword().equals("")){
        	errors.rejectValue("password", "NotEmpty.usuario.password");
        }else if(usuario.getPassword().length() != 8){
        	errors.rejectValue("password", "NotLenght.usuario.password");
        }else if(!tieneEspaciosEnBlanco(usuario.getPassword())){
        	errors.rejectValue("password", "NoBlankSpaces.usuario.password");
        }else if(tieneCaracteresConsecutivos(usuario.getPassword()) >= 3){
        	errors.rejectValue("password", "RepeatedCharacters.usuario.password");
        }else if(usuario.getPassword().contains(usuario.getNombre()) || usuario.getPassword().contains(usuario.getApellidoPaterno())){
        	errors.rejectValue("password", "NotUserName.usuario.password");
        }else if(usuario.getPassword().contains("Banamex") || usuario.getPassword().contains("Citi") || usuario.getPassword().contains("CitiBanamex")){
        	errors.rejectValue("password", "NotKeyWords.usuario.password");
        }else if(usuario.getPassword().charAt(0) == '0'){
        	errors.rejectValue("password", "NotZeroAtBeggining.usuario.password");
        }else if(!usuario.getPassword().matches(patronContraseñaSegura)){
        	errors.rejectValue("password", "NotPattern.usuario.password");
        }
        
        if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())){
        	 errors.rejectValue("passwordConfirmation", "notmatch.usuario.password");
        }

        if(usuario.getRol().getId() == 0){
                     errors.rejectValue("rol", "NotEmpty.usuario.rol");
        }
        
        if(validarExistenciaUsuario(usuario.getSoeid()) != 0){
        	errors.rejectValue("soeid", "ExistingRow.usuario.soeid");
        }
    }
    
    public boolean tieneEspaciosEnBlanco(String password){
    	boolean validacion = true;
    	int tamanoCadena = password.length();
        for (int i = 0; i < tamanoCadena; i++) {
          if (Character.isWhitespace(password.charAt(i))) {
            validacion = false;
          }
        }
    	return validacion;
    }
    
    public int tieneCaracteresConsecutivos(String password) {
        int charBlock = 0;
        int holder = 1;
        if(password.length() == 0){ 
           charBlock = 0;
        } else if(password.length() == 1){
           charBlock = 1;
        } else {
            for(int i=0; i < password.length()-1; i++){   
                if((password.length() == 2) && (password.charAt(i) != password.charAt(i+1))){ 
                    charBlock =1; 
                }   
                else if((password.length() == 3) && (password.charAt(i) != password.charAt(i+1))){
                   charBlock = 1; 
                } 
                else if (password.charAt(i) == password.charAt(i+1)){
                   holder = holder + 1; 
                     if(holder > charBlock){
                       charBlock = holder;
                       }
               } else holder = 1; 
            }
        }
        return charBlock;
      }
    
    public Long validarExistenciaUsuario(String soeid){
    	Long usuarioExiste;
    	usuarioExiste = usuarioService.validarExistenciaUsuario(soeid);
    	return usuarioExiste;
    }



}
