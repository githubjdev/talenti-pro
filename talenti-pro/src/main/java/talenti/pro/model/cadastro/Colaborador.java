package talenti.pro.model.cadastro;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import talenti.pro.model.BaseEntity;

@Entity
@Table(name = "cad_colaborador")
@SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador", allocationSize = 1, initialValue = 1)
public class Colaborador extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_colaborador")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDate dataNascimento;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String escolaridade;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "cargo_fk"))
	private Cargo cargo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "superior_id", nullable = false, foreignKey = @ForeignKey(name = "superior_fk"))
	private Cargo superior;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "departamento_id", nullable = false, foreignKey = @ForeignKey(name = "departamento_fk"))
	private Departamento departamento;

	@Column(nullable = false)
	private LocalDate dataAdmissao;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "vinculo_empregativo_id", nullable = false, foreignKey = @ForeignKey(name = "vinculo_empregativo_fk"))
	private VinculoEmpregativo vinculoEmpregativo;

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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public VinculoEmpregativo getVinculoEmpregativo() {
		return vinculoEmpregativo;
	}

	public void setVinculoEmpregativo(VinculoEmpregativo vinculoEmpregativo) {
		this.vinculoEmpregativo = vinculoEmpregativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Cargo getSuperior() {
		return superior;
	}

	public void setSuperior(Cargo superior) {
		this.superior = superior;
	}

}
