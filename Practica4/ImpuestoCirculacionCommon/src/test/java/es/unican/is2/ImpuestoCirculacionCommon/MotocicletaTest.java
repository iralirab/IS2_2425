package es.unican.is2.ImpuestoCirculacionCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class MotocicletaTest {

	@Test
	public void testPrecioImpuesto() {
		Motocicleta sut;
		LocalDate today = LocalDate.now();
		
		// CASOS VALIDOS.
		sut = new Motocicleta(1, "5678BFG", today.minusYears(1).plusDays(1), TipoMotor.GASOLINA, 0);
		assertEquals(8, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4).plusDays(1), TipoMotor.DIESEL, 126);
		assertEquals(15, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(25), TipoMotor.HIBRIDO, 251);
		assertEquals(30, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(40), TipoMotor.ELECTRICO, 501);
		assertEquals(0, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusDays(30), TipoMotor.GAS, 1001);
		assertEquals(60, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(2), TipoMotor.GASOLINA, 62);
		assertEquals(8, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(10), TipoMotor.DIESEL, 200);
		assertEquals(15, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(25).minusDays(1), TipoMotor.HIBRIDO, 375);
		assertEquals(0, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today, TipoMotor.ELECTRICO, 749);
		assertEquals(15, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(1), TipoMotor.GAS, 125);
		assertEquals(8, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 250);
		assertEquals(15, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(1).plusDays(1), TipoMotor.DIESEL, 500);
		assertEquals(30, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4).plusDays(1), TipoMotor.HIBRIDO, 1000);
		assertEquals(15, sut.precioImpuesto());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(25), TipoMotor.ELECTRICO, 1002);
		assertEquals(30, sut.precioImpuesto());
	}

	@Test
	public void Motocicleta() {
		Motocicleta sut;
		LocalDate today = LocalDate.now();
		
		// CASOS VALIDOS.
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.GASOLINA, sut.getMotor());
		assertEquals(0, sut.getCilindrada());
		
		sut = new Motocicleta(36, "5678BFG", today, TipoMotor.DIESEL, 100);
		assertEquals(36, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today, sut.getFechaMatriculacion());
		assertEquals(TipoMotor.DIESEL, sut.getMotor());
		assertEquals(100, sut.getCilindrada());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.HIBRIDO, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.HIBRIDO, sut.getMotor());
		assertEquals(0, sut.getCilindrada());
		
		sut = new Motocicleta(36, "5678BFG", today, TipoMotor.ELECTRICO, 100);
		assertEquals(36, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today, sut.getFechaMatriculacion());
		assertEquals(TipoMotor.ELECTRICO, sut.getMotor());
		assertEquals(100, sut.getCilindrada());
		
		sut = new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.GAS, 0);
		assertEquals(1, sut.getId());
		assertEquals("5678BFG", sut.getMatricula());
		assertEquals(today.minusYears(4), sut.getFechaMatriculacion());
		assertEquals(TipoMotor.GAS, sut.getMotor());
		assertEquals(0, sut.getCilindrada());
		
		// CASOS NO VALIDOS.
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(-5, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(0, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, null, today.minusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, "5678BFG", today.plusDays(1), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, "5678BFG", today.plusYears(4), TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, "5678BFG", null, TipoMotor.GASOLINA, 0));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, -36));
		assertThrows(OperacionNoValidaException.class,
				() -> new Motocicleta(1, "5678BFG", today.minusYears(4), TipoMotor.GASOLINA, -1));
	}

}
