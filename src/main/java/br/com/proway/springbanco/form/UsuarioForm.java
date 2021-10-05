//package br.com.proway.springbanco.form;
//
//import java.util.InputMismatchException;
//
//import br.com.proway.primeiroprojeto.projeto1.model.Usuario;
//
//public class UsuarioForm {
//
//	private Long id;
//
//	private String user;
//
//	private String senha;
//
//	private String nome;
//
//	private String cpf;
//
//	private String tipo;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getUser() {
//		return user;
//	}
//
//	public void setUser(String user) {
//		this.user = user;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getCpf() {
//		return cpf;
//	}
//
//	public void setCpf(String cpf) {
//		this.cpf = cpf;
//	}
//
//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}
//
//	public Usuario converter() throws InputMismatchException {
//		if (this.id == null)
//			throw new InputMismatchException("Erro ao inserir usuário. (id = " + this.id + ")");
//
//		if (this.senha == null || this.senha.trim().equals(""))
//			throw new InputMismatchException("Erro ao inserir usuário. (senha = " + this.senha + ")");
//
//		if (this.nome == null || this.nome.trim().equals(""))
//			throw new InputMismatchException("Erro ao inserir usuário. (nome = " + this.nome + ")");
//
//		if (this.cpf == null || this.cpf.trim().equals(""))
//			throw new InputMismatchException("Erro ao inserir usuário. (cpf = " + this.cpf + ")");
//
//		if (this.tipo == null || this.tipo.trim().equals(""))
//			throw new InputMismatchException("Erro ao inserir usuário. (tipo = " + this.tipo + ")");
//
//		return new Usuario(this.id, this.user, this.senha, this.nome, this.cpf, this.tipo);
//
//	}
//}
