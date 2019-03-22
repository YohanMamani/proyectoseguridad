package com.bolsadeideas.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.entity.Dia;

@Repository
public interface diarepository extends CrudRepository<Dia, Long>{

}
