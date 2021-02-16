package com.francode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francode.model.Producto;
import com.francode.repo.IGenericRepo;
import com.francode.repo.IProductoRepo;
import com.francode.service.IProductoService;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService{

	@Autowired
	private IProductoRepo repo;
	
	@Override
	protected IGenericRepo<Producto, Integer> getRepo(){
		return repo;
	}
}
