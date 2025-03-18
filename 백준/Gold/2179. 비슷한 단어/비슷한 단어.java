import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Item> items = new ArrayList<>();
        for(int i=0;i<N;i++) {
            String word = br.readLine();
            items.add(new Item(word, i));
        }

        items.sort(Comparator.comparing(o -> o.word));

        Map<String, Set<Item>> map = new HashMap<>();

        String prefixWithMaxLength = "";
        int firstOrder = N;
        Item preItem = items.get(0);
        for(int i=1;i<N;i++) {
            Item nowItem = items.get(i);
            // 공통되는 접두사 찾기
            int index=0;
            while (index<preItem.word.length() && preItem.word.charAt(index) == nowItem.word.charAt(index)) {
                index++;
            }
            // 해당 접두사를 가진 단어들을 map에 정리하기
            String prefix = preItem.word.substring(0,index);
            // 길이가 같은 경우
            if(prefixWithMaxLength.length() == prefix.length()) {
                // 단어가 같을 경우
                if(prefixWithMaxLength.equals(prefix)) {
                    firstOrder = Math.min(firstOrder, nowItem.order);
                }
                else {
                    int nowOrder = Math.min(preItem.order, nowItem.order);
                    if(nowOrder < firstOrder) {
                        firstOrder = nowOrder;
                        prefixWithMaxLength = prefix;
                    }
                }
            }
            else if(prefixWithMaxLength.length() < prefix.length()){
                prefixWithMaxLength = prefix;
                firstOrder = Math.min(preItem.order, nowItem.order);
            }
            if(map.containsKey(prefix)) {
                map.get(prefix).add(nowItem);
            }
            else {
                Set<Item> set = new HashSet<>();
                set.add(preItem);
                set.add(nowItem);
                map.put(prefix, set);
            }
            // 다음 인덱스로 넘어감
            preItem = nowItem;
        }

        for(String key : map.keySet()) {
            if(prefixWithMaxLength.equals(key)) {
                List<Item> result = new ArrayList<>(map.get(key));
                result.sort(Comparator.comparingInt(o -> o.order));
                for(int i=0;i<2;i++) {
                    System.out.println(result.get(i).word);
                }
                break;
            }
        }
    }

    private static class Item {
        String word;
        int order;
        public Item(String word, int order) {
            this.word = word;
            this.order = order;
        }
    }
}
