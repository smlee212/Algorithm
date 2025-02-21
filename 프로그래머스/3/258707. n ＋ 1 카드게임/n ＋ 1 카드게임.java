import java.util.*;
class Solution {
    
    /*        
        모든 카드들은 자신의 짝궁이 있음
        (1,n), (2,n-1), (3,n-2), ...
    
        2장을 뽑을지 말지를 매번 정하는 것이 아니라
        일단 가져가고 추후 필요할 때 코인을 써서 사용하면 된다는 아이디어.....!!!!!!
    */
    
    public int solution(int coin, int[] cards) {
        int round = 0; // 현재 라운드 수
        boolean isNextRound = true; // 다음 라운드로 갈 수 있는지 여부
        
        int n = cards.length; // 카드 개수
        
        int[] numOfCards = new int[n]; // 카드를 번호로 표시 (같은 번호이면 페어가 되는 카드임)
        for(int i=0;i<n;i++) {
            numOfCards[i] = Math.min(cards[i], n-cards[i]+1);
        }
        
        Set<Integer> hands = new HashSet<>(); // 보유한 카드 목록
        Set<Integer> hold = new HashSet<>(); // 보류중인 카드 목록
        int pairOfHands = 0; // 보유카드 중 페어 개수
        int pairOfhold = 0; // 보류카드 중 페어 개수
        
        // 현재 보유한 카드 목록 초기화
        for(int i=0;i<n/3;i++) {
            pairOfHands = addCard(hands, numOfCards[i], pairOfHands);
        }
        
        // 남아있는 카드들에 대해 라운드 진행
        int i=n/3;
        while(true) {
            round++;
            // 카드 2장를 보류 목록에 추가
            if(i<n) {
                pairOfhold = addCard(hold, numOfCards[i], pairOfhold);
                pairOfhold = addCard(hold, numOfCards[i+1], pairOfhold);
            }
            
            // 보유한 페어가 있는지 확인
            if(pairOfHands > 0) {
                pairOfHands--;
            }
            // 보유한 카드와 보류한 카드를 매칭하여 페어가 되는지 확인
            else {
                boolean isPair = false;
                for(int card : hands) {
                    // 보유한 카드가 보류한 카드에 존재한다면
                    if(hold.contains(card)) { 
                        // 코인 하나 사용하여 보류 카드 선택 후 라운드 넘어감
                        coin--; 
                        hold.remove(card);
                        hands.remove(card);
                        isPair = true;
                        break;
                    }
                }
                
                // 보유 카드와 보류 카드로 매칭이 안된다면
                if(!isPair) {
                    // 보류 카드 중에서 페어가 되는지 확인
                    if(pairOfhold > 0) {
                        pairOfhold--;
                        coin -= 2;
                    }
                    // 페어를 만들 수 없다면 게임 종료
                    else {
                        isNextRound = false;
                    }
                }
            }
            
            // 다음 라운드를 진행할 수 없거나 코인이 부족한 경우 종료
            if(!isNextRound || coin < 0) break;
            
            // 최종 라운드까지 도달했다면 종료
            if(i>=n) {                
                break;
            }
            else {
                i += 2;
            }
        }
        
        return round;
    }
    
    private int addCard(Set<Integer> cardSet, int card, int cnt) {        
        if(cardSet.contains(card)) { // 보유중이라면 제거하고 페어 개수 추가
            cardSet.remove(card);
            cnt++;
        }
        else { // 없다면 카드 목록에 추가
            cardSet.add(card);
        }
        return cnt;
    }
}