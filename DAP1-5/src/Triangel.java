
public class Triangel extends Simplex{
	private Point[] poi;
	
	public Triangel(int d, Point... points) {
		super(d, points);
		if(points.length!=d+1)
			for(int i=0;i<d+1;i++)
				poi[i]=points[i];
	}
	
	public boolean validate(){  //Soll: 3 Punkte(poi,length==3); jede Punke hat ein x, ein y(poi[].dim==2);
		if(poi.length!=3)
			return false;
		else{
			for(int i=0;i<3;i++){
				if(poi[i].dim()!=2)
					return false;
			}
			return true;	
		}
	}
	
}
