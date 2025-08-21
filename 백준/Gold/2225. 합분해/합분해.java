import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    st = new StringTokenizer(br.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
	    // j개의 숫자를 더해 i를 만든다
	    long[][] dp = new long[n+1][m+1];
	    	
	    // 1개의 숫자를 이용해 i를 만드는 경우는 1 
	    for(int i=0;i<=n;i++) {
	    	dp[i][1] = 1;
	    }
	    
	    // 여러 개의 숫자를 이용해 0을 만드는 경우는 1
	    for(int j=1;j<=m;j++) {
	    	dp[0][j] = 1;
	    }
	    
	    for(int i=1;i<=n;i++) {
	    	for(int j=2;j<=m;j++) {
	    		dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000;
	    	}
	    }
	    
	    
	    System.out.println(dp[n][m]);
	}

}