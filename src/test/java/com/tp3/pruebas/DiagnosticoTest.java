package com.tp3.pruebas;

import com.tp3.pruebas.dominio.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiagnosticoTest {

	@Test
	public void testDiagnosticoConstructor(){
		//Arrenge -> Preparar
		String nombreEsperado = "Angina";

		//Act -> Ejecutar
		Diagnostico diagnosticoPrueba = new Diagnostico(nombreEsperado);
		String nombreReal = diagnosticoPrueba.getNombre();

		//Assert -> Corroborar
		assertEquals(nombreEsperado, nombreReal);
	}

	@Test
	public void testDiagnosticoTieneNombre(){
		//Arrenge -> Preparar
		String nombreEsperado = "Angina";

		//Act -> Ejecutar
		Diagnostico diagnosticoPrueba = new Diagnostico(nombreEsperado);
		Boolean isNombreAsignado = diagnosticoPrueba.tieneNombre(nombreEsperado);

		//Assert -> Corroborar
		assertTrue(isNombreAsignado);
	}

	@Test
	public void testDiagnosticoAgregarEvolucionTextoSimple(){
		//Arrenge -> Preparar
		Diagnostico diagnostico = new Diagnostico("Angina");

		Doctor doctor = new Doctor("Dr. Darth Vader"); // sesion
		String informe = "Se detecta sintomas angina.";

		//Act -> Ejecutar
		diagnostico.agregarEvolucion(doctor, informe, null, null);
		Boolean tieneEvolucion = diagnostico.tieneEvolucion(doctor, informe);

		//Assert -> Corroborar
		assertTrue(tieneEvolucion);
		assertFalse(diagnostico.getEvoluciones().isEmpty());
		assertEquals(diagnostico.getEvoluciones().get(0).getInforme(), informe);
	}

	@Test
	public void testDiagnosticoAgregarEvolucionPedidoLaboratorio(){
		//Arrenge -> Preparar
		Diagnostico diagnostico = new Diagnostico("Angina");

		Doctor doctor = new Doctor("Dr. Darth Vader"); // sesion
		String informe = "Se detecta sintomas angina.";

		// Con pedido
		String textoPedido = "Se solicita Hemoglobina completo.";

		//Act -> Ejecutar
		diagnostico.agregarEvolucion(doctor, informe, textoPedido, null);

		//Assert -> Corroborar
		assertEquals(diagnostico.getEvoluciones().get(0).getPedidoLaboratorio().getTextoPedido(), textoPedido);
	}

	@Test
	public void testDiagnosticoAgregarEvolucionRecetaDigital(){
		//Arrenge -> Preparar
		Diagnostico diagnostico = new Diagnostico("Angina");

		Doctor doctor = new Doctor("Dr. Darth Vader"); // sesion
		String informe = "Se detecta sintomas angina.";

		// Con receta
		Medicamento medicamento = new Medicamento("Ibuprofeno", "1");
		List<Medicamento> medicamentosList = new ArrayList<>();
		medicamentosList.add(medicamento);

		//Act -> Ejecutar
		diagnostico.agregarEvolucion(doctor, informe, null, medicamentosList);
		List<Medicamento> medicamentoListReal = diagnostico.getEvoluciones().get(0).getRecetaDigital().getMedicamentos();

		//Assert -> Corroborar
		// Verificar que la receta digital no esté vacía
		assertFalse(medicamentoListReal.isEmpty(), "La receta digital debe contener medicamentos");

		// Verificar que el número de medicamentos coincida
		assertEquals(medicamentosList.size(), medicamentoListReal.size(), "El número de medicamentos debe coincidir");

		// Verificar que los medicamentos coincidan en nombre y cantidad
		for (int i = 0; i < medicamentosList.size(); i++) {
			Medicamento medicamentoEsperado = medicamentosList.get(i);
			Medicamento medicamentoReal = medicamentoListReal.get(i);

			assertEquals(medicamentoEsperado.getNombre(), medicamentoReal.getNombre(), "El nombre del medicamento debe coincidir");
			assertEquals(medicamentoEsperado.getCantidad(), medicamentoReal.getCantidad(), "La cantidad del medicamento debe coincidir");
		}
	}

}
