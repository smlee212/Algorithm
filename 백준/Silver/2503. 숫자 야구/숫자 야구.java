import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static int N;
	static int[] selected;
	static int[][] num;
	static int[] s,b;	
	static boolean[] visited;
	static int cnt=0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		selected = new int[3];
		num = new int[N][3];
		s = new int[N];
		b = new int[N];
		visited = new boolean[10];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			char[] input = st.nextToken().toCharArray();
			for(int j=0;j<3;j++) {
				num[i][j] = input[j] - '0';
			}
			
			s[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(cnt);
	}

	static void permutation(int d) {
		if(d == 3) {
			if(baseball()) cnt++;
			return;
		}
		
		for(int i=1;i<10;i++) {
			if(!visited[i]) {
				visited[i] =  true;
				selected[d] = i;
				permutation(d+1);
				visited[i] =  false;
			}
		}
	}
	
	static boolean baseball() {
		
		int strike, ball;
		
		for(int i=0;i<N;i++) {
			strike = 0;
			ball = 0;
			
			for(int a=0;a<3;a++) {
				for(int b=0;b<3;b++) {
					if(num[i][a] == selected[b]) {
						if(a==b) strike++;
						else ball++;
					}
				}
			}
			
			if(s[i]!=strike || b[i]!=ball) return false;
		}
		
		return true;
	}
}
