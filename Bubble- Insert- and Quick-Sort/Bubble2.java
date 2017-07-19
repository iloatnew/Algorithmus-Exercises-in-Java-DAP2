

public class Bubble2 {
	public static void bubbleSort(int[] array){
		int n = array.length;
		int tmp;
		for(int i=0;i<n;i++){
			for(int j=n-1;j>=i+1;j--){
				if(array[j-1]>array[j]){
					tmp=array[j];
					array[j]=array[j-1];
					array[j-1]=tmp;
				}
			}
		}
	}
	
	public static void main(String[] arr){
		try{
			float n = Float.parseFloat(arr[0]);
			if(n<0){
				System.out.println("bitte positive Zahle angeben");
				System.exit(0);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("bitte Eine Parameter angeben");
			System.exit(0);
		}
		catch(NumberFormatException e){
			System.out.println("Eingabe soll eine Nummer");
			System.exit(0);
		}
		float t = Float.parseFloat(arr[0]);
		//originale Laenge
		int n= 1000;
		while(bubbleMess(n)<t){
			//Laenge des Felds, wenn Laufzeit/2 <t <Laufzeit
			n=2*n;
		}
		binaersuche(n,t);
	}

	private static void binaersuche(int n, float t) {
		// untere Schranke
		int low = n/2;
		//obere Schranke
		int high = n;
		//Mittel
		int mid = (low + high)/2;
		//Counter 
		int cnt=1;
		// wirkliche Laufzeit
		float treal=bubbleMess(mid);
		//Ende von "while": |treal- gegebene Zeit t| < 0.1 
		while( (treal-t)>0.1f || (treal-t)<-0.1f){
			cnt++;
			System.out.println("Zyklus der Binaersuce: "+cnt);
			System.out.println("Feldgroesse: "+mid);
			System.out.println("Bubblelaufzeit: "+treal);
			//Binaersuche
			if(treal<t){
				low=mid;
				mid=(low + high)/2;
			}
			else{
				high=mid;
				mid=(low + high)/2;
			}
			treal=bubbleMess(mid);
		}
		System.out.println("Zyklus der Binaersuce: "+ cnt);
		System.out.println("Feldgroesse: "+mid);
		System.out.println("Bubblelaufzeit: "+treal);
	}

	private static float bubbleMess(int n){
		long[] msecs=new long[5];
		float durch=0;
		int arr1[] = new int[n];
		for(int cnt=0;cnt<5;cnt++){
			java.util.Random numberGenerator = new java.util.Random();
			for(int i=0;i<arr1.length;i++){
				arr1[i]= numberGenerator.nextInt();
			}
			long tStart, tEnd;
			tStart = System.currentTimeMillis();
			bubbleSort(arr1);
			tEnd = System.currentTimeMillis();
			msecs[cnt] = tEnd - tStart;
		}
		for(int i=0;i<5;i++){
			durch+=msecs[i];
		}
		//durch: Laufzeit vom Bubblesort
		durch=durch/5000.0f;
		if(!sortiert(arr1)){
			System.out.println("nicht sortiert! ");
			System.exit(0);
		}
		return durch;
	}
	
	private static boolean sortiert(int[] arr1) {
		for(int i=1;i<arr1.length;i++){
			if(arr1[i]<arr1[i-1])
				return false;
		}
		return true;
	}
}

