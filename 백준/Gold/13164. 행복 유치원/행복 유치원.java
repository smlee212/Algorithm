import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] children = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            children[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> diffHeight = new PriorityQueue<>();
        for(int i=0;i<N-1;i++) {
            diffHeight.add(children[i+1] - children[i]);
        }
        
        int cost = 0;
        for(int i=0;i<N-K;i++) {
            cost += diffHeight.poll();
        }
        System.out.println(cost);
    }
}