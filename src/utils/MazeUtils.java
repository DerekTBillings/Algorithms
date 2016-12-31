package utils;

import java.util.Random;

public class MazeUtils {
	private static char openNode = '0';
	private static char blockedNode = '1';
	private static char startNode = '2';
	private static char targetNode = '3';
	private static char checkedNode = '^';
	private static char bestPathNode = 'P';
	
	
	public static char getCheckedNode() {
		return checkedNode;
	}

	public static char getBastPathNode() {
		return bestPathNode;
	}

	public static char[][] generateMaze(int width, int height){
		Random random = new Random();
		char[][] maze = new char[height][width];
		
		for(int i = 0; i < height; i++){	
			setRow(maze[i], blockedNode);
		}
		
		for(int i = 2; i < height; i += 2){
			generateRow(maze, (char)i);
		}
		
		setRow(maze[0], openNode);
		setRow(maze[height - 1], openNode);
		maze[0][random.nextInt(width)] = startNode;
		maze[height - 1][random.nextInt(width)] = targetNode;
		return maze;
	}
	
	private static void generateRow(char[][] maze, int rowNum){
		Random random = new Random();
		char[] row = maze[rowNum];
		int currentRunStart = 0;
		int carveEastChance;
		int openNorthIndex;
		int wallThreshold = 80;
		
		row[0] = openNode;
		
		for(int i = 1; i < row.length; i++){
			carveEastChance = random.nextInt(100);
			if(carveEastChance > wallThreshold || i >= row.length - 1){
				openNorthIndex = random.nextInt(i - currentRunStart);
				openNorthIndex += currentRunStart;
				maze[rowNum-1][openNorthIndex] = openNode;
			}
			
			if(carveEastChance > wallThreshold){
				row[i] = blockedNode;
				currentRunStart = i;
			}
			else{	
				row[i] = openNode;
			}
		}
	}
	
	private static void setRow(char[] row, char value){
		for(int i = 0; i < row.length; i++){
			row[i] = value;
		}
	}
	
	public static void printMaze(char[][] mazeToPrint){
		for(int y = 0; y < mazeToPrint.length; y++){
			for(int x = 0; x < mazeToPrint[y].length; x++){
				System.out.print(mazeToPrint[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int getStartIndex(char[][] maze) throws Exception{
		for(int i = 0; i < maze[0].length; i++){
			if(maze[0][i] == startNode) return i;
		}
		throw new Exception("No Start Found");
	}

	public static char getOpenNode() {
		return openNode;
	}

	public static char getBlockedNode() {
		return blockedNode;
	}

	public static char getStartNode() {
		return startNode;
	}

	public static char getTargetNode() {
		return targetNode;
	}
}
