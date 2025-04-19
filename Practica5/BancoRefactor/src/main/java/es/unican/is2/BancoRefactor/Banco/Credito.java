package es.unican.is2.BancoRefactor.Banco;

import java.util.List;

public class Credito extends Tarjeta {
	private static final double COMISION_CREDITO = 0.05;
	
	private double saldo;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double saldo) {
		super(numero, titular, cvc, cuentaAsociada);
		this.saldo = saldo;
	} // WMC=1 // CCog=0

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param monto Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double monto) throws SaldoInsuficienteException, DatoErroneoException {
		if (monto<0) // WMC+1 // CCog+1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		if (getGastosAcumulados() + monto > saldo) // WMC+1 // CCog+1
			throw new SaldoInsuficienteException("Credito insuficiente");
		
		monto += monto * COMISION_CREDITO; // Comision por operacion con tarjetas credito
		MovimientosMensuales.add(new Movimiento("Retirada en cajero", -monto));
	} // WMC=3 // CCog=2

	@Override
	public void pagoEnEstablecimiento(String datos, double monto)
			throws SaldoInsuficienteException, DatoErroneoException {
		if (monto<0) // WMC+1 // CCog+1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
		if (getGastosAcumulados() + monto > saldo) // WMC+1 // CCog+1
			throw new SaldoInsuficienteException("Saldo insuficiente");

		MovimientosMensuales.add(new Movimiento("Compra a credito en: " + datos, -monto));
	} // WMC=3 // CCog=2
	
    private double getGastosAcumulados() {
		double gastos = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // WMC+1 // CCog+1
			Movimiento movimiento = MovimientosMensuales.get(i);
			gastos += movimiento.getImporte();
		}
		return gastos;
	} // WMC=2 // CCog=1
	
	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {
		double gastos = this.getGastosAcumulados();
		Movimiento movimiento = new Movimiento("Liquidacion de operaciones tarjeta credito", -gastos);
	
		if (gastos != 0) // WMC+1 // CCog+1
			cuentaAsociada.addMovimiento(movimiento);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	} // WMC=2 // CCog=1

	public List<Movimiento> getMovimientosMensuales() {
		return MovimientosMensuales;
	} // WMC=1 // CCog=0
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	} // WMC=1 // CCog=0
	
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	} // WMC=1 // CCog=0
}