package talenti.pro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "versionador_banco")
@SequenceGenerator(name = "seq_versionador_banco", sequenceName = "seq_versionador_banco", allocationSize = 1, initialValue = 1)
public class VersionadorBanco extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_versionador_banco")
	private Long id;

	@Column(nullable = false)
	private String arquivoSql;

	public void setArquivoSql(String arquivoSql) {
		this.arquivoSql = arquivoSql;
	}

	public String getArquivoSql() {
		return arquivoSql;
	}

	public VersionadorBanco(String arquivoSql) {
		super();
		this.arquivoSql = arquivoSql;
	}

}
