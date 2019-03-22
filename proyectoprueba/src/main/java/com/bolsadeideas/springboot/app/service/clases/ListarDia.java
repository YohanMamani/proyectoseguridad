package com.bolsadeideas.springboot.app.service.clases;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.controller.listardiacontroller;
import com.bolsadeideas.springboot.app.entity.Dia;
import com.bolsadeideas.springboot.app.repository.diarepository;
import com.bolsadeideas.springboot.app.service.interfaces.IlistarDia;

@Service
public class ListarDia implements IlistarDia {
	
	//Esta parte es de los Servicios 
	
	@Autowired
	private diarepository diarepository;
	private static final Log Logger = LogFactory.getLog(ListarDia.class);

	
	@Override
	public Iterable<Dia> listardia() {
		Logger.info("NOS ENCONTRAMOS EN EL SERVICE LISTADIA");		
		return   diarepository.findAll();
	}


	@Override
	public Dia buscarpordia(Long id) {
		return diarepository.findById(id).orElse(null);
	}

}
