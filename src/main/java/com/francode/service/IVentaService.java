package com.francode.service;

import com.francode.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer>{

	Venta registrarTransaccional(Venta venta) throws Exception;
}
