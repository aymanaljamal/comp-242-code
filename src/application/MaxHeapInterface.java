package application;

public interface MaxHeapInterface<A extends Comparable< A>> {
	public void add(A newEntry);

	public A removeMax();

	public A getMax();

	public boolean isEmpty();

	public int getSize();

	public void clear();
}