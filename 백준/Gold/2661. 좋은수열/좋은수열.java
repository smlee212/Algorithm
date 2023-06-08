import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	static int N;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		dfs("");
    }
	
	static void dfs(String str) {
		if(str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}
		
		for(int i=1;i<=3;i++) {
			if(check(str+i)) {
				dfs(str+i);
			}
		}
	}
	
	static boolean check(String str) {
		int n = str.length() / 2;
		
		for(int i=1;i<=n;i++) {
			if(str.substring(str.length()-i).equals(str.substring(str.length()-2*i,str.length()-i))) {
				return false;
			}
		}
		
		return true;
	}
}
