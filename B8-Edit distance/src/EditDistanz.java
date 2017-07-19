import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class EditDistanz {

	public static int distance(String a, String b) {
		int[][] dist = new int[a.length()+1][b.length()+1];
		int cost;
		// a String with length i, needs i step to transform to a null String
		for(int i = 0; i<=a.length(); i++)
			dist[i][0] = i;
		// so do j
		for(int j = 0; j<=b.length(); j++)
			dist[0][j] = j;
		//
		for(int i = 1; i<=a.length(); i++) {
			for(int j = 1; j<=b.length(); j++) {
				// if a and b in this position share a same Character, cost is 0
				if(a.charAt(i-1) == b.charAt(j-1)) {
					cost = 0;
				}
				else {
					cost = 1;
				}
				// which one is cheapest? delete, insert, or change?
				dist[i][j] = min(dist[i-1][j]+1, dist[i][j-1]+1, dist[i-1][j-1]+cost);
				
			}
		}
		return dist[a.length()][b.length()];
	}
	
	private static int min(int i, int j, int k) {
		if(i<=j && i<=k)
			return i;
		else if(j<=k && j<=i)
			return j;
		else
			return k;
	}

	//------------------------------------------------------try----------------------------------------------------------------	
	public static void main(String[] args) throws IOException{
		try{
            if(args.length == 1 || (args.length == 2) && (args[1].equals("-o")) ){
            	File directory = new File("");
   			 	String verzeichnis = directory.getCanonicalPath();
            	RandomAccessFile file = new RandomAccessFile(verzeichnis+"/"+args[0],"r");
				file.close();
            }
            else if(args.length == 2 || (args.length == 3) && (args[2].equals("-o")) ){
            }
            else {
            	System.out.println("incorrect form of Parameters");
            	System.exit(0);
            }
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("zwei Parameter");
			System.exit(0);
		}
	//--------------------------------------------main---------------------------------
		if(args.length == 1 || (args.length == 2) && (args[1].equals("-o")) ){
           	 int cnt = 0;
			 File directory = new File("");
			 String verzeichnis = directory.getCanonicalPath();
         	 RandomAccessFile file = new RandomAccessFile(verzeichnis+"/"+args[0],"r");
         	 String zeile = file.readLine();
         	// bestimmt die Anzahl von Zeilen in "cnt"
         	while(zeile!=null){
         		zeile = file.readLine();
         		cnt++;
         	}
         	file.close();
         	
         	// String[] für gelesene Datai
         	String[] datai = new String[cnt];
         	
         	// String[] for results
         	//String[] result = new String[(cnt-1)*(cnt)/2];
         	
         	// nochmal lesen
         	file = new RandomAccessFile(verzeichnis+"/"+args[0],"r");
         	for(int i = 0; i < cnt; i++){
         		zeile = file.readLine();
         		datai[i] = zeile;
         	}
         	for(int i = 0; i < cnt-1; i++) {
         		for(int j = i+1; j < cnt; j++) {
         			if(args.length==2) {
         				printEditOperations(datai[i],datai[j]);
         			}
         			else {
         				System.out.println("the distance between " + datai[i] 
         			        	 + " and " + datai[j] + " is " 
         	         			+ distance(datai[i],datai[j]) );
         			}
         		}
         	}
			file.close();
         }
         else {
        	 String a = args[0];
        	 String b = args[1];
        	 if(args.length==3) {
  				printEditOperations(a,b);
  			 }
        	 else {
        	 System.out.println("the distance between " + a 
        	 + " and " + b + " is " + distance(a,b));
        	 }
         }
	}

	private static void printEditOperations(String a, String b) {
		int[][] dist = new int[a.length()+1][b.length()+1];
		int cost;
		// a String with length i, needs i step to transform to a null String
		for(int i = 0; i<=a.length(); i++)
			dist[i][0] = i;
		// so do j
		for(int j = 0; j<=b.length(); j++)
			dist[0][j] = j;
		//
		for(int i = 1; i<=a.length(); i++) {
			for(int j = 1; j<=b.length(); j++) {
				// if a and b in this position share a same Character, cost is 0
				if(a.charAt(i-1) == b.charAt(j-1)) {
					cost = 0;
				}
				else {
					cost = 1;
				}
				// which one is cheapest? delete, insert, or change?
				dist[i][j] = min(dist[i-1][j]+1, dist[i][j-1]+1, dist[i-1][j-1]+cost);
				
			}
		}
		System.out.println("the distance between "
      			+ a + " and " + b + " is " 
      			+ dist[a.length()][b.length()] );
		System.out.println("==============================================");
		
		// until now, the result array is created, now try to print:
		ArrayList<String> aus = new ArrayList<String>();
		ArrayList<Character> B = new ArrayList<Character>();
		for(int i = 0; i<b.length(); i++) {
			B.add(b.charAt(i));
		}
		String step = new String();
		// aktuelle String
		int i = a.length(); 
		int j = b.length();
		while(i>0 && j>0){
			// Losche!
			/* 3 3  
			 * 4 4  
			 * */
			if(dist[i-1][j] == min(dist[i-1][j], dist[i-1][j-1], dist[i][j-1]) 
					&& dist[i-1][j]<dist[i][j]) {
				// Before delete, it was a character from a[i-1] at the position 
				// j?
				step = "Kosten 1 "+"Loesche "+ a.charAt(i-1)+" in Position "
					+ String.valueOf(j)+ " -->" + B.toString();
				B.add(j,a.charAt(i-1));
				i=i-1;
			}
			// Tausche:
			/* 2 3        2 2
			 * 3 3   or   2 2
			 */
			else if( dist[i-1][j-1]==min(dist[i-1][j], dist[i-1][j-1], dist[i][j-1])) {
				if(dist[i-1][j-1] == dist[i][j]) {
					// before swap, it was a character from a[i-1] at the position 
					// j-1?
					step = "Kosten 0 "+"Erzetze "+a.charAt(i-1)+ " mit " 
							+ a.charAt(i-1)+ " in Position "
							+ String.valueOf(j-1)+" -->" + B.toString();
				}
				else {
					step = "Kosten 1 "+"Erzetze "+a.charAt(i-1)+ " mit " 
							+ B.get(j-1)+ " in Position "
							+ String.valueOf(j-1)+ " -->" + B.toString();
					B.remove(j-1);
					B.add(j-1,a.charAt(i-1));
				}
				i=i-1;
				j=j-1;
			}
			// insert!
			/* 3 3
			 * 2 3
			 */
			else {
				// before insert, it was no Character in the place
				// j-1. the insertet value should be B[j-1]
				step = "Kosten 1 "+"Fuege "+ B.get(j-1) + " in Position "
						+String.valueOf(j-1)+  " -->" + B.toString();
				B.remove(j-1);
				j=j-1;
			}
			aus.add(step);
		}
		while(i>0) {
			step = "Kosten 1 "+"Loesche "+ a.charAt(i-1)+ " in Position "
					+String.valueOf(j)+ " -->" + B.toString();
			B.add(a.charAt(i-1));
			i=i-1;
			aus.add(step);
		}
		while(j>0) {
			step = "Kosten 1 "+"Fuege "+ B.get(j-1) + " in Position "
					+String.valueOf(j-1)+ " -->" + B.toString() ;
			B.remove(j-1);
			j=j-1;
			aus.add(step);
		}
		for(int p = aus.size()-1;p>=0;p--) {
			System.out.println(String.valueOf(aus.size()-p)+") "+aus.get(p));
		}
		System.out.println();
	}
}
