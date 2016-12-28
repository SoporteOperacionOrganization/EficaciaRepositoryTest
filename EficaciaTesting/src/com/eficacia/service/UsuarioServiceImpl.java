package com.eficacia.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.dao.UsuarioDao;
import com.eficacia.model.Usuario;
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario obtenerUsuario(String soeid) {
		return usuarioDao.obtenerUsuario(soeid);
	}
	
	@Override
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite){
		return usuarioDao.obtenerUsuariosPaginacion(offset, limite);
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioDao.obtenerUsuarios();
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		Calendar fecha = new GregorianCalendar();
		String fechaActual = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH);
		usuario.setUsuarioFechaExpiracionContrasena(fechaActual);
		usuario.setUsuarioCredencialesNoExpiradas(true);
		usuarioDao.agregarUsuario(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Usuario entity = usuarioDao.obtenerUsuario(usuario.getSoeid());
		if(entity != null){
			entity.setSoeid(usuario.getSoeid());
			if(!usuario.getPassword().equals("")){
				entity.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}
			entity.setNombre(usuario.getNombre());
			entity.setApellidoPaterno(usuario.getApellidoPaterno());
			entity.setApellidoMaterno(usuario.getApellidoMaterno());
			entity.setTelefono(usuario.getTelefono());
			entity.setRol(usuario.getRol());
		}
		//usuarioDao.modificarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(String soeid) {
		usuarioDao.eliminarUsuario(soeid);
	}

	@Override
	public List<Usuario> filtrarUsuarios(String soeid, Integer offset, Integer limite) {
		return usuarioDao.filtrarUsuarios(soeid, offset, limite);
	}

	@Override
	public Long contarRegistros() {
		return usuarioDao.contarRegistros();
	}
	
	@Override
	public Long contarRegistrosCond(String soeid) {
		return usuarioDao.contarRegistrosCond(soeid);
	}
	
	@Override
	public Long validarExistenciaUsuario(String soeid){
		return usuarioDao.validarExistenciaUsuario(soeid);
	}

	@Override
	public boolean validarExpiracionContrasena(String soeid) throws ParseException {
		boolean validacion = true;
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Usuario usuario = usuarioDao.obtenerUsuario(soeid);
		
		String fechaUltimaModificacionContrasena = usuario.getUsuarioFechaExpiracionContrasena().substring(0, 4) + "/" + usuario.getUsuarioFechaExpiracionContrasena().substring(5, 7) + "/" + usuario.getUsuarioFechaExpiracionContrasena().substring(8, 10);
		Calendar fecha = new GregorianCalendar();
		String fechaActual = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH);
		Date fechaUltim = null;
		Date fechaAct= null;
		fechaUltim = format.parse(fechaUltimaModificacionContrasena);
		fechaAct = format.parse(fechaActual);
		
		Calendar startCal = Calendar.getInstance();
	    startCal.setTime(fechaUltim);        
	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(fechaAct);
	    int diferencia = 0;
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        
	    }
	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(fechaAct);
	        endCal.setTime(fechaUltim);
	    }
	    do {
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++diferencia;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
	  
		if(diferencia > 15){
			validacion = false;
		}
		
		return validacion;
	}

	@Override
	public void modificarCredencialesExpiradas(boolean estatusCredencialesExpiradas, String soeid) {
		usuarioDao.modificarCredencialesExpiradas(estatusCredencialesExpiradas, soeid);
	}

	@Override
	public void renovarCredenciales(String passwordActual, String passwordNuevo, String fechaTransaccion, String soeid) {
		usuarioDao.renovarCredenciales(passwordActual, passwordNuevo, fechaTransaccion, soeid);
	}

	@Override
	public String validarCambioContraseña(CustomUsuario principal, String  passwordActual, String passwordNuevo, String confirmacionPasswordNuevo) {
		String msg = "";
		//String patronContraseñaSegura = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,100})";
		String patronContraseñaSegura = "^[a-zA-Z0-9]*$";
		Calendar fecha = new GregorianCalendar();
		String fechaTransaccion= fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
		
		Usuario usuario = usuarioDao.obtenerUsuario(principal.getUsername());
		if(!passwordEncoder.matches(passwordActual,usuario.getPassword())){
			msg = "La contraseña actual no coincide";
		}else if(passwordNuevo.equals(passwordActual)){
			msg = "La contraseña nueva debe ser diferente a la anterior";
		}else if(passwordNuevo.length() != 8){
			msg = "La contraseña debe tener 8 caracteres";
        }else if(!tieneEspaciosEnBlanco(passwordNuevo)){
        	msg = "No debe contener espacios en blanco";
        }else if(tieneCaracteresConsecutivos(passwordNuevo) >= 3){
        	msg = "No deben repetirse caracteres mas de dos veces consecutivas";
        }else if(passwordNuevo.contains("Banamex") || usuario.getPassword().contains("Citi") || usuario.getPassword().contains("CitiBanamex")){
        	msg = "No debe contener palabras Banamex o Citi";
        }else if(passwordNuevo.charAt(0) == '0'){
        	msg = "No debe iniciar con cero";
        }else if(!passwordNuevo.matches(patronContraseñaSegura)){
        	msg = "Debe contener mayúsculas, minúsculas y números";
		}else if(!passwordNuevo.equals(confirmacionPasswordNuevo)){
			msg = "Las nuevas contraseñas deben coindidir";
		}else{
			usuarioDao.renovarCredenciales(passwordEncoder.encode(passwordActual), passwordEncoder.encode(passwordNuevo), fechaTransaccion, principal.getUsername());
		}
		return msg;
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

	@Override
	public void modificarContraseñaReset(String soeid) {
		Usuario entity = usuarioDao.obtenerUsuario(soeid);
		if(entity != null){
			entity.setPassword(passwordEncoder.encode(soeid));
		}
	}

	@Override
	public void actualizarIntentosLoginFallidos(Usuario usuario) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date date = new Date();
		System.out.println("Actualizar intentos " + dateFormat.format(date));
		Usuario entity = usuarioDao.obtenerUsuario(usuario.getSoeid());
		if(entity != null){
			entity.setIntentosLoginFallidos(obtenerIntentosFallidosLogin(usuario.getSoeid())+1);
			entity.setUsuarioFechaUltimoIntento(dateFormat.format(date));
		}
	}

	@Override
	public void bloquearUsuario(Usuario usuario) {
		/*DateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date datet = new Date();
		Usuario entity = usuarioDao.obtenerUsuario(usuario.getSoeid());
		System.out.println("Bloquear usuario " + dateFormatt.format(datet));
		if(usuario != null){
			System.out.println("MODIFICAR");
			entity.setUsuarioNoBloqueado(false);
			entity.setIntentosLoginFallidos(obtenerIntentosFallidosLogin(usuario.getSoeid()) + 1);
			entity.setUsuarioFechaUltimoIntento(dateFormatt.format(datet));
		}*/
		usuarioDao.bloquearUsuario(usuario);
	}

	@Override
	public void resetearIntentosFallidosLogin(String soeid) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date date = new Date();
		System.out.println("Resetear intentos " + dateFormat.format(date));
		Usuario entity = usuarioDao.obtenerUsuario(soeid);
		System.out.println("Reset " + soeid);
		if(entity != null){
			entity.setIntentosLoginFallidos(0);
			entity.setUsuarioFechaUltimoIntento(dateFormat.format(date));
		}
	}

	@Override
	public Integer obtenerIntentosFallidosLogin(String soeid) {
		return usuarioDao.obtenerIntentosFallidosLogin(soeid);
	}
	
	
	
}
