package es.unican.is2.ImpuestoCirculacionBusiness;

import java.util.HashMap;

import es.unican.is2.ImpuestoCirculacionCommon.Contribuyente;
import es.unican.is2.ImpuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionCommon.Vehiculo;
import es.unican.is2.ImpuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAOH2.VehiculosDAO;

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion {
	HashMap<String, Vehiculo> vehiculos = new HashMap<>();
	HashMap<String, Contribuyente> contribuyentes = new HashMap<>();
	
	public GestionImpuestoCirculacion(ContribuyentesDAO contrib, VehiculosDAO vehic) {
		// TODO
	}
	
	public void altaContribuyente(Contribuyente c) {
	}
	
	public void bajaContribuyente(String dni) {
	}
	
	public void altaVehiculo(Vehiculo v, String dni) {
	}
	
	public void bajaVehiculo(String dni, String matricula) {
	}
	
	public void cambiarTitularVehiculo(String matricula,
			String dniActual, String dniNuevo) {
	}
	
	public Contribuyente contribuyente(String dni) {
		return null;
	}
	
	public Vehiculo vehiculo(String matricula) {
		return null;
	}
}
