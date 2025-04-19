package es.unican.is2.BancoRefactor.Banco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;
	
	@BeforeAll
	public static void inicializaAuxiliares() {
		m1 = new Movimiento("Concepto1", 100);
		m2 = new Movimiento("Concepto2", 200);
		m3 = new Movimiento("Concepto3", 1500);
	}

	@BeforeEach
	public void inicializa() {
		sut = new CuentaAhorro("794311");
	}

	@Test
	public void testConstructor() {
		// assertEquals(sut.getLimiteDebito(), 1000); Ya no tiene sentido.
		assertEquals(0, sut.getMovimientos().size());
		assertEquals("794311", sut.getNumCuenta());
		// assertNull(sut.getCaducidadDebito());
		// assertNull(sut.getCaducidadCredito());		
	}
	
	@Test
	public void testSetGetFechasCaducidad() {
		// LocalDate today = LocalDate.now();
		//sut.setCaducidadCredito(today);
		//sut.setCaducidadDebito(today);
		//assertEquals(today, sut.getCaducidadDebito());
		//assertEquals(today, sut.getCaducidadCredito());		
	}
	
	@Test
	public void testGetSaldoYAddMovimiento() {
		assertTrue(sut.getSaldo()==0);	

		sut.addMovimiento(m1);
		assertTrue(sut.getSaldo() == 100);
		assertTrue(sut.getMovimientos().size()==1);
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertTrue(sut.getSaldo()==1800);
		assertTrue(sut.getMovimientos().size()==3);
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(-10);
			fail("Debe lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		} catch (SaldoInsuficienteException e) {
			fail("Debe lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(50);
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals("Retirada de efectivo", sut.getMovimientos().get(1).getConcepto());
		} catch (DatoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (SaldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (DatoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (SaldoInsuficienteException e) { }
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		try {
			sut.ingresar(-1);
			fail("Debe lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		}

		try {
			sut.ingresar(0.01);
			assertTrue(sut.getSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals("Ingreso en efectivo", sut.getMovimientos().get(0).getConcepto());
			
			sut.ingresar(100);
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			
		} catch (DatoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	
	@Test
	public void testIngresarConConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar("Ingreso", -1);
			fail("Debe lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar("Ingreso1", 0.01);
			assertTrue(sut.getSaldo()==0.01);
			assertTrue(sut.getMovimientos().size()==1);
			assertEquals("Ingreso1", sut.getMovimientos().get(0).getConcepto());
			
			sut.ingresar("Ingreso2", 100);
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals("Ingreso2", sut.getMovimientos().get(1).getConcepto());
			
		} catch (DatoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	@Test
	public void testRetirarConConcepto() {
		
		try {
			sut.retirar("Retirada", -10);
			fail("Debe lanzar DatoErroneoException");
		} catch (DatoErroneoException e) {
		} catch (SaldoInsuficienteException e) {
			fail("Deber�a lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar("Retirada1", 50);
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals("Retirada1", sut.getMovimientos().get(1).getConcepto());
		} catch (DatoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (SaldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar("Retirada2", 100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (DatoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (SaldoInsuficienteException e) {
			
		}
	
	}

	
}
