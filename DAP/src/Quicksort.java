
public class Quicksort {
	public static void main(String[] args){
//		try{
//    		int size=Integer.parseInt(args[0]);
//    		if(size<0){
//				System.out.println("bitte positive Zahle angeben");
//				System.exit(0);
//			}
//            int[] feld = new int[size];
//		}
//		catch(ArrayIndexOutOfBoundsException e){
//			System.out.println("bitte nur ein Parameter angeben");
//			System.exit(0);
//		}
//		catch(NumberFormatException e){
//			System.out.println("Eingabe soll eine Nummer");
//			System.exit(0);
//		}
//-------------------------------------------parameter---------------------------------------------------------------
		int size=Integer.parseInt(args[0]);            //size of array
		int[] feld = new int[size];                    //Intiatalize: the input array
		long msecs[]= new long[5];                     //durchschnitt von 5 times
	    double durch=0;
//-----------------------------------------durchschinittlaufzeit rechnen-------------------------------------------------
		for(int cnt=0;cnt<5;cnt++){
			feld=zufallArray(size);
			long tStart, tEnd;
			tStart = System.currentTimeMillis();         //start time
	        quickSort(feld,0,feld.length-1);
	        tEnd = System.currentTimeMillis();            //end time
	        msecs[cnt]+= tEnd - tStart;
		}
		for(int i=0;i<5;i++)
			durch+=msecs[i];
		durch = durch/5000;
        if(isSortiert(feld)){
        	System.out.println("Laufzeit Quicksort: "+durch + "O(nlogn)");
        }
//--------------------------------------Laufzeit von anderen Methoden-------------------------------------------------------
        String[] inpu = new String[3];
		inpu[0]=Integer.toString(Integer.valueOf(size));
		Bubble.main(inpu);
        
        inpu[1]="insert";
        inpu[2]="rand";
        Sortierung.main(inpu);		
        
        inpu[1]="merge";
        Sortierung.main(inpu);	
	}
//-------------------------------------------------------ZufallArray----------------------------------------------------------------	
	private static int[] zufallArray(int size) {
		int[] feld =new int[size];
		java.util.Random numberGenerator = new java.util.Random();
        for(int i=0;i<size;i++){
                feld[i]=numberGenerator.nextInt();
        }	
		return feld;
	}
	//----------------------------------quickSort--------------------------------------------------------------------------
	private static void quickSort(int[] feld,int l, int r){
		if(l<r){
			int i = l;
			int j = r;
			int pivot = feld[(l+r)/2];						//pivot
			while(i<=j){                     //bis kein Element im linken Teil größer als pivot Element ist.
				                             //    kein Element im rechten Teil kleiner als pivot Element ist.
				while(feld[i]<pivot){        //tauscht das linke Elment, die größer als pivot ist  --
					i++;
				}
				while(feld[j]>pivot){        // --mit dem rechten Element, die kleiner als pivot ist
					j--;
				}
				if(i<=j){
					int tmp=feld[i];
					feld[i]=feld[j];
					feld[j]=tmp;
					i++;
					j--;
				}
			}
			quickSort(feld,l,j);
			quickSort(feld,i,r);
		}
	}
		

	private static boolean isSortiert(int[] arr1) {
		for(int i=1;i<arr1.length;i++){
			if(arr1[i]<arr1[i-1])
				return false;
		}
		return true;
	}
}
