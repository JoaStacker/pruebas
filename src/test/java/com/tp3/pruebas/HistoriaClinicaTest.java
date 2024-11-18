package com.tp3.pruebas;

import com.tp3.pruebas.dominio.*;
import io.cucumber.java.id.Diasumsikan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HistoriaClinicaTest {

	@Test
	public void testAgregarDiagnosticoConstructor(){
		//Arrenge -> Preparar
		List<String> diagnosticosListEsperado = new ArrayList<>();
		diagnosticosListEsperado.add("Angina");
		diagnosticosListEsperado.add("Dengue");
		HistoriaClinica historiaClinica = new HistoriaClinica(diagnosticosListEsperado);

		//Act -> Ejecutar
		List<Diagnostico> diagnosticosListReal = historiaClinica.getDiagnosticos();

		//Assert -> Corroborar
		// Verificar que la receta digital no esté vacía
		assertFalse(diagnosticosListReal.isEmpty(), "La historia clinica debe contener los diagnosticos.");

		// Verificar que el número de medicamentos coincida
		assertEquals(diagnosticosListEsperado.size(), diagnosticosListReal.size(), "El número de diagnosticos debe coincidir");

		// Verificar que los medicamentos coincidan en nombre y cantidad
		for (int i = 0; i < diagnosticosListEsperado.size(); i++) {
			Diagnostico diagnostico = diagnosticosListReal.get(i);
			assertEquals(diagnosticosListEsperado.get(i), diagnostico.getNombre(), "El nombre de diagnostico debe coincidir");
		}
	}

	@Test
	public void testBuscarDiagnostico(){
		//Arrenge -> Preparar
		String diagnosticoBusqueda = "Angina";
		List<String> diagnosticosListEsperado = new ArrayList<>();
		diagnosticosListEsperado.add(diagnosticoBusqueda);
		HistoriaClinica historiaClinica = new HistoriaClinica(diagnosticosListEsperado);

		//Act -> Ejecutar
		Diagnostico diagnosticoResultado = historiaClinica.buscarDiagnostico(diagnosticoBusqueda);

		//Assert -> Corroborar
		// Verificar que el número de medicamentos coincida
		assertEquals(diagnosticoResultado.getNombre(), diagnosticoBusqueda, "El número de medicamentos debe coincidir");
	}

	@Test
	public void testAgregarEvolucion(){
		//Arrenge -> Preparar
		Doctor doctor = new Doctor("Dr. Darth Vader.");
		String diagnosticoElegido = "Angina";
		String informe = "Se determina sintomas de angina";
		List<String> diagnosticosListEsperado = new ArrayList<>();
		diagnosticosListEsperado.add(diagnosticoElegido);
		HistoriaClinica historiaClinica = new HistoriaClinica(diagnosticosListEsperado);

		//Act -> Ejecutar
		historiaClinica.agregarEvolucion(diagnosticoElegido, doctor, informe, null, null);
		List<Diagnostico> diagnosticosListReal = historiaClinica.getDiagnosticos();

		//Assert -> Corroborar
		// Verificar que la receta digital no esté vacía
		assertFalse(diagnosticosListReal.isEmpty(), "La historia clinica debe tener diagnosticos");

		// Verificar que el número de medicamentos coincida
		assertEquals(diagnosticosListEsperado.size(), diagnosticosListReal.size(), "El número de medicamentos debe coincidir");

		// Verificar que los medicamentos coincidan en nombre y cantidad
		for (int i = 0; i < diagnosticosListEsperado.size(); i++) {
			Diagnostico diagnostico = diagnosticosListReal.get(i);
			assertTrue(diagnostico.tieneEvolucion(doctor, informe), "El diagnostico debe tener la evolucion.");
		}
	}

	@Test
	public void testAgregarDiagnostico(){
		//Arrenge -> Preparar
		Doctor doctor = new Doctor("Dr. Darth Vader.");
		String informe = "Se determina sintomas de angina";
		List<String> diagnosticos = new ArrayList<>();
		diagnosticos.add("Angina");
		diagnosticos.add("Dengue");
		diagnosticos.add("Neumonia");
		HistoriaClinica historiaClinica = new HistoriaClinica(diagnosticos);
		String nuevoDiagnostico = "Gastritis";

		//Act -> Ejecutar
		historiaClinica.agregarDiagnostico(nuevoDiagnostico);
		Diagnostico diagnosticoReal = historiaClinica.buscarDiagnostico(nuevoDiagnostico);

		//Assert -> Corroborar
		// Verificar que la receta digital no esté vacía
		assertEquals(diagnosticoReal.getNombre(), nuevoDiagnostico, "El diagnostico debe ser el nuevo ingresado.");
	}
}
