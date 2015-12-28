import java.util.*;
import java.io.*;
/*
 * WordGrid problem
 * Given n by n grid of alphabets and a dictionary, 
 * find all possible words that can be formed by traversing through the grid in all 8 direction
 * @author: Ishola Babatunde
 * @date: 12/24/2015
 * @todo: Build graph appropriately (automate) - get rid of manual buildNodes()
 * @note: HashSet has been replaced by Trie data structure to provide a isPrefix for backtracking
 *        hashset code has been commented and replaced by trie - see TrieDictionary
 * */
public class WordGrid{
	
    static ArrayList<Node> grid = new ArrayList<>();
    static ArrayList<String> foundWords = new ArrayList<>();
    /*static Set<String> dictionary = new HashSet<>(); //for O(1) complexity on .contains()*/
    static TrieNode dictionary_trie;
    static TrieDictionary trie;
    
    public static void main(String[] args){
		WordGrid wordGrid = new WordGrid();
		grid = wordGrid.buildNodes();
        /*String[] newWords = {"abc", "abcfi", "bcei", "cedi", "edg", "fehi", "ghi"};
        dictionary = wordGrid.buildDictionary(dictionary, newWords);*/
        trie = new TrieDictionary("sowpods.txt");
		System.out.println("done building dictionary");
		dictionary_trie = trie.dictionary;
        //use all nodes as starting node
        for(Node cell:grid){
			wordGrid.searchWordRecursive(String.valueOf(cell.value), cell);
		}
		System.out.println(foundWords);
	}
	
	/*DFS search for a words contained in the dictionary that appear on the grid
	 * max word possible is 9 for a 3 x 3 grid if no duplication
	 * @param: current string, and current 'root' node
	 * */
	private void searchWordRecursive(String curStr, Node curNode){
		curNode.isVisited = true;
		if(trie.isFullWord(dictionary_trie, curStr)){
			foundWords.add(curStr);
		}
		
		ArrayList<Node> neighbours = curNode.getNeighbours();
		for(int i = 0; i < neighbours.size(); i++){
			Node curNeighbour = neighbours.get(i);
			if(!curNeighbour.isVisited){
				String newStr = curStr.concat(String.valueOf(curNeighbour.value));
				if(newStr.length() <=  9 && trie.isPrefix(dictionary_trie, newStr))
					searchWordRecursive(newStr, curNeighbour);
			}
		}
		curNode.isVisited = false;
	}
	
	/*clear all nodes once done
	 * */
	private void unmarkNodes(ArrayList<Node> nodes){
		for(Node node:nodes){
			node.isVisited = false;
		}
	}
    
    /* populates dictionary
     * @param: dictionary set, array of new words to add
     * @returnValue: populated dictionary
    */
    private Set<String> buildDictionary(Set<String> dictionary, String[] words){
        for(int i = 0; i < words.length; i++){
            dictionary.add(words[i]);
        }
        return dictionary;
    }
    
    /* manually build nodes, a 3 x 3 grid with alphabets a to i, with their connections
     * @returnValue: list of nodes (each node with its own neighbours)
    */
    private ArrayList<Node> buildNodes(){
		ArrayList<Node> nodes = new ArrayList<>();
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node('E');
        Node nodeF = new Node('F');
        Node nodeG = new Node('G');
        Node nodeH = new Node('H');
        Node nodeI = new Node('I');
        nodeA.addNeighbours(new Node[]{nodeB, nodeE, nodeD});
        nodeB.addNeighbours(new Node[]{nodeC, nodeA, nodeD, nodeE, nodeF});
        nodeC.addNeighbours(new Node[]{nodeB, nodeE, nodeF});
        nodeD.addNeighbours(new Node[]{nodeG, nodeA, nodeB, nodeE, nodeH});
        nodeE.addNeighbours(new Node[]{nodeI, nodeH, nodeE, nodeA, nodeB, nodeC, nodeD, nodeF, nodeG});
        nodeF.addNeighbours(new Node[]{nodeC, nodeB, nodeE, nodeH, nodeI});
        nodeG.addNeighbours(new Node[]{nodeD, nodeH, nodeC});
        nodeH.addNeighbours(new Node[]{nodeI, nodeG, nodeD, nodeE, nodeF});
        nodeI.addNeighbours(new Node[]{nodeF, nodeE, nodeH});
       
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);
        nodes.add(nodeE);
        nodes.add(nodeF);
        nodes.add(nodeG);
        nodes.add(nodeH);
        nodes.add(nodeI);
        return nodes;
    }

}

/*
 * Seems unnecessary - could have achieved the same thing with just indexing
 * Need to analyse the complexity of using a Node class
 * */
class Node{
    public char value;
    public ArrayList<Node> neighbours;
    public boolean isVisited;
    
    public Node(char value){
        this.value = value;
        this.neighbours = new ArrayList<>();
    }
    
    public void addNeighbours(Node[] neighbours){
        for (int i = 0; i < neighbours.length; i++){
            this.neighbours.add(neighbours[i]);
        }
    }
    
    public ArrayList<Node> getNeighbours(){
        return this.neighbours;
    }
    
    public String toString(){
		return String.valueOf(this.value);
	}
}
