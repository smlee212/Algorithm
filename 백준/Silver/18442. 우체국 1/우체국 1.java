import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 입력 변수
	static int V,P;
	static long L;
	static long[] town;
	
	// 거리들의 합의 최소값
	static long minLen;
	// 조합으로 선택한 경찰서 위치
	static long[] selected;
	
	static StringBuilder sb = new StringBuilder();	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); 
		P = Integer.parseInt(st.nextToken()); 
		L = Long.parseLong(st.nextToken()); 
		
		town = new long[V];
		selected = new long[P];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<V;i++) {
			town[i] = Long.parseLong(st.nextToken());
		}
		
		minLen = Long.MAX_VALUE;
		
		// 조합 실행
		combination(0, 0);
			
		System.out.println(minLen);
		System.out.println(sb);

	}

	// 조합 알고리즘으로 경찰서의 위치를 선택하고
	// calcLen() 함수로 거리들의 합의 최소값을 구한다
	static void combination(int now, int depth) {
		// 경찰서들의 위치를 조합으로 모두 구했다면
		if(depth==P) {	
			// 거리들의 합의 최소값을 구한다
			calcLen();
			return;
		}
		
		for(int i=now;i<V;i++) {
			selected[depth] = town[i];
			combination(i+1, depth+1);
		}
	}
	
	static void calcLen() {
		
		// 거리들의 합
		long sum = 0;

		// 각각의 마을마다 경찰서들과 비교
		for(long now : town) {
			
			// 현재 마을에서 가장 가까운 경찰서와의 거리
			long minL = Long.MAX_VALUE;
			
			// 각각의 경찰서들과 비교
			for(long target : selected) {
				// 마을과 경찰서가 같은 위치라면 빠져나감
				if(target == now) {
					minL = 0; break;
				}
				
				// 원형이기 때문에 좌 우로의 거리를 계산
				long lenA = Math.abs(now - target);
				long lenB = Math.abs(L - lenA);
				
				// 최소 거리 갱신
				minL = Math.min(minL, Math.min(lenA, lenB));
			}
			
			// 최소 거리를 더해준다
			sum += minL;
		}
		
		// 거리들의 합의 최소값을 갱신
//		minLen = Math.min(minLen, sum);
		
		if(minLen > sum) {
			minLen = sum;
			sb.setLength(0);
			for(int i=0;i<P;i++) {
				sb.append(selected[i]).append(" ");
			}
		}
		
	}
}











