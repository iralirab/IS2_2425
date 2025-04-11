package es.unican.is2.BancoOriginal.Banco;

import java.util.List;

public class Credito extends Tarjeta {
	private static final double COMISION_CREDITO = 0.05;
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) {
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	} // WMC=1 // CCog=0

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param monto Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double monto) throws saldoInsuficienteException, datoErroneoException {
		if (monto<0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getGastosAcumulados() + monto > credito) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Credito insuficiente");
		
		monto += monto * COMISION_CREDITO; // Comision por operacion con tarjetas credito
		MovimientosMensuales.add(new Movimiento("Retirada en cajero", -monto));
	} // WMC=3 // CCog=2

	@Override
	public void pagoEnEstablecimiento(String datos, double monto)
			throws saldoInsuficienteException, datoErroneoException {
		if (monto<0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getGastosAcumulados() + monto > credito) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");

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