package com.bolsadeideas.springboot.app.service.clases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.entity.Dia;
import com.bolsadeideas.springboot.app.repository.diarepository;
import com.bolsadeideas.springboot.app.service.interfaces.IlistarDia;

@Service
public class ListarDia implements IlistarDia {

	@Autowired
	private diarepository diarepository;
	
	
	@Override
	public Iterable<Dia> listardia() {
		return   diarepository.findAll();
	}

}
