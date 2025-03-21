package es.unican.is2.ImpuestoCirculacionMain;

import static org.junit.jupiter.api.Assertions.*;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.ImpuestoCirculacionBusiness.GestionImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAOH2.VehiculosDAO;
import es.unican.is2.ImpuestoCirculacionGUI.VistaFuncionario;

class VistaFuncionarioIT {
	private FrameFixture demo;

	@BeforeEach
	public void setUp() {
		VistaFuncionario gui = 
				new VistaFuncionario(new GestionImpuestoCirculacion(
						new ContribuyentesDAO(), new VehiculosDAO()));
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	void test() {
		// CASOS VALIDOS.
		demo.textBox("txtDniContribuyente").enterText("33333333A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Luis Ruiz Rivas");
		demo.list("listMatriculasVehiculos").requireItemCount(0);
		demo.textBox("txtTotalContribuyente").requireText("0.00");
		demo.textBox("txtDniContribuyente").deleteText();
		
		demo.textBox("txtDniContribuyente").enterText("22222222A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Ana Cuesta Ruiz");
		assertArrayEquals(new String[] { "2222AAA" },
				demo.list("listMatriculasVehiculos").contents());
		demo.textBox("txtTotalContribuyente").requireText("223.00");
		demo.textBox("txtDniContribuyente").deleteText();
		
		demo.textBox("txtDniContribuyente").enterText("11111111A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez");
		assertArrayEquals(new String[] { "1111AAA", "1111BBB", "1111CCC" },
				demo.list("listMatriculasVehiculos").contents());
		demo.textBox("txtTotalContribuyente").requireText("206.75");
		demo.textBox("txtDniContribuyente").deleteText();
		
		// CASOS NO VALIDOS.
		demo.textBox("txtDniContribuyente").enterText("");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
		demo.list("listMatriculasVehiculos").requireItemCount(0);
		demo.textBox("txtTotalContribuyente").requireText("0.00");
		demo.textBox("txtDniContribuyente").deleteText();
		
		demo.textBox("txtDniContribuyente").enterText("66666666Z");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
		demo.list("listMatriculasVehiculos").requireItemCount(0);
		demo.textBox("txtTotalContribuyente").requireText("0.00");
	}

}
