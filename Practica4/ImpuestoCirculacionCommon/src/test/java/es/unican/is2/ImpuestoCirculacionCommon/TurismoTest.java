package es.unican.is2.ImpuestoCirculacionCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TurismoTest {

	@Test
	public void testPrecioImpuesto() {
		Turismo sut;
		LocalDate today = LocalDate.now();
		
		// CASOS VALIDOS.
		sut = new Turismo(1, "5678BFG", today.minusYears(1).plusDays(1), TipoMotor.GASOLINA, 0);
		assertEquals(25, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(4).plusDays(1), TipoMotor.DIESEL, 8);
		assertEquals(67, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(25), TipoMotor.HIBRIDO, 12);
		assertEquals(143, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(40), TipoMotor.ELECTRICO, 16);
		assertEquals(0, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusDays(30), TipoMotor.GAS, 20);
		assertEquals(111.5, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(2), TipoMotor.GASOLINA, 4);
		assertEquals(25, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(10), TipoMotor.DIESEL, 9);
		assertEquals(67, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(25).minusDays(1), TipoMotor.HIBRIDO, 13);
		assertEquals(0, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today, TipoMotor.ELECTRICO, 17);
		assertEquals(44.5, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(1), TipoMotor.GAS, 21);
		assertEquals(223, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 7);
		assertEquals(25, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(1).plusDays(1), TipoMotor.DIESEL, 11);
		assertEquals(67, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(4).plusDays(1), TipoMotor.HIBRIDO, 15);
		assertEquals(35.75, sut.precioImpuesto());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(25), TipoMotor.ELECTRICO, 19);
		assertEquals(44.5, sut.precioImpuesto());
	}

	@Test
	public void Turismo() {
		Turismo sut;
		LocalDate today = LocalDate.now();
		
		// CASOS VALIDOS.
		sut = new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.GASOLINA, sut.getMotor());
		assertEquals(0, sut.getPotencia());
		
		sut = new Turismo(36, "5678BFG", today, TipoMotor.DIESEL, 20);
		assertEquals(36, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today, sut.getFechaMatriculacion());
		assertEquals(TipoMotor.DIESEL, sut.getMotor());
		assertEquals(20, sut.getPotencia());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.HIBRIDO, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.HIBRIDO, sut.getMotor());
		assertEquals(0, sut.getPotencia());
		
		sut = new Turismo(36, "5678BFG", today, TipoMotor.ELECTRICO, 20);
		assertEquals(36, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today, sut.getFechaMatriculacion());
		assertEquals(TipoMotor.ELECTRICO, sut.getMotor());
		assertEquals(20, sut.getPotencia());
		
		sut = new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.GAS, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.GAS, sut.getMotor());
		assertEquals(0, sut.getPotencia());
		
		// CASOS NO VALIDOS.
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(-5, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(0, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, null, today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, "5678BFG", today.plusDays(1), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, "5678BFG", today.plusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, "5678BFG", null, TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, -36));
		assertThrows(OperacionNoValidaException.class,
				() -> new Turismo(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, -1));
	}

}
