package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
public class PratoController {
	
	@Autowired
	PratoService pratoService;
	
	public boolean existsByIdProduto(Long idProduto) {
		return pratoService.existsById(idProduto);
	}

	public Prato buscaPorId(Long codigo) {
		return pratoService.buscarPorId(codigo);
	}

}
