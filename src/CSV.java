import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 * Clase CSV: se hara lectura del archivo CSV y se procesará los resultados del reporte
 */
public class CSV {
	/**
	 * Lee un archivo CSV y convierte sus líneas en objetos Transaction, se espera que el archivo
	 * tenga el formato: id,tipo,monto
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Lista de transacciones.
	 */
	public static ArrayList<Transaction> readCSV(Path pathCSV) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		int nbLine = 0;
		// Obtenemos el bufferedreader del path CSV
		try (BufferedReader br = Files.newBufferedReader(pathCSV)) {
			String strLine;
			// Rotamos todas las lineas del archivo CSV
			while ((strLine = br.readLine()) != null) {
				nbLine++;
				// Ignoramos la primera línea porque es la cabecera del CSV y también omitimos las lineas en
				// blanco
				if (nbLine == 1 || strLine.isBlank()) {
					continue;
				}
				// Dividimos la línea en columnas basándonos en la ",", asumiendo que el CSV está bien
				// formateado
				String[] args = strLine.split(",");
				// Convertimos el 1er arg a un entero que reprsenta el ID de la transacción
				int id = Integer.parseInt(args[0].trim());
				// Asociamos el 2do arg al tipo de transaccion
				String type = args[1].trim();
				// Convertimos el 3er arg a un double (más preciso para decimales) que representa el monto
				// de la transacción
				double amount = Double.parseDouble(args[2].trim());
				// Instaciamos un objeto Transaction con los valores obtenidos (id, tipo, monto)
				Transaction t = new Transaction(id, type, amount);
				transactions.add(t);
			}
		} catch (Exception e) {
			// Retorna null al encontrar una exception durante el procesamiento del CSV
			e.printStackTrace();
			return null;
		}
		return transactions;
	}
	
	/**
	 * Generamos un string con el encabezado "Reporte de Transacciones", seguido del Balance Final, la
	 * Transacción con Mayor Montoy el Conteo de Transacciones
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto del reporte final conteniendo Balance Final, Transacción Mayor Monto y Conteo de
	 *         Transacciones.
	 */
	public static String finalReport(List<Transaction> transactions) {
		// Variable que almacenará el contenido del reporte
		StringBuilder str = new StringBuilder();
		str.append("\n\nReporte de Transacciones");
		str.append("\n");
		str.append("---------------------------------------------");
		str.append("\n");
		str.append(finalBalance(transactions));
		str.append("\n");
		str.append(majorAmountTransaction(transactions));
		str.append("\n");
		str.append(countTransactions(transactions));
		return str.toString();
	}
	
	/**
	 * Generamos el balance final sumando todos los montos sólo de las transacciones tipo "Crédito"
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto del balance final en 2 decimales.
	 */
	public static String finalBalance(List<Transaction> transactions) {
		double finalBalance = 0;
		for (Transaction t : transactions) {
			if (t.get_type().equals("Crédito")) {
				finalBalance += t.get_amount();
			}
		}
		return String.format("Balance Final: %.2f", finalBalance);
	}
	
	/**
	 * Comparamos todas las transacciones para obtener la transacción con el monto mayor, en caso de
	 * encontrar dos transacciones con el mismo monto prevalecerá la actual mayor transacción
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto de la Transacción con Mayor Monto con ID y Monto
	 */
	public static String majorAmountTransaction(List<Transaction> transactions) {
		Transaction major = null;
		for (Transaction t : transactions) {
			if (major == null || major.get_amount() < t.get_amount()) {
				major = t;
			}
		}
		return String.format("Transacción de Mayor Monto: ID: %d - %.2f", major.get_id(), major.get_amount());
	}
	
	/**
	 * Conteo de las transacciones de tipo Crédito y Débito
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto con la cantidad de transacciones Crédito y Débito
	 */
	public static String countTransactions(List<Transaction> transactions) {
		int countCredit = 0;
		int countDebit = 0;
		for (Transaction t : transactions) {
			if (t.get_type().equals("Crédito")) {
				countCredit++;
			} else if (t.get_type().equals("Débito")) {
				countDebit++;
			}
		}
		return String.format("Conteo de Transacciones: Crédito: %d Débito: %d", countCredit, countDebit);
	}
}
