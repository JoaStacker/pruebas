Feature: Agregar una nueva evolucion.

  Para que se pueda diagnosticar al paciente
  el doctor
  quiere añadir una nueva evolucion en la historia clinica del paciente.

  Background: El doctor visualiza una historia clinica del paciente.
    Given el doctor con "Dr. Palpatine" ha iniciado sesion
    And ha buscado la historia clinica del paciente "123456" que posee los siguientes diagnosticos:
      | Angina    |
      | Gastritis |
      | Dengue    |

  Scenario: 1- El doctor agrega una evolucion con texto libre.
    When el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico "Angina" que dice "El paciente presenta los sintomas de una angina viral"
    And el doctor guarda la evolucion con texto libre
    Then se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el doctor.

  Scenario: 2- El doctor agrega una evolucion con pedido de laboratorio de texto libre.
    When el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico "Angina" que dice "El paciente presenta los sintomas de una angina viral"
    And ingresa un texto para el pedido de laboratorio que dice "Se solicita analisis de hemoglobina completo"
    And el doctor guarda la nueva evolucion con pedido de laboratorio
    Then se debe registrar la evolucion en la historia clinica del paciente con el diagnostico, el pedido de laboratorio y el doctor.

  Scenario: 3- El doctor agrega una evolucion con receta digital.
    When el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico "Angina" que dice "El paciente presenta los sintomas de una angina viral"
    And ingresa una instruccion para una receta "Tomar el ibuprofeno cada 8hs"
    And los medicamentos:
      | Dipirona   |
      | Ibuprofeno |
    And las cantidades de cada uno:
      | 4 |
      | 2 |
    And el doctor guarda la nueva evolucion con receta digital
    Then se debe registrar la evolucion en la historia clinica del paciente con el diagnostico, la receta digital y el doctor.
