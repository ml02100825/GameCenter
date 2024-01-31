import java.lang.reflect.Array;
import java.util.*;
public class BlackJack implements IFGames ,Draw{
    Scanner stdIn = new Scanner(System.in);


    
    
    public void startUp(){
        System.out.println("ブラックジャックを開始します！");
        //アプリの本機能である、calcメソッドを呼び出す
        blackjack();
    }
    // アプリの起動 endメソッド
    public void end(){
        System.out.println("ブラックジャックを終了します。");
    }
    public int draw(List<Integer> tranp){
            int drownumber = new Random().nextInt(tranp.size());
            int draw = tranp.get(drownumber);
            tranp.remove(drownumber);
            return draw;
    }
    public void blackjack(){
        Map<Integer, Integer> hands = new HashMap<>();
        List<Integer> tranp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,10,10,10,1,2,3,4,5,6,7,8,9,10,10,10,10,1,2,3,4,5,6,7,8,9,10,10,10,10,1,2,3,4,5,6,7,8,9,10,10,10,10));
        System.out.println("何人で遊びますか(1~5人)：");
        int players = stdIn.nextInt();
        int dealer= draw(tranp);
        System.out.println("手札が配られます");
        for (int i = 0; i < players; i++){
            hands.put(i + 1, draw(tranp));
        }
        for (Integer key : hands.keySet()) {
            System.out.println(key + ":" + hands.get(key));
        }
        System.out.println("ディーラーの手札はこちらです");
        System.out.println(dealer);
        for (int i = 0; i < players; i++){
            System.out.println("Player" + (i+1) + "さん、ドローしますか？ y / n");
            String decision = stdIn.next();
            if (decision.equals("n")){
                break;
            }
            else if(decision.equals("y")){
                hands.put(i + 1, draw(tranp)); // プレイヤーのカードの数値をいったん抜いてその数値にドローしたものを加算し再度mapに加える
            }
        }
        for (Integer key : hands.keySet()) {
            System.out.println(key + ":" + hands.get(key));
        }
    }
}

