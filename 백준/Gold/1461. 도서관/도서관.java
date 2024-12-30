import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> rightBooks = new ArrayList<>();
        List<Integer> leftBooks = new ArrayList<>();

        int maxAbsBook = 0;
        boolean isRightOfMaxBookLocation = false;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int book = Integer.parseInt(st.nextToken());
            if(book > 0) rightBooks.add(book);
            else if(book < 0) leftBooks.add(book);
            if(Math.abs(book) > maxAbsBook) {
                maxAbsBook = Math.abs(book);
                isRightOfMaxBookLocation = book > 0;
            }
        }

        rightBooks.sort(Comparator.naturalOrder());
        leftBooks.sort(Comparator.reverseOrder());

        int sum = 0;
        int rIndex = rightBooks.size()-1;
        int lIndex = leftBooks.size()-1;
        if(isRightOfMaxBookLocation) {
            sum = rightBooks.get(rightBooks.size()-1);
            rIndex -= M;
        }
        else {
            sum = -leftBooks.get(leftBooks.size()-1);
            lIndex -= M;
        }

        for(;rIndex>=0;rIndex-=M) {
            sum += 2 * rightBooks.get(rIndex);
        }
        for(;lIndex>=0;lIndex-=M) {
            sum -= 2 * leftBooks.get(lIndex);
        }

        System.out.println(sum);
    }
}