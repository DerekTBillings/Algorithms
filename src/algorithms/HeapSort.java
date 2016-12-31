package algorithms;

import utils.Sorter;

public class HeapSort extends Sorter {

	@Override
	public int[] sort(int[] data) {
		data = heapSort(data);
		
		return data;
	}
	
	
	private static int[] heapSort(int[] data) {
		int length = data.length;
		int rightBounds = length/2;
		
		for (int differential = 0; differential <= rightBounds; differential++) {
			
			for (int parentIndex = differential; parentIndex <= rightBounds; parentIndex++) {
				int leftChild = differential+((parentIndex-differential)*2)+1;
				int rightChild = leftChild+1;
				
				data = compare(data, parentIndex, leftChild);
				data = compare(data, parentIndex, rightChild);
				data = compare(data, leftChild, rightChild);
			}
		}
		
		return data;
	}
	
	
	private static int[] compare(int[] data, int parentIndex, int childIndex) {
		if (childIndex < data.length &&
				parentIndex < data.length) {
		
			int parentValue = data[parentIndex];
			int childValue = data[childIndex];
			
			if (childValue < parentValue) {
				data[parentIndex] = childValue;
				data[childIndex] = parentValue;

				//recursive parent push
				int newParentIndex = (parentIndex-1)/2;
				data = compare(data, newParentIndex, parentIndex);
			}
		}
		return data;
	}
}









