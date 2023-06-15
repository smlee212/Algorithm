import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static int N;
	static int[] chu;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		chu = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		int k = Integer.parseInt(br.readLine());

		visited = new boolean[31][15001];
		
		dfs(0,0);

		st = new StringTokenizer(br.readLine());
		for(int i=0;i<k;i++) {
			int res = Integer.parseInt(st.nextToken());
			
			if(res > 15000) sb.append("N ");
			else sb.append(visited[N][res]?"Y ":"N ");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int index, int sum) {
		if(visited[index][sum])
			return;
		
		visited[index][sum] = true;

		if(index == N)
			return;
		
		dfs(index+1, sum+chu[index+1]);
		dfs(index+1, sum);
		dfs(index+1, Math.abs(sum-chu[index+1]));
	}
}
