package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;
	
	/**
	 * Crea un vehiculo.
	 * @param id El identificador del vehiculo.
	 * @param matricula La matricula del vehiculo.
	 * @param fechaMatriculacion Fecha de matriculacion del vehiculo.
	 * @param motor Tipo de motor que tiene el vehiculo.
	 * @throws OperacionNoValidaException si alguno de los parametros es incorrecto.
	 */
	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion,
			TipoMotor motor) throws OperacionNoValidaException {
		if (id <= 0)
			throw new OperacionNoValidaException("ID no valido!");
		if (matricula == null)
			throw new OperacionNoValidaException("Matricula no introducida!");
		if (fechaMatriculacion == null || fechaMatriculacion.isAfter(LocalDate.now()))
			throw new OperacionNoValidaException("Fecha incorrecta o no introducida!");
		
		this.id = id;
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	public abstract double precioImpuesto();
}
