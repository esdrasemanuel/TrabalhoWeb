package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.repository.UsuarioRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository pessoaRepository;

	
	
	public void salvar(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		pessoaRepository.save(usuario);
	}
	
	
	public List<Usuario> listarPessoas(){
		return pessoaRepository.findAll();
	}

	public void excluir(Long codigo) {
		pessoaRepository.deleteById(codigo);
		
	}
	
	public Usuario buscarPorId(Long codigo) {
		return pessoaRepository.getOne(codigo);
	}

}