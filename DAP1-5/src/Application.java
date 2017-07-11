
public class Application {
	public static void main(String[] args){
		try{
			if(args.length>0){
				Point[] points = new Point[6];
				for(int i=0;i<3;i++){
					points[i] = new Point( 2,Double.parseDouble(args[2*i]),Double.parseDouble(args[2*i+1]) );
				}
			}
		}
		catch(NumberFormatException e){
			System.out.println("Eingabe soll in Nummer");
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("bitte sechs Parameter eingeben");
			System.exit(0);
		}
		//------------------------Eingabe in gueltiger Form----------------------------------------------
		Point[] points = new Point[6];
		if(args.length>0){          //wenn Parametern gibt, intitalisieren die 3 Punkte
			for(int i=0;i<3;i++){
				points[i] = new Point( 2,Double.parseDouble(args[2*i]),Double.parseDouble(args[2*i+1]) );
			}
		}
		else{						//wenn Parametern nicht gibt, erzeugen 3 Zufall Punkte
			int grenze = 1000;
			java.util.Random numberGenerator = new java.util.Random();
			for(int i=0;i<3;i++){
				double randomNumber = numberGenerator.nextDouble() * grenze;
				if(numberGenerator.nextBoolean())
				randomNumber *= -1;
				double x = randomNumber;
				randomNumber = numberGenerator.nextDouble() * grenze;
				if(numberGenerator.nextBoolean())
				randomNumber *= -1;
				double y = randomNumber;
				points[i] = new Point( 2,x,y );
			}
		}
		Triangel tri = new Triangel(2, points[0], points[1], points[2] );
		System.out.println("Umfang: "+ tri.perimeter());
	}
	
}
