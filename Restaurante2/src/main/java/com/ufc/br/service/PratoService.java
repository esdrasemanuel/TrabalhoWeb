package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class PratoService {
	
	@Autowired
	private PratoRepository pratoRepository;
	
	public void salvarPrato(Prato prato, MultipartFile imagem) {
		
		String caminho = "images/" + prato.getNome() + ".png";
		
		AulaFileUtils.salvarImagem(caminho,imagem);

		pratoRepository.save(prato);
	}
	
	public List<Prato> listarPratos(){
		return pratoRepository.findAll();
	}

	public void excluirPrato(Long codigo) {
		pratoRepository.deleteById(codigo);
		
	}
	
	public Prato buscarPorId(Long codigo) {
		return pratoRepository.getOne(codigo);
	}
	
	public boolean existsById(Long codigo) {
		return pratoRepository.existsById(codigo);
}


}
