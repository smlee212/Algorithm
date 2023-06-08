import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	static final int N = 5;
	static int k, cnt, max;
	static boolean[][] map;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		map = new boolean[N+1][N+1];
		
		k = Integer.parseInt(br.readLine());
		cnt = 0;
		max = 0;
		
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = true;
		}
		
		dfs(1,1,1);
		
		System.out.println(cnt);
    }
	
	static void dfs(int y, int x, int size) {
		if(y==5 && x==5) {
			if(size == 25 - k) {
				cnt++;
			}
			if(max < size) {
				max = size;
			}
		}
		
		map[y][x] = true;
		
		if(y > 1 && !map[y-1][x])
			dfs(y-1,x,size+1);
		if(y < 5 && !map[y+1][x])
			dfs(y+1,x,size+1);
		if(x > 1 && !map[y][x-1])
			dfs(y,x-1,size+1);
		if(x < 5 && !map[y][x+1])
			dfs(y,x+1,size+1);
		
		map[y][x] = false;
	}
}
