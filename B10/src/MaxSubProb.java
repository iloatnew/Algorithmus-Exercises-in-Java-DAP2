import java.util.ArrayList;
import java.util.Random;

public class MaxSubProb {
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> maxNaive(ArrayList<Integer> A) {
		int maxSum = 0;
	    int thisSum = 0;
	    ArrayList<Integer> tmpList = new ArrayList<Integer>();
	    ArrayList<Integer> retList = new ArrayList<Integer>();
	    int len = A.size();
	    for (int i = 0; i < len; i++) {
	        thisSum = 0; 
	        tmpList.clear();
	        // Berechne alle Summe[i,j] für alle i,j mit j>i
	        for (int j = i; j < len; j++) {
	            thisSum += A.get(j);
	            tmpList.add(A.get(j));
	            if (thisSum > maxSum) {
	                maxSum = thisSum;
//	            	retList = (ArrayList<Integer>) tmpList.clone();
//	                retList = tmpList;
	            }
	        }
	    }
//	    for(Integer i:retList) {
//	    	System.out.print(i + " ");
//	    }
//	    System.out.println();
	    return retList;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> maxDynpro (ArrayList<Integer> args){
		ArrayList<Integer> retList = new ArrayList<Integer>();
		ArrayList<Integer> kandiList = new ArrayList<Integer>();
		int teilsum = 0;  //E(i,j)A[k]
		int curmax[] = new int[args.size()+1];	//max[1...j]
		curmax[0]=0;	//max[null]
		int j=0;
		while(j<args.size()) { //Zeiger von 0 bis size()-1
			if(teilsum+args.get(j)>=0) { // E(i,j-1)+Wert(j)>0
				teilsum+=args.get(j);   // E(i,j) erweitet
				kandiList.add(args.get(j));		//kandiList erweitet
			}
			else {
				teilsum=0;	
				kandiList.clear();
			}
			j=j+1;
			curmax[j] = Math.max(teilsum,curmax[j-1]);
			//retList(j) = max(kandiList,retList(j-1)
			// Problem: clone daurt O(n^2)
			if(teilsum>curmax[j-1]) {
//				retList = (ArrayList<Integer>)kandiList.clone();
//				retList = kandiList;
			}
		}
//		for(Integer i:retList) {
//	    	System.out.print(i + " ");
//	    }
//	    System.out.println();
		return retList;
	}
	
	public static void main (String[] args) {
		try {
			if(args.length!=1) {
				System.out.println("no more than one parameter");
				System.exit(0);
			}
			int n = Integer.parseInt(args[0]);
			if(n<0) {
				System.out.println("length at least: 1");
				System.exit(0);
			}
		    // Baue ein Array
			Random ran = new Random();
			ArrayList<Integer> arg = new ArrayList<Integer>();
		    for(int i=0;i<n;i++) {
		    	arg.add(-80+ran.nextInt(101));
		    }
			long tStart, tEnd;
			//-------------------------Naive------------------------------
		    System.out.println("Naive");
			tStart = System.currentTimeMillis();
		    maxNaive(arg);
			tEnd = System.currentTimeMillis();
			System.out.println("Time by Naive is: " + (tEnd-tStart));
			//------------------------DynPro-------------------------------
		    System.out.println("Dynpro");
			tStart = System.currentTimeMillis();
		    maxDynpro(arg);
		    tEnd = System.currentTimeMillis();
		    System.out.println("Time by Dynpro is: " + (tEnd-tStart));
		}
		catch (NumberFormatException e) {
			System.out.println("positive length of the array");
			System.exit(0);
		}
	}
}
