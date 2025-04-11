package es.unican.is2.BancoOriginal.Banco;

public class Direccion {
	private String calle;
	private String zip;
	private String localidad;
	
	public Direccion(String calle, String zip, String localidad) {
		this.setCalle(calle);
		this.setZip(zip);
		this.setLocalidad(localidad);
	} // WMC=1 // CCog=0

	public String getCalle() {
		return calle;
	} // WMC=1 // CCog=0

	public void setCalle(String calle) {
		this.calle = calle;
	} // WMC=1 // CCog=0

	public String getZip() {
		return zip;
	} // WMC=1 // CCog=0

	public void setZip(String zip) {
		this.zip = zip;
	} // WMC=1 // CCog=0

	public String getLocalidad() {
		return localidad;
	} // WMC=1 // CCog=0

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	} // WMC=1 // CCog=0
}
