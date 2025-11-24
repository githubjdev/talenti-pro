package talenti.pro.model;

import java.io.Serializable;

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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "departamento")
@SequenceGenerator(name = "seq_departamento", sequenceName = "seq_departamento", allocationSize = 1, initialValue = 1)
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_departamento")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "gestor_id", nullable = false, foreignKey = @ForeignKey(name = "gestor_id"))
	private Gestor gestor;

}
