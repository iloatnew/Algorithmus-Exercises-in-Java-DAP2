import java.util.List;
import java.util.ArrayList;

public class Hulltest {
	public static void main(String[] args){
		Point[] points = new Point[1000];
//		int grenze = 100;
//		java.util.Random numberGenerator = new java.util.Random();
//		for(int i=0;i<1000;i++){       //1000 zufaellige Punkte
//			double randomNumber = numberGenerator.nextDouble() * grenze;
//			if(numberGenerator.nextBoolean())
//			randomNumber *= -1;
//			double x = randomNumber;
//			randomNumber = numberGenerator.nextDouble() * grenze;
//			if(numberGenerator.nextBoolean())
//			randomNumber *= -1;
//			double y = randomNumber;
//			points[i] = new Point( 2,x,y );
//	     }
		Point p1 = new Point(2,80,10);
		Point p2 = new Point(2,10,100);
		Point p3 = new Point(2,100,10);
		//points[997]=p1;
		//points[998]=p2;
	    //points[999]=p3;
		double r1=0, r2=0;
		double x, y;
		for(int i=0;i<=999;i++){
			r1=Math.random();
			r2=Math.random();
			x=p1.get(0)*(1 - Math.sqrt(r1)) + p2.get(0)*Math.sqrt(r1)*(1 - r2) + p3.get(0)*r2*Math.sqrt(r1);
			y=p1.get(1)*(1 - Math.sqrt(r1)) + p2.get(1)*Math.sqrt(r1)*(1 - r2) + p3.get(1)*r2*Math.sqrt(r1);
			points[i]=new Point(2, x, y );
		}
	//P=A*(1 - sqrt(r1)) + B*sqrt(r1)*(1 - r2) + C*r2*sqrt(r1)
//points[0]=new Point(2,0.5,1.8);
//points[1]=new Point(2,1,0);
//points[2]=new Point(2,0.5,-1.8);
//points[3]=new Point(2,-0.5,-1.8);
//points[4]=new Point(2,-1,0);
//points[5]=new Point(2,-0.5,1.8);
//points[6]=new Point(2,0,0);
//points[7]=new Point(2,-0.15,1.18);
//points[8]=new Point(2,-0.35,1.48);
//points[9]=new Point(2,-0.45,1.48);
		
		ConvexHull exp = new ConvexHull();
		List<Point> list = new ArrayList<Point>();
		list = exp.simpleConvex(points);
		
		for(int i=0;i<list.size();i++){
		    System.out.print("x= "+list.get(i).get(0));
		    System.out.println("  y= "+list.get(i).get(1));
		} 
	}
}
