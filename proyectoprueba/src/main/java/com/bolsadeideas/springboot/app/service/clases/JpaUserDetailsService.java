package com.bolsadeideas.springboot.app.service.clases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.entity.Usuario;
import com.bolsadeideas.springboot.app.entity.Role;
import com.bolsadeideas.springboot.app.repository.IUsuarioDao;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	//Esta parte es donde se crea los usuarios y para esto tenemos este servicio que implementa UserDetailsService

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 Usuario usuario = usuarioDao.findByUsername(username);
	        if(usuario == null) {
	        	throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
	        }
	        
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        
	        
	        for(Role role: usuario.getRoles()) {
	        	authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
	        }
	        
	        if(authorities.isEmpty()) {
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
	        }
	        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
