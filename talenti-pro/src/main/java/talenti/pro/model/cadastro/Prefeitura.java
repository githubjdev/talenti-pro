package talenti.pro.model.cadastro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import talenti.pro.model.BaseEntity;

@Entity
@Table(name = "cad_prefeitura")
@SequenceGenerator(name = "seq_prefeitura", sequenceName = "seq_prefeitura", allocationSize = 1, initialValue = 1)
public class Prefeitura extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prefeitura")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = true)
	private String schema_db_name;

	public void setSchema_db_name(String schema_db_name) {
		this.schema_db_name = schema_db_name;
	}

	public String getSchema_db_name() {
		return schema_db_name;
	}

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

}
