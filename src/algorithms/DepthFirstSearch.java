package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DepthFirstSearch {

private int[][] grid;
	
	private int startValue = 2;
	private int stopValue = 3;
	private int xBounds;
	private int yBounds;
	
	private Element startElement;
	private LinkedList<Element> steps;
	private Set<String> stepHistory;
	private Compass[] compasses;

	
	public DepthFirstSearch(int[][] grid) {
		this.grid = grid;
		
		steps = new LinkedList<Element>();
		
		stepHistory = new HashSet<String>();
		
		xBounds = grid.length;
		yBounds = grid[0].length;
		
		compasses = buildCompasses();
	}
	
	
	private Compass[] buildCompasses() {
		Compass[] compasses = new Compass[8];
		int compassPointer = 0;
		
		for (int x=-1; x<=1; x++) {
			for (int y=-1; y<=1; y++) {
				if (x != 0 || y != 0) {
					compasses[compassPointer++] = new Compass(x, y);
				}
			}
		}
		
		return compasses;
	}
	
	
	public String[] traverseGrid() {
		findStart();
		
		stepHistory.add(startElement.toString());
		steps.add(startElement);
		
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
		int compassPointer = 0;
		int attemptsAtCurrentElement = 0;
		Compass compass = compasses[compassPointer];
		
		while (steps.peek() != null && !found) {
			Element currentElement = steps.peekLast();
			
			int currentX = currentElement.x;
			int currentY = currentElement.y;

			currentX += compass.moveX;
			currentY += compass.moveY;
			
			if (isValidPoint(currentX, currentY)) {
				attemptsAtCurrentElement = 0;
				
				Element element = new Element(currentX, currentY);
				stepHistory.add(element.toString());
				steps.add(element);
				
				if (grid[currentX][currentY] == stopValue) {
					found = true;
				}
				
			} else {
				compassPointer++;
				attemptsAtCurrentElement++;
				
				if (compassPointer >= 8) {
					compassPointer = 0;
				}
				
				compass = compasses[compassPointer];
				
				if (attemptsAtCurrentElement > 9) {
					attemptsAtCurrentElement = 0;
					steps.removeLast();
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
	
	
	private class Compass {
		int moveX;
		int moveY;
		
		public Compass(int moveX, int moveY) {
			this.moveX = moveX;
			this.moveY = moveY;
		}
	}

}
