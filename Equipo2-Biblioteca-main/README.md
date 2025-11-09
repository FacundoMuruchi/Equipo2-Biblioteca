# Trabajo Práctico Obligatorio – Programación 2
## Escenario: Biblioteca

### Consigna general:
Cada equipo deberá desarrollar un sistema, en Java, que implemente el escenario asignado. El objetivo es aplicar de forma práctica los conceptos aprendidos en la materia, especialmente el uso de estructuras de datos

Es mandatorio que al menos una de las estructuras utilizadas tenga implementación
estática y al menos otra tenga implementación dinámica. Además, se recomienda
incluir un análisis de costos de la solución, dado que este aspecto podría ser
evaluado durante la defensa individual

### Consigna especifica:

Sistema de control de préstamos en una biblioteca Desarrollar un sistema para gestionar préstamos y devoluciones de libros en una biblioteca.

### Requisitos:

· Utilizar un **conjunto** para almacenar los libros disponibles. Cada libro debe tener título, autor, ISBN y cantidad de copias disponibles.

· Utilizar un **diccionario** para registrar los usuarios. La clave será el número de documento, y el valor contendrá los datos personales y los préstamos activos.

· Utilizar una **cola** para manejar la lista de espera de usuarios por cada libro sin copias disponibles.

· El sistema debe permitir buscar libros, solicitar préstamos, realizar devoluciones y registrar automáticamente los cambios en disponibilidad.

· Permitir generar reportes de préstamos realizados, devoluciones pendientes y usuarios en lista de espera.