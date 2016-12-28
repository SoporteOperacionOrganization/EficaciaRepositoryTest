package com.eficacia.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eficacia.model.Rol;
import com.eficacia.model.Usuario;
import com.eficacia.propertyeditors.RolPropertyEditor;
import com.eficacia.service.RolService;
import com.eficacia.service.UsuarioService;
import com.eficacia.validator.UsuarioValidator;
import com.eficacia.validator.UsuarioUpdateValidator;


@Controller
public class UsuariosController {

       @Autowired
       private UsuarioService usuarioService;
       
       @Autowired
       private RolService rolService;
       
       @Autowired
       private RolPropertyEditor rolPropertyEditor;
       
       /*@Autowired
       private UsuarioValidator usuarioValidator;*/
       
       @InitBinder
       public void initBinder(WebDataBinder webDataBinder){
             webDataBinder.registerCustomEditor(Rol.class, rolPropertyEditor);
             //webDataBinder.setValidator(usuarioValidator);
       }
       
       @Inject
    private UsuarioValidator usuarioValidation;
       
       @Inject
    private UsuarioUpdateValidator usuarioUpdateValidation;

       
   

	
	@RequestMapping(value = "/listarRoles", method = RequestMethod.GET)
	public String test(Model model){
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("roles", roles);
		return "roles/listarRoles";
	}
	
	@RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
	public String listarUsuariosPag(Model model, Integer offset, Integer limite){
		List<Usuario> usuarios = usuarioService.obtenerUsuariosPaginacion(offset, limite);
		model.addAttribute("count", usuarioService.contarRegistros());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("offset", offset);
		return "usuarios/listarUsuarios";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(Model model, Integer offset, Integer limite){
		List<Usuario> usuarios = usuarioService.obtenerUsuariosPaginacion(offset, limite);
		model.addAttribute("count", usuarioService.contarRegistros());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("offset", offset);
		return "login/listarUsuariosResetPass";
	}
	
	@RequestMapping(value = "/filtrarUsuarios", method = RequestMethod.GET)
	public String filtrarUsuarios(Model model, String criterio, Integer offset, Integer limite){
		List<Usuario> usuarios;
		usuarios = usuarioService.filtrarUsuarios(criterio, offset, limite);
		
		model.addAttribute("criterio", criterio);
		model.addAttribute("count", usuarioService.contarRegistrosCond(criterio));
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("offset", offset);
		model.addAttribute("filtro", true);
		return "usuarios/listarUsuarios";
	}
	
	@RequestMapping(value = "/filtrarReset", method = RequestMethod.GET)
	public String filtrarReset(Model model, String criterio, Integer offset, Integer limite){
		List<Usuario> usuarios;
		usuarios = usuarioService.filtrarUsuarios(criterio, offset, limite);
		
		model.addAttribute("criterio", criterio);
		model.addAttribute("count", usuarioService.contarRegistrosCond(criterio));
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("offset", offset);
		model.addAttribute("filtro", true);
		return "login/listarUsuariosResetPass";
	}
	
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.GET)
	public String agregarUsuario(Model model){
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("usuario", new Usuario());
		
		model.addAttribute("roles",roles);
		return "usuarios/formularioUsuario";
	}
	
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.POST)
	public String registrarUsuario(Model model, @Valid Usuario usuario, BindingResult result){
		usuarioValidation.validate(usuario, result);
		if(result.hasErrors()){
			List<Rol> roles = rolService.obtenerRoles();
			model.addAttribute("roles",roles);
			return "usuarios/formularioUsuario";
		}
		usuarioService.agregarUsuario(usuario);
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping(value = "/editarUsuario/{soeid}", method = RequestMethod.GET)
	public String editarUsuario(Model model, @PathVariable("soeid") String soeid){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String soeidSesion = auth.getName();
	    soeidSesion.toUpperCase();
	    if(soeidSesion.equals(soeid.toUpperCase())){
	    	return "redirect:/403";
	    }
		Usuario usuario = usuarioService.obtenerUsuario(soeid);
		List<Rol> roles = rolService.obtenerRoles();
		model.addAttribute("edit", true);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles",roles);
		usuario.setPassword("");
		//model.addAttribute("password", usuario.setPassword(""));
		//model.addAttribute("passwordConfirmation", usuario.getPassword());
		return "usuarios/formularioUsuarioModificar";
	}
	
	@RequestMapping(value = "/editarUsuario/{soeid1}", method = RequestMethod.POST)
	public String modificarUsuario(Model model,  @Valid Usuario usuario, BindingResult result){
		usuarioUpdateValidation.validate(usuario, result);
		if(result.hasErrors()){
			List<Rol> roles = rolService.obtenerRoles();
			model.addAttribute("roles",roles);
			return "usuarios/formularioUsuarioModificar";
		}
		usuarioService.modificarUsuario(usuario);
		return "redirect:/listarUsuarios";
	}
	
	@RequestMapping(value = "/eliminarUsuario/{soeid}", method = RequestMethod.GET)
	public String eliminarUsuario(Model model, @PathVariable String soeid){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String soeidSesion = auth.getName();
	    soeidSesion.toUpperCase();
	    if(soeidSesion.equals(soeid.toUpperCase())){
	    	return "redirect:/403";
	    }
		usuarioService.eliminarUsuario(soeid);
		return "redirect:/listarUsuarios";
	}

}
