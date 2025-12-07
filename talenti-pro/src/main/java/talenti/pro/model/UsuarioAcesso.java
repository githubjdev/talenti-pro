package talenti.pro.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
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
@Table(name = "aut_usuario_acesso")
@SequenceGenerator(name = "seq_usuario_acesso", sequenceName = "seq_usuario_acesso", allocationSize = 1, initialValue = 1)
public class UsuarioAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_acesso")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "usuario_fk"))
	private Usuario usuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "acesso_id", nullable = false, foreignKey = @ForeignKey(name = "acesso_fk"))
	private Acesso acesso;

	public UsuarioAcesso(Usuario usuario, Acesso acesso) {
		this.usuario = usuario;
		this.acesso = acesso;
	}

}
