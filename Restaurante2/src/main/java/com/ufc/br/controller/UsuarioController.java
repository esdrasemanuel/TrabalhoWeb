package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Carrinho;
import com.ufc.br.model.Item;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.PratoService;
import com.ufc.br.service.UsuarioService;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private PratoService pratoService;
	
	public Carrinho carrinho() {
		return Carrinho.getInstance();
	}
	
	//@RequestMapping("/index")
	//public String olaMundo() {
	//	return "index";
	//}
	
	@RequestMapping("/pessoa/cadastro")
	public ModelAndView retornarForm() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@RequestMapping("/pessoa/carrinho")
	public ModelAndView retornarcarrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@RequestMapping("/pessoa/meuspedidos")
	public ModelAndView retornarpedidos() {
		ModelAndView mv = new ModelAndView("meusPedidos");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@PostMapping("/pessoa/cadastrar")
	public ModelAndView cadastrar(Usuario usuario) {
		userService.salvar(usuario);
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("mensagem", "cadastrado com sucesso!");
	
		return mv;
	}
	
	
	@GetMapping("/index")
	public ModelAndView listarPessoas() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}
	
	
	@RequestMapping("pessoa/addAoCarrinho/{codigo}")
	public ModelAndView adicionarAoCarrinho(@PathVariable Long codigo) {
		
		ModelAndView mv = new ModelAndView("redirect:/pessoa/carrinho");
		mv.addObject(new Prato());
		
		if(existsByIdProduto(codigo)) {
			Prato produto =  pratoService.buscarPorId(codigo);
			carrinho().adicionarProdutoAoCarrinho(produto);
			System.out.println("TOTAL:");
			System.out.println(carrinho().valorTotal());
			}
		return mv;
	}
	

	@RequestMapping("/pessoa/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	
	@RequestMapping("/")
	public ModelAndView pagInicial() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	public boolean existsByIdProduto(Long codigo) {
		return pratoService.existsById(codigo);
}
	

}