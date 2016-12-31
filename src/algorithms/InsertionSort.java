package algorithms;

import utils.Sorter;

public class InsertionSort extends Sorter {
	
	@Override
	public int[] sort(int[] data) {
		return doSort(data);
	}
	
	public static int[] doSort(int[] data) {
		int length = data.length;
		
		for (int i=1; i<length; i++) {
			int currentIndex = i;
			
			while (currentIndex > 0) {
				
				if (data[currentIndex] < data[currentIndex-1]) {
					int temp = data[currentIndex-1];
					data[currentIndex-1] = data[currentIndex];
					data[currentIndex] = temp;
					currentIndex--;
					
				} else {
					break;
				}
			}
		}
		return data;
	}
}
