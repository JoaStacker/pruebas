package com.tp3.pruebas;

import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.Medicamento;
import com.tp3.pruebas.dominio.Paciente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteTest {

    @Test
    public void testPacienteConstructor(){
        //Arrenge -> Preparar
        String dniEsperado = "43920840";
        String nombreEsperado = "Juan Perez";
        List<String> diagnosticoList = new ArrayList<>();
        diagnosticoList.add("Fiebre");

        //Act -> Ejecutar
        Paciente pacienteReal = new Paciente(dniEsperado, nombreEsperado, diagnosticoList);

        //Assert -> Corroborar
        assertEquals(pacienteReal.getDni(), dniEsperado);
        assertEquals(pacienteReal.getNombre(), nombreEsperado);
    }

    @Test
    public void testPacienteAgregarEvolucion(){
        //Arrenge -> Preparar
        String dniEsperado = "43920840";
        String nombreEsperado = "Juan Perez";
        List<String> diagnosticoList = new ArrayList<>();
        diagnosticoList.add("Fiebre");
        Paciente pacienteReal = new Paciente(dniEsperado, nombreEsperado, diagnosticoList);


        Doctor doctor = new Doctor("Dr. Darth Vader"); // sesion
        String informe = "Se detecta sintomas angina.";
        String textoPedido = "Se solicita Hemoglobina completo.";
        Medicamento medicamento = new Medicamento("Ibuprofeno", "1");
        List<Medicamento> medicamentosList = new ArrayList<>();
        medicamentosList.add(medicamento);

        //Act -> Ejecutar
        pacienteReal.agregarEvolucion(diagnosticoList.get(0), doctor, informe, textoPedido, medicamentosList);

        //Assert -> Corroborar
        assertEquals(pacienteReal.getHistoriaClinica().getDiagnosticos().get(0).getEvoluciones().get(0).getDoctor(), doctor);
        assertEquals(pacienteReal.getHistoriaClinica().getDiagnosticos().get(0).getEvoluciones().get(0).getInforme(), informe);
    }

    @Test
    public void testPacienteAgregarDiagnostico(){
        //Arrenge -> Preparar
        String dniEsperado = "43920840";
        String nombreEsperado = "Juan Perez";
        List<String> diagnosticoList = new ArrayList<>();
        diagnosticoList.add("Fiebre");
        Paciente pacienteReal = new Paciente(dniEsperado, nombreEsperado, diagnosticoList);

        //Act -> Ejecutar
        pacienteReal.agregarDiagnostico(diagnosticoList.get(0));

        //Assert -> Corroborar
        assertEquals(pacienteReal.getHistoriaClinica().getDiagnosticos().get(0).getNombre(), diagnosticoList.get(0));
    }

    @Test
    public void testPacienteBuscarDiagnostico(){
        //Arrenge -> Preparar
        String dniEsperado = "43920840";
        String nombreEsperado = "Juan Perez";
        List<String> diagnosticoList = new ArrayList<>();
        diagnosticoList.add("Fiebre");
        Paciente pacienteReal = new Paciente(dniEsperado, nombreEsperado, diagnosticoList);

        //Act -> Ejecutar
        Diagnostico diagnostico = pacienteReal.buscarDiagnostico(diagnosticoList.get(0));

        //Assert -> Corroborar
        assertEquals(diagnostico.getNombre(), diagnosticoList.get(0));
    }

}