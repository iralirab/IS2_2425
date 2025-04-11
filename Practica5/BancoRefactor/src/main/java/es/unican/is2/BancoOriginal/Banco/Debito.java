package es.unican.is2.BancoOriginal.Banco;

public class Debito extends Tarjeta {
	private static final double LIMITE_DEBITO = 1000;
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = LIMITE_DEBITO;
	} // WMC=1 // CCog=0

	@Override
	public void retirar(double monto) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<monto) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");

		this.cuentaAsociada.retirar("Retirada en cajero", monto);
		saldoDiarioDisponible-=monto;
	} // WMC=2 // CCog=1
	
	@Override
	public void pagoEnEstablecimiento(String datos, double monto)
			throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<monto) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");

		this.cuentaAsociada.retirar("Compra en : " + datos, monto);
		saldoDiarioDisponible-=monto;
	} // WMC=2 // CCog=1
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = LIMITE_DEBITO;
	} // WMC=1 // CCog=0
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	} // WMC=1 // CCog=0
}