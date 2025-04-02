package es.unican.is2.BancoOriginal.Banco;

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
	
}
