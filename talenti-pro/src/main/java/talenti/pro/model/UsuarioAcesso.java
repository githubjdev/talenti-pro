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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	    name = "aut_usuario_acesso",
	    uniqueConstraints = {
	        @UniqueConstraint(
	            name = "uk_aut_usuario_acesso_acesso_usuario",
	            columnNames = { "acesso_id", "usuario_id" }
	        )
	    }
	)
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
	
	public UsuarioAcesso() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Acesso getAcesso() {
		return acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

}
