package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.PratoService;

@Controller
public class AdmController {
	
	@Autowired
	private PratoService pratoService;
	

	@RequestMapping("/adm/cadprato")
	public ModelAndView formPrato() {
		ModelAndView mv = new ModelAndView("cadprato");
		mv.addObject(new Prato());
		return mv;
	}
	
	@PostMapping("/adm/addprato")
	public ModelAndView addPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("redirect:/adm/cadprato");
		
		if(result.hasErrors()) {
			return mv; //retorna pra página de form e nem tenta salvar
		}
		
		pratoService.salvarPrato(prato, imagem);
		
		mv.addObject("mensagem", "Pessoa Cadastrada com sucesso");
		
		return mv;
		
	}
	
	@GetMapping("/adm/listar")
	public ModelAndView listar() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/adm/excluir/{codigo}")
	public ModelAndView excluir(@PathVariable Long codigo) {
		pratoService.excluirPrato(codigo);
		ModelAndView mv = new ModelAndView("redirect:/index");
		return mv;
		
	}
	
	@RequestMapping("/adm/atualizar/{codigo}")
	public ModelAndView atualizar(@PathVariable Long codigo) {
		Prato prato = pratoService.buscarPorId(codigo);
		ModelAndView mv = new ModelAndView("cadprato");
		mv.addObject("prato", prato);
		return mv;
	}
	
			
			
			

			

}
