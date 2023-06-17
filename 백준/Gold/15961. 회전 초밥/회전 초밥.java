import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    
	    int N = Integer.parseInt(st.nextToken());
	    int d = Integer.parseInt(st.nextToken());
	    int k = Integer.parseInt(st.nextToken());
	    int c = Integer.parseInt(st.nextToken());
	   
	    int[] sushi = new int[N]; // 회전 초밥 배열
	    int[] selected = new int[d+1]; // 1번부터 d번까지 초밥 중 선택한 개수
	    
	    for(int i=0;i<N;i++) {
	    	sushi[i] = Integer.parseInt(br.readLine());
	    }
	    
	    int res = 0, // 초밥 가짓수의 최대값
	    	now = 0; // 현재 선택한 초밥 가짓수
	    
	    // 초기 윈도우
	    for(int i=0;i<k;i++) {
	    	// 새롭게 선택한 초밥이라면 가짓수 증가
	    	if(selected[sushi[i]] == 0) {
	    		res++;
	    	}
	    	// 선택한 초밥 개수 증가
	    	selected[sushi[i]]++;
	    }
	    
	    // 초기 윈도우 설정
	    now = res;
	    
	    // 쿠폰 초밥 고려
	    if(selected[c] == 0) {
	    	res++;
	    }
	    
	    // 윈도우를 한칸씩 슬라이딩하며 가짓수 고려
	    for(int i=1;i<N;i++) {
	    	// 이전 초밥을 윈도우에서 제거
	    	selected[sushi[i-1]]--;
	    	
	    	// 이전 초밥이 하나뿐이였다면 가짓수 제거
	    	if(selected[sushi[i-1]] == 0) {
	    		now--;
	    	}
	    	
	    	// 슬라이딩으로 선택한 초밥이 새롭게 선택했다면 가짓수 추가 
	    	if(selected[sushi[(i+k-1)%N]] == 0) {
	    		now++;
	    	}
	    	// 선택한 초밥 개수 증가
	    	selected[sushi[(i+k-1)%N]]++;
	    	
	    	// 쿠폰 초밥 고려
	    	if(selected[c]==0) {
	    		res = Math.max(res, now+1);
	    	}
	    	else {
	    		res = Math.max(res, now);
	    	}
	    }	    
	    
	    System.out.println(res);
	}
}