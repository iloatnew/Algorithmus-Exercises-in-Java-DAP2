
public class Interval implements Scheduleable{
	private int starte;
	private int ende;
	// Konstruktur für Eingabe
	public Interval(int in1, int in2){
		starte = in1;
		ende = in2;
	}
	// Methode für Rückgabe des Start Indexes
	public int getA(){
		return starte;
	}
	// Methode für Rückgabe des Ende Indexes
	public int getB(){
		return ende;
	}
	// Methode toString
	public String toString(){
		return ("Start: "+starte+" End: "+ende);
	}
}
