import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static ArrayList<Integer> arr;
	static int max;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    
	    int N = Integer.parseInt(br.readLine());
	    
	    arr = new ArrayList<>();
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0;i<N;i++) {
	    	arr.add(Integer.parseInt(st.nextToken()));
	    }
	    
	    max = 0;
	    
	    dfs(0);
	    	   
	    System.out.println(max);
	}
	
	static void dfs(int sum) {
		if(arr.size()<=2) {
			if(max < sum) {
				max = sum;
			}
			return;
		}
		
		for(int i=1;i<arr.size()-1;i++) {
			int now = arr.get(i);
			int e = arr.get(i-1) * arr.get(i+1);
			arr.remove(i);
			dfs(sum + e);
			arr.add(i,now);
		}
	}
	
}