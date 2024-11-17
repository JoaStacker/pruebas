Feature: Agregar un diagnostico a la historia clinica del paciente.

  Para que se pueda diagnosticar a un paciente
  el doctor
  quiere añadir un diagnostico disponible a la historia clinica del paciente.

  Background: El doctor visualiza una historia clinica del paciente.
    Given el doctor "Dr. Palpatine" ha iniciado sesion
    And ha buscado la historia clinica del paciente "123456"
    And existen los siguientes diagnosticos disponibles en el CIE:
      | Dengue   |
      | Covid    |
      | Neumonia |

  Scenario: El doctor agrega un nuevo diagnostico.
    When el doctor selecciona el diagnostico "Neumonia"
    And el doctor guarda el nuevo diagnostico
    Then se debe registrar el nuevo diagnostico en la historia clinica del paciente.
