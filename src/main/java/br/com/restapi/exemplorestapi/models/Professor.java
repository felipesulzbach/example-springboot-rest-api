package br.com.restapi.exemplorestapi.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "PROFESSOR")
@PrimaryKeyJoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
@DynamicUpdate
public class Professor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_CURSO", referencedColumnName = "id")
	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
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
		strb.append("]");
		return strb.toString();
	}

	public static synchronized Professor create() {
		return new Professor();
	}

	public Professor withCurso(final Curso curso) {
		this.curso = curso;
		return this;
	}
}
