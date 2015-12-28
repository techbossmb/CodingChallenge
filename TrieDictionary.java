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
	public static TrieNode dictionary;
	
	public TrieDictionary(){}
	public TrieDictionary(String fileName){
		dictionary = buildDictionaryTrieFromFile(fileName);
	}
	private TrieNode buildDictionaryTrieFromFile(String fileName){	
	if(fileName == null)return null;
		String line = null;
		BufferedReader bufferedReader = null;
		TrieNode dictionary = new TrieNode('\0');
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
	
	public void insertWord(TrieNode root, String value){
		char[] letters = value.toCharArray();
		TrieNode curNode = root;
		for(int i = 0; i < letters.length; i++){
			curNode = curNode.addNextLetter(letters[i]);
		}
		curNode.isWord = true;//last character isWord
	}
	
	public boolean isPrefix(TrieNode curNode, String word){
		return isPrefix(curNode, word, false);
	}
	
	public boolean isFullWord(TrieNode curNode, String word){
		return isPrefix(curNode, word, true);	
	}
	
	public boolean isPrefix(TrieNode curNode, String prefix, boolean checkWord){
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
	
	public void printWords(TrieNode curNode, String str, ArrayList<String> foundWords){
		str += curNode.letter;
		if(curNode.isWord){
			foundWords.add(str);
		}

		for(TrieNode node:curNode.nextLetters){
			printWords(node, str, foundWords);
		}	
	}
	
	public static void main(String[] args){
		TrieNode root = new TrieNode('\0');
		TrieDictionary trieDictionary = new TrieDictionary();
		String fileName = "sowpods.txt";
		System.out.println("Building Trie Dictionary");
		dictionary = trieDictionary.buildDictionaryTrieFromFile(fileName);
		//ArrayList<String> foundWords = new ArrayList<>();
		//trieDictionary.printWords(dictionary, "", foundWords);
		//System.out.println(foundWords);
		System.out.print("Done\nEnter word check isPrefix:");
		Scanner scanner = new Scanner(System.in);
		String prefixToFind = scanner.next();
		System.out.println(trieDictionary.isPrefix(dictionary, prefixToFind.toUpperCase()));
		
		System.out.print("Enter word check isFullWord:");
		String wordToFind = scanner.next();
		System.out.println(trieDictionary.isFullWord(dictionary, wordToFind.toUpperCase()));
	}
}

class TrieNode{
	char letter;
	ArrayList<TrieNode> nextLetters;
	boolean isWord;
	
	public TrieNode(char letter){
		this.letter = letter;
		this.isWord = false;
		nextLetters = new ArrayList<>();
	}
	
	public TrieNode(char letter, boolean isWord){
		this.letter = letter;
		this.isWord = isWord;
		nextLetters = new ArrayList<>();
	}
	
	/*
	 * add the next letter in the word to the trie 
	 * */
	public TrieNode addNextLetter(char nextLetter){
		TrieNode currentNode = null;
		int currentIndex;
		
		ArrayList<Character> charsLink = new ArrayList<>();
		for(int i=0; i < this.nextLetters.size(); i++){
			charsLink.add(nextLetters.get(i).letter);
		}
		if((currentIndex = charsLink.indexOf(nextLetter)) == -1){
			TrieNode letter = new TrieNode(nextLetter);
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
