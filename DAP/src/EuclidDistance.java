
public class EuclidDistance implements Distance{

	public double distance(Point p1, Point p2) {
		if(p1.dim()!=p2.dim()){			//zwei Punkte sollen in einem Universum sein
			System.out.println("nicht in einem Universum geeignet");
			System.exit(0);
		}
		int dim = p1.dim();
		double sum = 0;
		for(int i=0;i<dim;i++){
			sum += (p1.get(i)-p2.get(i))*(p1.get(i)-p2.get(i)); //Summe von die Quadart aller Substanz
		}
		sum = Math.sqrt(sum);	//^0,5
		return sum;
	}

}
