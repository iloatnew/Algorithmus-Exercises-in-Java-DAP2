
public abstract class Simplex {
	private Point[] poi;
	public Simplex(int d, Point... points){
		if(points.length==d+1){				//soll genau d+1 Punkte enthalten
			poi = new Point[d+1];
			for(int i=0;i<points.length;i++)
				poi[i]=points[i];			//poi: Punkte des Simplex
		}
		else
			throw new IllegalArgumentException("falsche Anzahl der Parameter");
	}
	
	public Point get(int i){
		return poi[i];
	}
	
	public double perimeter(){  //Summe von Seitenlaengen
		double sum=0.0;
		EuclidDistance dis = new EuclidDistance();
		for(int i=0;i<poi.length;i++){		//Beispiel: Summe von 3 Dimension Simplex: 1-2,1-3,1-4, 2-3,2-4, 3-4; 6 Seiten
			for(int j=i+1;j<poi.length;j++)
				sum += dis.distance(poi[i],poi[j]);
		}
		return sum;
	}
	
	public abstract boolean validate();
}
