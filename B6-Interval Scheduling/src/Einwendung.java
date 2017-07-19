
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
public class Einwendung {
//-------------------------------------IntervalScheduling-------------------------------------------------------	
	private static ArrayList<Scheduleable> intervalScheduling(ArrayList<Scheduleable> intervals){
		//--------------------------------------Before Sortierung------------------------------------------------
		insertionSort(intervals);
//		System.out.println("----------------after sort---------------------");
//		for(int i=0;i<intervals.size();i++){
//			System.out.println(intervals.get(i).toString());
//		}
		//--------------------------------------After Sortierung-------------------------------------------------
		ArrayList<Scheduleable> schedlist= new ArrayList<Scheduleable>();
		schedlist.add(intervals.get(0));
		int j = 0;
		for(int i=1;i<intervals.size();i++){
			if(intervals.get(i).getA()>=schedlist.get(j).getB()){
				schedlist.add(intervals.get(i));
			    j++;
			}
		}
		//--------------------------------------After Scheduling--------------------------------------------------
		return schedlist;
	}

//--------------------------------------Lateness Scheduling-----------------------------------------------------------
	private static int[] latenessScheduling(ArrayList<Scheduleable> list) {
		int[] retarr = new int[list.size()];
		int z = 0;
		//--------------------------------------Before Sortierung------------------------------------------------
		insertionSort(list);
		//--------------------------------------After Sortierung-------------------------------------------------
		for(int i=0;i<list.size();i++){
			retarr[i]=z;
			z+=list.get(i).getA();
		}
		return retarr;
	}
//-----------------------------------------Sortierung--------------------------------------------------------------------
	 private static void insertionSort(ArrayList<Scheduleable> arr) {
     	int n = arr.size();  
         for (int j = 1; j < n; j++) { 
         	//von Erste bis Letzte
             Interval temp = new Interval(arr.get(j).getA(),arr.get(j).getB());  
             int i = j-1;  
             //bis neues Element temp>=feld[i]
             while ( (i > -1) && ( arr.get(i).getB() > temp.getB() ) ) {  
                 arr.set(i+1, arr.get(i));  //  arr [i+1] = arr [i];  
                 i--;  
             }  
             //insertion
             arr.set(i+1,temp);  
         }  
     }
//------------------------------------------------------try----------------------------------------------------------------	
	public static void main(String[] args) throws IOException{
		try{
            if(!( args[0].equals("Interval") || args[0].equals("Lateness") ) ){
            	System.out.println("Interval or Lateness");
            	System.exit(0);
            }
			RandomAccessFile file = new RandomAccessFile(args[1],"r");
			file.close();
			// /home/wentao/workspace/B6/src/datenBsp1.zahlen
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("zwei Parameter");
			System.exit(0);
		}
//------------------------------------------------------main-------------------------------------------------------------
		ArrayList<Scheduleable>list = new ArrayList<Scheduleable>();   //list for Interval Scheduling
		RandomAccessFile file = new RandomAccessFile(args[1],"r");
		String zeile = file.readLine();
		//----------------original list einfügen-------------------------------------------------------------------------
			while(zeile!=null){
				try{
//					StringTokenizer st = new StringTokenizer(zeile,",");
//					int start = Integer.parseInt(st.nextToken());
//					int ende = Integer.parseInt(st.nextToken());
				}
				catch(NumberFormatException e){
					System.out.println("Eingabe soll eine Nummer");
					System.exit(0);
				}
				StringTokenizer st = new StringTokenizer(zeile,",");
				int start = Integer.parseInt(st.nextToken());
				int ende = Integer.parseInt(st.nextToken());
				if(args[0].equals("Interval")){
					Interval ivall = new Interval(start, ende);
					list.add(ivall);
				}
				else{
					Job ajob = new Job(start, ende);
					list.add(ajob);
				}
				zeile = file.readLine();
			}
		file.close();
		//---------------------print-------------------------------------------------------------------------------
		System.out.println("schedule type: "+ args[0]);
		System.out.println("------------------before scheduling----------------");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
		//----------------------------------------------------------Scheduling---------------------------------------
		if(args[0].equals("Interval")){
			list = intervalScheduling(list);
			System.out.println("---------------------after schelduling--------------------------");
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).toString());
			}
		}
		else{
			int[] ss = latenessScheduling(list);
			ArrayList<Scheduleable> calcuWorst = new ArrayList<Scheduleable>();
			System.out.println("--------------------after schelduling-------------------------");
			for(int i=0;i<ss.length;i++){
				int begin = ss[i];
				int end = ss[i]+list.get(i).getA();
				int deadline = list.get(i).getB();
				int verspaetung = end - deadline;
				if(verspaetung<0)
					verspaetung = 0;
				Job ajob = new Job(0, verspaetung);
				calcuWorst.add(ajob);
				System.out.println("begin: "+ begin+ "; end: " +end+"; deadline: "+deadline + "; vespaetung: " + verspaetung);
			}
			latenessScheduling(calcuWorst);
			System.out.println("Worst ist: "+calcuWorst.get(calcuWorst.size()-1).getB());
		}
		
	}
}
