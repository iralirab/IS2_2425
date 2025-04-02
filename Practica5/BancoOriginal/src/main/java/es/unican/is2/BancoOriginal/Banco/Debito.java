package es.unican.is2.BancoOriginal.Banco;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	} // WMC=1 // CCog=0

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	} // WMC=2 // CCog=1
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	} // WMC=2 // CCog=1
	
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	} // WMC=1 // CCog=0
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	} // WMC=1 // CCog=0
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	} // WMC=1 // CCog=0

}