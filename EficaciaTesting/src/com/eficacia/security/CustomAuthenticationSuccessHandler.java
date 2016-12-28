package com.eficacia.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.eficacia.service.UsuarioService;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
        usuarioService.resetearIntentosFallidosLogin(request.getParameter("soeid"));
    }
	
	 protected void handle(HttpServletRequest request, 
		      HttpServletResponse response, Authentication authentication) throws IOException {
		        String targetUrl = determineTargetUrl(authentication);
		 
		        if (response.isCommitted()) {		       
		            return;
		        }
		        redirectStrategy.sendRedirect(request, response, targetUrl);
	 }
	 
	 protected String determineTargetUrl(Authentication authentication) {
		    
	        boolean isUser = false; 
	        //boolean isAdmin = false;
	        boolean isCredentialsExpired = false;
	        boolean isChangePassword = false;
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        for (GrantedAuthority grantedAuthority : authorities) {
	            if(grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ROLE_EJECUTIVO")){
	            	isUser = true;
	                break;
	            }else if(grantedAuthority.getAuthority().equals("ROLE_EXPIREDCREDENTIALS")){
	            	isCredentialsExpired = true;
	            }else if(grantedAuthority.getAuthority().equals("ROLE_CHANGEPASSWORD")){
	            	isChangePassword = true;
	            }
	        }
	 
	        if (isUser) {
	            return "/inicio";
	        }else if(isCredentialsExpired){
	        	return "/credencialesExpiradas";
	        }else if(isChangePassword){
	        	return "/editarContrasena";
	        }
	        else {
	            throw new IllegalStateException();
	        }
	    }
	 
	 protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            return;
	        }
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
	
}
