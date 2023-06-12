import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k;
	static int[] card;
	
	static int[] selected;
	static boolean[] visited;
	static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    n = Integer.parseInt(br.readLine());
	    k = Integer.parseInt(br.readLine());

	    card = new int[n];
	    selected = new int[k];
	    visited = new boolean[n];

	    for(int i=0;i<n;i++) {
	    	card[i] = Integer.parseInt(br.readLine());
	    }
	    
	    dfs(0);
	
	    System.out.println(set.size());
	}
	
	static void dfs(int d) {
		if(d == k) {
			String num = "";
			for(int i=0;i<k;i++) {
				num += selected[i];
			}
			set.add(Integer.parseInt(num));
			return;
		}
		
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[d] = card[i];
				dfs(d+1);
				visited[i] = false;
			}
		}
	}
}