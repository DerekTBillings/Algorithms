package algorithms;

import utils.Sorter;

public class DualPivotQuickSort extends Sorter {
	
	private int leftPivot;
	private int rightPivot;

	@Override
	public int[] sort(int[] data) {
		DualPivotQuickSort quickSort = new DualPivotQuickSort();
		data = quickSort.doSort(data);
		
		return data;
	}
	
	
	private int[] doSort(int[] data) {
		int length = data.length-1;
		
		doSort(data, 0, length);
		
		return data;
	}
	
	
	private void doSort(int[] data, int leftBounds, int rightBounds) {
		int breadth = rightBounds - leftBounds;
		
		if (breadth > 17) {
			quickSort(data, leftBounds, rightBounds);
			
		} else if (breadth > 1) {
			insertionSort(data, leftBounds, rightBounds);
		}
	}
	
	
	private void quickSort(int[] data, int leftBounds, int rightBounds) {
		setupPivots(data, leftBounds, rightBounds);

		int leftWall = leftBounds;
		int rightWall = rightBounds;
		int leftIndex = leftBounds+1;
		
		while (leftIndex <= rightWall) {
			if (data[leftIndex] < leftPivot) {
				leftWall++;
				swapIndexValues(data, leftWall, leftIndex);
				leftIndex++;
				
			} else if (data[leftIndex] > rightPivot) {
				rightWall--;
				swapIndexValues(data, rightWall, leftIndex);
				
			} else {
				leftIndex++;
			}
		}
		
		swapIndexValues(data, leftWall, leftBounds);
		swapIndexValues(data, rightWall, rightBounds);

		doSort(data, leftBounds, leftWall-1);
		doSort(data, leftWall+1, rightWall-1);
		doSort(data, rightWall+1, rightBounds);
	}
	
	
	private void setupPivots(int[] data, int leftPivotIndex, int rightPivotIndex) {
		assignPivots(data, leftPivotIndex, rightPivotIndex);
		
		if (rightPivot < leftPivot) {
			swapIndexValues(data, leftPivotIndex, rightPivotIndex);
			assignPivots(data, leftPivotIndex, rightPivotIndex);
		} 
	}
	
	
	private void assignPivots(int[] data, int leftPivotIndex, int rightPivotIndex) {
		leftPivot = data[leftPivotIndex];
		rightPivot = data[rightPivotIndex];
	}
	
	
	private void insertionSort(int[] data, int leftBounds, int rightBounds) {
		boolean swapped;
		int insertionIndex;
		
		for (int index = leftBounds+1; index <= rightBounds; index++) {
			insertionIndex = index;
			do {
				swapped = false;

				if (data[insertionIndex] < data[insertionIndex-1]) {
					swapped = true;
					swapIndexValues(data, insertionIndex-1, insertionIndex);
					insertionIndex--;
				}
				
			} while(swapped && insertionIndex > leftBounds);
		}
	}
	
	
	private void swapIndexValues(int[] data, int targetIndex, int swapperIndex) {
		int tempValue = data[targetIndex];
		data[targetIndex] = data[swapperIndex];
		
		data[swapperIndex] = tempValue;
	}
}
