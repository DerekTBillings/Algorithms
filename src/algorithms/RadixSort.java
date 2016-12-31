package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.MyLinkedList;
import utils.Sorter;

public class RadixSort extends Sorter {
	
	private int[] data;

	private List<MyLinkedList> bucket;
	
	private MyLinkedList collectedBuckets;
	
	private int divisor = 1;
	private int modValue = 10;
	private int greatestValue = 0;
	private int greatestIndex;
	
	
	public RadixSort(int[] data) {
		this.data = data;
		this.bucket = buildBucket();
	}
	
	@Override
	public int[] sort(int[] data) {
		RadixSort sorter = new RadixSort(data);
		sorter.doSort();
		MyLinkedList sortedData = sorter.collectedBuckets;
		
		data = sortedData.toArray(data);
		
		return data;
	}
	
	
	private void doSort() {
		
		for (int element : data) {
			if (element > greatestValue) {
				greatestValue = element;
			}
			
			filterElement(element);
		}

		String valueAsString = Integer.toString(greatestValue);
		greatestIndex = valueAsString.length();
		collectedBuckets = new MyLinkedList();
		
		for (int currentIndex = 1; currentIndex <= greatestIndex; currentIndex++) {
			divisor *= 10;
			modValue *= 10;
			
			for (int i=0; i<10; i++) {
				collectedBuckets.addAll(bucket.get(i));
			}
			
			if (currentIndex < greatestIndex) {
				
				while (collectedBuckets.hasNext()) {
					filterElement(collectedBuckets.poll());
				}
			}
		} 
	}
	
	
	private List<MyLinkedList> buildBucket() {
		List<MyLinkedList> bucket = new ArrayList<MyLinkedList>();
		
		for (int i=0; i<10; i++) {
			bucket.add(new MyLinkedList());
		}
		
		return bucket;
	}
	
	
	private void filterElement(int element) {
		int index = (element % modValue)/divisor;
		bucket.get(index).add(element);
	}
	
}
