package lab3;

import java.io.*;
import java.util.*; // for Stack class

public class Tree implements Comparable<Tree> {
	public Node root; 
	public int frequency = 0;

	public Tree()
	{
		root = null;
	}

	public Tree(Node inRoot, int inFrequency) {
		
		this.root = inRoot;
		this.frequency = inFrequency;
	}

	public int compareTo(Tree object) { //
		if (frequency - object.frequency > 0) { 

			return 1;
		} else if (frequency - object.frequency < 0) {
			return -1; // return 1 or -1 depending on whether these frequencies
						// are bigger or smaller
		} else {
			return 0; // return 0 if they're the same
		}
	}

	
	String path = "error"; // this variable will track the path to the letter
	
	public String getCode(char letter) {

		inOrder(root, letter, ""); 
		return path;

	}

	// INORDER Traversal -> LEFT -> ROOT -> RIGHT
	private void inOrder(Node localRoot, char letter, String path) {
		if (localRoot != null) { // While the local root node of the tree is not Null ( Checking if it has run off tree or is original root )
			
			if (localRoot.letter == letter)
				
				this.path = path;
			
			else {
				
				inOrder(localRoot.leftChild, letter, path + "0");
				inOrder(localRoot.rightChild, letter, path + "1");
			}
		}
	}
}
