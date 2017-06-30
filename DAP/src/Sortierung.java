public class Sortierung {

            public static void main (String[] args){
                    // ob das erste Parameter eine positive Zahl ist?
//                    try{
//                    	int size=Integer.parseInt(args[0]);
//                		if(size<0){
//            				System.out.println("bitte positive Zahle angeben");
//            				System.exit(0);
//            			}
//                        int[] feld = new int[size];
//                    }
//                    catch(ArrayIndexOutOfBoundsException e){
//            			System.out.println("bitte nur ein Parameter angeben");
//            			System.exit(0);
//            		}
//            		catch(NumberFormatException e){
//            			System.out.println("Eingabe soll eine Nummer");
//            			System.exit(0);
//            		}
//                    //create an array 
                    boolean isinsert = false;
                    int size=Integer.parseInt(args[0]);
                    int[] feld = new int[size];
                    // default type : random, merge
                    if(args.length == 1){
                    	feld=zufallArray(size);	
                    }
                    
                    // type defined by parameter
                    if(args.length == 3){
                            if(args[2].equals("auf")) 
                                    for(int i=0;i<size;i++){
                                            feld[i]=i;
                                    }
                              
                            else if(args[2].equals("ab"))
                                    for(int i=size;i>0;i--){
                                            feld[size-i]=i;
                                    }
                                    
                            else if(args[2].equals("rand")){
                            		feld=zufallArray(size);      
                            }
            // invalid type
                            else{
                                    System.out.println("invalid type");
                                    System.exit(0);
                            }
                
                    // Sortierungstyp
		                    if(args[1].equals("insert")){
		                    	isinsert = true;
		                    }
		                    else{
		                    	if(args[1].equals("merge")){
		                    		isinsert = false;
		                    	}
		                    	else{
		                    		System.out.println("invalid sort-type");
		                    		System.exit(0);
		                    	}
		                    }
                    }
                    //Übergabeparametern
                    if(args.length > 3 || args.length == 2){
                            System.out.println("Falsche Parameternlänge");
                            System.exit(0);
                    }
                    //Laufzeit
                    long[] tStart = new long[5], tEnd = new long[5], msecs = new long[5];
                    double durch = 0;
                    //Sortierung: Laufzeit im Durchschnitt
                    for(int i=0;i<5;i++){
                    		feld=zufallArray(size);
                            tStart[i] = System.currentTimeMillis();
                            //merge oder insert?
                            if(isinsert)
                            	insertionSort(feld);
                            else
                            	mergeSort(feld);
                            tEnd[i] = System.currentTimeMillis();
                            msecs[i] = tEnd[i] - tStart[i];
                            
                            //muss ein positive Zahl sein
             
                            assert msecs[i]>0 : "Laufzeitfalsch";
                    }
                    //durchschnittliche Laufzeit
                    for(int i=0;i<5;i++){
                            durch+=msecs[i];
                    }
                    durch=durch/5000;
                    
                    //Sortiert?
                    if(isSorted(feld))
                    	if(isinsert)
                    		System.out.println("Laufzeit Insert: "+durch +" O(n^2)");
                    	else
                    		System.out.println("Laufzeit Merge: "+durch+ " O(nlogn)");
                
                    
                    //praezentiert, wenn <=100 Element in feld steht
//                    if(size<=100){
//                    	for(int i=0;i<size;i++){
//                    		System.out.print(" "+feld[i]);
//                    	}
//                    	System.out.println("");
//                    }
                                    
            }
            
            private static int[] zufallArray(int size) {
        		int[] feld =new int[size];
        		java.util.Random numberGenerator = new java.util.Random();
                for(int i=0;i<size;i++){
                        feld[i]=numberGenerator.nextInt();
                }	
        		return feld;
        	}
            
            private static boolean isSorted(int[] feld) {
                    for(int i=0;i<feld.length-1;i++){
                    	if(feld[i+1]<feld[i])
                    		return false;
                    }
                    return true;
            }

            private static void insertionSort(int[] feld) {
            	int n = feld.length;  
                for (int j = 1; j < n; j++) { 
                	//von Erste bis Letzte
                    int temp = feld[j];  
                    int i = j-1;  
                    //bis neues Element temp>=feld[i]
                    while ( (i > -1) && ( feld [i] > temp ) ) {  
                        feld [i+1] = feld [i];  
                        i--;  
                    }  
                    //insertion
                    feld[i+1] = temp;  
                }  
            }
            
            public static void mergeSort(int[] array) {
            	int[] tmpArray = new int[array.length];
            	mergeSort(array, tmpArray, 0, array.length-1);
            }

			private static void mergeSort(int[] array, int[] tmpArray, int left, int right) {
				//Brechung Voraussetzung
				if(left>=right)
					return;
				int mid = (left+right)/2;
				//Teile in zwei left-mid und mid-right, rekursiv
				mergeSort(array, tmpArray, left, mid);
				mergeSort(array, tmpArray, mid+1, right);
				//Vereinigen
				merge(array, tmpArray, left, mid, right);
				assert isSorted(array);
				
			}
			
			private static void merge(int[] arr1, int[] arr2, int left, int mid, int right){
				//int[] tmp = new int[arr1.length];
				int r1 = mid + 1;
		        int tIndex = left;
		        int cIndex=left;
		        // sortiren, bis eine der beiden Teilfolge sich endet
		        while(left <=mid && r1 <= right) {
		            if (arr1[left] <= arr1[r1]) 
		                arr2[tIndex++] = arr1[left++];
		            else
		                arr2[tIndex++] = arr1[r1++];
		        }
	            //sortiren weiter das "left-mid" Teil
	            while (left <=mid) {
	                arr2[tIndex++] = arr1[left++];
	            }
	            // sortiren das "mid-right" Teil
	            while ( r1 <= right ) {
	                arr2[tIndex++] = arr1[r1++];
	            }
	          //kopieren die sortierte Folge zurueck (von left bis right)
	            while(cIndex<=right){
	                arr1[cIndex]=arr2[cIndex];
	                cIndex++;
	            }
			}	
}      
