
public class Bubble {
	private static void bubbleSort(int[] array){
		int n = array.length;
		int tmp;
		for(int i=0;i<n;i++){
			for(int j=n-1;j>=i+1;j--){
				//austauschen, wenn leztes Element groesser als dieses Element ist
				if(array[j-1]>array[j]){
					tmp=array[j];
					array[j]=array[j-1];
					array[j-1]=tmp;
				}
			}
		}
	}
	
	public static void main(String[] arr){
//		try{
//			int n = Integer.parseInt(arr[0],10);
//		}
//		catch(NegativeArraySizeException e)
//		{
//			System.out.println("Eingabe soll positive Zahl");
//			System.exit(0);
//		}
//		catch(ArrayIndexOutOfBoundsException e){
//			System.out.println("bitte Eine Zahl angeben");
//			System.exit(0);
//		}
//		catch(NumberFormatException e){
//			System.out.println("Eingabe soll eine Nummer");
//			System.exit(0);
//		}
		int n = Integer.parseInt(arr[0]);
		int arr1[] = new int[n];
		long[] msecs=new long[5];
		double durch=0;
		for(int cnt=0;cnt<5;cnt++){
			// eine neue Liste erzeugen
			arr1=zufallArray(n);
			//Laufzeit messen
			long tStart, tEnd;
			tStart = System.currentTimeMillis();
			bubbleSort(arr1);
			tEnd = System.currentTimeMillis();
			msecs[cnt] = tEnd - tStart;
		}
		//Durchschnitt rechnen
		for(int i=0;i<5;i++){
			durch+=msecs[i];
		}
		durch=durch/5000;
		if(sortiert(arr1))
			//Milisekunde umrechnen nach Sekunde
			System.out.println("Laufzeit Bubble: "+durch+" O(n^2)");
		else
			System.out.println("nicht sortiert! ");
	}

	private static boolean sortiert(int[] arr1) {
		for(int i=1;i<arr1.length;i++){
			if(arr1[i]<arr1[i-1])
				return false;
		}
		return true;
	}
	private static int[] zufallArray(int size) {
		int[] feld =new int[size];
		java.util.Random numberGenerator = new java.util.Random();
        for(int i=0;i<size;i++){
                feld[i]=numberGenerator.nextInt();
        }	
		return feld;
	}
}
