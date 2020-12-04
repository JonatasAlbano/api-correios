package com.joalbano.correiosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joalbano.correiosapi.services.CorreiosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Delivery API")
@RestController
@RequestMapping("/delivery")
public class CorreiosController {
	
	@Autowired
	private CorreiosService correiosService;
	
	@ApiOperation(value="Returns the maximum delivery date for a commodity")
	@PostMapping("calcMaxDate")
	public ResponseEntity<?> calcMaxDate(@RequestParam("CdServico") String cdServico, 
			@RequestParam("sCepOrigem") String sCepOrigem,
			@RequestParam("sCepDestino") String sCepDestino) {
		
		if((cdServico != null && sCepOrigem != null && sCepDestino != null)
				&& (cdServico.matches("[0-9]+") && sCepOrigem.matches("[0-9]+") && sCepDestino.matches("[0-9]+"))) {
			return new ResponseEntity<>(correiosService.getMaxDateByCorreios(cdServico, sCepOrigem, sCepDestino), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
