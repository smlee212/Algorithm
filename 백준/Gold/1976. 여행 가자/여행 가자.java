import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] arr;
    static boolean[] visited, travelCity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1;j<=n;j++){
                int target = Integer.parseInt(st.nextToken());
                if(target == 1){
                    arr[i].add(j);
                }
            }
        }

        visited = new boolean[n+1];
        travelCity = new boolean[n+1];

        int start = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            start = Integer.parseInt(st.nextToken());
            travelCity[start] = true;
        }

        // 연결 관계를 dfs, bfs로 파악하면서 travelCity을 true -> false로 바꿔나가자

        dfs(start);

        for(int i=0;i<=n;i++){
            if(travelCity[i]){
                System.out.println("NO");
                System.exit(0);
            }
        }

        System.out.println("YES");
    }

    static void dfs(int x){
        visited[x] = true;
        travelCity[x] = false;

        for(int nx : arr[x]){
            if(!visited[nx]){
                dfs(nx);
            }
        }
    }

}