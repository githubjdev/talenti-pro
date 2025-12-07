package talenti.pro.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(
	    name = "aut_acesso",
	    uniqueConstraints = {
	        @UniqueConstraint(
	            name = "uk_aut_acesso_acesso",
	            columnNames = { "acesso" }
	        )
	    }
	)
@SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", allocationSize = 1, initialValue = 1)
public class Acesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
	private Long id;

	@Column(nullable = false, unique = true)
	private String acesso;

}
