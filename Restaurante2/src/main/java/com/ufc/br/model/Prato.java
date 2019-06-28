package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Proxy;



@Entity
@Proxy(lazy=false)
public class Prato {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long codigo;
	
	private String nome;
	private double preco;
	private String descricao;
	private int quantidade;
	
	@ManyToMany(mappedBy = "produtos")
	private List<Usuario> pessoa;
	
	public Prato() { }
	
	public Prato(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		this.pessoa = new ArrayList<>();
}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Usuario> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<Usuario> pessoa) {
		this.pessoa = pessoa;
	}

	public void setPessoas(List<Usuario> pessoas) {
		this.pessoa = pessoas;
	}
	
	public boolean addPessoa(Usuario pessoa) {
		this.pessoa.add(pessoa);
		return pessoa.getProdutos().add(this);
	}
	
	public boolean removerPessoa(Usuario pessoa) {
		this.pessoa.remove(pessoa);
		return pessoa.getProdutos().remove(this);
}
	



}
