package algorithms;

import utils.Sorter;


public class QuickSort extends Sorter {
	
	@Override
	public int[] sort(int[] data) {
		data = doSort(data, 0, data.length);
		
		return data;
	}
	
	
	private static int[] doSort(int[] data, int leftBounds, int rightBounds) {
		if (rightBounds - leftBounds < 2) {
			return data;
		} 
		
		int upperBounds = rightBounds-1;
		int pivot = data[upperBounds];
		int pivotIndex = leftBounds;
		int tempElement = -1;
		int swapElement = data[leftBounds];
		
		for (int index = leftBounds; index < upperBounds; index++) {
			tempElement = data[index];
			
			if (tempElement < pivot) {
				
				data[index] = swapElement;
				data[pivotIndex] = tempElement;
				
				pivotIndex++;
				swapElement = data[pivotIndex];
			}
		}
		
		data[upperBounds] = swapElement;
		data[pivotIndex] = pivot;
		
		
		data = doSort(data, leftBounds, pivotIndex);
		data = doSort(data, pivotIndex+1, rightBounds);
		
		return data;
	}
}










