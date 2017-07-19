
public class Point {
	private double[] val;
	private int dim;
	
	public Point(int d, double... values){
		if(d!=values.length){
			throw new IllegalArgumentException("falsche Anzahl der Parameter");
		}
		else{
			val = new double[d];	//value of dimension 1, 2, 3, ...d
			for(int i=0;i<d;i++)
				val[i]=values[i];
			dim = d;
		}
	}
	
	public double get(int i){
		return val[i];
	}
	
	public int dim(){
		return dim;
	}
}
