import com.tp3.pruebas.app.SistemaClinica;
import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.HistoriaClinica;
import com.tp3.pruebas.dominio.Paciente;
import com.tp3.pruebas.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DiagnosticoAHistoriaClinicaStepDefinition {
    private Doctor doctor;
    private Paciente paciente;

    private List<String> diagnosticosCIE10;
    private String dniPaciente;
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

    @Given("el doctor {string} ha iniciado sesion")
    public void elDoctorHaIniciadoSesion(String nombreDoctor) {
        this.doctor = new Doctor(nombreDoctor);
    }

    @And("ha buscado la historia clinica del paciente {string}")
    public void haBuscadoLaHistoriaClinicaDelPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
        Paciente paciente = new Paciente(dniPaciente, "Anakin Skywalker", new ArrayList<>());

        // Obligo a retornar un optional con el paciente para poder mockear.
        when(repositorioPaciente.buscarPaciente(dniPaciente))
                .thenReturn(Optional.of(paciente));
    }

    @And("existen los siguientes diagnosticos disponibles en el CIE:")
    public void existenLosSiguientesDiagnosticosDisponiblesEnElCIE(List<String> diagnosticosCIE10) {
        this.diagnosticosCIE10 = diagnosticosCIE10;
        List<Diagnostico> diagnosticos = diagnosticosCIE10
                .stream()
                .map(Diagnostico::new)
                .collect(Collectors.toList());

        when(repositorioPaciente.buscarDiagnosticosCIE())
                .thenReturn(Optional.of(diagnosticos));
    }

    @When("el doctor selecciona el diagnostico {string}")
    public void elDoctorSeleccionaElDiagnostico(String nombreDiagnostico) {
        this.diagnosticoElegido = nombreDiagnostico;

        assertThat(this.diagnosticosCIE10).contains(nombreDiagnostico);
    }

    @And("el doctor guarda el nuevo diagnostico")
    public void elDoctorGuardaElNuevoDiagnostico() {
        this.pacienteResultante = sistemaClinica.agregarDiagnosticoAHistoriaClinica(dniPaciente, diagnosticoElegido);
    }

    @Then("se debe registrar el nuevo diagnostico en la historia clinica del paciente.")
    public void seDebeRegistrarElNuevoDiagnosticoEnLaHistoriaClinicaDelPaciente() {
        Diagnostico diagnostico = pacienteResultante.buscarDiagnostico(diagnosticoElegido);
        assertThat(diagnostico.getNombre())
                .as("El diagnostico registrado debe coincidir con el diagnostico elegido por el doctor.")
                .isEqualTo(diagnosticoElegido);

        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }
}
