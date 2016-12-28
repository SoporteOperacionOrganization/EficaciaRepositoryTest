package com.eficacia.security;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.eficacia.model.Usuario;
import com.eficacia.service.UsuarioService;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Autowired 
	private UsuarioService usuarioService;
	
	private String DEFAULT_FAILURE_URL = "/login?error=true";
	private String USER_NOT_FOUND_URL = "/login?userNotFound=true";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String soeid = request.getParameter("soeid");
		
		Usuario usuario = usuarioService.obtenerUsuario(soeid);
		if(usuario != null){
			try {
				verificarUsuarioBloqueado(usuario);
			} catch (UsernameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
	              setDefaultFailureUrl(DEFAULT_FAILURE_URL);
	        }else if(exception.getClass().isAssignableFrom(LockedException.class)){
	        	setDefaultFailureUrl(DEFAULT_FAILURE_URL);
	        }else if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)){
	        	setDefaultFailureUrl(DEFAULT_FAILURE_URL);
	        }
		}else{
			setDefaultFailureUrl(USER_NOT_FOUND_URL);
		}
		super.onAuthenticationFailure(request, response, exception);
	}
	
	private boolean verificarUsuarioBloqueado(Usuario usuario) throws UsernameNotFoundException, ParseException{
		boolean usuarioBloqueado = false;

		
		
		Date fechaActual = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaUltimoIntentoLogin = simpleDateFormat.parse(usuario.getUsuarioFechaUltimoIntento());
		
		Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fechaActual); 
		      calendar.add(Calendar.DAY_OF_WEEK, -1);
		if(fechaUltimoIntentoLogin.before(calendar.getTime())){
			System.out.println("La de actualizacion es mayor, contador se resetea " + usuario.getSoeid());
			usuarioService.resetearIntentosFallidosLogin(usuario.getSoeid());
			usuarioService.actualizarIntentosLoginFallidos(usuario);
		}else{
			System.out.println("La de actualizacion es mayor, contador se mantiene ");
			if(usuarioService.obtenerIntentosFallidosLogin(usuario.getSoeid()) + 1 > 2){
				System.out.println("Contador mayor a 2, bloquear");
				usuarioService.bloquearUsuario(usuario);
			}else{
				System.out.println("Contador menor a 2 ");
				usuarioService.actualizarIntentosLoginFallidos(usuario);
			}
		}
		
		/*System.out.println("Dia actual - 1: " + simpleDateFormat.format(calendar.getTime()));
		System.out.println("Contador de intentos " + usuarioService.obtenerIntentosFallidosLogin(usuario.getSoeid()));
		if(usuarioService.obtenerIntentosFallidosLogin(usuario.getSoeid()) + 1 > 2){
			usuarioService.bloquearUsuario(usuario);
		}else{
			usuarioService.actualizarIntentosLoginFallidos(usuario);
		}*/
		return usuarioBloqueado;
	}
	
}
