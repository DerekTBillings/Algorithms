package algorithms;

import utils.Sorter;

public class MergeSort extends Sorter {

	@Override
	public int[] sort(int[] data) {
		data = mergeSort(data, 0, data.length);
		
		return data;
	}
	
	private static int[] mergeSort(int[] data, int leftBounds, int rightBounds) {
		int boundSize = rightBounds - leftBounds;
		
		if (boundSize == 1) {
			int[] returnArray = new int[1];
			returnArray[0] = data[leftBounds];
			
			return returnArray;
			
		} else {
			int midIndex = leftBounds + (boundSize / 2);
			
			int[] leftArray = mergeSort(data, leftBounds, midIndex);
			int[] rightArray = mergeSort(data, midIndex, rightBounds);
			
			return doMerge(leftArray, rightArray);
		}
	}
	
	private static int[] doMerge(int[] leftArray, int[] rightArray) {
		int leftIndex = 0;
		int rightIndex = 0;
		int sortedIndex = 0;
		int leftBounds = leftArray.length;
		int rightBounds = rightArray.length;
		int sortedSize = leftBounds+rightBounds;
		
		int[] sortedList = new int[sortedSize];
		
		while (leftIndex < leftBounds &&
				rightIndex < rightBounds) {
			
			int leftValue = leftArray[leftIndex];
			int rightValue = rightArray[rightIndex];
			
			if (leftValue < rightValue) {
				sortedList[sortedIndex] = leftValue;
				leftIndex++;
			} else if (leftValue > rightValue) {
				sortedList[sortedIndex] = rightValue;
				rightIndex++;
			} else {
				sortedList[sortedIndex] = leftValue;
				sortedIndex++;
				sortedList[sortedIndex] = rightValue;
				leftIndex++;
				rightIndex++;
			}
			
			sortedIndex++;
		}
		
		while (leftIndex < leftBounds) {
			sortedList[sortedIndex] = leftArray[leftIndex];
			sortedIndex++;
			leftIndex++;
		}
		
		while (rightIndex < rightBounds) {
			sortedList[sortedIndex] = rightArray[rightIndex];
			sortedIndex++;
			rightIndex++;
		}
		
		return sortedList;
		
	}
	
}
