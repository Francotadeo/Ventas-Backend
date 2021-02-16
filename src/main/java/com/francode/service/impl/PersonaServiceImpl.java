package com.francode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francode.model.Persona;
import com.francode.repo.IGenericRepo;
import com.francode.repo.IPersonaRepo;
import com.francode.service.IPersonaService;

@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer> implements IPersonaService{

	@Autowired
	private IPersonaRepo repo;
	
	@Override
	protected IGenericRepo<Persona, Integer> getRepo(){
		return repo;
	}
}
