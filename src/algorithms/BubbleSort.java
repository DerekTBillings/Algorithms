package algorithms;

import utils.Sorter;


public class BubbleSort extends Sorter {

	@Override
	public int[] sort(int[] data) {
		int length = data.length;
		int maxIndex = length-1;
		boolean changed;
		
		do {
			changed = false;
			
			for (int i=0; i<maxIndex; i++) {
				int leftIndex = data[i];
				int rightIndex = data[i+1];
				
				if (rightIndex < leftIndex) {
					data[i] = rightIndex;
					data[i+1] = leftIndex;
					changed = true;
				}
			}
			
			maxIndex--;
			
		} while (changed);
		
		return data;
	}

}
