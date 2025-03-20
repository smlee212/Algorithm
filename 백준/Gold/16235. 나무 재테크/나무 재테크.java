import java.io.*;
import java.util.*;

public class Main {

    private static int N,M,K;
    private static int[][] map;
    private static int[][] initMap;
    private static int[] dy = {1,1,1,0,0,-1,-1,-1},
                         dx = {1,0,-1,1,-1,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        initMap = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = 5;
                initMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Tree> treeList = new ArrayList<>();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x,y,z));
        }

        // 재테크 시작
        for(int k=0;k<K;k++) {
            // 봄
            List<Tree> aliveTreeList = new ArrayList<>();
            List<Tree> deadTreeList = new ArrayList<>();
            treeList.sort(Comparator.comparingInt(o -> o.age)); // 나이순으로 정렬
            for(Tree tree : treeList) {
                if(map[tree.x][tree.y] >= tree.age) {
                    map[tree.x][tree.y] -= tree.age;
                    tree.age += 1;
                    aliveTreeList.add(tree);
                }
                else {
                    deadTreeList.add(tree);
                }
            }

            // 여름
            for(Tree tree : deadTreeList) {
                map[tree.x][tree.y] += tree.age / 2;
            }

            // 가을
            treeList = new ArrayList<>(aliveTreeList);
            for(Tree tree : aliveTreeList) {
                if(tree.age % 5 != 0) continue;

                for(int i=0;i<8;i++) {
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];

                    if(nx<=0||ny<=0||nx>N||ny>N) continue;

                    treeList.add(new Tree(nx,ny, 1));
                }
            }

            // 겨울
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    map[i][j] += initMap[i][j];
                }
            }
        }

        System.out.println(treeList.size());
    }

    private static class Tree {
        int x; int y; int age;
        public Tree(int x, int y, int age) {
            this.x=x; this.y=y; this.age=age;
        }
    }
}
