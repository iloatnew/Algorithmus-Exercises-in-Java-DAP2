
public class Hanoi {
	public static void main(String[] args) {
		try {
			if(args.length!=1) {
				System.out.println("no more than one parameter");
				System.exit(0);
			}
			int n = Integer.parseInt(args[0]);
			if(n<0) {
				System.out.println("positive number of plate");
				System.exit(0);
			}
			move(n,'A','B','C');
		}
		catch (NumberFormatException e) {
			System.out.println("positive length of the array");
			System.exit(0);
		}
	}
//-----------------------------------start,   help,  target------------
	private static void move(int n, char a, char b, char c) {
		if(n==1) {
			System.out.println("Verschiebe oberste Scheibe von "+a+" nach "+c);
		}
		else {
			move(n-1,a,c,b);
			System.out.println("Verschiebe oberste Scheibe von "+a+" nach "+c);
			move(n-1,b,a,c);
		}
		
	}
}
