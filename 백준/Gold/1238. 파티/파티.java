import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, X;
	static ArrayList<Node>[] graph, reverse;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;

	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
	    X = Integer.parseInt(st.nextToken());
	    
	    graph = new ArrayList[N+1];
	    reverse = new ArrayList[N+1];

	    for(int i=0;i<=N;i++) {
	    	graph[i] = new ArrayList<>();
	    	reverse[i] = new ArrayList<>();
	    }
	    
	    for(int i=0;i<M;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int from = Integer.parseInt(st.nextToken());
	    	int to = Integer.parseInt(st.nextToken());
	    	int time = Integer.parseInt(st.nextToken());
	    	
	    	graph[from].add(new Node(to,time));
	    	reverse[to].add(new Node(from,time));
	    }
	    
	    int[] D1 = dijkstra(graph);
	    int[] D2 = dijkstra(reverse);
	    
	    int maxRes = 0;
	    for(int i=1;i<=N;i++) {
	    	maxRes = Math.max(maxRes, D1[i]+D2[i]);
	    }	 
	    
	    System.out.println(maxRes);
	}
	
	public static int[] dijkstra(ArrayList<Node>[] graph) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] D = new int[N+1];
	    
	    for(int j=0;j<=N;j++) {
	    	D[j] = Integer.MAX_VALUE;
	    }
	    
	    D[X] = 0;
	    pq.add(new Node(X,0));
	    
	    while(!pq.isEmpty()) {
	    	
	    	Node now = pq.poll();
	    	if(visited[now.to]) continue;
	    	
	    	visited[now.to] = true;
	    	
	    	for(Node next : graph[now.to]) {
	    		if(!visited[next.to] && D[next.to] > D[now.to] + next.time) {
	    			D[next.to] = D[now.to] + next.time;
	    			pq.add(new Node(next.to,D[next.to]));
	    		}
	    	}		    
	    }
		
		return D;
	}
}

class Node implements Comparable<Node>{
	int to;
	int time;
	public Node(int to, int time) {
		this.to = to;
		this.time = time;
	}
	@Override
	public int compareTo(Node o) {
		return this.time - o.time;
	}
}