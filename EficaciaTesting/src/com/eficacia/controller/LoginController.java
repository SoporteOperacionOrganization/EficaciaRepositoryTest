package com.eficacia.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eficacia.custommodel.CustomUsuario;
import com.eficacia.model.Usuario;
import com.eficacia.service.UsuarioService;

@Controller
@SessionAttributes("UsuarioSesion")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "login/login";
	}
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String inicio(Model model) throws ParseException{
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				boolean verificacionFechaUltimaModificacion = usuarioService.validarExpiracionContrasena(principal.getUsername());
		if(!verificacionFechaUltimaModificacion ){
			usuarioService.modificarCredencialesExpiradas(verificacionFechaUltimaModificacion, principal.getUsername());
		}
	
		Usuario usuario = usuarioService.obtenerUsuario(principal.getUsername());
		if(!usuario.isUsuarioCredencialesNoExpiradas()){
		
			return "redirect:/credencialesExpiradas";
		}
			model.addAttribute("UsuarioSesion", principal);
		return "login/inicio";
		}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String forbidden(Model model){
		return "error/403";
	}
	
	/*
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String recursoNoEncontrado(Model model){
		return "errors/404";
	}
	*/

	@RequestMapping(value = "/credencialesExpiradas", method = RequestMethod.GET)
	public String contrasenaExpirada(Model model){
		return "login/credencialesExpiradas";
	}
	
	@RequestMapping(value = "/contrasenaExpirada", method = RequestMethod.GET)
	public String contrasenaExpirada(Model model,  @RequestParam("passwordActual") String passwordActual,  @RequestParam("passwordNuevo") String passwordNuevo, @RequestParam("confirmacionPasswordNuevo") String confirmacionPasswordNuevo, RedirectAttributes redirect){
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String validacion = usuarioService.validarCambioContraseña(principal, passwordActual, passwordNuevo, confirmacionPasswordNuevo);
		if(!validacion.equals("")){
			redirect.addFlashAttribute("error", validacion);
			redirect.addFlashAttribute("passActual", passwordActual);
			redirect.addFlashAttribute("passNuevo", passwordNuevo);
			redirect.addFlashAttribute("confirmacionPasswordNuevo", confirmacionPasswordNuevo);
			return "redirect:/credencialesExpiradas";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/modificarContrasenaReseteo/{soeid}")
	public String modificarFraseContraseña(Model model, @PathVariable("soeid") String soeid, RedirectAttributes redirectAttributes){
		usuarioService.modificarContraseñaReset(soeid);
		redirectAttributes.addFlashAttribute("message", "La contraseña del usuario " + soeid + " ha sido cambiada para que éste la reestablesca en su proximo inicio de sesión. ");
		return "redirect:/success";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String operacionExitosa(Model model){
		return "success/success";
	}
	
	@RequestMapping(value = "/editarContrasena", method = RequestMethod.GET)
	public String editarContraseña(Model model){
		return "login/cambiarContrasena";
	}
	
	@RequestMapping(value = "/cambiarContrasena", method = RequestMethod.GET)
	public String cambiarContraseña(Model model, @RequestParam("passwordActual") String passwordActual,  @RequestParam("passwordNuevo") String passwordNuevo, @RequestParam("confirmacionPasswordNuevo") String confirmacionPasswordNuevo, RedirectAttributes redirect){
		CustomUsuario principal = (CustomUsuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String validacion = usuarioService.validarCambioContraseña(principal, passwordActual, passwordNuevo, confirmacionPasswordNuevo);
		if(!validacion.equals("")){
			redirect.addFlashAttribute("error", validacion);
			redirect.addFlashAttribute("passActual", passwordActual);
			redirect.addFlashAttribute("passNuevo", passwordNuevo);
			redirect.addFlashAttribute("confirmacionPasswordNuevo", confirmacionPasswordNuevo);
			return "redirect:/editarContrasena";
		}
		return "redirect:/login";
	}
	
}
