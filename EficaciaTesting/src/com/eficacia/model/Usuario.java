package com.eficacia.model;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuarios")
public class Usuario{

	@Id
	@GeneratedValue
	@Column(name = "usuarioid")
	private int id;
	@Column(name = "usuariosoeid")
	private String soeid;
	@Column(name = "usuariocontrasena")
	private String password;
	@Transient
	private String passwordConfirmation;
	@Column(name = "usuarioNombre")
	private String nombre;
	@Column(name = "usuarioApellidoPaterno")
	private String apellidoPaterno;
	@Column(name = "usuarioApellidoMaterno")
	private String apellidoMaterno;
	@Column(name = "usuarioTelefono")
	private String telefono;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuariorol")
	private Rol rol;
	
	@Column(name = "usuarioContrasenaAnterior")
	private String usuarioContrasenaAnterior;
	@Column(name = "usuarioFechaExpiracionContrasena")
	private String usuarioFechaExpiracionContrasena;
	@Column(name = "usuarioCredencialesNoExpiradas")
	private boolean usuarioCredencialesNoExpiradas;
	
	@Column(name = "usuarioIntentosLogin")
	private int intentosLoginFallidos;
	/*@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuarioIntentosLogin")
	private IntentoLogin intentoLogin;*/
	@Column(name = "usuarioNoBloqueado")
	private boolean usuarioNoBloqueado;
	
	@Column(name = "usuarioFechaUltimoIntentoLogin")
	private String usuarioFechaUltimoIntentoLogin;
	
	public String getUsuarioFechaUltimoIntento() {
		return usuarioFechaUltimoIntentoLogin;
	}
	public void setUsuarioFechaUltimoIntento(String usuarioFechaUltimoIntentoLogin) {
		this.usuarioFechaUltimoIntentoLogin = usuarioFechaUltimoIntentoLogin;
	}
	public boolean isUsuarioNoBloqueado() {
		return usuarioNoBloqueado;
	}
	public void setUsuarioNoBloqueado(boolean usuarioNoBloqueado) {
		this.usuarioNoBloqueado = usuarioNoBloqueado;
	}
	public int getIntentosLoginFallidos() {
		return intentosLoginFallidos;
	}
	public void setIntentosLoginFallidos(int intentosLoginFallidos) {
		this.intentosLoginFallidos = intentosLoginFallidos;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getSoeid(){
		return soeid;
	}
	public void setSoeid(String soeid){
		this.soeid = soeid;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public Rol getRol(){
		return rol;
	}
	public void setRol(Rol rol){
		this.rol = rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public String getUsuarioContrasenaAnterior() {
		return usuarioContrasenaAnterior;
	}
	public void setUsuarioContrasenaAnterior(String usuarioContrasenaAnterior) {
		this.usuarioContrasenaAnterior = usuarioContrasenaAnterior;
	}
	public String getUsuarioFechaExpiracionContrasena() {
		return usuarioFechaExpiracionContrasena;
	}
	public void setUsuarioFechaExpiracionContrasena(String usuarioFechaExpiracionContrasena) {
		this.usuarioFechaExpiracionContrasena = usuarioFechaExpiracionContrasena;
	}
	public boolean isUsuarioCredencialesNoExpiradas() {
		return usuarioCredencialesNoExpiradas;
	}
	public void setUsuarioCredencialesNoExpiradas(boolean usuarioCredencialesNoExpiradas) {
		this.usuarioCredencialesNoExpiradas = usuarioCredencialesNoExpiradas;
	}
	/*public int getConteoLoginFallido() {
		return conteoLoginFallido;
	}
	public void setConteoLoginFallido(int conteoLoginFallido) {
		this.conteoLoginFallido = conteoLoginFallido;
	}*/	
	
	/*public IntentoLogin getIntentoLogin(){
		return intentoLogin;
	}
	public void setIntentoLogin(IntentoLogin intentoLogin){
		this.intentoLogin=intentoLogin;
	}*/
	
	
}
