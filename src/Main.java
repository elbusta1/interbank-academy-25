import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *  Clase principal: es donde se ejecutara el método "main" 
 */
public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// Variable para ruta del archivo CSV
		Path pathCSV;
		// Instanciamiento de la clase Scanner
		Scanner scan = new Scanner(System.in);
		// Array que contendrá transacciones del archivo CSV
		ArrayList<Transaction> transactions;
		// Bucle que verificará que se procese correctamente el archivo CSV
		while (true) {
			try {
				System.out.println("Por favor, ingrese la ubicación del archivo CSV:");
				// Solicitamos ingresar la ruta del archivo CSV
				String dirCSV = scan.nextLine();
				pathCSV = Paths.get(dirCSV);
				// Verificiamos de que el archivo CSV existe
				if (!Files.exists(pathCSV)) {
					// Mensaje de error en caso el archivo no exista, retornamos al inicio bucle
					System.err.println("El archivo CSV no existe.");
					continue;
				}
				// Converitmos la ruta CSV a un array de objetos Transaction
				transactions = CSV.readCSV(pathCSV);
				if (transactions == null) {
					// Mensaje de error en caso haya un error tipográfico en el contenido CSV, retornamos al
					// inicio del bucle
					System.err.println("El archivo CSV tiene errores en su contenido.");
					continue;
				}
				if (transactions.isEmpty()) {
					// Mensaje de erorr en caso no existan transacciones, lo cual no podrá dar como reporte
					// final, retornamos al inicio del bucle
					System.err.println("El archivo CSV no tiene transacciones.");
					continue;
				}
				// Si no hubo erroes y el array no esta vacío, salimos del bucle
				break;
			} catch (Exception e) {
				// Mostramos la exception generada, retorna al inicio del bucle
				e.printStackTrace();
			}
		}
		// String con el reporte final
		String report = CSV.finalReport(transactions);
		// Imprimimos el reporte final
		System.out.println(report);
	}
}
