package es.unican.is2.ImpuestoCirculacionBusiness;

import es.unican.is2.ImpuestoCirculacionCommon.Contribuyente;
import es.unican.is2.ImpuestoCirculacionCommon.DataAccessException;
import es.unican.is2.ImpuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionCommon.OperacionNoValidaException;
import es.unican.is2.ImpuestoCirculacionCommon.Vehiculo;
import es.unican.is2.ImpuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAOH2.VehiculosDAO;

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion {
	ContribuyentesDAO contribDAO;
	VehiculosDAO vehicDAO;
	
	public GestionImpuestoCirculacion(ContribuyentesDAO contrib, VehiculosDAO vehic) {
		this.contribDAO = contrib;
		this.vehicDAO = vehic;
	}
	
	public void altaContribuyente(Contribuyente c) throws DataAccessException {
		if (this.contribDAO.contribuyente(c.getDni()) != null) {
			throw new OperacionNoValidaException("Contribuyente existente!");
		}
		this.contribDAO.creaContribuyente(c);
	}
	
	public void bajaContribuyente(String dni) throws DataAccessException {
		Contribuyente c = this.contribDAO.contribuyente(dni);
		if (c == null || !c.getVehiculos().isEmpty()) {
			throw new
				OperacionNoValidaException("Contribuyente inexistente o con vehiculos!");
		}
		this.contribDAO.eliminaContribuyente(dni);
	}
	
	public void altaVehiculo(Vehiculo v, String dni) throws DataAccessException {
		if (this.vehicDAO.vehiculo(v.getId()) != null) {
			throw new OperacionNoValidaException("Vehiculo existente!");
		}
		if (this.contribDAO.contribuyente(dni) == null) {
			throw new OperacionNoValidaException("Contribuyente inexistente!");
		}
		this.vehicDAO.creaVehiculo(v);
	}
	
	public void bajaVehiculo(String dni, String matricula) throws DataAccessException {
		Contribuyente c = this.contribuyente(dni);
		this.vehiculo(matricula);
		
		if (c.buscaVehiculo(matricula) == null)
			throw new
				OperacionNoValidaException("Vehiculo no perteneciente a contribuyente!");
		
		this.vehicDAO.eliminaVehiculo(matricula);
	}
	
	public void cambiarTitularVehiculo(String matricula,
			String dniActual, String dniNuevo) throws DataAccessException {
		Contribuyente c1 = this.contribuyente(dniActual);
		Contribuyente c2 = this.contribuyente(dniNuevo);
		Vehiculo v = this.vehiculo(matricula);
		
		// TODO No hay metodos que permitan modificar los vehiculos de un contribuyente
		// 		en la declaracion de la aplicacion.
	}
	
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		Contribuyente c = this.contribDAO.contribuyente(dni);
		if (c == null)
			throw new OperacionNoValidaException("DNI no asociado a contribuyente!");
		
		return c;
	}
	
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		Vehiculo v = this.vehicDAO.vehiculoPorMatricula(matricula);
		if (v == null)
			throw new OperacionNoValidaException("Matricula no asociada a vehiculo!");
		
		return v;
	}
}
