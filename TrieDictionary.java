import java.util.*;
import java.io.*;
/* 
 * Trie implementation of a dictionary supports insertWord, isPrefix, isFullWord, printWords
 * dictionary file download from:
 * http://scrabblehelper.googlecode.com/svn-history/r20/trunk/ScrabbleHelper/src/dictionaries/sowpods.txt
 * alternatively, you could use the usr/share/dict/words file (if using a unix system) 
 * @author: Ishola Babatunde
 * @date:12/27/15
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
	
	public boolean isPrefix(Node curNode, String word){
		return isPrefix(curNode, word, false);
	}
	
	public boolean isFullWord(Node curNode, String word){
		return isPrefix(curNode, word, true);	
	}
	
	public boolean isPrefix(Node curNode, String prefix, boolean checkWord){
		if(curNode == null)return false;
		if(prefix.isEmpty())return true;//empty string is a prefix to any string
		char[] prfx = prefix.toCharArray();
		for(int i = 0; i < prfx.length; i++){
			ArrayList<Character> charsLink = new ArrayList<>();
			for(int j=0; j < curNode.nextLetters.size(); j++){
				charsLink.add(curNode.nextLetters.get(j).letter);
			}
			if(!charsLink.contains(prfx[i]))return false;
			curNode = curNode.nextLetters.get(charsLink.indexOf(prfx[i]));
		}
		
		if(!checkWord){
			return true;
		}else{//checking is the last node isWord
			if(curNode.isWord){
				return true;
			}else{
				return false;
			}		
		}
	}
	
	public void printWords(Node curNode, String str, ArrayList<String> foundWords){
		str += curNode.letter;
		if(curNode.isWord){
			foundWords.add(str);
		}

		for(Node node:curNode.nextLetters){
			printWords(node, str, foundWords);
		}	
	}
	
	public static void main(String[] args){
		Node root = new Node('\0');
		TrieDictionary trieDictionary = new TrieDictionary();
		String fileName = "sowpods.txt";
		System.out.println("Building Trie Dictionary");
		Node dictionary = trieDictionary.buildDictionaryTrieFromFile(fileName);
		System.out.print("Done\nEnter word check isPrefix:");
		Scanner scanner = new Scanner(System.in);
		String prefixToFind = scanner.next();
		System.out.println(trieDictionary.isPrefix(dictionary, prefixToFind.toUpperCase()));
		
		System.out.print("Enter word check isFullWord:");
		String wordToFind = scanner.next();
		System.out.println(trieDictionary.isFullWord(dictionary, wordToFind.toUpperCase()));
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
