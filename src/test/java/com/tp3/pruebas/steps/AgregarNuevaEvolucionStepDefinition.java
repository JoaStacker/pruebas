package com.tp3.pruebas.steps;

import com.tp3.pruebas.app.SistemaClinica;
import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.Paciente;
import com.tp3.pruebas.dominio.PedidoLaboratorio;
import com.tp3.pruebas.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class AgregarNuevaEvolucionStepDefinition {
    private Doctor doctor;
    private Paciente paciente;

    private List<String> diagnosticos;
    private String dniPaciente;
    private String informe;
    private String textoPedido;
    private String diagnosticoElegido;
    private Paciente pacienteResultante;

    // Equivale a SERVICIO -> CONTROLADOR DE CASO DE USO.
    private SistemaClinica sistemaClinica;

    //Repositori = Base de datos. -> Simular una base de datos. -> Mockear una DB.
    private RepositorioPaciente repositorioPaciente;


    @Before
    public void setUp(){
        this.doctor = null;
        this.dniPaciente = null;
        this.diagnosticoElegido = null;
        this.pacienteResultante = null;

        // SIMULAR UN REPOSITORIO
        this.repositorioPaciente = mock(RepositorioPaciente.class);
        this.sistemaClinica = new SistemaClinica(repositorioPaciente);
    }

    @Given("el doctor con {string} ha iniciado sesion")
    public void elDoctorConHaIniciadoSesion(String nombreDoctor) {
        doctor = new Doctor(nombreDoctor);
    }

    @And("ha buscado la historia clinica del paciente {string} que posee los siguientes diagnosticos:")
    public void haBuscadoLaHistoriaClinicaDelPacienteQuePoseeLosSiguientesDiagnosticos(String dniPaciente, List<String> diagnosticos) {
        this.dniPaciente = dniPaciente;
        Paciente paciente = new Paciente(dniPaciente, "Anakin Skywalker", diagnosticos);
        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(paciente));
    }

    @When("el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico {string} que dice {string}")
    public void elDoctorEscribeParaElPacientePreviamenteBuscadoUnInformeSobreElDiagnosticoQueDice(String nombreDiagnostico, String informe) {
        diagnosticoElegido = nombreDiagnostico;
        this.informe = informe;
    }

    @And("el doctor guarda la evolucion con texto libre")
    public void elDoctorGuardaLaEvolucionConTextoLibre() {
        this.pacienteResultante = sistemaClinica.agregarEvolucion(doctor, dniPaciente, diagnosticoElegido, informe, null, null);
    }

    @Then("se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el doctor.")
    public void seRegistraLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoLaDescripcionYElDoctor() {
        Diagnostico diagnostico = pacienteResultante.buscarDiagnostico(diagnosticoElegido);
        assertThat(diagnostico.tieneEvolucion(doctor, informe)).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }

    @When("el doctor escribe para el paciente buscado un informe sobre el diagnostico {string} que dice {string}")
    public void elDoctorEscribeParaElPacienteBuscadoUnInformeSobreElDiagnosticoQueDice(String nombreDiagnostico, String informe) {
        diagnosticoElegido = nombreDiagnostico;
        this.informe = informe;
    }

    @And("ingresa un texto para el pedido de laboratorio que dice {string}")
    public void ingresaUnTextoParaElPedidoDeLaboratorioQueDice(String textoPedido) {
        this.textoPedido = textoPedido;
    }


    @And("el doctor guarda la nueva evolucion con pedido de laboratorio")
    public void elDoctorGuardaLaNuevaEvolucionConPedidoDeLaboratorio() {
        this.pacienteResultante = sistemaClinica.agregarEvolucion(doctor, dniPaciente, diagnosticoElegido, informe, textoPedido, null);
    }

    @Then("se debe registrar la evolucion en la historia clinica del paciente con el diagnostico, el pedido de laboratorio y el doctor.")
    public void seDebeRegistrarLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoElPedidoDeLaboratorioYElDoctor() {
        
    }

    @And("ingresa una instruccion para una receta {string}")
    public void ingresaUnaInstruccionParaUnaReceta(String arg0) {
        
    }

    @And("los medicamentos:")
    public void losMedicamentos() {
        
    }

    @And("las cantidades de cada uno:")
    public void lasCantidadesDeCadaUno() {

    }

    @And("el doctor guarda la nueva evolucion con receta digital")
    public void elDoctorGuardaLaNuevaEvolucionConRecetaDigital() {

    }

    @Then("se debe registrar la evolucion en la historia clinica del paciente con el diagnostico, la receta digital y el doctor.")
    public void seDebeRegistrarLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoLaRecetaDigitalYElDoctor() {
    }
}
