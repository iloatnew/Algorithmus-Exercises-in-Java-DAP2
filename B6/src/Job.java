
public class Job implements Scheduleable {
	private int dauer;
	private int deadline;
	// Konstruktur für Eingabe
	public Job(int in1, int in2){
		dauer = in1;
		deadline = in2;
	}
	// Methode für Rückgabe des Start Indexes
	public int getA(){
		return dauer;
	}
	// Methode für Rückgabe des Ende Indexes
	public int getB(){
		return deadline;
	}
	// Methode toString
	public String toString(){
		return ("Dauer: "+dauer+" Deadline: "+deadline);
	}
}

