import java.util.*;
public class BlackJack extends Trunp implements IFGames ,Draw{
    // スキャナークラスをインスタンス化
    Scanner stdIn = new Scanner(System.in);
    
    Map<Integer, Integer> hands;        // プレイヤーの手札
    List<Integer> winner;               // 勝ったプレイヤー
    List<Integer> drawer;               // 引き分けたプレイヤー
    List<Integer> tranp = super.tranp;  // トランプの山札
    int drawcnt;                        // 引き分けをカウントする変数
    int cnt;                            // カウントする変数
    
    

    
    // メソッド定義 抽象メソッドのオーバーライド
    // アプリの起動 startUpメソッド
    public void startUp(){
        System.out.println("ブラックジャックを開始します！");
        //アプリの本機能である、blackjackメソッドを呼び出す
        blackjack();
    }
    // アプリの終了 endメソッド
    public void end(){
        System.out.println("ブラックジャックを終了します。");
    }
    // カードをドローするdrawメソッド(戻り値はint型)
    public int draw(List<Integer> tranp){
            // 山札からランダムにカードを取り出す
            int drownumber = new Random().nextInt(tranp.size());
            // その数値をdrawに代入
            int draw = tranp.get(drownumber);
            // 山札から取り出したカードを削除する
            tranp.remove(drownumber);
            // 代入した数字を返す
            return draw;
    }
    // ブラックジャックを実行するblackjackメソッド
    public void blackjack(){
        this.hands = new HashMap<>();
        this.winner = new ArrayList<>();
        this.drawer = new ArrayList<>();
        this.cnt = 0;    //カウントする変数cntを定義
        this.drawcnt = 0; // 引き分けをカウントする変数draw_cntを定義
        System.out.println("プレイヤーの人数を入力してください(推奨人数：1 ~ 5　最大10人)"); 
    
        try{
        // 数値の入力
        // 最初はString型で入力を促す
        String strplayers = stdIn.next();   
        // int型に変換
        int players = Integer.valueOf(strplayers);
        if (players < 1 || players > 10){
            throw new NumericLimit(players);
        }
        System.out.println("手札が配られます");
        // 入力されたプレイヤー数分ループ
        for (int i = 0; i < players; i++){
            // 手札をドローしhandに代入
            int hand = draw(tranp) + draw(tranp);
            // mapリストhandsにプレイヤー(i + 1)として手札を追加
            hands.put(i + 1, hand);
        }
        // 全プレイヤーの手札を表示
        for (Integer key : hands.keySet()) {
            System.out.println(key + ":" + hands.get(key));
        }
        // 無限ループ
        while (true) {
            
        // プレイヤー数分ループ
        for (int i = 1; i <= players; i++){
            // ドローするか提案
            System.out.println("Player" + (i) + "さん、ドローしますか？ y / n");
            // y か n を入力
            String decision = stdIn.next();
            if (decision.equals("n") == false && decision.equals("y") == false){
                throw new YorN(decision);
            }
            // nが入力された場合
            if (decision.equals("n") == true){
                // カウントを増やす
                this.cnt += 1;

            }
            // yと入力された場合
            else if(decision.equals("y") == true){
                // 変数handを作成
                int hand = 0;
                // handにドロー前の手札を代入
                hand =  hands.get(i); // プレイヤーのカードの数値をいったん抜いてその数値にドローしたものを加算し再度mapに加える
                // もし手札の合計が21以下なら
                if (hand <=21){
                    //  mapリストhandsからプレイヤーのデータを削除
                    hands.remove(i);
                    // handにドローした数字を足す
                    hand += draw(tranp);
                    // handsにデータを追加しなおす
                    hands.put(i, hand);
                }
                // 21を超えていた場合
                else{
                    System.out.println("あなたは21を超えているのでドローできません");
                }



            }
            
                // ドロー後の手札を表示
                System.out.println((i) + ":" + hands.get(i));
        }
        // もし全員がnを選択していた場合
        if ( this.cnt == players){
            // ループを終了
            break;
        }
        // 選択していなかった場合はカウントを初期化しループを続行
        this.cnt = 0;
        // 全員が一回ドローするかしないかを選択した段階で全員の手札を表示
        for (Integer key : hands.keySet()) {
            // 見やすいように最初の行にのみ改行を入れる
            if(key == 1){
                System.out.println();
            }
            System.out.println(key + ":" + hands.get(key));
        }


    }
 
    this.cnt = 0;           // カウントを初期化
    int dealer= 0;     // ディーラーの手札を初期化
    System.out.println("ディーラーがドローします");
    // ルールに従いディーラーの手札の合計が17以上になるまでループ
    while (dealer < 17){
        // ドローしたものを加算
        dealer += draw(tranp);
    }
    // もしディーラーの手札の合計が21を超えた場合
    if (dealer > 21){
        System.out.println("ディーラーの点数は"  + dealer + "です");
        System.out.println("ディーラーがバーストしました。バーストしていない全てのプレイヤーの勝ちとなります");
        // プレイヤー数分ループ
        for (Integer key : hands.keySet()) {
            // もしそのプレイヤーの手札の合計が21を超えてなければ
            if (hands.get(key) < 22){
                // 勝者リストにプレイヤーを追加
                winner.add(key);
                // カウントを増やす
                this.cnt += 1;

            }

        }
        // もしカウントが0以上(勝者が1人以上いた場合)
        if (this.cnt >= 1){
            // 勝者の人数をsizeに代入
            int size = winner.size();
            // 勝者の人数分ループ
            for (int i = 0; i < size; i++){
               // リストの末尾である場合
                if (i == size -1){
                    // 空白を入れずに出力
                    System.out.print("プレイヤー" + winner.get(i));
                }
                // 末尾ではない場合
                else{
                    // 空白を入れて出力
                    System.out.print("プレイヤー" + winner.get(i) + " ");
                }
            }
            System.out.println("の勝ちです");
        }
        // 勝者がいなかった場合
        else{
            System.out.println("バーストしていないプレイヤーはいませんでした。");
        }

    }
    // ディーラーの点数が22未満の場合
    else{
        // ディーラーの点数を表示
        System.out.println("ディーラーの点数は"  + dealer + "です");
        // プレイヤー数分ループ
        for (Integer key : hands.keySet()) {
            // ディーラーが21にどれだけ近いか
            int dealerscore = 21 - dealer;
            int playerscore = 0;    // プレイヤーが21にどれだけ近いか
            // プレイヤーの手札が22未満の場合
            if (hands.get(key) < 22){
                // プレイヤーは21にどれだけ近いかを代入
                playerscore += 21 -hands.get(key);
                // もしプレイヤーがディーラーよりも21に近かった場合
                if(playerscore < dealerscore){
                    // 勝者リストにプレイヤーを追加
                    winner.add(key);
                    // カウントを増やす
                    this.cnt += 1;
    
                }
            // もしプレイヤーとディーラーのスコアが同じだtt場合
                else if(playerscore == dealerscore){
                    // 引き分けリストにプレイヤーを追加
                    this.drawer.add(key);
                    // カウントを減らす
                    this.drawcnt = -1;
        
                }
                }
     

        }
        // 勝者が一人でもいた場合
        if (cnt > 0){
            // 勝者の人数をsizeに代入
            int size = winner.size();
            // 勝者の人数分ループ
            for (int i = 0; i < size; i++){
                // リストの末尾である場合
                if (i == size -1){
                    System.out.print("プレイヤー" + this.winner.get(i));
                }
                // リストの末尾ではない場合
                else{
                    System.out.print("プレイヤー" + this.winner.get(i) + " ");
                }
            }
            System.out.println("の勝ちです");
        }
        // 勝者がいなく引き分けの人数が1人以上の場合
        else if (this.drawcnt < 0){
            // 引き分けの人数をsizeに代入
            int size = this.drawer.size();
            // 引き分けの人数分ループ
            for (int i = 0; i < size; i++){
                // リストの末尾である場合
                if (i == size -1){
                    // 空白を入れずに出力
                    System.out.print("プレイヤー" + this.winner.get(i));
                }
                // リストの末尾以外の場合
                else{
                    // 空白を入れて出力
                    System.out.print("プレイヤー" + winner.get(i) + " ");
                }
            }
        
            System.out.println("は引き分けです");
        }
        // 引き分け以上のプレイヤーがいなかった場合
        else{
            System.out.println("ディーラーの勝ちです。");
        }
    }
    }catch(NumberFormatException e){
        System.out.println("数字以外の値が入力されました");
    }
    catch (NumericLimit e){
        System.out.println("1 ~ 10の値を入力してください");
}
    catch(YorN e){
        System.out.println("y か n を入力してください");
}
}
    


    }

