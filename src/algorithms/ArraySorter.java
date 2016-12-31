package algorithms;

import java.util.Arrays;

import utils.Sorter;

public class ArraySorter extends Sorter {

	@Override
	public int[] sort(int[] data) {
		Arrays.sort(data);
		return data;
	}

}
