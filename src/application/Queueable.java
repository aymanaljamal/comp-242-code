package application;

public interface Queueable<T> {
	public T dequeue();

	public T getFront();

	public void enqueue(T data);

	public boolean isEmpty();

	public void clear();
}
