
public class Heap {
	private int curSize;
	private int heapSize;
	private int[] heap;
	public Heap( int n) {
		heapSize = n;
		curSize=0;
		heap = new int[heapSize+1];
		heap[0] = Integer.MIN_VALUE;
	}
	
	public int left(int i) {
		if(i*2<=curSize && i>0) {
			return i*2;
		}
		else {
			System.out.println("unavailible index left");
			System.exit(0);
			return Integer.MIN_VALUE;
		}
	}
	
	public int right(int i) {
		if(i*2+1<=curSize && i>0) {
			return i*2+1;
		}
		else {
			System.out.println("unavailible index right");
			System.exit(0);
			return Integer.MIN_VALUE;
		}
	}
		
	public int parent(int i) {
		if(i>1 && i<=curSize) {
			return i/2;
		}
		else {
			System.out.println("unavailible index parent");
			System.exit(0);
			return Integer.MIN_VALUE;
		}
	}
	
	public int heapify(int i) {
		if(i>0 && i<=curSize) {
			return heap[i];
		}
		else {
			System.out.println("unavailible index heapify");
			System.exit(0);
			return Integer.MIN_VALUE;
		}
	}
	
	public void insert(int key) {
		if(curSize<heapSize) {
			curSize++;
			heap[curSize]=key;
			int i = curSize;
			if(i!=1) {
				while(heapify(parent(i))<heapify(i)) {
					swap(i,parent(i));
					i=parent(i);
					if(i==1) {
						break;
					}
				}
			}
		}
		else{
			System.out.println("unavailible index insert");
		}
	}

	public int extractMax() {
		if(curSize>0) {
			int ret = heap[1];
			heap[1] = heapify(curSize);
			curSize--;
			int i = 1;
			while(curSize!=0) {
				// asured, at least one kind exists
				if(2*i>curSize) {
					break;
				}
				// if both childs exist
				else if(2*i+1<=curSize) {
					// if max(left,right)>parent
					if(Math.max(heapify(2*i), heapify(2*i+1))>heapify(i)) {
						if(heapify(2*i)>heapify(2*i+1)) {
							swap(i,2*i);
							i = 2*i;
						}
						else {
							swap(i,2*i+1);
							i = 2*i+1;
						}
					}
					// max(left,right)<=parent -> end
					else {
						break;
					}
				}
				// if only one left child:
				else {
					if(heapify(2*i)>heapify(i)) {
						swap(i,2*i);
						i = 2*i;
					}
					else {
						break;
					}
				}
			}
			return ret;
		}
		System.out.println("empty heap");
		System.exit(0);
		return Integer.MIN_VALUE;
	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void printHeap() {
		int level = 0;
		for(int i = 1;i<=curSize;i++) {
			System.out.print(heapify(i)+" ");
			if(i==2*level+1) {
				System.out.println();
				level++;
			}
		}
		System.out.println();
	}
	
}
