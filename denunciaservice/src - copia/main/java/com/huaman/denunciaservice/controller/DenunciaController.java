package com.huaman.denunciaservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import com.huaman.denunciaservice.converter.DenunciaConverter;
import com.huaman.denunciaservice.dto.DenunciaDTO;
import com.huaman.denunciaservice.entity.Denuncia;
import com.huaman.denunciaservice.service.DenunciaServices;



@RestController
@RequestMapping("/v1/denuncias")
public class DenunciaController {
	@Autowired
	private DenunciaServices service;
	@Autowired
	private DenunciaConverter converterInf;
	@GetMapping()
	public ResponseEntity<List<DenunciaDTO>> getAll(
			@RequestParam(value="dni",required=false,defaultValue="") String dni,
			@RequestParam(value="offset",required=false,defaultValue="0") int pageNumber,
			@RequestParam(value="limit",required=false,defaultValue="5") int pageSize
			){
		Pageable page=PageRequest.of(pageNumber,pageSize);
		List<Denuncia> denuncias;
		if(dni == null) {
			denuncias=service.findAll(page);
		}else {
			denuncias=service.findByDni(dni, page);
		}
		if(denuncias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<DenunciaDTO> denunciaDTO = converterInf.fromEntity(denuncias);
		return ResponseEntity.ok(denunciaDTO);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> getById(@PathVariable("id") int id) {
		Denuncia denuncia = service.findById(id);
		DenunciaDTO denunciaDTO=converterInf.fromEntity(denuncia);
		return ResponseEntity.status(HttpStatus.OK).body(denunciaDTO);
	}
	@GetMapping(value="/{dni}")
	public ResponseEntity<DenunciaDTO> getByDni(@PathVariable("dni") String dni) {
		Denuncia denuncia = service.findByDni(dni);
		DenunciaDTO denunciaDTO=converterInf.fromEntity(denuncia);
		return ResponseEntity.status(HttpStatus.OK).body(denunciaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Denuncia> create(@RequestBody Denuncia denuncia) {
		Denuncia denunciaDb=service.create(denuncia);
		return ResponseEntity.status(HttpStatus.CREATED).body(denunciaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
