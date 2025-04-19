package es.unican.is2.BancoRefactor.Banco;

public abstract class Cuenta {
	
	private String numCuenta;
	
	protected Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	} // WMC=1 // CCog=0
	
	public String getNumCuenta() {
		return numCuenta;
	} // WMC=1 // CCog=0

	public abstract double getSaldo();	
}
