/*
 * Clase Transaction: Contiene el ID, Tipo y Monto de una transacción
 */
public class Transaction {
	private int _id;
	private String _type;
	private double _amount;
	public Transaction(int _id, String _type, double _amount) {
		this._id = _id;
		this._type = _type;
		this._amount = _amount;
	}
	
	public int get_id() {
		return _id;
	}
	
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String get_type() {
		return _type;
	}
	
	public void set_type(String _type) {
		this._type = _type;
	}
	
	public double get_amount() {
		return _amount;
	}
	
	public void set_amount(double _amount) {
		this._amount = _amount;
	}
}