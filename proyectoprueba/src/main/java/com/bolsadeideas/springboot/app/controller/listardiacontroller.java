package com.bolsadeideas.springboot.app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.entity.Dia;
import com.bolsadeideas.springboot.app.service.interfaces.IlistarDia;

import ch.qos.logback.classic.Logger;

@RestController
public class listardiacontroller {
	//Esta parte es de los controles
	@Autowired
	private IlistarDia servicelistar;
	
	private static final Log Logger = LogFactory.getLog(listardiacontroller.class);
	
	@GetMapping(value = "/listar")
	@Secured("ROLE_ADMIN")  
	public Iterable<Dia> listardia(Authentication authentication){
		
		
		Logger.info("EL ROL ES "+ authentication.getAuthorities());
		return servicelistar.listardia();
	}

}
