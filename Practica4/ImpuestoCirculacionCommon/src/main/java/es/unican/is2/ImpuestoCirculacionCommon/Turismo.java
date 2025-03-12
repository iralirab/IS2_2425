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
	 */
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
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
		int anhoMatriculacion = this.getFechaMatriculacion().getYear();
		int anho = LocalDate.now().getYear();
		
		// Los vehiculos de 25 anhos o mas no pagan.
		if (this.getFechaMatriculacion().getYear() < anho - 25) {
			return 0;
		}

		// Variables.
		double total;
		TipoMotor tMotor = this.getMotor();
		double bonificacion = tMotor.descuentoImpuesto;
		
		// Descuentos especiales por tiempo.
		if (tMotor.equals(TipoMotor.HIBRIDO)
				&& anhoMatriculacion > anho - 4) {
			bonificacion = 0.75;
		} else if (tMotor.equals(TipoMotor.GAS)
				&& anhoMatriculacion > anho - 1) {
			bonificacion = 0.5;
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
