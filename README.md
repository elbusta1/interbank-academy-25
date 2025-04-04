# Reto Técnico: Procesamiento de Transacciones Bancarias (CLI)

## 1.- INTRODUCCIÓN

Este programa consiste en la ejecución de linea de comandos CLI, a fin de poder leer y analizar un archivo CSV que contiene información de transacciones bancarias, a fin de obtener e imprimir en consola un reporte: Balance Final, Transacción de Mayor Monto y Conteo de Transacciones por Tipo.


## 2.- INSTRUCCIONES

### Requisitos:
- Java version 8+, o descargar e instalar desde https://www.java.com/es/download/manual.jsp

### Instalación:
- Clonar repositorio o descargar archivo "reto_cobol.jar"

### Empleo:
- Ejecutar el jar:
```bash
java -jar reto_cobol.jar
```
- El terminal le solicitará que ingrese la ruta del archivo CSV


## 3.- ENFOQUE Y SOLUCIÓN

La solución emplea POO, manejo de errores en la ruta del archivo o en su contenido.
La lógica es la siguiente:
- Solicitar ruta del archivo CSV, si no existe, se vuelve a solicitar.
- Leer y analizar archivo CSV.
- Almacenar datos en lista de transacciones.
- Obtener el Balance Final, Transacción Mayor Monto, Conteo de Transacciones
- Imprimir reporte de transacciones


## 4.- ESTRUCTURA DEL PROYECTO

```
.
├── README.md
├── src
	├── Main.java
	├── CSV.java
	├── Transaction.java
├── reto_cobol.jar
└── data.csv
```
