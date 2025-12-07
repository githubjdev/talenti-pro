package talenti.pro.exceptionhandler;

public class ValidacaoException extends ExceptionTalentiPro {
	private static final long serialVersionUID = 1L;

	public ValidacaoException(String mensagem) {
		super("VALIDACAO", mensagem);
	}
}
