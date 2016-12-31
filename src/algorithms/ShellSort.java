package algorithms;

import utils.Sorter;

public class ShellSort extends Sorter {

	@Override
	public int[] sort(int[] data) {
		int stepRange = data.length / 2;
		
		if (stepRange < 1) {
			stepRange = 1;
		}
		
		data = shellSort(stepRange, data);
		
		return data;
	}
	
	private static int[] shellSort(int stepRange, int[] arrayToSort) {
		boolean changed;
		int maxIndex = arrayToSort.length;
		
		do {
			changed = false;
			int rangeBounds = maxIndex-stepRange;
			
			for (int i=0; i<rangeBounds; i++) {
				int leftValue = arrayToSort[i];
				int rightValue = arrayToSort[i+stepRange];
				
				if (rightValue < leftValue) {
					arrayToSort[i] = rightValue;
					arrayToSort[i+1] = leftValue;
					
					changed = true;
				}
			}
			
			if (stepRange > 1) {
				stepRange--;
			} else {
				maxIndex--;
			}
			
		} while (stepRange > 1 || changed);
		
		return arrayToSort;
	}

}
