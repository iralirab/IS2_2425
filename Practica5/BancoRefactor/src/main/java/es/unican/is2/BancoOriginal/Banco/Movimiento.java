package es.unican.is2.BancoOriginal.Banco;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;
	
	public Movimiento(String concepto, double importe) {
		this.fecha = LocalDateTime.now();
		this.concepto = concepto;
		this.importe = importe;
	} // WMC=1 // CCog=0
	
	public double getImporte() {
		return importe;
	} // WMC=1 // CCog=0

	public void setImporte(double newMImporte) {
		importe = newMImporte;
	} // WMC=1 // CCog=0
	
	public String getConcepto() {
		return concepto;
	} // WMC=1 // CCog=0

	public void setConcepto(String newMConcepto) {
		concepto = newMConcepto;
	} // WMC=1 // CCog=0

	public LocalDateTime getFecha() {
		return fecha;
	} // WMC=1 // CCog=0

	public void setFecha(LocalDateTime newMFecha) {
		fecha = newMFecha;
	} // WMC=1 // CCog=0
	
	@Override
	public boolean equals(Object obj) {
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto)
				&& fecha.equals(other.fecha)
				&& importe == other.importe);
	} // WMC=4 // CCog=2
}