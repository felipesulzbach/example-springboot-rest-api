package br.com.restapi.exemplorestapi.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ALUNO")
@PrimaryKeyJoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
@DynamicUpdate
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_TURMA", referencedColumnName = "id")
	private Turma turma;

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(getClass().getSimpleName());
		strb.append(" [");
		strb.append("ID: ");
		strb.append(getId());
		strb.append(", TURMA: ");
		strb.append(getTurma());
		strb.append("]");
		return strb.toString();
	}

	public static synchronized Aluno create() {
		return new Aluno();
	}

	public Aluno withTurma(final Turma turma) {
		this.turma = turma;
		return this;
	}
}
