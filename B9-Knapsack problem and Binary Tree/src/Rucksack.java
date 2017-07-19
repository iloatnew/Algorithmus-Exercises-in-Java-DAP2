import java.util.Random;

public class Rucksack {

	private static Random ran = new Random();
	
	public static void main(String[] args) {
		try {
			if(args.length==3) {
				int n = Integer.parseInt(args[0]);
				int G = Integer.parseInt(args[1]);
				int p = Integer.parseInt(args[2]);
				Ware[] ware = new Ware[n];
				// set n ware with wert [0.8p,1.25p], gewicht[100,1000]
				for(int i=0;i<n;i++) {
					ware[i] = new Ware(0.45*p*ran.nextDouble()+0.8*p,
							100+ran.nextInt(901));
					System.out.println("Nr "+i+"; wert: "+ware[i].getWert()+
							"; gewicht: "+ware[i].getGewicht());
				}
				System.out.println("mit DynPro :"+RuckSack_dynpro(ware, G));
				System.out.println("mit Gierig :"+RuckSack_gierig(ware, G));
			}
			else {
				System.out.println("3 Parameter: n, G, p");
				System.exit(0);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Success Input only with Integer");
			System.exit(0);
		}
	}

	private static double RuckSack_gierig(Ware[] ware, int G) {
		double gierig[] = new double[ware.length];
		boolean avai[] = new boolean[ware.length];
		for(int i=0;i<ware.length;i++) {
			avai[i] = true;
		}
		for(int i=0;i<ware.length;i++) {
			gierig[i] = ware[i].getWert()/ware[i].getGewicht();
			System.out.println("Nr"+i+" hat Prozent: "+gierig[i]);
		}
		int cnt = 0;
		double max = 0;
		double ret = 0;
		int max_index = 0;
		int cur_gewicht = 0;
		while(cur_gewicht<=G && cnt<ware.length) {
			for(int i=0;i<ware.length;i++) {
				if(avai[i]) {
					if(gierig[i]>max) {
//						System.out.println(gierig[i]+" > "+max);
						max = gierig[i];
						max_index = i;
					}
				}
			}
			cnt++;			
			cur_gewicht += ware[max_index].getGewicht();
			if(cur_gewicht<=G) {
				System.out.println("...add ware["+max_index+"]");
				ret += ware[max_index].getWert();
			}
			avai[max_index] = false;
			max = 0;
		}
		return ret;
	}

	private static double RuckSack_dynpro(Ware[] ware, int G) {
		double[][] opt = new double[ware.length][G+1];
		// i: anzahle der Waren; j:obere Schranke der Gewicht
		for(int j=0; j<=G; j++) {
			opt[0][j] = 0;
		}
		for(int i=1; i<ware.length; i++) {
			for(int j=0; j<=G; j++) {
				if(i>0 && j>=ware[i].getGewicht()) {
					opt[i][j] = Math.max(opt[i-1][j],
							ware[i].getWert()+opt[i-1][j-ware[i].getGewicht()]);
				}
				else {
					opt[i][j] = opt[i-1][j];
				}
			}
		}
//		for(int i=0; i<ware.length; i++) {
//			for(int j = 0; j<=G; j++) {
//				System.out.print(opt[i][j] + " ");
//				if(j==G) {
//					System.out.println();
//				}
//			}
//		}
//		System.out.println();
		return opt[ware.length-1][G];
		
	}
}
