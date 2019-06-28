package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private static Carrinho carrinho;
	
	private List<Prato> pratos;
	
	private Carrinho() {
		pratos = new ArrayList<Prato>();
	}
	

	public static Carrinho getInstance() {
		if(carrinho == null) {
			carrinho = new Carrinho();
		}
		return carrinho;
	}
	
	public void adicionarProdutoAoCarrinho(Prato produto) {
		pratos.add(produto);
	}
	
	public void removerProdutoDoCarrinho(Prato produto) {
		for (int i = 0; i < pratos.size(); i++) {
			if(pratos.get(i).getCodigo() == produto.getCodigo()) {
				pratos.remove(i);
				break;
			}
		}
	}
	
	public float valorTotal() {
		float total = 0;
		
		for (Prato produto : pratos) {
			total += produto.getPreco();
		}
		return total;
	}
	
	public List<Prato> listarProdutos() {
		return pratos;
	}

	public void esvaziar() {
		pratos.clear();
}
	
}
