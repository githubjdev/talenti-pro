package talenti.pro.exceptionhandler;

public class AcessoNegadoException extends ExceptionTalentiPro {
	private static final long serialVersionUID = 1L;

	public AcessoNegadoException(String mensagem) {
		super("ACESSO_NEGADO", mensagem);
	}
}
