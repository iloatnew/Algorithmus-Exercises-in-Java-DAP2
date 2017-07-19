import java.util.Random;

public class PriorityQueue {
	public static void main(String[] args) {
		try {
			if(args.length!=2) {
				System.out.println("please give 2 parameter");
				System.exit(0);
			}
			int n = Integer.parseInt(args[0]);
			int k = Integer.parseInt(args[1]);
			if(n<0||k<0) {
				System.out.println("please give 2 positive number");
				System.exit(0);
			}
			Random ran = new Random();
			Random prozent = new Random();
			Heap heap = new Heap(n+k);
			for(int i=0;i<n;i++) {
				System.out.println("new job");
				heap.insert(ran.nextInt(101));
				heap.printHeap();
			}
			for(int j=0;j<k;j++) {
				if(prozent.nextInt(101)>=75) {
					System.out.println("new job");
					heap.insert(ran.nextInt(101));
					heap.printHeap();
				}
				else {
					System.out.println("old job done");
					heap.extractMax();
					heap.printHeap();
				}
			}
		}
		catch(NumberFormatException e) {
			System.out.println("please give 2 number");
			System.exit(0);
		}
	}
}
