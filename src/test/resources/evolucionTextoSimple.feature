Feature: Agregar una nueva evolucion con diagnostico previo.

  Para que se pueda diagnosticar al paciente
  el doctor
  quiere añadir una nueva evolucion en la historia clinica del paciente eligiendo un diagnostico previo.

  Background: El doctor visualiza una historia clinica del paciente.
    Given el doctor con "Dr. Joaquin Sarmiento" ha iniciado sesion
    And ha buscado la historia clinica del paciente "123456" que posee los siguientes diagnosticos:
      | Angina    |
      | Gastritis |
      | Dengue    |

  Scenario: 1- El doctor agrega una evolucion con texto libre.
    When el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico "Angina" que dice "El paciente presenta los sintomas de una angina viral"
    And el doctor guarda la evolucion
    Then se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el doctor.