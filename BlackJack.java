import java.util.*;
public class BlackJack extends Trunp implements IFGames ,Draw{
    // スキャナークラスをインスタンス化
    Scanner stdIn = new Scanner(System.in);
    
    private Map<Integer, Integer> hands;        // プレイヤーの手札
    private List<Integer> winner;               // 勝ったプレイヤー
    private List<Integer> drawer;               // 引き分けたプレイヤー
    private List<Integer> tranp;                        // トランプの山札
    private List<Integer> deletecard;                   // トランプの山札から削除したカード
    private int drawcnt;                        // 引き分けをカウントする変数
    private int cnt;                            // カウントする変数
    private String strplayers;                  // 一時的にString型としてプレイヤーの人数保持する変数
    private int players;                        // プレイヤーの人数
    private String decision;                    // プレイヤーの選択を判定するための変数
    private int hand;                           // プレイヤーの手札を一時的に保持する変数
    private int dealer;                         // ディーラーの手札
    private int size;                           // winner, drawerどちらかのサイズを保持する変数
    private int playerscore;                    // プレイヤーのスコア
    private int dealerscore;                    // ディーラーのスコア
    
    

    
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
    @Override public int draw(List<Integer> tranp, List<Integer> deletecard){
            // 山札からランダムにカードを取り出す
            int drownumber = new Random().nextInt(tranp.size());
            // その数値をdrawに代入
            int draw = tranp.get(drownumber);
            deletecard.add(draw);
            // 山札から取り出したカードを削除する
            tranp.remove(drownumber);
            // 代入した数字を返す
            return draw;
    }
    // トランプの枚数を初期化するrecoverytrunpメソッド
    @Override public void recoverytranp(List<Integer>tranp, List<Integer> deletecard){
            // 削除したカードの枚数をsizeに代入
            int size = deletecard.size();
            // sizeの回数ループ
            for (int i = 0; i < size; i ++){
                // 削除したカードをtranpに追加
                tranp.add(deletecard.get(i));
            }
    }
    // ブラックジャックを実行するblackjackメソッド
    public void blackjack(){
        while (true) {
            this.hands = new HashMap<>();
            this.winner = new ArrayList<>();
            this.drawer = new ArrayList<>();
            this.deletecard = new ArrayList<>();
            this.cnt = 0;    //カウントする変数cntを定義
            this.drawcnt = 0; // 引き分けをカウントする変数draw_cntを定義
            System.out.println("プレイヤーの人数を入力してください(推奨人数：1 ~ 5　最大10人)"); 
            System.out.println("99と入力するとループを終了します");
            System.out.print("プレイヤーの人数を入力：");      
            try{
                this.tranp = super.tranp; 
                // プレイヤーの人数を入力
                // 最初はString型で入力を促す
                this.strplayers = stdIn.next();   
                // int型に変換
                this.players = Integer.valueOf(this.strplayers);
                // 入力された値が99なら
                if ( this.players == 99){
                    // ループを終了
                    break;
                }
                //  入力された値が1未満か10より大きかった場合
                if (this.players < 1 || this.players > 10){
                    // 例外を送出する
                    throw new NumericLimit(this.players);
                }
                System.out.println("手札が配られます");
                // 入力されたプレイヤー数分ループ
                for (int i = 0; i < this.players; i++){
                    // 手札をドローしhandに代入
                    this.hand = draw(this.tranp, this.deletecard) + draw(this.tranp, this.deletecard);
                    // mapリストhandsにプレイヤー(i + 1)として手札を追加
                    this.hands.put(i + 1, this.hand);
                }
                // 全プレイヤーの手札を表示
                for (Integer key : this.hands.keySet()) {
                    System.out.println(key + ":" + hands.get(key));
                }
                // 無限ループ
                while (true) {                    
                // プレイヤー数分ループ
                for (int i = 1; i <= this.players; i++){
                    // ドローするか提案
                    System.out.print("Player" + (i) + "さん、ドローしますか？ y / n");
                    // y か n を入力
                    this.decision = stdIn.next();
                    // 入力された文字がnでもyでもなかった場合
                    if (this.decision.equals("n") == false && this.decision.equals("y") == false){
                        // 例外を送出
                        throw new YorN(this.decision);
                    }
                    // nが入力された場合
                    if (this.decision.equals("n") == true){
                        // カウントを増やす
                        this.cnt += 1;

                    }
                    // yと入力された場合
                    else if(this.decision.equals("y") == true){
                        // 変数handを作成
                        this.hand = 0;
                        // handにドロー前の手札を代入
                        this.hand =  this.hands.get(i); // プレイヤーのカードの数値をいったん抜いてその数値にドローしたものを加算し再度mapに加える
                        // もし手札の合計が21以下なら
                        if (this.hand <=21){
                            //  mapリストhandsからプレイヤーのデータを削除
                            this.hands.remove(i);
                            // handにドローした数字を足す
                            this.hand += draw(this.tranp, this.deletecard);
                            // handsにデータを追加しなおす
                            this.hands.put(i, this.hand);
                        }
                        // 21を超えていた場合
                        else{
                            System.out.println("あなたは21を超えているのでドローできません");
                        }
                    }                
                        // ドロー後の手札を表示
                        System.out.println((i) + ":" + this.hands.get(i));
                }
                // もし全員がnを選択していた場合
                if ( this.cnt == this.players){
                    // ループを終了
                    break;
                }
                // 選択していなかった場合はカウントを初期化しループを続行
                this.cnt = 0;
                // 全員が一回ドローするかしないかを選択した段階で全員の手札を表示
                for (Integer key : this.hands.keySet()) {
                    // 見やすいように最初の行にのみ改行を入れる
                    if(key == 1){
                        System.out.println();
                    }
                    System.out.println(key + ":" + this.hands.get(key));
                }
            }
            this.cnt = 0;           // カウントを初期化
            this.dealer= 0;     // ディーラーの手札を初期化
            System.out.println("ディーラーがドローします");
            // ルールに従いディーラーの手札の合計が17以上になるまでループ
            while (this.dealer < 17){
                // ドローしたものを加算
                this.dealer += draw(this.tranp, this.deletecard);
            }
            // もしディーラーの手札の合計が21を超えた場合
            if (this.dealer > 21){
                // ディーラーの手札を表示
                System.out.println("ディーラーの点数は"  + this.dealer + "です");
                System.out.println("ディーラーがバーストしました。バーストしていない全てのプレイヤーの勝ちとなります");
                // プレイヤー数分ループ
                for (Integer key : this.hands.keySet()) {
                    // もしそのプレイヤーの手札の合計が21を超えてなければ
                    if (this.hands.get(key) < 22){
                        // 勝者リストにプレイヤーを追加
                        this.winner.add(key);
                        // カウントを増やす
                        this.cnt += 1;

                    }
                }
                // もしカウントが0以上(勝者が1人以上いた場合)
                if (this.cnt >= 1){
                    // 勝者の人数をsizeに代入
                    this.size = this.winner.size();
                    // 勝者の人数分ループ
                    for (int i = 0; i < this.size; i++){
                    // リストの末尾である場合
                        if (i == this.size -1){
                            // 空白を入れずに出力
                            System.out.print("プレイヤー" + this.winner.get(i));
                        }
                        // 末尾ではない場合
                        else{
                            // 空白を入れて出力
                            System.out.print("プレイヤー" + this.winner.get(i) + " ");
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
                System.out.println("ディーラーの点数は"  + this.dealer + "です");
                // プレイヤー数分ループ
                for (Integer key : this.hands.keySet()) {
                    // ディーラーが21にどれだけ近いか代入
                    this.dealerscore = 21 - this.dealer;
                    // プレイヤーのスコアを初期化
                    this.playerscore = 0; 
                    // プレイヤーの手札が22未満の場合
                    if (this.hands.get(key) < 22){
                        // プレイヤーは21にどれだけ近いかを代入
                        this.playerscore += 21 - this.hands.get(key);
                        // もしプレイヤーがディーラーよりも21に近かった場合
                        if(this.playerscore < this.dealerscore){
                            // 勝者リストにプレイヤーを追加
                            this.winner.add(key);
                            // カウントを増やす
                            this.cnt += 1;         
                        }
                    // もしプレイヤーとディーラーのスコアが同じだtt場合
                        else if(this.playerscore == this.dealerscore){
                            // 引き分けリストにプレイヤーを追加
                            this.drawer.add(key);
                            // カウントを減らす
                            this.drawcnt = -1;           
                        }
                        }
                }
                // 勝者が一人でもいた場合
                if (this.cnt > 0){
                    // 勝者の人数をsizeに代入
                    this.size = this.winner.size();
                    // 勝者の人数分ループ
                    for (int i = 0; i < this.size; i++){
                        // リストの末尾である場合
                        if (i == this.size -1){
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
                    this.size = this.drawer.size();
                    // 引き分けの人数分ループ
                    for (int i = 0; i < this.size; i++){
                        if (size > 0){
                        // リストの末尾である場合
                        if (i == this.size -1){
                            // 空白を入れずに出力
                            System.out.print("プレイヤー" + this.drawer.get(i));
                        }
                        // リストの末尾以外の場合
                        else{
                            // 空白を入れて出力
                            System.out.print("プレイヤー" + this.drawer.get(i) + " ");
                        }
                    }
                    }                
                    System.out.println("は引き分けです");
                }
                // 引き分け以上のプレイヤーがいなかった場合
                else{
                    System.out.println("ディーラーの勝ちです。");
                }
            }
            }catch(NumberFormatException e){    // 入力されたものがintに変換できなかった場合
                System.out.println("数字以外の値が入力されました");
                System.out.println("1 ~ 10 の整数を入力してください");
                System.out.println("ゲームの開始時に戻ります");
            }
            catch (NumericLimit e){             // 入力されたものが指定された範囲ではなかった場合
                System.out.println("1 ~ 10の値を入力してください");
                System.out.println("ゲームの開始時に戻ります");
        }
            catch(YorN e){                      // 入力されたものが y か n ではなかった場合
                System.out.println("y か n を入力してください");
                System.out.println("ゲームの開始時に戻ります");
        }
            // トランプの枚数を初期化
            recoverytranp(this.tranp, this.deletecard);
  }
}

}

