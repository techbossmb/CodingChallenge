import java.util.*;
import java.io.*;
/* 
 * Trie implementation of a dictionary supports insert, print
 * dictionary file download from:
 * http://scrabblehelper.googlecode.com/svn-history/r20/trunk/ScrabbleHelper/src/dictionaries/sowpods.txt
 * alternatively, you could use the usr/share/dict/words file (if using a unix system) 
 * @author: Ishola Babatunde
 * @date:12/27/15
 * @todo: isPrefix, contains
*/
class TrieDictionary{
	
	private Node buildDictionaryTrieFromFile(String fileName){	
	if(fileName == null)return null;
		String line = null;
		BufferedReader bufferedReader = null;
		Node dictionary = new Node('\0');
		try{
			FileReader fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null){
				insertWord(dictionary, line);
			}
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(bufferedReader != null){
					bufferedReader.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return dictionary;
	}
	
	public void insertWord(Node root, String value){
		char[] letters = value.toCharArray();
		Node curNode = root;
		for(int i = 0; i < letters.length; i++){
			curNode = curNode.addNextLetter(letters[i]);
		}
		curNode.isWord = true;//last character isWord
	}
	
	public void printWords(Node curNode, String str, ArrayList<String> foundWords){
		str += curNode.letter;
		if(curNode.isWord)foundWords.add(str);
		for(Node node:curNode.nextLetters){
			printWords(node, str, foundWords);
		}	
	}
	
	public static void main(String[] args){
		Node root = new Node('\0');
		TrieDictionary trieDictionary = new TrieDictionary();
		String fileName = "sowpods.txt";
		Node dictionary = trieDictionary.buildDictionaryTrieFromFile(fileName);
		ArrayList<String> foundWords = new ArrayList<>();
		trieDictionary.printWords(dictionary, "", foundWords);
		System.out.println(foundWords);
	}
}

class Node{
	char letter;
	ArrayList<Node> nextLetters;
	boolean isWord;
	
	public Node(char letter){
		this.letter = letter;
		this.isWord = false;
		nextLetters = new ArrayList<>();
	}
	
	public Node(char letter, boolean isWord){
		this.letter = letter;
		this.isWord = isWord;
		nextLetters = new ArrayList<>();
	}
	
	/*
	 * add the next letter in the word to the trie 
	 * */
	public Node addNextLetter(char nextLetter){
		Node currentNode = null;
		int currentIndex;
		
		ArrayList<Character> charsLink = new ArrayList<>();
		for(int i=0; i < this.nextLetters.size(); i++){
			charsLink.add(nextLetters.get(i).letter);
		}
		if((currentIndex = charsLink.indexOf(nextLetter)) == -1){
			Node letter = new Node(nextLetter);
			this.nextLetters.add(letter);
			currentNode = letter;
		}else{
			currentNode = this.nextLetters.get(currentIndex);
		}
		return currentNode;
	}
	
	/*
	 * overide the toString() method
	 * */
	public String toString(){
		return String.valueOf(this.letter);
	}
}
