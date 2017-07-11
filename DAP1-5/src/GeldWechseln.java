
public class GeldWechseln {
	public static void main(String[] args){
		try{
			int wert = Integer.parseInt(args[1]);
			if(!(args[0].equals("Euro") || args[0].equals("Alternativ") ) || wert<=0 || args.length>2 ){
				System.out.println("bitte zwei Parametern angeben, P1:Euro or Alternativ; P2: eine postive Zahl");
				System.exit(0);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("bitte zwei Parametern angeben, P1:Euro or Alternativ; P2: eine postive Zahl");
			System.exit(0);
		}
		catch(NumberFormatException e){
			System.out.println("bitte zwei Parametern angeben, P1:Euro or Alternativ; P2: eine postive Zahl");
			System.exit(0);
		}
//-------------------------------------------------------------------------------------------------------------------------
		int wert = Integer.parseInt(args[1]);
		int[] values;
		if(args[0].equals("Euro")){                           // EURO values
			values = new int[]{200,100,50,20,10,5,2,1};
		}
		else{                                                 // alternative values
			values = new int[]{200,100,50,20,10,5,4,2,1};
		}
//-------------------------------------------------------result-------------------------------------------------------------
		for(int i=0;i<values.length;i++)
			System.out.print(values[i]+":"+change(values,wert)[i]+"; ");
		System.out.println();
	}
//-----------------------------------------------------tausch-------------------------------------------------------------
	private static int[] change(int[] values, int wert) {
		int[] weight=new int[values.length];
		for(int i=0;i<values.length;i++){
			weight[i]=wert/values[i];          //gierige Algorithmus, so groß wie möglich tauschen
			wert%=values[i];                   //das reste Geld
		}
		return weight;
		
	}
	
}
