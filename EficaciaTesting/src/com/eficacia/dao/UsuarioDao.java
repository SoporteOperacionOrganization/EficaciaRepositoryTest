package com.eficacia.dao;

import java.util.List;

import com.eficacia.model.Usuario;

public interface UsuarioDao {
	
	public Usuario obtenerUsuario(String Soeid);
	
	public List<Usuario> filtrarUsuarios(String soeid, Integer offset, Integer limite);

	public List<Usuario> obtenerUsuarios();
	
	public List<Usuario> obtenerUsuariosPaginacion(Integer offset, Integer limite);
	
	public void agregarUsuario(Usuario usuario);
	
	public void eliminarUsuario(String soeid);
	
	public void modificarUsuario(Usuario usuario);
	
	public Long contarRegistros();
	
	public Long validarExistenciaUsuario(String soeid);
	
	public Long contarRegistrosCond(String soeid);
	
	public void modificarCredencialesExpiradas(boolean estatusCredencialesExpiradas, String soeid);
	
	public void renovarCredenciales(String passwordActual, String passwordNuevo, String fechaTransaccion, String soeid);
	
	//public void modificarContraseñaReset(String soeid);
	
	//public void resetearIntentosFallidosLogin(String soeid);
	
	public Integer obtenerIntentosFallidosLogin(String soeid);
	
	public void bloquearUsuario(Usuario usuario);
	
}
