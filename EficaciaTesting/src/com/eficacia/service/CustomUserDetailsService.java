package com.eficacia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eficacia.custommodel.CustomUsuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	

	@Autowired
	private com.eficacia.service.UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String soeid) throws UsernameNotFoundException {
		com.eficacia.model.Usuario domainUser = usuarioService.obtenerUsuario(soeid);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		
		if(domainUser != null){
		return new CustomUsuario(domainUser.getSoeid(), domainUser.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, domainUser.isUsuarioNoBloqueado() , domainUser.getRol().getNombre(),
				getAuthorities(domainUser.getRol().getId(), domainUser.isUsuarioCredencialesNoExpiradas(), domainUser),
				domainUser.getNombre(), domainUser.getApellidoPaterno());
		}else{
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

	}

	public Collection<? extends GrantedAuthority> getAuthorities(Integer role, Boolean credentialsNonExpired, com.eficacia.model.Usuario domainUser) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role, credentialsNonExpired, domainUser));
		return authList;
	}

	public List<String> getRoles(Integer role, boolean NonExpired, com.eficacia.model.Usuario domainUser) {

		List<String> roles = new ArrayList<String>();
		if (NonExpired) {
			if(passwordEncoder.matches(domainUser.getSoeid(), domainUser.getPassword())){
				roles.add("ROLE_CHANGEPASSWORD");
			}else{
				if (role.intValue() == 1) {
					roles.add("ROLE_ADMIN");
				} else if (role.intValue() == 2) {
					roles.add("ROLE_EJECUTIVO");
				}
			}
		}else{
			roles.add("ROLE_EXPIREDCREDENTIALS");
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
