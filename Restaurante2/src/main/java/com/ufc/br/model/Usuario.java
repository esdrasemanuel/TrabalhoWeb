package com.ufc.br.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
	        name = "pessoas_roles", 
	        joinColumns = @JoinColumn(
	          name = "pessoa_codigo", referencedColumnName = "codigo"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_codigo", referencedColumnName = "papel")) 
	private List<Role> roles;
	
	public List<Prato> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Prato> produtos) {
		this.produtos = produtos;
	}
	@ManyToMany
	(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable( 
	        name = "pessoas_produtos", 
	        joinColumns = @JoinColumn(
	          name = "pessoa_id", referencedColumnName = "codigo"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "produto_id", referencedColumnName = "codigo")) 
	private List<Prato> produtos;
	
	
	@NotBlank(message = "Preencha seu nome")
	private String nome;
	
	@NotBlank(message = "Preencha seu CPF")
	private String Cpf;
	
	@NotNull(message = "Data não pode ser nula")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@NotBlank(message = "Preencha seu endereco")
	private String endereco;
	
	private String senha;
	
	@NotBlank(message = "Preencha seu Email")
	private String email;

	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	

}
