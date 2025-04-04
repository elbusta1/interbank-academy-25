# Reto Técnico: Procesamiento de Transacciones Bancarias (CLI)

1.- INTRODUCCIÓN

Este reto consiste en desarrollar un programa que pueda ejecutarse desde la linea de comandos CLI, a fin de poder leer y analizar un archivo CSV que contiene información de transacciones del banco para posterior generar e imprimir en consola un reporte: balance final, transacción de mayor monto y cantidad de transacciones.


2.- INSTRUCCIONES
- Primero se debe tener instalado JAVA version 8+, caso contrario descargalo desde "https://www.java.com/es/download/manual.jsp"
- Abrir una terminal en donde se encuentre el archivo "reto_cobol.jar"
- Ingresar la linea de comando "java -jar reto_cobol.jar" y presionar enter
- Ingresar el path donde se encuentre el archivo CSV, si esta en el mismo directorio del archivo "reto_cobol.jar" no será necseario poner todo el path absoluto


3.- ENFOQUE Y SOLUCIÓN

Se empleó la siguiente lógica de programación:
- Solicito que el usuario ingrese la ruta del archivo CSV, si el archivo no existe, se volvera a solicitar la ruta.
- Creo una lista de transacciones, analizo todas las líneas del CSV ignorando la primera línea (encabezado), obtengo los valores de cada transaccion separando los valores de cada línea por la ",", posterior agrego el objeto a la lista de transacciones
- Creo los métodos Balance Final, Transacción Mayor, Conteo de Transacciones, basándome en solicitado para cada método, empleando como datos la lista de transacciones.
- Imprimo el reporte final, invocando cada método del paso anterior.

Emplee lenguaje de programación JAVA con POO creando 3 clases: Main, CSV y Transaction. Definí las clases, métodos y variables en inglés con nomenclatura camelCase y snake_case por convención, pero los comentarios los puse en español asumiendo que podría ser leído por programadores peruanos, asi mismo, no comenté algunas líneas de codigo que eran obvias, suponiendo que quien lo lea tendrá conocimientos básicos de programación. El programa informa al usuario con mensajes en caso se genere un error e intenta repetir el proceso a fin de cumplir su objetivo: IMPRIMIR UN REPORTE DE TRANSACCIONES


4.- ESTRUCTURA DEL PROYECTO

El repositorio contiene una carpeta "src" que contine las 3 clases java: Main, CSV y Transaction; y el archivo compilado java (jar) con el nombre "reto_cobol.jar".

En caso el usuario solo quiera ejecutar el programa, deberá descargar el archivo "reto_cobol.jar" y seguir las instrucciones del punto 2, caso contrario podrá dscargar el código fuente para que sea analizado y/o modificado por el usuario.
