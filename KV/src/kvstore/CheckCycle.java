package kvstore;
import java.util.*;

	import java.util.InputMismatchException;
	import java.util.Scanner;
	import java.util.Stack;
	 
	public class CheckCycle
	{
		//private Map<Integer, List<Integer>> adjacencyList;
	    private Stack<Integer> stack;
	    private int adjacencyMatrix[][];
	    int source;
	    public CheckCycle() 
	    {
	        stack = new Stack<Integer>();
	        
	    }
	 
	   
	    
	   public void dfs(int adjacency_matrix[][], int source)
	    {
	    	System.out.println("dude i am in dfs");
	        int number_of_nodes = adjacency_matrix[source].length - 1;
	 
	        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
	        for (int sourcevertex = 1; sourcevertex <= number_of_nodes; sourcevertex++)
	        {
	            for (int destinationvertex = 1; destinationvertex <= number_of_nodes; destinationvertex++)
	            {
	                adjacencyMatrix[sourcevertex][destinationvertex] =
	                   adjacency_matrix[sourcevertex][destinationvertex];
	            }
	        }
	 
	        int visited[] = new int[number_of_nodes + 1];		
	        int element = source;		
	        int destination = source;			
	        visited[source] = 1;		
	        stack.push(source);
	 
	        while (!stack.isEmpty())
	        {
	            element = stack.peek();
	            destination = element;	
		    while (destination <= number_of_nodes)
		    {
	                if (adjacencyMatrix[element][destination] == 1 && visited[destination] == 1)
	                {
	                    if (stack.contains(destination))
	                    {	
	                        System.out.println("The Graph contains cycle");
	                        return;	
	                    }
	                }
	 
	              	if (adjacencyMatrix[element][destination] == 1 && visited[destination] == 0)
		        {
	                    stack.push(destination);
	                    visited[destination] = 1;
	                    adjacencyMatrix[element][destination] = 0;
	                    element = destination;
	                    destination = 1;
		            continue;
	                }
	                destination++;
		    }
	            stack.pop();	
	        }
	        System.out.println("dude i am done with dfs");
	    }
	 
	  
	  	
	}