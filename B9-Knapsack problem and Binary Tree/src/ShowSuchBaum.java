import java.util.StringTokenizer;

public class ShowSuchBaum {
	// warte auf einer Eingabe von Array
	public static void main( String[] args){
		try {
			if(args.length!=1) {
				System.out.println("give no more than one array");
				System.exit(0);
			}
		    // Baue ein Baum
		    SuchBaum baum = new SuchBaum();
		    StringTokenizer token = new StringTokenizer(args[0], ",");   		    
		    while (token.hasMoreElements()) {     
			      baum.set( Integer.valueOf(token.nextToken()) );
			}
		    System.out.println("in order: ");
		    baum.show_in();
		    System.out.println("");
		    System.out.println("pre order: ");
		    baum.show_pre();
		    System.out.println("");
		    System.out.println("post order: ");
		    baum.show_post();
		    System.out.println("");
		}
		catch (NumberFormatException e) {
			System.out.println("array contains only integer and ','");
			System.exit(0);
		}
	}
}
