import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> rightBooks = new PriorityQueue<>(Collections.reverseOrder()); // 양수 책
        PriorityQueue<Integer> leftBooks = new PriorityQueue<>();

        int maxAbsBook = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int book = Integer.parseInt(st.nextToken());

            if(book > 0) rightBooks.add(book);
            else if(book < 0) leftBooks.add(book);

            maxAbsBook = Math.max(Math.abs(book), maxAbsBook);
        }

        int sum = 0;
        while(!rightBooks.isEmpty()) {
            sum += 2 * rightBooks.poll();
            for(int i=0;i<M-1 && !rightBooks.isEmpty();i++) {
                rightBooks.poll();
            }
        }
        while(!leftBooks.isEmpty()) {
            sum -= 2 * leftBooks.poll();
            for(int i=0;i<M-1 && !leftBooks.isEmpty();i++) {
                leftBooks.poll();
            }
        }

        System.out.println(sum-maxAbsBook);
    }
}