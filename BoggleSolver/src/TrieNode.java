import java.util.ArrayList;

public class TrieNode 
{
	// Instance Variable
	    char data; 
	    boolean isEnd; 
	    int count;
	    ArrayList<TrieNode> children;
	    // children List
	    // need to modify!
	    // you can implement this with arrayList or LinkedList or array
	    
	// Constructors
	    public TrieNode(char c)
	    {    
	        data = c;
	        isEnd = false;
	        count = 0;
	        children = new ArrayList<TrieNode>();
	        // Modify: Initialize the children list
	    }  
	    
	    public TrieNode()
	    {
	    	data = ' ';
	    	count = 0;
	    	isEnd = false;
	    	children = new ArrayList<TrieNode>();
	    }

	 // Methods
 
	    public TrieNode getChild(char c)
	    {
	    	/*
	    	 * if the children list is not null
	    	 * for each TrieNode in the children list
	    	 * if you find a TrieNode that contains the char c
	    	 * return the node
	    	 * else return null
	    	 */
	    	if (children != null)
	    	{
	    		for (int i = 0; i < children.size(); i++)
	    		{
	    			if (children.get(i).data == c)
	    				return children.get(i);
	    		}
	    	}
	    	return null;
	    }
	}
