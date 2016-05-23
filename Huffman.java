package lab3;

import java.util.*;

public class Huffman {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter your sentence: ");
		
		String sentence = in.nextLine();
		
		String binaryString = ""; // this stores the string of binary code

		for (int i = 0; i < sentence.length(); i++) { 
			
			// CHAR -> ASCII BINARY CONVERSION
			
			int character= (int) sentence.charAt(i); 
			
			String binaryValue = Integer.toBinaryString(character); 
			
			// For any 6 Bit Values add a zero for 7 Bit ASCII Binary
			
			for (int j = 7; j > binaryValue.length(); j--) 
				binaryString += "0"; 
			
			
			binaryString += binaryValue + " "; // add to the string of binary
			
		}
		
		
		System.out.println(binaryString);
		
		int[] ASCII = new int[256];

		for (int i = 0; i < sentence.length(); i++) {
			ASCII[(int) sentence.charAt(i)]++;
		}

		PriorityQueue<Tree> PQ = new PriorityQueue<Tree>();

		for (int i = 0; i < ASCII.length; i++) {
			
			if (ASCII[i] > 0) {
				
				// Ternary Operator to use time for 1 occurence or times for > 1
				System.out.println("'" + (char) i + "' appeared " + ASCII[i] + ((ASCII[i] == 1) ? " time" : " times"));
				
				Node rootNode = new Node((char) i);
				Tree letterTree = new Tree(rootNode, ASCII[(char) i]);
				PQ.add(letterTree);
			}
		}

		while (PQ.size() > 1) { // while there are two or more Trees left in the forest
			
			// Removal of the trees
			Tree tree1 = PQ.poll();
			Tree tree2 = PQ.poll();
			
			Node newRoot = new Node(); //Sets new root
			newRoot.leftChild = tree1.root; //sets trees as children of root
			newRoot.rightChild = tree2.root;

			int newFrequency = tree1.frequency + tree2.frequency; //Calculates new frequency of the combined nodes into a single Tree
			Tree newTree = new Tree(newRoot, newFrequency); //Creates new tree
			PQ.add(newTree); //puts in PQ
		}

		// Fully Combined Tree
		Tree HuffmanTree = PQ.poll();
		
		
		// String to store the binary encoded string
		String binaryString2 = "";
		for (int i = 0; i < sentence.length(); i++) { // Gets the Hoffman Encoding per character in the sentence
			
			binaryString2 += HuffmanTree.getCode(sentence.charAt(i));
			binaryString2 += " ";
		}

		System.out.println('\n' + binaryString2);

		// print out compression ratios
		int asciiRatio = (binaryString.replaceAll("\\s+", "").length());
		int huffmanRatio = (binaryString2.replaceAll("\\s+", "").length());
		double compress = ((double) huffmanRatio / asciiRatio);
		compress = compress * 100;
		System.out.println("Compressed size is: " + huffmanRatio + " / " + asciiRatio + " = " + (int) compress + " %");
	}
}