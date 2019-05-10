package br.com.restapi.exemplorestapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TURMA")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_CURSO", referencedColumnName = "id")
	private Curso curso;

	@Column(name = "DATA_INICIO")
	private LocalDateTime dataInicio;

	@Column(name = "DATA_FIM")
	private LocalDateTime dataFim;

	@Column(name = "DATA_CADASTRO")
	private LocalDateTime dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
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
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Turma other = (Turma) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		strb.append(", CURSO: ");
		strb.append(getCurso());
		strb.append(", DATAINICIO: ");
		strb.append(getDataInicio());
		strb.append(", DATAFIM: ");
		strb.append(getDataFim());
		strb.append(", DATACADASTRO: ");
		strb.append(getDataCadastro());
		strb.append("]");
		return strb.toString();
	}

	public static synchronized Turma create() {
		return new Turma();
	}

	public Turma withId(final Long id) {
		this.id = id;
		return this;
	}

	public Turma withCurso(final Curso curso) {
		this.curso = curso;
		return this;
	}

	public Turma withDataInicio(final LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
		return this;
	}

	public Turma withDataFim(final LocalDateTime dataFim) {
		this.dataFim = dataFim;
		return this;
	}

	public Turma withDataCadastro(final LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
		return this;
	}
}
