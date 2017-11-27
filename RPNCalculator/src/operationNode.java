// DO NOT MODITY

public class operationNode extends Node 
{
	// field
	public String operator;
	
	// constructor
	public operationNode(String operator)
	{
		this.operator = operator;
		this.nodeType = "operationNode";
	}
}
