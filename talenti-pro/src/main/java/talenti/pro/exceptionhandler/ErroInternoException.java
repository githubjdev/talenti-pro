package talenti.pro.exceptionhandler;

public class ErroInternoException extends ExceptionTalentiPro {
	private static final long serialVersionUID = 1L;

	public ErroInternoException(String mensagem, Throwable causa) {
		super("ERRO_INTERNO:" + mensagem, causa);
	}
}
