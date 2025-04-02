package es.unican.is2.BancoOriginal.Banco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	} // WMC=1 // CCog=0

	public void ingresar(double x) throws datoErroneoException {
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.Movimientos.add(m);
	} // WMC=1 // CCog=0

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x <= 0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.Movimientos.add(m);
	} // WMC=3 // CCog=2

	public void ingresar(String concepto, double x) throws datoErroneoException {
		if (x <= 0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.Movimientos.add(m);
	} // WMC=2 // CCog=1

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.Movimientos.add(m);
	} // WMC=3 // CCog=2

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // WMC+1 // CCog+1
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	} // WMC=2 // CCog=1

	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	} // WMC=1 // CCog=0

	public List<Movimiento> getMovimientos() {
		return Movimientos;
	} // WMC=1 // CCog=0

	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	} // WMC=1 // CCog=0

	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	} // WMC=1 // CCog=0

	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	} // WMC=1 // CCog=0

	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	} // WMC=1 // CCog=0

	public double getLimiteDebito() {
		return limiteDebito;
	} // WMC=1 // CCog=0

}