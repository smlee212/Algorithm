import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static TreeSet<Problem> arr;
    static HashMap<Integer, Integer> map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new TreeSet<>();
        map = new HashMap<>();

        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            arr.add(new Problem(p,l));
            map.put(p,l);
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            if("recommend".equals(commend)){
                int x = Integer.parseInt(st.nextToken());
                fRecommend(x);
            }
            else if("add".equals(commend)){
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                fAdd(p,l);
            }
            else if("solved".equals(commend)){
                int p = Integer.parseInt(st.nextToken());
                fSolved(p);
            }

        }
        System.out.println(sb.toString());
    }

    static void fRecommend(int x){
        if(x == 1){
            sb.append(arr.last().p).append("\n");
        }
        else if(x == -1){
            sb.append(arr.first().p).append("\n");
        }
    }

    static void fAdd(int p, int l){
        arr.add(new Problem(p,l));
        map.put(p,l);
    }

    static void fSolved(int p){
        int l = map.get(p);
        map.remove(p);
        arr.remove(new Problem(p,l));
    }

    static class Problem implements Comparable<Problem>{
        int p; int l;
        public Problem(int p, int l){
            this.p = p;
            this.l = l;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.l == o.l){
                return this.p - o.p;
            }
            return this.l - o.l;
        }
    }
}
