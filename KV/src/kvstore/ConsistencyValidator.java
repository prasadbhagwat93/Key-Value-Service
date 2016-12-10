package kvstore;
import java.util.*;
public class ConsistencyValidator {
	private static List<LogEntry>[] points;
	static List<LogEntry> list ;
	@SuppressWarnings("unchecked")
	public ConsistencyValidator(){
		/*LogEntry e = new LogEntry();
		LogEntry e1 = new LogEntry();
		LogEntry e2 = new LogEntry();
		LogEntry e3 = new LogEntry();
		e.start =1; e.end = 2; e.operation =1; e.value =Integer.toString(0);	e.entryNumber=0;
		e1.start = 3; e1.end =8; e1.operation =1; e1.value = Integer.toString(1); e1.entryNumber=1;
		e2.start = 4; e2.end = 5; e2.operation =0; e2.value = Integer.toString(1); e2.entryNumber =2;
		e3.start =6; e3.end = 7; e3.operation =0; e3.value = Integer.toString(0);	e3.entryNumber =3;
		

		list.add(e);
		list.add(e1);
		list.add(e2);
		list.add(e3);
*/		list = EntryNumberProvider.getLogList();
		points	= new LinkedList[list.size()];
		
	}
	public static void call(){
		
		drawGraph(list);
	}

	public static void drawGraph(List<LogEntry> list){
		
		for(int i=0; i< list.size();i++){
			points[i] = new LinkedList<LogEntry>();
		}
		DrawTimeEdges(list,points);
		drawDataEdges(list,points);
		drawHybridEdges(list,points);
		CheckGraphHasCycle(points);
		System.out.println(points[0].size());
		System.out.println(points[1].size());
		System.out.println(points[2].size());
		System.out.println(points[3].size());
		System.out.println(isReachable(list.get(2),list.get(3)));
	}
	public static void DrawTimeEdges(List<LogEntry> list,List<LogEntry>[] points){
		for(int i=0;i<list.size();i++){
			LogEntry e = list.get(i);
			for(int j =0;j<list.size();j++){
				if(i!=j && e.end < list.get(j).start){
					points[i].add(list.get(j));
				}
			}
		}
	}
	public static void drawDataEdges(List<LogEntry> list,List<LogEntry>[] points){
		for(int i=0;i<list.size();i++){
			LogEntry e = list.get(i);
			for(int j =0;j<list.size();j++){
				if(i!=j && e.operation ==1){
					if(list.get(j).operation == 0 && e.value.equals(list.get(j).value)){
						if(!points[i].contains(list.get(j))){
							points[i].add(list.get(j));
						}
					}
				}
			}
		}
	}
	
	public static void drawHybridEdges(List<LogEntry> list,List<LogEntry>[] points){
		for(int i=0;i<list.size();i++){
			LogEntry e = list.get(i);
			if(e.operation == 1){
			for(int j =0;j<list.size();j++){
				if(i!=j && !e.value.equals(list.get(j).value) && e.operation!=list.get(j).operation){		//found a non dictating read
					if(isReachable(e,list.get(j))){
						for(int k=0;k<list.size();k++){
							if(list.get(k).value.equals(list.get(j).value) && list.get(k).operation == e.operation){
								if(!points[i].contains(list.get(k))){
									points[i].add(list.get(k));
								}
							}
						}
					}
				}
			}
		}
	}
	}

	public static boolean isReachable(LogEntry s, LogEntry d)
    {
        LinkedList<Integer>temp;
 
        // Mark all the vertices as not entryNumber(By default set
        // as false)
        boolean entryNumber[] = new boolean[list.size()];
 
        // Create a queue for BFS
        LinkedList<LogEntry> queue = new LinkedList<LogEntry>();
 
        // Mark the current node as entryNumber and enqueue it
        entryNumber[s.entryNumber]=true;
        queue.add(s);
 
        // 'i' will be used to get all adjacent vertices of a vertex
        Iterator<LogEntry> i;
        while (queue.size()!=0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
 
            LogEntry n;
            i = points[s.entryNumber].listIterator();
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been entryNumber, then mark it
            // entryNumber and enqueue it
            while (i.hasNext())
            {
                n = i.next();
 
                // If this adjacent node is the destination node,
                // then return true
                if (n==d)
                    return true;
 
                // Else, continue to do BFS
                if (!entryNumber[n.entryNumber])
                {
                    entryNumber[n.entryNumber] = true;
                    queue.add(n);
                }
            }
        }
 
        // If BFS is complete without entryNumber d
        return false;
    }

   static void CheckGraphHasCycle(List<LogEntry>[] points){
	   int adj[][] = new int[points.length+1][points.length+1];
	   System.out.println("adj matrix size is "+adj.length);
	   
	   List<Integer> dls;
	   List<int[]> list = new LinkedList<int[]>();
       
	   for(int i =0;i<points.length;i++){
		   System.out.println("test size "+points[i].size());
		   
		   for(int j =0;j<points[i].size();j++){
			   
			   
			   adj[i+1][points[i].get(j).entryNumber+1] = 1;	//constructing adjacent matrix
			   
		   }
	   }
	   for(int i =0;i<adj.length;i++){
		  // System.out.println("test size "+points[i].size());
		
		   for(int j =0;j<adj.length;j++){			// printing matrix
			   System.out.print(adj[i][j]+ " ");
		   }
		   System.out.println();
	   }
	  
	   //System.out.println("---> "+list.get(4)[1]);
	   CheckCycle check = new CheckCycle();
	   check.dfs(adj,1);
	 //System.out.println(damn);
   }
   
   
}
