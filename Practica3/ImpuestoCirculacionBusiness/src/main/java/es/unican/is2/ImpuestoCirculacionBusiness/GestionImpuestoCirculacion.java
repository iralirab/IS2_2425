package es.unican.is2.ImpuestoCirculacionBusiness;

import es.unican.is2.ImpuestoCirculacionCommon.Contribuyente;
import es.unican.is2.ImpuestoCirculacionCommon.DataAccessException;
import es.unican.is2.ImpuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionCommon.OperacionNoValidaException;
import es.unican.is2.ImpuestoCirculacionCommon.Vehiculo;
import es.unican.is2.ImpuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAOH2.VehiculosDAO;

/**
 * Clase de gestion de impuestos de circulacion.
 */
public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion {
	ContribuyentesDAO contribDAO;
	VehiculosDAO vehicDAO;
	
	/**
	 * Crea un objeto de gestion.
	 * @param contrib El DAO de datos de contribuyentes.
	 * @param vehic El DAO de datos de vehiculos.
	 */
	public GestionImpuestoCirculacion(ContribuyentesDAO contrib, VehiculosDAO vehic) {
		this.contribDAO = contrib;
		this.vehicDAO = vehic;
	}
	
	/**
	 * Da de alta a un contribuyente en el sistema.
	 * @param c El contribuyente a dar de alta.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si el contribuyente indicado ya existe.
	 */
	public void altaContribuyente(Contribuyente c)
			throws DataAccessException, OperacionNoValidaException {
		if (this.contribDAO.contribuyente(c.getDni()) != null) {
			throw new OperacionNoValidaException("Contribuyente existente!");
		}
		this.contribDAO.creaContribuyente(c);
	}
	
	/**
	 * Elimina a un contribuyente del sistema.
	 * @param dni El DNI del contribuyente a eliminar.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si el contribuyente indicado no existe o
	 * 		   tiene vehiculos en posesion.
	 */
	public void bajaContribuyente(String dni)
			throws DataAccessException, OperacionNoValidaException {
		Contribuyente c = this.contribDAO.contribuyente(dni);
		if (c == null || !c.getVehiculos().isEmpty()) {
			throw new
				OperacionNoValidaException("Contribuyente inexistente o con vehiculos!");
		}
		this.contribDAO.eliminaContribuyente(dni);
	}
	
	/**
	 * Da un vehiculo de alta en el sistema.
	 * @param v El vehiculo a dar de alta.
	 * @param dni El DNI del propietario del vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si el vehiculo introducido ya existe o el
	 * 		   contribuyente no existe.
	 */
	public void altaVehiculo(Vehiculo v, String dni)
			throws DataAccessException, OperacionNoValidaException {
		if (this.vehicDAO.vehiculo(v.getId()) != null) {
			throw new OperacionNoValidaException("Vehiculo existente!");
		}
		if (this.contribDAO.contribuyente(dni) == null) {
			throw new OperacionNoValidaException("Contribuyente inexistente!");
		}
		this.vehicDAO.creaVehiculo(v);
	}
	
	/**
	 * Elimina un vehiculo del sistema.
	 * @param dni El DNI del propietario del vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si el vehiculo no pertenece al contribuyente.
	 */
	public void bajaVehiculo(String dni, String matricula)
			throws DataAccessException, OperacionNoValidaException {
		Contribuyente c = this.contribuyente(dni);
		this.vehiculo(matricula);
		
		if (c.buscaVehiculo(matricula) == null)
			throw new
				OperacionNoValidaException("Vehiculo no perteneciente a contribuyente!");
		
		this.vehicDAO.eliminaVehiculo(matricula);
	}
	
	/**
	 * Cambia el contribuyente titular de un vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @param dniActual El DNI del contribuyente al que pertenece el vehiculo.
	 * @param dniNuevo El DNI del contribuyente al que pertenecera el vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public void cambiarTitularVehiculo(String matricula,
			String dniActual, String dniNuevo) throws DataAccessException {
		Contribuyente c1 = this.contribuyente(dniActual);
		Contribuyente c2 = this.contribuyente(dniNuevo);
		Vehiculo v = this.vehiculo(matricula);
		
		// TODO No hay metodos que permitan modificar los vehiculos de un contribuyente
		// 		en la declaracion de la aplicacion.
	}
	
	/**
	 * Busca un contribuyente por su DNI.
	 * @param dni El DNI del contribuyente.
	 * @return El contribuyente cuyo DNI haya sido introducido.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si no existe un contribuyente con ese DNI.
	 */
	public Contribuyente contribuyente(String dni)
			throws DataAccessException, OperacionNoValidaException {
		Contribuyente c = this.contribDAO.contribuyente(dni);
		if (c == null)
			throw new OperacionNoValidaException("DNI no asociado a contribuyente!");
		
		return c;
	}
	
	/**
	 * Busca un vehiculo por su matricula.
	 * @param matricula La matricula a buscar.
	 * @return El vehiculo cuya matricula haya sido introducida.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 * @throws OperacionNoValidaException Si no existe un vehiculo con esa matricula.
	 */
	public Vehiculo vehiculo(String matricula)
			throws DataAccessException, OperacionNoValidaException {
		Vehiculo v = this.vehicDAO.vehiculoPorMatricula(matricula);
		if (v == null)
			throw new OperacionNoValidaException("Matricula no asociada a vehiculo!");
		
		return v;
	}
}
