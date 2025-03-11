import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int N = Integer.parseInt(br.readLine());
	    int M = Integer.parseInt(br.readLine());
	    
	    ArrayList<Node>[] graph = new ArrayList[N+1];
	    int[] D = new int[N+1];

	    for(int i=0;i<=N;i++) {
	    	graph[i] = new ArrayList<>();
	    	D[i] = Integer.MAX_VALUE;
	    }
	    
	    for(int i=0;i<M;i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int from = Integer.parseInt(st.nextToken());
	    	int to = Integer.parseInt(st.nextToken());
	    	int dist = Integer.parseInt(st.nextToken());
	    	
	    	graph[from].add(new Node(to,dist));
	    }
	    
	    st = new StringTokenizer(br.readLine());
	    
	    int A = Integer.parseInt(st.nextToken());
	    int B = Integer.parseInt(st.nextToken());
	    
	    PriorityQueue<Node> pq = new PriorityQueue<>();
	    D[A] = 0;
	    pq.add(new Node(A,D[A]));

	    boolean[] visited = new boolean[N+1];
	    
	    while(!pq.isEmpty()) {
	    	
	    	Node now = pq.poll();
	    	if(visited[now.to]) continue;
	    	
	    	visited[now.to] = true;
	    	
	    	for(Node next : graph[now.to]) {
	    		if(!visited[next.to] && D[next.to] > D[now.to] + next.dist) {
	    			D[next.to] = D[now.to] + next.dist;
	    			pq.add(new Node(next.to,D[next.to]));
	    		}
	    	}
	    }
	    	    
	    System.out.println(D[B]);
	}	
}

class Node implements Comparable<Node>{
	int to;
	int dist;
	public Node(int to, int dist) {
		super();
		this.to = to;
		this.dist = dist;
	}
	@Override
	public int compareTo(Node o) {
		return dist - o.dist;
	}
}