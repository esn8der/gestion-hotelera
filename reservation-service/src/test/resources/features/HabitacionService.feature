Feature: Obtener detalles de una habitación

  Scenario: Habitación encontrada por ID
    Given que existe una habitación con ID 1
    When solicito la habitación con ID 1
    Then la respuesta debe contener los detalles de la habitación con ID 1

  Scenario: Habitación no encontrada por ID
    Given que no existe una habitación con ID 2
    When solicito la habitación con ID 2
    Then debo recibir un error de "No se encontró la habitación con ID: 2"

  Scenario: Habitaciones encontradas
    Given que existen habitaciones en la base de datos
    When solicito todas las habitaciones
    Then la respuesta debe contener todas las habitaciones

  Scenario: No se encontraron habitaciones
    Given que no existen habitaciones en la base de datos
    When solicito todas las habitaciones
    Then debo recibir un error de habitaciones "No se encontraron habitaciones"

  Scenario: Crear habitación exitosamente
    Given se recibe una solicitud de creación de habitación con tipo "Single Room", camas 1, capacidad 1, precio 100 y hotel ID 1
    When se realiza la creación de la habitación
    Then la habitación es creada exitosamente

