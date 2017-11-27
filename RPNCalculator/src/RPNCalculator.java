import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RPNCalculator 
{
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		numberNode n1 = null;
		numberNode n2 = null;
		//operationNode operator = null;
		
		// initialize a stack
		Stack stack = new Stack();
		// initialize a queue
		Queue queue = new Queue();
		
		// 1. after you finish Stack.java and Queue.java,
		// uncomment the following code to make sure
		// that you are adding and removing correctly
		// from a stack and a queue
			
		/*
		stack.push(new numberNode(10.3));
		stack.push(new numberNode(11));
		stack.push(new numberNode(20.3));
		stack.push(new numberNode(59.3));
		int stackLength = stack.size();
		for (int i = 0; i < stackLength; i++){
			System.out.println(((numberNode)stack.pop()).value);
		}
		
		
		System.out.println();
		queue.enqueue(new numberNode(10.3));
		queue.enqueue(new numberNode(2.3));
		queue.enqueue(new numberNode(3.3));
		queue.enqueue(new numberNode(5.3));
		queue.enqueue(new numberNode(109.3));
		// we need to save the size of the queue
		// otherwise when we print using for
		// the size will change as we dequeue nodes
		int queueLength = queue.size();
		for (int i = 0; i < queueLength; i++){
			System.out.println(((numberNode)queue.dequeue()).value);
		}
		*/
		
		// 2. load the files in from the console
		// Assumption: always read in stack file first and then queue file
		// Do not need to modify
		File stackFile = new File("stackTest.txt");
		File queueFile = new File("queueTest.txt");
		
		// 3. Use Scanner objects to scan through the files
		// and add values to the stack and the queue accordingly.
		// need to modify
		
		try {
			Scanner stackScanner = new Scanner(stackFile);
			// while there's a new line in the file
			// scan it and check to see if it's a number or an operator
			// if it's a number, save it as a numberNode
			// if it's an operator, save it as an operationNode
			// then push the node onto the stack
			
			while (stackScanner.hasNextLine())
			{
				if (stackScanner.hasNextDouble())
				{
					double nextDouble = stackScanner.nextDouble();
					//stackScanner.nextLine();
					numberNode newNode = new numberNode(nextDouble);
					stack.push(newNode);
					System.out.println("pushing " + nextDouble + " onto main stack");
				}
				else
				{
					String nextOp = stackScanner.next();
					//stackScanner.nextLine();
					operationNode newOp = new operationNode(nextOp);
					stack.push(newOp);
					System.out.println("pushing " + nextOp + " onto main stack");
				}
			}
			
			stackScanner.close();
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			
			// while there's a new line in the file
			// scan it and check to see if it's a number or an operator
			// if it's a number, save it as a numberNode
			// if it's an operator, save it as an operationNode
			// then enqueue the node onto the queue
			Scanner queueScanner = new Scanner(queueFile);
			while (queueScanner.hasNextLine())
			{
				if (queueScanner.hasNextDouble())
				{
					double nextDouble = queueScanner.nextDouble();
					//queueScanner.nextLine();
					numberNode newNode = new numberNode(nextDouble);
					queue.enqueue(newNode);
					System.out.println("Enqueuing " + nextDouble + " within main queue");
				}
				else
				{
					String nextOp = queueScanner.next();
					//queueScanner.nextLine();
					operationNode newOp = new operationNode(nextOp);
					queue.enqueue(newOp);
					System.out.println("Enqueuing " + nextOp + " within main queue");
				}
			}
			queueScanner.close();
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4. now you build your stack and queue from the input file
		// now you can do the calculation!
		// need to modify
		
		// stack version
		
		
		System.out.println("Performing operations using main stack: ");
		// while the stack is not empty
		Stack operandStack = new Stack();
		Node tempNode = new Node();
		while(!stack.isEmpty())
		{
			// pop the nodes from the stack
			// it should always be number, number, operator
			// if you see any wrong pattern,
			// return an error message
			// if nothing is wrong,
			// save numbers to n1 and n2
			// and do calculation based on the operator
			tempNode = stack.pop();
			if (tempNode instanceof numberNode)
			{
				System.out.println("Pushing " + ((numberNode)tempNode).value + " to operand stack");
				operandStack.push(tempNode);
			}
			else if (tempNode instanceof operationNode)
			{
				if (operandStack.size() < 2)
				{
					System.out.println("Text file not formatted correctly for reverse polish notation");
				}
				else
				{
					operationNode workingNode = (operationNode)tempNode;
					n2 = (numberNode)operandStack.pop();
					n1 = (numberNode)operandStack.pop();
					if (workingNode.operator.charAt(0) == '+')
					{
						n1.value = n1.value + n2.value;
						System.out.println("Computing addition: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '-')
					{
						n1.value = n1.value - n2.value;
						System.out.println("Computing subtraction: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '*')
					{
						n1.value = n1.value * n2.value;
						System.out.println("Computing multiplication: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '/')
					{
						n1.value = n1.value / n2.value;
						System.out.println("Computing division: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '^')
					{
						n1.value = Math.pow(n1.value, n2.value)  ;
						System.out.println("Computing power: result = " + n1.value);
						operandStack.push(n1);
					}
				}
			}
				
		}
		numberNode showNode = (numberNode)operandStack.pop();
		System.out.println("");
		System.out.println("Result of calculations: " + showNode.value);
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// queue version
		
		// while the queue is not empty
		System.out.println("Performing operations using main queue and operation stack: ");
		while(!queue.isEmpty())
		{
			// dequeue the nodes from the queue
			// it should always be number, number, operator
			// if you see any wrong pattern,
			// return an error message
			// if nothing is wrong,
			// save numbers to n1 and n2
			// and do calculation based on the operator
			tempNode = queue.dequeue();
			if (tempNode instanceof numberNode)
			{
				System.out.println("Pushing " + ((numberNode)tempNode).value + " to operand stack");
				operandStack.push(tempNode);
			}
			else if (tempNode instanceof operationNode)
			{
				if (operandStack.size() < 2)
				{
					System.out.println("Text file not formatted correctly for reverse polish notation");
				}
				else
				{
					operationNode workingNode = (operationNode)tempNode;
					n2 = (numberNode)operandStack.pop();
					n1 = (numberNode)operandStack.pop();
					if (workingNode.operator.charAt(0) == '+')
					{
						n1.value = n1.value + n2.value;
						System.out.println("Computing addition: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '-')
					{
						n1.value = n1.value - n2.value;
						System.out.println("Computing subtraction: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '*')
					{
						n1.value = n1.value * n2.value;
						System.out.println("Computing multiplication: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '/')
					{
						n1.value = n1.value / n2.value;
						System.out.println("Computing division: result = " + n1.value);
						operandStack.push(n1);
					}
					else if (workingNode.operator.charAt(0) == '^')
					{
						n1.value = Math.pow(n1.value, n2.value)  ;
						System.out.println("Computing power: result = " + n1.value);
						operandStack.push(n1);
					}
				}
			}
			
		}
		
		showNode = (numberNode)operandStack.pop();
		System.out.println("");
		System.out.println("Result of calculations: " + showNode.value);
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Reloading data into main queue to perform operations the alternative way: ");
		try
		{
			Scanner queueScanner = new Scanner(queueFile);
			while (queueScanner.hasNextLine())
			{
				if (queueScanner.hasNextDouble())
				{
					double nextDouble = queueScanner.nextDouble();
					//queueScanner.nextLine();
					numberNode newNode = new numberNode(nextDouble);
					queue.enqueue(newNode);
					System.out.println("Enqueuing " + nextDouble + " within main queue");
				}
				else
				{
					String nextOp = queueScanner.next();
					//queueScanner.nextLine();
					operationNode newOp = new operationNode(nextOp);
					queue.enqueue(newOp);
					System.out.println("Enqueuing " + nextOp + " within main queue");
				}
			}
			queueScanner.close();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		System.out.println("Performing operations using main queue with no operation stack: ");
		Node tempQNode = new Node();
		operationNode workingNode = null;
		if (!queue.isEmpty())
		{
			n1 = (numberNode)queue.dequeue();
			n2 = (numberNode)queue.dequeue();
			workingNode = (operationNode)queue.dequeue();
			if (workingNode.operator.charAt(0) == '+')
			{
				n1.value = n1.value + n2.value;
				System.out.println("Computing addition: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '-')
			{
				n1.value = n1.value - n2.value;
				System.out.println("Computing subtraction: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '*')
			{
				n1.value = n1.value * n2.value;
				System.out.println("Computing multiplication: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '/')
			{
				n1.value = n1.value / n2.value;
				System.out.println("Computing division: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '^')
			{
				n1.value = Math.pow(n1.value, n2.value)  ;
				System.out.println("Computing power: result = " + n1.value);
				operandStack.push(n1);
			}
		}
		while(!queue.isEmpty())
		{
			n2 = (numberNode)queue.dequeue();
			workingNode = (operationNode)queue.dequeue();
			if (workingNode.operator.charAt(0) == '+')
			{
				n1.value = n1.value + n2.value;
				System.out.println("Computing addition: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '-')
			{
				n1.value = n1.value - n2.value;
				System.out.println("Computing subtraction: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '*')
			{
				n1.value = n1.value * n2.value;
				System.out.println("Computing multiplication: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '/')
			{
				n1.value = n1.value / n2.value;
				System.out.println("Computing division: result = " + n1.value);
				operandStack.push(n1);
			}
			else if (workingNode.operator.charAt(0) == '^')
			{
				n1.value = Math.pow(n1.value, n2.value)  ;
				System.out.println("Computing power: result = " + n1.value);
				operandStack.push(n1);
			}
		}
		
		System.out.println("");
		System.out.println("Result of calculations: " + n1.value);
		//5. Once the calculations are done
		// print out the results from both files
		// need to modify
		
	}

}
