package lab3;

import java.io.*;

public class Node{

	public char letter; // stores letter

	public Node leftChild = null; // this node's left child
	public Node rightChild = null; // this node's right child

	public Node() {
		
	}

	public Node(char inLetter) {
		
		this.letter = inLetter;
	}

}
