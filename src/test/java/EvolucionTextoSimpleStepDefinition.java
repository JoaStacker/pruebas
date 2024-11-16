import com.tp3.pruebas.app.SistemaClinica;
import com.tp3.pruebas.dominio.Diagnostico;
import com.tp3.pruebas.dominio.Doctor;
import com.tp3.pruebas.dominio.Paciente;
import com.tp3.pruebas.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;

import javax.tools.DiagnosticCollector;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class EvolucionTextoSimpleStepDefinition {
    private Doctor doctor;
    private Paciente paciente;

    private List<String> diagnosticos;
    private String dniPaciente;
    private String informe;
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
        Paciente paciente = new Paciente(dniPaciente, "Juan Doe", diagnosticos);
        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(paciente));
    }

    @When("el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico {string} que dice {string}")
    public void elDoctorEscribeParaElPacientePreviamenteBuscadoUnInformeSobreElDiagnosticoQueDice(String nombreDiagnostico, String informe) {
        diagnosticoElegido = nombreDiagnostico;
        this.informe = informe;
    }

    @And("el doctor guarda la evolucion")
    public void elDoctorGuardaLaEvolucion() {
        this.pacienteResultante = sistemaClinica.agregarEvolucion(doctor, dniPaciente, diagnosticoElegido, informe);
    }

    @Then("se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el doctor.")
    public void seRegistraLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoLaDescripcionYElDoctor() {
        Diagnostico diagnostico = pacienteResultante.buscarDiagnostico(diagnosticoElegido);
        assertThat(diagnostico.tieneEvolucion(doctor, informe)).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }
}
