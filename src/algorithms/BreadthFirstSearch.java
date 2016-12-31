package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BreadthFirstSearch {

	private int[][] grid;
	
	private int startValue = 2;
	private int stopValue = 3;
	private int xBounds;
	private int yBounds;
	
	private Element startElement;
	private LinkedList<Element> steps;
	private LinkedList<Element> heap;
	private Set<String> stepHistory;

	
	public BreadthFirstSearch(int[][] grid) {
		this.grid = grid;
		
		steps = new LinkedList<Element>();
		heap = new LinkedList<Element>();
		
		stepHistory = new HashSet<String>();
		
		xBounds = grid.length;
		yBounds = grid[0].length;
		
	}
	
	
	public String[] traverseGrid() {
		findStart();
		
		stepHistory.add(startElement.toString());
		
		heap.add(startElement);
		
		traverse();
		
		String[] results = new String[steps.size()];
		int pointer = 0;
		
		while (steps.peek() != null) {
			results[pointer++] = steps.poll().toString();
		}
		
		return results;
	}
	
	
	private void findStart() {
		int rowCount = grid.length;
		boolean breakOut = false;
		
		for (int x=0; x<rowCount; x++) {
			int elementCount = grid[x].length;
			
			for (int y=0; y<elementCount; y++) {
				if (grid[x][y] == startValue) {
					startElement = new Element(x, y);
					breakOut = true;
					break;
				}
			}
			
			if (breakOut) {
				break;
			}
		}
	}
	
	
	private void traverse() {
		boolean found = false;

		while (!found && heap.peek() != null) {
			Element currentElement = heap.poll();
			steps.add(currentElement);
			
			int currentX = currentElement.x;
			int currentY = currentElement.y;
			
			for (int x = currentX-1; x <= currentX+1; x++) {
				for (int y = currentY-1; y<= currentY+1; y++) {
					if (isValidPoint(x, y)) {
						Element element = new Element(x, y);
						stepHistory.add(element.toString());
						
						if (grid[x][y] != stopValue) {
							heap.add(element);
						} else {
							found = true;
							steps.add(element);
						}
					}
				}
				if (found) {
					break;
				}
			}
		}
	}
	
	
	private boolean isValidPoint(int x, int y) {
		boolean valid = true;
		
		if (x < 0) {
			valid = false;
		} else if (y < 0) {
			valid = false;
		} else if (x >= xBounds) {
			valid = false;
		} else if (y >= yBounds) {
			valid = false;
		} else if (grid[x][y] == 1) {
			valid = false;
		} else if (stepHistory.contains(x+", "+y)) {
			valid = false;
		}
		
		return valid;
	}
	
	
	private class Element {
		int x;
		int y;
		
		public Element(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return x+", "+y;
		}
	}
	
}
