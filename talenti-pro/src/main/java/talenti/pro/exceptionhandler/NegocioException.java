package talenti.pro.exceptionhandler;

public class NegocioException extends ExceptionTalentiPro {
	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		super("NEGOCIO", mensagem);
	}
}
