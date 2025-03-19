package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	/**
	 * Crea un vehiculo de turismo.
	 * @param id El identificador del vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @param fechaMatriculacion Fecha de matriculacion del vehiculo.
	 * @param motor Tipo de motor que tiene el vehiculo.
	 * @param potencia La potencia en caballos del vehiculo.
	 * @throws OperacionNoValidaException si alguno de los datos es erroneo.
	 */
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion,
			TipoMotor motor, double potencia) throws OperacionNoValidaException {
		super(id, matricula, fechaMatriculacion, motor);
		
		if (potencia < 0)
			throw new OperacionNoValidaException("Potencia menor a 0!");		
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	/**
	 * Obtiene el precio impuesto del vehiculo segun sus caracteristicas.
	 * @returns el precio impuesto del vehiculo segun sus caracteristicas.
	 */
	@Override
	public double precioImpuesto() {
		LocalDate anho = LocalDate.now();
		
		// Los vehiculos de 25 anhos o mas no pagan.
		if (this.getFechaMatriculacion().isBefore(anho.minusYears(25)))
			return 0;

		// Variables.
		double total;
		TipoMotor tMotor = this.getMotor();
		double bonificacion = 0;
		
		// Descuentos especiales por tiempo.
		if ((tMotor.equals(TipoMotor.HIBRIDO) && this.getFechaMatriculacion().isAfter(anho.minusYears(4)))
				|| (tMotor.equals(TipoMotor.GAS) && this.getFechaMatriculacion().isAfter(anho.minusYears(1)))
				|| tMotor.equals(TipoMotor.ELECTRICO)) {
			bonificacion = tMotor.descuentoImpuesto;
		}
		
		// Valores segun potencia.
		if (this.potencia < 8) {
			total = 25;
		} else if (this.potencia < 12) {
			total = 67;
		} else if (this.potencia < 16) {
			total = 143;
		} else if (this.potencia < 20) {
			total = 178;
		} else {
			total = 223;
		}
		
		return (1 - bonificacion) * total;
	}

}
