package es.unican.is2.BancoOriginal.Banco;

import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	private List<Movimiento> Movimientos;

	public CuentaAhorro(String numCuenta) throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
	} // WMC=1 // CCog=0

	public void ingresar(double monto) throws datoErroneoException {
		this.ingresar("Ingreso en efectivo", monto);
	} // WMC=1 // CCog=0

	public void retirar(double monto)
			throws saldoInsuficienteException, datoErroneoException {
		this.retirar("Retirada de efectivo", monto);
	} // WMC=1 // CCog=0

	public void ingresar(String concepto, double monto)
			throws datoErroneoException {
		if (monto <= 0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		
		this.addMovimiento(new Movimiento(concepto, monto));
	} // WMC=2 // CCog=1

	public void retirar(String concepto, double monto)
			throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < monto) // WMC+1 // CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (monto <= 0) // WMC+1 // CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		this.addMovimiento(new Movimiento(concepto, -monto));
	} // WMC=3 // CCog=2

	@Override
	public double getSaldo() {
		double total = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // WMC+1 // CCog+1
			Movimiento movimiento = Movimientos.get(i);
			total += movimiento.getImporte();
		}
		return total;
	} // WMC=2 // CCog=1
	
	public void addMovimiento(Movimiento movimiento) {
		Movimientos.add(movimiento);
	} // WMC=1 // CCog=0

	public List<Movimiento> getMovimientos() {
		return Movimientos;
	} // WMC=1 // CCog=0
}