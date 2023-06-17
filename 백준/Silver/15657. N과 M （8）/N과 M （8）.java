import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] num, selected;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken()); 
	    M = Integer.parseInt(st.nextToken());
	    
	    num = new int[N];
	    selected = new int[M];
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0;i<N;i++) {
	    	num[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    Arrays.sort(num);
	    
	    combination(0,0);
	    
	    System.out.println(sb);
	}
	
	static void combination(int now, int cnt) {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=now;i<N;i++) {
			selected[cnt] = num[i];
			combination(i,cnt+1);
		}
	}
}