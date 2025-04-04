import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 * Clase CSV: se hara lectura del archivo CSV y se procesar� los resultados del reporte
 */
public class CSV {
	/**
	 * Lee un archivo CSV y convierte sus l�neas en objetos Transaction, se espera que el archivo
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
				// Ignoramos la primera l�nea porque es la cabecera del CSV y tambi�n omitimos las lineas en
				// blanco
				if (nbLine == 1 || strLine.isBlank()) {
					continue;
				}
				// Dividimos la l�nea en columnas bas�ndonos en la ",", asumiendo que el CSV est� bien
				// formateado
				String[] args = strLine.split(",");
				// Convertimos el 1er arg a un entero que reprsenta el ID de la transacci�n
				int id = Integer.parseInt(args[0].trim());
				// Asociamos el 2do arg al tipo de transaccion
				String type = args[1].trim();
				// Convertimos el 3er arg a un double (m�s preciso para decimales) que representa el monto
				// de la transacci�n
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
	 * Transacci�n con Mayor Montoy el Conteo de Transacciones
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto del reporte final conteniendo Balance Final, Transacci�n Mayor Monto y Conteo de
	 *         Transacciones.
	 */
	public static String finalReport(List<Transaction> transactions) {
		// Variable que almacenar� el contenido del reporte
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
	 * Generamos el balance final sumando todos los montos s�lo de las transacciones tipo "Cr�dito"
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto del balance final en 2 decimales.
	 */
	public static String finalBalance(List<Transaction> transactions) {
		double finalBalance = 0;
		for (Transaction t : transactions) {
			if (t.get_type().equals("Cr�dito")) {
				finalBalance += t.get_amount();
			}
		}
		return String.format("Balance Final: %.2f", finalBalance);
	}
	
	/**
	 * Comparamos todas las transacciones para obtener la transacci�n con el monto mayor, en caso de
	 * encontrar dos transacciones con el mismo monto prevalecer� la actual mayor transacci�n
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto de la Transacci�n con Mayor Monto con ID y Monto
	 */
	public static String majorAmountTransaction(List<Transaction> transactions) {
		Transaction major = null;
		for (Transaction t : transactions) {
			if (major == null || major.get_amount() < t.get_amount()) {
				major = t;
			}
		}
		return String.format("Transacci�n de Mayor Monto: ID: %d - %.2f", major.get_id(), major.get_amount());
	}
	
	/**
	 * Conteo de las transacciones de tipo Cr�dito y D�bito
	 * 
	 * @param pathCSV
	 *          Ruta del archivo CSV.
	 * @return Texto con la cantidad de transacciones Cr�dito y D�bito
	 */
	public static String countTransactions(List<Transaction> transactions) {
		int countCredit = 0;
		int countDebit = 0;
		for (Transaction t : transactions) {
			if (t.get_type().equals("Cr�dito")) {
				countCredit++;
			} else if (t.get_type().equals("D�bito")) {
				countDebit++;
			}
		}
		return String.format("Conteo de Transacciones: Cr�dito: %d D�bito: %d", countCredit, countDebit);
	}
}
