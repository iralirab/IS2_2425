package es.unican.is2.ImpuestoCirculacionBusiness;

import java.util.List;

import es.unican.is2.ImpuestoCirculacionCommon.Contribuyente;
import es.unican.is2.ImpuestoCirculacionCommon.DataAccessException;
import es.unican.is2.ImpuestoCirculacionCommon.IContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionCommon.IVehiculosDAO;
import es.unican.is2.ImpuestoCirculacionCommon.OperacionNoValidaException;
import es.unican.is2.ImpuestoCirculacionCommon.Vehiculo;

/**
 * Clase de gestion de impuestos de circulacion.
 */
public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IContribuyentesDAO, IVehiculosDAO {
	/**
	 * Crea un objeto de gestion.
	 */
	public GestionImpuestoCirculacion() {}
	
	/**
	 * Da de alta a un contribuyente en el sistema.
	 * @param c El contribuyente a dar de alta.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		if (contribuyente(c.getDni()) != null) return null;
		
		creaContribuyente(c);
		return c;
	}
	
	/**
	 * Elimina a un contribuyente del sistema.
	 * @param dni El DNI del contribuyente a eliminar.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Contribuyente bajaContribuyente(String dni) throws DataAccessException, OperacionNoValidaException {
		Contribuyente c = contribuyente(dni);
		if (c == null) return null;
		if (!c.getVehiculos().isEmpty())
			throw new OperacionNoValidaException("El contribuyente tiene vehiculos!");
		
		eliminaContribuyente(dni);
		return c;
	}
	
	/**
	 * Da un vehiculo de alta en el sistema.
	 * @param v El vehiculo a dar de alta.
	 * @param dni El DNI del propietario del vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws DataAccessException {
		Contribuyente c = contribuyente(dni);
		if (vehiculo(v.getId()) != null || c == null) return null;
		
		creaVehiculo(v);
		c.getVehiculos().add(vehiculo(v.getId()));
		actualizaContribuyente(c);
		
		return v;
	}
	
	/**
	 * Elimina un vehiculo del sistema.
	 * @param dni El DNI del propietario del vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Vehiculo bajaVehiculo(String dni, String matricula) throws DataAccessException {
		Contribuyente c = this.contribuyente(dni);
		Vehiculo v = vehiculo(matricula);
		if (c == null || c.buscaVehiculo(matricula) == null || v == null) return null;
		
		eliminaVehiculo(matricula);
		c.getVehiculos().remove(v);
		actualizaContribuyente(c);
		
		return v;
	}
	
	/**
	 * Cambia el contribuyente titular de un vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @param dniActual El DNI del contribuyente al que pertenece el vehiculo.
	 * @param dniNuevo El DNI del contribuyente al que pertenecera el vehiculo.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public boolean cambiarTitularVehiculo(String matricula,
			String dniActual, String dniNuevo) throws DataAccessException {
		Contribuyente c1 = contribuyente(dniActual);
		Contribuyente c2 = contribuyente(dniNuevo);
		Vehiculo v = this.vehiculo(matricula);
		if (v == null || c1 == null || c2 == null
				|| c1.buscaVehiculo(matricula) == null || c2.buscaVehiculo(matricula) != null)
			return false;
		
		c1.getVehiculos().remove(v);
		c2.getVehiculos().add(v);		
		actualizaContribuyente(c1);
		actualizaContribuyente(c2);
		
		return true;
	}
	
	/**
	 * Busca un contribuyente por su DNI.
	 * @param dni El DNI del contribuyente.
	 * @return El contribuyente cuyo DNI haya sido introducido.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		Contribuyente c = contribuyente(dni);
		if (c == null) return null;
		
		return c;
	}
	
	/**
	 * Busca un vehiculo por su matricula.
	 * @param matricula La matricula a buscar.
	 * @return El vehiculo cuya matricula haya sido introducida.
	 * @throws DataAccessException Si ocurre un error al acceder a la base de datos.
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		Vehiculo v = vehiculoPorMatricula(matricula);
		if (v == null) return null;
		
		return v;
	}

	@Override
	public Vehiculo creaVehiculo(Vehiculo v) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo eliminaVehiculo(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo actualizaVehiculo(Vehiculo nuevo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo vehiculoPorMatricula(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehiculo> vehiculos() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo vehiculo(long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente creaContribuyente(Contribuyente c) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente actualizaContribuyente(Contribuyente nuevo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente eliminaContribuyente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contribuyente> contribuyentes() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
