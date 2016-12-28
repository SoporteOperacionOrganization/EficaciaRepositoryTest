package com.eficacia.service;

import java.util.List;
import java.text.ParseException;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.model.Usuario;

public interface UsuarioService {

	public Usuario obtenerUsuario(String soeid);
	
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite);
	
	public List<Usuario> filtrarUsuarios(String soeid, Integer offset, Integer limite);
	
	public List<Usuario> obtenerUsuarios();
		
	public void agregarUsuario(Usuario usuario);
	
	public void modificarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String soeid);
	
	public Long contarRegistros();
	
	public Long contarRegistrosCond(String soeid);
	
	public Long validarExistenciaUsuario(String soeid);
	
	public boolean validarExpiracionContrasena(String soeid) throws ParseException;
	
	public void modificarCredencialesExpiradas(boolean estatusCredencialesExpiradas, String soeid);
	
	public void renovarCredenciales(String passwordActual, String passwordNuevo, String fechaTransaccion, String soeid);
	
	public String validarCambioContraseña(CustomUsuario usuario, String  passwordActual, String passwordNuevo, String confirmacionPasswordNuevo);
	
	public void modificarContraseñaReset(String soeid);
	
	public void actualizarIntentosLoginFallidos(Usuario usuario);
	
	public void bloquearUsuario(Usuario usuario);
	
	public void resetearIntentosFallidosLogin(String soeid);
	
	public Integer obtenerIntentosFallidosLogin(String soeid);
	
}
