package com.francode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.francode.exception.ModeloNotFoundException;
import com.francode.model.Producto;
import com.francode.service.IProductoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() throws Exception{
		List<Producto> lista = service.listar();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Producto> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Producto obj = service.listarPorId(id);
		if(obj.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		EntityModel<Producto> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(linkTo.withRel("producto-recurso"));
		return recurso;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Producto obj = service.listarPorId(id);
		if(obj.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Producto producto) throws Exception{
		Producto obj = service.registrar(producto);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdProducto()).toUri();
				
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) throws Exception{
		Producto obj = service.modificar(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Producto obj = service.listarPorId(id);
		if(obj.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: "+ id);
		}
		service.eliminar(id);
		return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
