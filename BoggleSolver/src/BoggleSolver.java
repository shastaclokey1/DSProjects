import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoggleSolver 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		TrieTree t = new TrieTree();  
		
		int[] test = new int[3];
		
		
		// Step 1: Scan in the dictionary
		// 			Save words into a Trie
		File wordsEn = new File("wordsEn.txt");
		Scanner dictScanner;
		try 
		{
			dictScanner = new Scanner(wordsEn);
			
			// MODIFY:
			// insert each word into the TrieTree t
			while (dictScanner.hasNext())
			{
				t.insert(dictScanner.next());
			}
			
			
			dictScanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// Step 2: Scan in the board
		// DO NOT NEED TO MODIFY
		// this scans the board and save it into 
		// a 2-D array called gameBoard
		// each line is saved into a String array
		// 
		
		File board = new File("board.txt");
		Scanner bScanner;
		// A game board of 4 by 4
		char[][] gameBoard = new char[4][4];
		// 
		String[] line = new String[4];
		
		int count = 0;
		try 
		{
			bScanner = new Scanner(board);
			while (bScanner.hasNextLine()) 
			{
				line[count] = bScanner.nextLine();
				count++;
			}
			bScanner.close();
			for (int x = 0; x < 4; x++)
			{
				for (int y = 0; y < 4; y++) 
				{
					gameBoard[x][y] = line[x].charAt(y);
					System.out.print(gameBoard[x][y]);
				}
				System.out.println();
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// NEED TO MODIFY! 
		// find all possible combinations of the board
		// check to see which of the combinations are valid
			
		// when found a valid word, print the word and the path. 
		int[][]	path = new int[2][4];
		String currentWord = "";
		for (int i = 0; i < 4; i++)//starting index of row
			for (int j = 0; j < 4; j++)//starting index of col
			{
				
				//Horizontal Right
				for (int k = j; k < 4; k++)//stopping index of col
				{
					currentWord = "";
					count = 0;
					for (int m = j; m <= k && m < 4; m++)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[i][m];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Horizontal Left
				for (int k = j; k >= 0; k--)//stopping index of col
				{
					currentWord = "";
					count = 0;
					for (int m = j; m >= k && m >= 0; m--)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[i][m];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Vertical down
				for (int k = i; k < 4; k++)//stopping index of row
				{
					currentWord = "";
					count = 0;
					for (int m = i; m <= k && m < 4; m++)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][j];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Vertical up
				for (int k = i; k >= 0; k--)//stopping index of row
				{
					currentWord = "";
					count = 0;
					for (int m = i; m >= k && m >= 0; m--)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][j];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Diagonal down right
				for (int k = i,n = j; k < 4 && n < 4; k++,n++)//stopping index of row and col
				{
					currentWord = "";
					count = 0;
					for (int m = i,l = j; m <= k && m < 4 && l <= n && l < 4; m++, l++)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][l];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Diagonal down left
				for (int k = i,n = j; k < 4 && n >= 0; k++,n--)//stopping index of row and col
				{
					currentWord = "";
					count = 0;
					for (int m = i,l = j; m <= k && m < 4 && l >= n && l >= 0; m++, l--)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][l];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				
				//Diagonal up left
				for (int k = i,n = j; k >= 0 && n >= 0; k--,n--)//stopping index of row and col
				{
					currentWord = "";
					count = 0;
					for (int m = i,l = j; m >= k && m >= 0 && l >= n && l >= 0; m--, l--)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][l];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				//Diagonal up right
				for (int k = i,n = j; k >= 0 && n < 4; k--,n++)//stopping index of row and col
				{
					currentWord = "";
					count = 0;
					for (int m = i,l = j; m >= k && m >= 0 && l <= n && l < 4; m--, l++)//Incremental index of word(creates current word)
					{
						currentWord = currentWord + gameBoard[m][l];
						path[0][count] = i;
						path[1][count] = m;
						count++;
					}
					if (t.search(currentWord) && currentWord.length() >= 3)
					{
						System.out.println("Found: " + currentWord);
						printPath(path, count);
					}
					
				}
				
				
			}	
		// example:
		// s b w r
		// t o m e
		// v n n p
		// p e q l
				
		// Let's say a valid word is bone. You can print the result in different ways.
		
		// Example 1:
		// Bone is found from the board. Path: [0,1] -> [1,1] -> [2,1] -> [3,1]
		
		// Example 2:
		// Word found:
		// * b * *
		// * o * *
		// * n * *      
		// * e * *  
	}

	private static void printPath(int[][] path, int count) 
	{
		// TODO Auto-generated method stub
		System.out.print("Path: ");
		for (int i = 0; i < count; i++)
		{
			System.out.print("[" + path[0][i] + "," + path[1][i] + "] ");
		}
		System.out.println();
	}

	
}
