import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<Integer> realSet = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i=0;i<K;i++) {
            int person = Integer.parseInt(st.nextToken());
            realSet.add(person);
        }

        List<Set<Integer>> list = new ArrayList<>();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            Set<Integer> set = new HashSet<>();
            for(int j=0;j<K;j++) {
                int person = Integer.parseInt(st.nextToken());
                set.add(person);
            }
            list.add(set);
        }

        boolean[] isRealParty = new boolean[M];
        int size = realSet.size();
        int nextSize = size;
        do {
            size = nextSize;
            for(int i=0;i<list.size();i++) {
                Set<Integer> set = list.get(i);

                boolean isReal = false;
                for(int person : set) {
                    if(realSet.contains(person)) {
                        isReal = true;
                        break;
                    }
                }

                if(isReal) {
                    realSet.addAll(set);
                    isRealParty[i] = true;
                }
            }
            nextSize = realSet.size();
        } while(size < nextSize);

        int cnt = 0;
        for(int i=0;i<M;i++) {
            cnt += isRealParty[i] ? 0 : 1;
        }

        System.out.println(cnt);
    }
}
