package talenti.pro.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "aut_usuario", uniqueConstraints = {
		@UniqueConstraint(name = "uk_aut_usuario_login", columnNames = { "login" }) })
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UsuarioAcesso> acessos = new ArrayList<UsuarioAcesso>();

	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public void addAcesso(Acesso acesso) {
		acessos.add(new UsuarioAcesso(this, acesso));
	}

	public void removeAcesso(Acesso acesso) {
		acessos.removeIf(a -> a.getAcesso().equals(acesso));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UsuarioAcesso> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<UsuarioAcesso> acessos) {
		this.acessos = acessos;
	}

}
