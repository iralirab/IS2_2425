package es.unican.is2.BancoOriginal.Banco;

public class DatosCliente {
	private String titular, telefono, dni;
	
	public DatosCliente(String titular, String telefono, String dni) {
		this.setTitular(titular);
		this.setTelefono(telefono);
		this.setDni(dni);
	} // WMC=1 // CCog=0

	public String getTitular() {
		return titular;
	} // WMC=1 // CCog=0

	public void setTitular(String titular) {
		this.titular = titular;
	} // WMC=1 // CCog=0

	public String getTelefono() {
		return telefono;
	} // WMC=1 // CCog=0

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	} // WMC=1 // CCog=0

	public String getDni() {
		return dni;
	} // WMC=1 // CCog=0

	public void setDni(String dni) {
		this.dni = dni;
	} // WMC=1 // CCog=0
}
