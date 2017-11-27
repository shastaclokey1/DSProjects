
public class TrieTree 
{
	
	public TrieNode root;
	 
    // Constructor
	
   public TrieTree()
   {
	  root = new TrieNode(' ');
   }
  
   // Insert
   public void insert(String word)
   {
       /*
        * Here are the algorithm steps how to insert a word into a Trie:
			Set current node to root node.
			The root node does not contain any letter.
			Convert the word to a char array.
			Set the current letter to the first letter in the word.
			If the current node already has an existing reference to the current letter
			(through one of the elements in the children list field) 
			then set current node to that referenced node.
			Otherwise, create a new node,
			set the letter to current letter,
			and set current node to this new node.
			Repeat until all letters in the current word has been processed.

        */
	   TrieNode currentNode = root;
	   char[] wordCstr = word.toCharArray();
	   char currentLetter;
	   for (int i = 0; i < word.length(); i++)
	   {
		   currentLetter = wordCstr[i];
		   if (currentNode.getChild(currentLetter) != null)
		   {
			   currentNode = currentNode.getChild(currentLetter);
		   }
		   else
		   {
			   TrieNode tempNode = new TrieNode(currentLetter);
			   currentNode.children.add(tempNode);
			   currentNode = currentNode.getChild(currentLetter);
		   }
	   }
	   root.count++;
	   currentNode.isEnd = true;
	   
   }
   
   // search
   public boolean search(String word)
   {
      /*
       * Algorithm steps for how to find a word in a Trie:
			Set current node to root node.
			Set the current letter to the first letter in the word.
			If children list contains the current letter, 
			then set the current node to that node including its children.
			Repeat until all letters in the word have been processed.

			Now there are two possibilities that may indicate the letter is not there in the tree:
			the current letter is the last letter and there is no valid node containing this letter.
			there is a valid node containing the last letter but the node does not indicate it contains a full word.
			If conditions above are not met, then we have a match for the word in the Trie.
       */
	   
	   TrieNode currentNode = root;
	   char[] wordCstr = word.toCharArray();
	   char currentLetter;
	   for (int i = 0; i < word.length(); i++)
	   {
		   currentLetter = wordCstr[i];
		   if (currentNode.getChild(currentLetter) != null)
			   currentNode = currentNode.getChild(currentLetter);
		   else
			   return false;
	   }
	   if (currentNode.isEnd)
		   return true;
	   else
		   return false;
   }
   
   // remove
   public void remove(String word)
   {
	   /*
	    * Algorithm steps for how to remove a word in a Trie:
	    * if we can't find the word in trie
	    * print the word doesn't present in trie
	    * else
	    * similar to search algorithm
	    * when we find the word, we remove it from the Trie.
	    * make sure when you remove, you need to decrease count and remove from the children list
	    */
	   TrieNode currentNode = root;
	   char[] wordCstr = word.toCharArray();
	   char currentLetter;
	   for (int i = 0; i < word.length(); i++)
	   {
		   currentLetter = wordCstr[i];
		   if (currentNode.getChild(currentLetter) != null)
			   currentNode = currentNode.getChild(currentLetter);
		   else
			   System.out.println("Word does not exist.");
	   }
	   if (currentNode.isEnd)
	   {
		   currentNode.isEnd = false;
		   root.count--;
	   }
	   else
		   System.out.println("Input is not a word.");;
	   
   }
 
}
