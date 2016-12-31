package utils;

public class MyLinkedList {
	
	private Node first;
	private Node last;
	
	
	public void add(int value) {
		Node node = new Node(value);
		
		if (first != null) {
			last.next = node;
			last = node;
		} else {
			first = node;
			last = node;
		}
	}
	
	
	public void addAll(MyLinkedList listToAdd) {
		if (listToAdd.first == null) {
			return;
		}
		
		if (first != null) {
			last.next = listToAdd.first;
			last = listToAdd.last;
		
		} else {
			this.first = listToAdd.first;
			this.last = listToAdd.last;
		}
		
		listToAdd.first = null;
	}
	
	
	public int poll() {
		int value = first.value;
		
		first = first.next;
		
		return value;
	}
	
	
	public boolean hasNext() {
		return first != null;
	}
	
	
	public int[] toArray(int[] array) {
		int length = array.length;
		
		for (int i=0; i<length; i++) {
			array[i] = poll();
		}
		
		return array;
	}
	
	
	private class Node {
		int value;
		Node next;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
}
