package talenti.pro.interceptor;

import java.time.LocalDateTime;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import talenti.pro.anotations.AuditavelSalvar;
import talenti.pro.filter.UserContext;
import talenti.pro.model.BaseEntity;
import talenti.pro.model.Usuario;
import talenti.pro.service.cadastro.UsuarioService;

@AuditavelSalvar
@Interceptor
public class AuditavelSalvarInterceptor {

	@Inject
	private UsuarioService service;

	@AroundInvoke
	public Object interceptar(InvocationContext ctx) throws Exception {

		Object[] params = ctx.getParameters();

		if (params.length > 0 && params[0] instanceof BaseEntity entity) {

			Usuario user = service.getUserByLogin(UserContext.getUsuario());

			if (entity.getDataCriacao() == null) {
				entity.setDataCriacao(LocalDateTime.now());
			}

			entity.setDataAtualizacao(LocalDateTime.now());
			entity.setAtualizadoPor(user);

			if (entity.getCriadoPor() == null) {
				entity.setCriadoPor(user);
			}

			if (entity.getDataCriacao() == null) {
				entity.setDataCriacao(LocalDateTime.now());
			}

		}

		return ctx.proceed();
	}
}