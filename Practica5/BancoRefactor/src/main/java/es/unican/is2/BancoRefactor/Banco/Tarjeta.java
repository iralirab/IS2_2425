package es.unican.is2.BancoRefactor.Banco;

import java.time.LocalDate;

public abstract class Tarjeta {
	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;
	private LocalDate caducidad;

	protected Tarjeta(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada) {
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
	} // WMC=1 // CCog=0

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param monto Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirar(double monto)
			throws SaldoInsuficienteException, DatoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param monto Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double monto)
			throws SaldoInsuficienteException, DatoErroneoException;
	
	/**
	 * Retorna la fecha de caducidad.
	 * @return La fecha de caducidad.
	 */
	public LocalDate getCaducidad() {
		return caducidad;
	} // WMC=1 // CCog=0
	
	/**
	 * Modifica la fecha de caducidad.
	 * @param caducidad La nueva fecha de caducidad.
	 */
	public void setCaducidad(LocalDate caducidad) {
		this.caducidad = caducidad;
	} // WMC=1 // CCog=0
	
}