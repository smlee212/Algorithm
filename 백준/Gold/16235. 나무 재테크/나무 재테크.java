import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][] map, initMap;
    private static int[] dy = {1, 1, 1, 0, 0, -1, -1, -1},
            dx = {1, 0, -1, 1, -1, 1, 0, -1};

    private static Deque<Tree>[][] trees; // 좌표별 트리 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        initMap = new int[N + 1][N + 1];
        trees = new Deque[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = 5;
                initMap[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(z)); // 나이순 정렬이 필요 없도록 앞에서부터 추가
        }

        // K년 동안 시뮬레이션
        for (int year = 0; year < K; year++) {
            List<Tree> deadTrees = new ArrayList<>();
            List<Tree> breedingTrees = new ArrayList<>();

            // 봄 & 여름 (좌표별로 트리 관리)
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    Deque<Tree> newTrees = new ArrayDeque<>();
                    int deadNutrient = 0;

                    while (!trees[i][j].isEmpty()) {
                        Tree tree = trees[i][j].pollFirst(); // 가장 어린 트리부터 처리
                        if (map[i][j] >= tree.age) {
                            map[i][j] -= tree.age;
                            tree.age++;
                            newTrees.add(tree);

                            if (tree.age % 5 == 0) {
                                breedingTrees.add(new Tree(i, j, 1)); // 가을 번식 트리 저장
                            }
                        } else {
                            deadNutrient += tree.age / 2; // 여름 양분화
                        }
                    }
                    trees[i][j] = newTrees;
                    map[i][j] += deadNutrient;
                }
            }

            // 가을 (번식)
            for (Tree tree : breedingTrees) {
                int x = tree.x, y = tree.y;
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx > 0 && ny > 0 && nx <= N && ny <= N) {
                        trees[nx][ny].addFirst(new Tree(1)); // 1살 나무를 맨 앞에 추가
                    }
                }
            }

            // 겨울 (양분 추가)
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] += initMap[i][j];
                }
            }
        }

        // 생존한 나무 개수 계산
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count += trees[i][j].size();
            }
        }
        System.out.println(count);
    }

    private static class Tree {
        int x, y, age;

        public Tree(int age) {
            this.age = age;
        }

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}