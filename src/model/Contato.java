package model;

public class Contato {
	private int codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String celular;
	private String email;
	private TipoEnum tipo;
	private String observacao;
	
	public Contato() {
	
	}
	
	public Contato(int codigo, String nome, String endereco, String telefone, String celular, String email,
			TipoEnum tipo, String observacao) {
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.tipo = tipo;
		this.observacao = observacao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public TipoEnum getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	
}
