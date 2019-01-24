package A5_Dijkstra;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will
												// initially
												// be null. This is ok! Just
												// build out
												// from array[1]

	public MinBinHeap() {
		size = 0;
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(-100000, null); // 0th will be unused for
													// simplicity
													// of child/parent
													// computations...
													// the book/animation page
													// both do this.
	}

	// Please do not remove or modify this method! Used to test your entire
	// Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if (entry == null) {
			return;
		}

		// we insert the entry into the next available slot, because the array
		// starts at 0, size is the index of the last element

		array[size + 1] = entry;

		// all we have to do is bubble up that one element
		bubbleUp(size + 1);

		size++;

	}

	@Override
	public void delMin() {
		if (array[1] == null) {
			return;
		}

		if (array[size] == null) {
			return;
		}

		// we put the value of the last element into the 1st element and then
		// bubble that down, starting with a 1 because we want to bubble down
		// from the root

		array[1] = array[size];
		array[size] = null;
		size--;
		bubbleDown(1);

	}

	@Override
	public EntryPair getMin() {
		return array[1];
	}

	@Override
	public int size() {
		if (array[1] == null || size < 0) {
			return 0;
		}
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		if (size > 0 || entries == null || entries.length > array.length) {
			return;
		}

		// first we put all the elements of the argument array into the main
		// array, starting at 1 instead of 0

		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
			size++;
		}

		// we start with the parent of the last node and bubble up, bubbling up
		// for every element on the second to last row, and bubbling up from the
		// first spot twice

		int b = size / 2;
		bubbleDown(b);
		while (b > 1) {
			b--;
			bubbleDown(b);
		}

	}

	// helper methods

	public void bubbleUp(int b) {

		// this method tests the priority of the parent and then swaps them if
		// needed and recursively calls itself

		EntryPair temp = null;
		if (b / 2 > 0) {
			if (array[b / 2].priority > array[b].priority) {
				temp = array[b / 2];
				array[b / 2] = array[b];
				array[b] = temp;
				bubbleUp(b / 2);
			}
		} else {
			return;
		}

	}

	public void bubbleDown(int b) {

		// this method tests whether or not the current EntryPair has children,
		// if it does not, it returns, if it has only the left one (impossible
		// to have just the right one with a complete tree) then it tests
		// whether or not it has a higher priority and swaps and recursively
		// calls if it does

		// if it has two children, it finds out if it is greater than both or
		// just one and swaps with the one it is greater than, in every case, it
		// recursively calls the same method, unless it is smaller than both of
		// the children, then it returns

		EntryPair temp = null;
		if (b * 2 > array.length) {
			return;
		} else if (array[b * 2] == null && array[b * 2 + 1] == null) {
			return;
		} else if (array[b * 2 + 1] == null) {
			if (array[b * 2].priority < array[b].priority) {
				temp = array[b * 2];
				array[b * 2] = array[b];
				array[b] = temp;
				bubbleDown(b * 2);
			}
		} else {
			if (array[b].priority > array[b * 2].priority && array[b].priority > array[b * 2 + 1].priority) {
				if (array[b * 2].priority > array[b * 2 + 1].priority) {
					temp = array[b * 2 + 1];
					array[b * 2 + 1] = array[b];
					array[b] = temp;
					bubbleDown(b * 2 + 1);
				} else {
					temp = array[b * 2];
					array[b * 2] = array[b];
					array[b] = temp;
					bubbleDown(b * 2);
				}
			} else if (array[b].priority > array[b * 2].priority) {
				temp = array[b * 2];
				array[b * 2] = array[b];
				array[b] = temp;
				bubbleDown(b * 2);
			} else if (array[b].priority > array[b * 2 + 1].priority) {
				temp = array[b * 2 + 1];
				array[b * 2 + 1] = array[b];
				array[b] = temp;
				bubbleDown(b * 2 + 1);
			} else {
				return;
			}
		}
	}

}