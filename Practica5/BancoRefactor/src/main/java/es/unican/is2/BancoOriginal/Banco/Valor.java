package es.unican.is2.BancoOriginal.Banco;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	} // WMC=1 // CCog=0
	
	public int getNumValores() {
		return numAcciones;
	} // WMC=1 // CCog=0

	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	} // WMC=1 // CCog=0

	public double getCotizacion() {
		return cotizacion;
	} // WMC=1 // CCog=0
	
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	} // WMC=1 // CCog=0

	public String getEntidad() {
		return entidad;
	} // WMC=1 // CCog=0
	
	@Override
	public boolean equals(Object obj) {
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);
	} // WMC=3 // CCog=2

}