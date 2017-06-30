import java.util.List;
import java.util.ArrayList;
public class ConvexHull {
	public List<Point> simpleConvex(Point[] P){
	    int length = P.length;
	    boolean[][] valid = new boolean[length][length];
	    List<Point> list = new ArrayList<Point>(); 
	    // Intitalizierung: setze alle valid[][] in "true"
		for(int i=0;i<length;i++){
			for(int j=0;j<length;j++)
				valid[i][j]=true;
		}
		
		for(int i=0;i<length;i++){	    //fuer alle moegliche Linie
			valid[i][i]=false;              //Punkte[i]--[i] ist keine Linie
			for(int j=i;j<length;j++){	//hier haben wir eine Linie P[i]--P[j]
				int cnt=0;				//wieviele Punkte in gleicher Seite?
				int correction=0;		//wenn eine Punkte genau in der Linie steht, wird es auch als "gleich Seite" markiert
				if(i!=j){
						for(int k=0;k<length;k++){  //alle andere Punkte - [P[i]+P[j]]
							if(k!=i && k!=j){   
								if(whichside(P[i],P[j],P[k])>0)		//eine Punkte in recht: cnt++
									cnt++;
							    if(whichside(P[i],P[j],P[k])<0)		//eine Punkte in link: cnt--
									cnt--;
							    if(whichside(P[i],P[j],P[k])==0){	//eine Punkte in line: correction++
							    	correction++;
							    	//System.out.println(" "+i+j+k+" 中间 ："+sort(i,j,k,P[i],P[j],P[k])[1]);
							    	//entfernen die mittele Punkte, wenn drei Punkte in einer Linie stehen
							    	valid[sort(i,j,k,P[i],P[j],P[k])[0]][sort(i,j,k,P[i],P[j],P[k])[1]]=false;
							    	valid[sort(i,j,k,P[i],P[j],P[k])[1]][sort(i,j,k,P[i],P[j],P[k])[0]]=false;
							    	valid[sort(i,j,k,P[i],P[j],P[k])[1]][sort(i,j,k,P[i],P[j],P[k])[2]]=false;
							    	valid[sort(i,j,k,P[i],P[j],P[k])[2]][sort(i,j,k,P[i],P[j],P[k])[1]]=false;
							    }
							}
						}
				}
				 //alle Punkte legen in einer Seite von dieser Linie?	
				if((cnt+correction)==length-2 || (cnt-correction) == -length+2 ||correction == length-2){
				}
				//wenn nicht, ist die Linie nicht in dem Hull
				else{									  
					valid[i][j]=false;		
					valid[j][i]=false;    
				}
			}
		}
	
		
//--------------------alle richtige Linie wurden als valid[i][j]=true markiert--------------------------------------------
		int ax=-1,ay=-1;
		for(int i=0;i<length;i++){					//suchen nach die erste Linie  P[ax]--P[ay]
			for(int j=0;j<length;j++){
				if(valid[i][j]){
					ax=i;
					ay=j;
					j=length;
					i=length;
					list.add(P[ax]);				//der erste Punkt im Hull
					list.add(P[ay]);				//der zweite Punkt im Hull
				}
			}
		}	
		
		int begin=ax;							//Durchlaufabbruch: wenn wieder am "begin" Punkt treffen.
		int cnt=0;
		for(int k=0;k<length;k++){				// suchen nach weitere Linie, vom P[ay]--P[k] an
			if(k!=ax && k!=begin && valid[ay][k]){	//gefunden!	"!=ax"bedeutet, dass es keine "hin und zurueck" Linie ist
				list.add(P[k]);						
				ax=ay;						//ax <- last Punkt
				ay=k;                       //suchen nach P[k]--P[k']
				k=-1;						//k'=k(-1)+1=0, neuer Durchlauf anfangen
				cnt++;
			}
			else if(k==begin && valid[ay][k] && cnt>0){
				//System.out.println("Finalay= "+ay+" P[ax]= "+ P[ay].get(0)+ " "+ P[ay].get(1));
				//System.out.println("Finalk= "+k+" P[k]= "+ P[k].get(0)+ " "+ P[k].get(1));
				k=length;		//Abbruch
			}
		}
		
		return list;
	}
	

	private int[] sort(int i, int j, int k, Point p, Point p2, Point p3) {
		double x1=p.get(0); double y1=p.get(1);
		double x2=p2.get(0); double y2=p2.get(1);
		double x3=p3.get(0); double y3=p3.get(1);
		int[] ret=new int[3];
		if(x1==x2 && x2==x3){
			if(y1>y2 && y2>y3){
				ret[0]=i;ret[1]=j;ret[2]=k;
			}
			if(y1>y3 && y3>y2){
				ret[0]=i;ret[1]=k;ret[2]=j;
			}
			if(y2>y3 && y3>y1){
				ret[0]=j;ret[1]=k;ret[2]=i;
			}
			if(y2>y1 && y1>y3){
				ret[0]=j;ret[1]=i;ret[2]=k;
			}
			if(y3>y1 && y1>y2){
				ret[0]=k;ret[1]=i;ret[2]=j;
			}
			if(y3>y2 && y2>y1){
				ret[0]=k;ret[1]=j;ret[2]=i;
			}
		}
		else{
			if(x1>x2 && x2>x3){
				ret[0]=i;ret[1]=j;ret[2]=k;
			}
			if(x1>x3 && x3>x2){
				ret[0]=i;ret[1]=k;ret[2]=j;
			}
			if(x2>x3 && x3>x1){
				ret[0]=j;ret[1]=k;ret[2]=i;
			}
			if(x2>x1 && x1>x3){
				ret[0]=j;ret[1]=i;ret[2]=k;
			}
			if(x3>x1 && x1>x2){
				ret[0]=k;ret[1]=i;ret[2]=j;
			}
			if(x3>x2 && x2>x1){
				ret[0]=k;ret[1]=j;ret[2]=i;
			}
		}
		return ret;
	}


	private double whichside(Point i, Point j, Point k) {
		double x1=i.get(0); double y1=i.get(1);
		double x2=j.get(0); double y2=j.get(1);
		double x3=k.get(0); double y3=k.get(1);
		return x1*y2+x3*y1+x2*y3-x3*y2-x2*y1-x1*y3;
		
	}
}
