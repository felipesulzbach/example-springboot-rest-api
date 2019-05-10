package br.com.restapi.exemplorestapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "NOME")
	private String nome;

	@Column(name = "NUMERO_CPF")
	private String numeroCpf;

	@Column(name = "NUMERO_CELULAR")
	private String numeroCelular;

	@Column(name = "CIDADE")
	private String cidade;

	@Column(name = "NUMERO_CEP")
	private String numeroCep;

	@Column(name = "ENDERECO")
	private String endereco;

	@Column(name = "DATA_CADASTRO")
	private LocalDateTime dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumeroCep() {
		return numeroCep;
	}

	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(getClass().getSimpleName());
		strb.append(" [");
		strb.append("ID: ");
		strb.append(getId());
		strb.append(", NOME: ");
		strb.append(getNome());
		strb.append(", NUMEROCPF: ");
		strb.append(getNumeroCpf());
		strb.append(", NUMEROCELULAR: ");
		strb.append(getNumeroCelular());
		strb.append(", CIDADE: ");
		strb.append(getCidade());
		strb.append(", NUMEROCEP: ");
		strb.append(getNumeroCep());
		strb.append(", ENDERECO: ");
		strb.append(getEndereco());
		strb.append(", dataCadastro: ");
		strb.append(getDataCadastro());
		strb.append("]");
		return strb.toString();
	}

	public static synchronized Pessoa create() {
		return new Pessoa();
	}

	public Pessoa withId(final Long id) {
		this.id = id;
		return this;
	}

	public Pessoa withNome(final String nome) {
		this.nome = nome;
		return this;
	}

	public Pessoa withNumeroCpf(final String numeroCpf) {
		this.numeroCpf = numeroCpf;
		return this;
	}

	public Pessoa withNumeroCelular(final String numeroCelular) {
		this.numeroCelular = numeroCelular;
		return this;
	}

	public Pessoa withCidade(final String cidade) {
		this.cidade = cidade;
		return this;
	}

	public Pessoa withNumeroCep(final String numeroCep) {
		this.numeroCep = numeroCep;
		return this;
	}

	public Pessoa withEndereco(final String endereco) {
		this.endereco = endereco;
		return this;
	}

	public Pessoa withDataCadastro(final LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
		return this;
	}
}
