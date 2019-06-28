package com.ufc.br.model;

public class Item {

	private Prato produto;
	  
	private Integer quantidade;

	public Prato getProduto() {
		return produto;
	}

	public void setProduto(Prato produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
