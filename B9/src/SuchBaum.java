
public class SuchBaum {

	SuchBaum leftChild;
	SuchBaum rightChild;
	Integer value;
	
	public SuchBaum() {
		leftChild = null;
		rightChild = null;
		value =  null;
	}
	
	public SuchBaum(int x) {
		leftChild = null;
		rightChild = null;
		value = x;
	}
	
	public boolean isLeaf() {
		return (leftChild == null)&&(rightChild == null);
	}
	
	public boolean isEmpty() {
		return (leftChild == null)&&(rightChild == null)&&(value==null);
	}
	
	public SuchBaum getLeftChild() {
		return leftChild;
	}
	
	public SuchBaum getRightChild() {
		return rightChild;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void set(Integer x) {
		if(isEmpty()) {
			value = x;
		}
		else if( x > value ) {
			if(rightChild == null) {
				rightChild = new SuchBaum(x);
			}
			else {
				rightChild.set(x);
			}
		}
		else if( x < value) {
			if(leftChild == null) {
				leftChild = new SuchBaum(x);
			}
			else {
			leftChild.set(x);
			}
		}
		else {
			System.out.println("duplication in set value!");
			System.out.println("value " + x + " duplicated!");
		}
	}
	
	public void show_in() {
		if(isLeaf()) {
			System.out.print(value +" ");
		}
		else {
			if(leftChild!=null) {
				leftChild.show_in();
			}
			System.out.print(value +" ");
			if(rightChild!=null) {
				rightChild.show_in();
			}
		}
	}

	public void show_pre() {
		if(isLeaf()) {
			System.out.print(value +" ");
		}
		else {
			System.out.print(value +" ");
			if(leftChild!=null) {
				leftChild.show_pre();
			}
			if(rightChild!=null) {
				rightChild.show_pre();
			}
		}
	}

	public void show_post() {
		if(isLeaf()) {
			System.out.print(value +" ");
		}
		else {
			if(leftChild!=null) {
				leftChild.show_post();
			}
			if(rightChild!=null) {
				rightChild.show_post();
			}
			System.out.print(value +" ");
		}		
	}
}
