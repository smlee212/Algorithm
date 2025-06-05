import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N+1]; // 선수 과목 개수
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b); // a 문제를 풀고 b를 풀 수 있음
            inDegree[b]++; // b 문제의 선수 과목 개수 증가
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));

        for(int i=1;i<=N;i++) {
            if(inDegree[i] == 0) { // 선수 과목이 없는 문제들
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int problem = pq.poll();
            sb.append(problem).append(" ");

            // 현재 문제를 풀었음으로 인해 다음 문제들의 선수 과목 개수 감소
            for(int nextProblem : graph.get(problem)) {
                inDegree[nextProblem]--;

                if(inDegree[nextProblem] == 0) { // 선수 과목을 모두 풀었다면
                    pq.add(nextProblem);
                }
            }
        }

        System.out.println(sb);
    }
}