package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	/**
	 * Crea una motocicleta.
	 * @param id El identificador del vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @param fechaMatriculacion Fecha de matriculacion del vehiculo.
	 * @param motor Tipo de motor que tiene el vehiculo.
	 * @param cilindrada La cilindrada del vehiculo.
	 * @throws OperacionNoValida si alguno de los parametros es incorrecto.
	 */
	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion,
			TipoMotor motor, int cilindrada) throws OperacionNoValidaException {
		super(id, matricula, fechaMatriculacion, motor);
		
		if (cilindrada < 0)
			throw new OperacionNoValidaException("Cilindrada menor a 0!");	
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
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
		if (this.cilindrada <= 125)
			total = 8;
		else if (this.cilindrada <= 250)
			total = 15;
		else if (this.cilindrada <= 500)
			total = 30;
		else if (this.cilindrada <= 1000)
			total = 60;
		else
			total = 120;
		
		return (1 - bonificacion) * total;
	}

}
