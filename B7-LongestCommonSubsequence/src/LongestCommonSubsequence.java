import java.util.ArrayList;
import java.util.Random;

public class LongestCommonSubsequence {
	public static void main (String[] arr) {
		try {
			int length;
			length = Integer.parseInt(arr[0]);
			if(length<=0) {
				System.out.println("the length shoud be positive");
				System.exit(0);
			}
			if(arr.length>1) {
				System.out.println("there should only be one parameter");
				System.exit(0);
			}
		}
		catch(NumberFormatException e){
			System.out.println("Eingabe soll eine Nummer");
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ein Parameter");
			System.exit(0);
		}
		//-----------------------------Main----------------------------------
		//Zeit messen:
		long tStart, tEnd, msecs;
		msecs=0;
		int laenge;
//		for(int cnt = 0;cnt<10; cnt++) {
			Random ran = new Random();
			laenge = Integer.parseInt(arr[0]);
			String x = new String("");
			String y = new String("");
			x = randStr(laenge, ran);
			y = randStr(laenge, ran);
			System.out.println(x);
			System.out.println(y);
			int[][] z_feld= new int[laenge+1][laenge+1];
			// Beginn der Messung
			tStart = System.currentTimeMillis();
			// Rechnung
			z_feld = lcs(x,y);
			// Ende der Messung
			tEnd = System.currentTimeMillis();
			// Die Vergangene Zeit ist die Differenz von tStart und tEnd
			msecs += tEnd - tStart;
			//-----------------gemeinsame Teilfolge bestimmen------------------------
			//Idee: "Ecke" zu suchen
			ArrayList<Character> aus = new ArrayList<Character>();
			int i = laenge; 
			int j = i;
			while(i>0 && j>0){
				if(z_feld[i][j]>z_feld[i-1][j] && z_feld[i][j]>z_feld[i][j-1]) {
					aus.add(0,x.charAt(i-1));
					i=i-1;
					j=j-1;
				}
				else if( z_feld[i][j]==z_feld[i-1][j] ) {
					i=i-1;
				}
				else {
					j=j-1;
				}
			}
			System.out.println("length = "+ z_feld[laenge][laenge]);
			if(aus.size()!=0) {
				for(int k = 0; k<aus.size();k++) {
					System.out.print(aus.get(k));
				}
				System.out.println();
			}
			else {
				System.out.println("Keine gesamte Teilfolge");
			}
//		}
		msecs=msecs/10;
		System.out.println("durchschnitliche Laufzeit: " + msecs);
		
		int max = lcs(x,y)[laenge][laenge];
		System.out.println("max: "+max);
		for(int p =0;p<=laenge;p++) {
			for(int q = 0; q<=laenge; q++){
				System.out.print(z_feld[p][q]+" ");
				if(q==laenge) {
					System.out.println();
				}
			}
		}
	}
	//--------------------------lcs matrix---------------------------------
    private static int[][] lcs(String x, String y) {
		int m = x.length();
		//Beware: 0 to m means, it contains totally m+1 chars
		int[][] C = new int[m+1][m+1];
		//die erste Zeile sowie Reihe ist 0, da sie keine Bedeutung hat
		for(int i = 0; i<m; i++) {
			C[i][0]=0;
		}
		for(int i = 0; i<m; i++) {
			C[0][i]=0;
		}
		//die matrix[i][j] wird durch "berechnung" bestimmt
		for(int i =1; i<=m; i++)
			for(int j =1; j<=m; j++) {
				laengenberechnung(x,y,C,i,j);
			}
		return C;
	}
    //--------------------------lcs matrix apply----------------------------
	private static void laengenberechnung(String x, String y, int[][] C, int i, int j) {
		//if current x[i] == y[j], that means we've found a Common Character
		//the length of the longest Common Subsequence should be one more longer
		//than the length of the lcs from String x\x[i], String y\y[j] 
		if(x.charAt(i-1)==y.charAt(j-1)) {
			C[i][j]=C[i-1][j-1]+1;
		}
		//if not, the length should keep the same value as 
		//lcs(x\x[i],y), when z[i]!=x[i]
		else if(C[i-1][j]>=C[i][j-1]) {
			C[i][j]=C[i-1][j];
		}
		//or lcs(x,y\y[i]), when z[i]!=y[i]
		else {
			C[i][j]=C[i][j-1];
		}
	}
	//----------------------random String erzeugen-----------------------------
	private static String randStr(int laenge, Random ran) {
		String alphabet = 
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(laenge);
		while(--laenge>=0) {
			res.append(alphabet.charAt(ran.nextInt(alphabet.length())));
		}
		return res.toString();
	}
}
