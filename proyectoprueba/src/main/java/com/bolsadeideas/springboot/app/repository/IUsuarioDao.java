package com.bolsadeideas.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	
}
