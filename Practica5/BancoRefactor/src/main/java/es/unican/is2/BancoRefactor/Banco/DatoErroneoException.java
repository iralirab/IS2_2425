package es.unican.is2.BancoRefactor.Banco;

@SuppressWarnings("serial")
public class DatoErroneoException extends RuntimeException {
	
	public DatoErroneoException (String mensaje) {
		super(mensaje);
	}

}
