package es.unican.is2.BancoRefactor.Banco;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	} // WMC=1 // CCog=0
	
	public List<Valor> getValores() {
		return valores;
	} // WMC=1 // CCog=0
	
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) { // WMC+1 // CCog+1
			if (v.getEntidad().equals(valor.getEntidad())) // WMC+1 // CCog+2
				return false;
		}
		valores.add(valor);
		return true;
	} // WMC=3 // CCog=3
	
	@Override
	public double getSaldo() {
		double total = 0;
		for (Valor v: this.getValores()) { // WMC+1 // CCog+1
			total += v.getCotizacion()*v.getNumValores();
		}
		return total;
	} // WMC=2 // CCog=1
}
