package es.unican.is2.BancoOriginal.Banco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
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
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero");
		x += x * 0.05; // Comision por operacion con tarjetas credito
		m.setI(-x);
		
		if (getGastosAcumulados()+x > credito) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Credito insuficiente");
		else { // CCog+1
			MovimientosMensuales.add(m);
		}
	} // WMC=3 // CCog=3

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > credito) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a credito en: " + datos);
		m.setI(-x);
		MovimientosMensuales.add(m);
	} // WMC=3 // CCog=2
	
    private double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // WMC+1 // CCog+1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	} // WMC=2 // CCog=1
	
	
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	} // WMC=1 // CCog=0

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { // WMC+1 // CCog+1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(-r);
	
		if (r != 0) // WMC+1 // CCog+1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	} // WMC=3 // CCog=2

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