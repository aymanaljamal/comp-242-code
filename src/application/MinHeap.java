package application;

import java.util.Arrays;

class MinHeap<A extends Comparable<A>> implements MinHeapInterface<A> {
    private A[] heap;
    private int size;
    private int CAPACITY = 10;

   
    public MinHeap(int initialCapacity) {
    	CAPACITY= initialCapacity;
        heap = (A[]) new Comparable[initialCapacity + 1];
        size = 0;
    }

    @Override
    public void add(A newEntry) {
        if (size == heap.length - 1) {
            resize();
        }
        heap[++size] = newEntry;
        swim(size);
    }

    @Override
    public A removeMin() {
        if (isEmpty()) {
            return null;
        }
        A min = heap[1];
        swap(1, size--);
        sink(1);
        heap[size + 1] = null;
        return min;
    }

    @Override
    public A getMin() {
        if (isEmpty()) {
            return null;
        }
        return heap[1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeMin();
        }
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }

    private void swap(int i, int j) {
        A temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static boolean isMinHeap(Comparable[] a) {
		int N = a.length;
		for (int i = 1; i <= N/2; i++) {
			int lc = i*2, rc = lc + 1;
			if (lc < N && a[i].compareTo(a[lc]) > 0) return false;
			if (rc < N && a[i].compareTo(a[rc]) > 0) return false;
		}
		return true;
	}
	
	public static void heapSortDesc(Comparable[] a) {
		int N = a.length - 1;
		Comparable temp;
		
		minHeapify(a);  // to a max-heap 
		
		while (N > 1) {
			// 1. Swap the first element with the last element
			temp = a[1];
			a[1] = a[N];
			a[N] = temp;
			
			// 2. decrement N
			N--;
			
			// 3. sink[1]
			int k = 1;
			while (2*k <= N) {
				int j = 2*k;
				if (j < N && a[j].compareTo(a[j+1]) >= 0) j++;
				if (a[k].compareTo(a[j]) < 0) break;
				temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				k = j;
			}
		}
	}
	
	public static void minHeapify(Comparable[] a) {
		int N = a.length - 1, i = N / 2;
		Comparable temp;
		while (i-- > 0) {
			// sink [i+1]
			int k = i+1;
			while (2*k <= N) {
				int j = 2*k;
				if (j < N && a[j].compareTo(a[j+1]) >= 0) j++;
				if (a[k].compareTo(a[j]) < 0) break;
				temp = a[k];
				a[k] = a[j];
				a[j] = temp;
				k = j;
			}
		}
	}
    private void resize() {
        A[] newHeap = (A[]) new Comparable[heap.length * 2];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(heap, 1, size + 1));
    }
 
        public static void main(String[] args) {
           
            MaxHeap<Integer> maxHeap = new MaxHeap<>(10);
            maxHeap.add(10);
            maxHeap.add(20);
            maxHeap.add(5);
            System.out.println("MaxHeap: " + maxHeap);

          
            MinHeap<Integer> minHeap = new MinHeap<>(10);
            minHeap.add(10);
            minHeap.add(20);
            minHeap.add(5);
            System.out.println("MinHeap: " + minHeap); 
        }
    
}
