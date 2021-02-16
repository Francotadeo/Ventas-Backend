package com.francode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francode.model.Venta;
import com.francode.repo.IGenericRepo;
import com.francode.repo.IVentaRepo;
import com.francode.service.IVentaService;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService{

	@Autowired
	private IVentaRepo repo;
	
	@Override
	protected IGenericRepo<Venta, Integer> getRepo(){
		return repo;
	}
	
	@Override
	public Venta registrarTransaccional(Venta venta) throws Exception {
		venta.getDetalleVenta().forEach(det -> det.setVenta(venta));
		return repo.save(venta);
	}
}
