import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Point> remain;
	static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		remain = new ArrayList<>();
		
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) remain.add(new Point(i,j));
			}
		}		
			
		dfs(0);
	}
	
	static void dfs(int cnt) {
		if(cnt == remain.size()) {
			StringBuilder sb = new StringBuilder();
			
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}

			System.out.println(sb);
			System.exit(0);
		}
		
		int y = remain.get(cnt).y;
		int x = remain.get(cnt).x;
		
		for(int num=1;num<=9;num++) {
			if(checkRow(x, num)&&checkCol(y, num)&&checkSquare(y, x, num)) {
				map[y][x] = num;
				dfs(cnt+1);
				map[y][x] = 0;
			}
		}
	}
	
	static boolean checkRow(int x, int num) {		
		for(int i=0;i<9;i++) {
			if(map[i][x]==num) return false;
		}
		return true;
	}
	
	static boolean checkCol(int y, int num) {		
		for(int j=0;j<9;j++) {
			if(map[y][j]==num) return false;
		}
		return true;
	}
	
	static boolean checkSquare(int y, int x, int num) {		
		y = (y/3) * 3;
		x = (x/3) * 3;
		
		for(int i=y;i<y+3;i++) {
			for(int j=x;j<x+3;j++) {
				if(map[i][j]==num) return false;
			}
		}
		return true;
	}
	
	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}		
	}

}
