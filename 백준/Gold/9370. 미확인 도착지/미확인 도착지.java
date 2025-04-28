import java.io.*;
import java.util.*;

public class Main {

    static int n, m ,t;
    static int s, g, h;
    static List<List<int[]>> graph;
    static List<Integer> candidateList;
    static final int INF = 1000000000;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            read(br);

            dijkstra();

            func(sb);
        }

        System.out.println(sb);
    }

    static void func(StringBuilder sb) {
        candidateList.sort(Comparator.comparingInt(o -> o));

        // 최단 거리가 홀수일 경우 확정 경로를 지나간 것
        for(int target : candidateList) {
            if(dist[target]%2==1) {
                sb.append(target).append(" ");
            }
        }
        sb.append("\n");
    }

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[n+1];
        dist = new int[n+1];

        // 초기화
        Arrays.fill(dist, INF);

        // 시작점
        dist[s] = 0;
        pq.add(new int[]{s,0});

        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowX = now[0];

            if(visited[nowX]) continue;
            visited[nowX] = true;

            for(int[] next : graph.get(nowX)) {
                int nextX = next[0];
                int nextDist = next[1];

                if(!visited[nextX] && dist[nextX] > dist[nowX] + nextDist) {
                    dist[nextX] = dist[nowX] + nextDist;
                    pq.add(new int[]{nextX, dist[nextX]});
                }
            }
        }
    }

    static void read(BufferedReader br) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if((a==g&&b==h)||(a==h&&b==g)) { // 확정 경로는 홀수로 변경
                d = 2*d - 1; // 동일한 거리의 경로가 존재할 때 확정 경로를 지나가게 하기 위해 1을 빼줌
            }
            else { // 이외의 경로는 짝수로 변경
                d *= 2;
            }
            graph.get(a).add(new int[]{b,d});
            graph.get(b).add(new int[]{a,d});
        }

        candidateList = new ArrayList<>();
        for(int i=0;i<t;i++) {
            candidateList.add(Integer.parseInt(br.readLine()));
        }
    }
}
