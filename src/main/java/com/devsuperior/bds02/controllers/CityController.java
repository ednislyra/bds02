package com.devsuperior.bds02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.service.CityService;
import com.devsuperior.bds02.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping (value = "/cities")
public class CityController {

	@Autowired
	private CityService service;

	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		List<CityDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		try {
		service.delete(id);
		return ResponseEntity.noContent().build();
		}
		catch(ResourceNotFoundException e){
			return ResponseEntity.notFound().build();
		}
		catch(DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().build();
		}
	}


}