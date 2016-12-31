package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import utils.Sorter;
import algorithms.ArraySorter;
import algorithms.BreadthFirstSearch;
import algorithms.DualPivotQuickSort;

public class Main {
	
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	
	
	public void run() {
//		int max = 30;
		int max = 1000000;
		int size = 1000;
		int range = 1000;
		
		int[] data = generateRandomIntArray(max, max);

		testAlgorithms(data);
//		overallSpeedTest();
//		testTraversal(size, range);
	}
	
	
	private int[] generateRandomIntArray(int size, int range) {
		int[] arrayToSort = new int[size];
		Random random = new Random();
		
		for (int i = 0; i < size; i++) {
			int temp = random.nextInt(range);

			arrayToSort[i] = temp;
		}
		
		return arrayToSort;
	}
	
	
	private void testAlgorithms(int[] data) {
//		int result = Factorial.iterativeFactorial(40);

//		dataTest(
		timeTest(
//			new BubbleSort()
			new DualPivotQuickSort()
//			new HeapSort()
//			new InsertionSort()
//			new MergeSort()
//			new QuickSort()
//			new RadixSort()
//			new ShellSort()
//			new SpeedySorter()
		, data);
		
	}
	
	
	private long timeTest(Sorter sorter, int[] data) {
		long start = System.currentTimeMillis();
		
		sorter.sort(data);
//		Arrays.sort(data);
		
		long stop = System.currentTimeMillis();
		long duration = stop - start;
		System.out.println("duration = "+duration);
		return duration;
	}
	
	
	private void dataTest(Sorter sorter, int[] data) {
		int[] resultData = sorter.sort(data);
		
		printArrayData(resultData);
	}
	
	
	private void overallSpeedTest() {
		int loops = 5000;
		int startingSize = 10;
		int maxArraySize = 10000000;
		
		calculateFastestAlgorithm(loops, startingSize, maxArraySize, 
//			new BubbleSort(),
//			new DualPivotQuickSort(),
//			new HeapSort(),
//			new InsertionSort(),
//			new MergeSort(),
//			new QuickSort(),
			new ArraySorter()
//			new RadixSort()
//			new ShellSort()
		);
	}
	
	
	private void calculateFastestAlgorithm(int loopCount, int arraySize, int maxArraySize, Sorter... sorters) {
		
		Map<String, Long> sorterDurations = new HashMap<String, Long>(); 
		List<Integer> arraySizes = new ArrayList<Integer>();
		
		while(arraySize <= maxArraySize) {
			for (int l=0; l<loopCount; l++) {
				
				for (Sorter sorter : sorters) {
					int[] data = generateRandomIntArray(arraySize, arraySize);
					
					long duration = timeTest(sorter, data);
					
					String sorterName = createSorterName(sorter, arraySize);
					
					if (sorterDurations.containsKey(sorterName)) {
						duration += sorterDurations.get(sorterName);
					}
						
					sorterDurations.put(sorterName, duration);
				}
			}
			
			Set<String> keys = sorterDurations.keySet();
			
			arraySizes.add(arraySize);
			
			if (arraySize < 100) {
				arraySize += 10;
			} else if (arraySize < 1000) {
				arraySize += 100;
			} else {
				arraySize *= 10;
			}
		}
		
		for (Sorter sorter : sorters) {
			
			System.out.println();
			
			for (Integer size : arraySizes) {
				String sorterName = createSorterName(sorter, size);
				
				long sorterDuration = sorterDurations.get(sorterName);
				sorterDuration /= loopCount;
				
				System.out.printf("%s took %s nano-seconds.\r\n", sorterName, sorterDuration);
			}
		}
		
		System.out.println();
		for (Integer size : arraySizes) {
			String bestSorter = "";
			long bestTime = Long.MAX_VALUE;
			
			for (Sorter sorter : sorters) {
				String sorterName = createSorterName(sorter, size);
				long duration = (sorterDurations.get(sorterName) / loopCount);
				
				if (duration < bestTime) {
					bestTime = duration;
					bestSorter = sorterName;
				}
			}
			
			System.out.printf("%s was the fastest with %s nano-seconds.\r\n", bestSorter, bestTime);
		}
		
	}
	
	
	private String createSorterName(Sorter sorter, int arraySize) {
		String sorterName = sorter
				.getClass()
				.getName()
				.toString()
				.replace("algorithms.", "")
				+":"+arraySize;
		
		return sorterName;
	}
	
	
	private void printArrayData(int[] array) {
		for (int element : array) {
			System.out.print(element+", ");
		}
	}
	
	
	private void testTraversal(int xSize, int ySize) {
		char[][] grid = createTraversalGrid(xSize, ySize);
//		printGrid(grid);
		
		long start = System.currentTimeMillis();
		
//		BreadthFirstSearch searcher = new BreadthFirstSearch(grid);
//		DepthFirstSearch searcher = new DepthFirstSearch(grid);
		
//		String[] steps = searcher.traverseGrid();
		
		long end = System.currentTimeMillis();
		long duration = end - start;
		
		System.out.println("\r\n\r\n\r\n\r\n");
//		printGrid(grid);
		
//		System.out.println("Up, Across");
//		for (String step : steps) {
//			System.out.println(step);
//		}
		System.out.println();
		System.out.println("Duration = "+duration);
	}
	
	
	private char[][] createTraversalGrid(int xSize, int ySize) {
		Random random = new Random();
		
		char[][] grid = new char[xSize][ySize];
		char gridPlace;
		
		for (int x=0; x<xSize; x++) {
			for (int y=0; y<ySize; y++) {
				int ran = random.nextInt(10);
				
				if (ran > 3) {
					gridPlace = '0';
				} else {
					gridPlace = '1';
				}
				
				grid[x][y] = gridPlace;
			}
		}

		grid[random.nextInt(xSize)][random.nextInt(ySize)] = '2';
		grid[random.nextInt(xSize)][random.nextInt(ySize)] = '3';
		
		return grid;
	}
	
	
	private void printGrid(int[][] grid) {
		int xSize = grid[0].length;
		int rowNum = 0;
		
		for (int i=-1; i<xSize; i++) {
			System.out.print(i+"  ");
		}
		
		for (int[] row : grid) {
			System.out.print("\r\n");
			System.out.print(rowNum+++":  ");
			for (int e : row) {
				System.out.print(e+"  ");
			}
		}
		
		System.out.println("\r\n");
	}

	private void printGrid(char[][] grid) {
		int xSize = grid[0].length;
		int rowNum = 0;
		
		for (int i=-1; i<xSize; i++) {
			System.out.print(i+"  ");
		}
		
		for (char[] row : grid) {
			System.out.print("\r\n");
			System.out.print(rowNum+++":  ");
			for (char e : row) {
				System.out.print(e+"  ");
			}
		}
		
		System.out.println("\r\n");
	}
	
}
