package talenti.pro.exceptionhandler;

public abstract class ExceptionTalentiPro extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String codigo;

	public ExceptionTalentiPro(String mensagem) {
		super(mensagem);
	}

	public ExceptionTalentiPro(String codigo, String mensagem) {
		super(mensagem);
		this.codigo = codigo;
	}

	public ExceptionTalentiPro(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public String getCodigo() {
		return codigo;
	}

}
