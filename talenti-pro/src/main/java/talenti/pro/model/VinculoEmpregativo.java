package talenti.pro.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "vinculo_empregativo")
@SequenceGenerator(name = "seq_vinculo_empregativo", sequenceName = "seq_vinculo_empregativo", allocationSize = 1, initialValue = 1)
public class VinculoEmpregativo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cargo")
	private Long id;

	@Column(nullable = false)
	private String nome;

}
