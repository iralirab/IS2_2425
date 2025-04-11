package es.unican.is2.BancoOriginal.Banco;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	public DatosCliente datosCliente;
	public Direccion direccion;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(DatosCliente datosCliente, Direccion direccion) {  
		this.datosCliente = datosCliente;
		this.direccion = direccion;
	} // WMC=1 // CCog=0
	
	public void cambiaDireccion(Direccion direccion) {
		this.direccion = direccion;
	} // WMC=1 // CCog=0
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	} // WMC=1 // CCog=0
	
	public void anhadeTarjeta(Tarjeta t) {
		tarjetas.add(t);
	} // WMC=1 // CCog=0
	
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) // WMC+1 // CCog+1
			total += c.getSaldo();
		return total;
	} // WMC=2 // CCog=1

	public DatosCliente getDatosCliente() {
		return datosCliente;
	} // WMC=1 // CCog=0

	public Direccion getDireccion() {
		return direccion;
	} // WMC=1 // CCog=0
}