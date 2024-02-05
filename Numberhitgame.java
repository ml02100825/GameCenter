package GameCenter;
import java.util.*;
public class Numberhitgame implements IFGames{
    // スキャナークラスをインスタンス化
    Scanner stdIn = new Scanner(System.in);
    // ランダムクラスをインスタンス化
    Random rand = new Random();
    private int cnt;                // カウントする変数
    private String strnum;          // 一時的にString型としてユーザーが入力した数字を保持する変数
    private int num;                // ユーザーが入力した数字
    private int randomnum;          // 数あての答え
    private boolean roop;           // ループを継続するかどうか判定する変数
    private int winstreek;          // 連続正解数
    private String windecision;     // 正解したときにループを継続するかどうか判定する変数
    private String defeatdecision;  // 不正解だった時にループを継続するかどうか判定する変数
    // メソッド定義 抽象メソッドのオーバーライド
    // アプリの起動 startUpメソッド
    public void startUp(){
        System.out.println("数当てゲームを開始します！");
        //アプリの本機能である、numberhitメソッドを呼び出す
        numberhit();
    }
    // アプリの終了 endメソッド
    public void end(){
        System.out.println("数当てゲームを終了します。");
    }
      // メソッド定義
      // 数当て機能の実装  
    public void numberhit(){
        this.winstreek = 0; // 連続正解した数
        this.roop = true;    // roopをtrueに設定
       // 無限ループ 
        while (true){
            try{
                this.cnt = 0; // ミスをした回数
                this.randomnum = rand.nextInt(0, 101); // 正解の数字
                // roopがfalseなら
                if (this.roop == false){
                    // まだゲームを続けるか提案する
                    System.out.print("まだ続けますか？ y / n ：");
                    // 文字を入力
                    this.windecision = stdIn.next();
                    // 入力された値が y か n 以外だった場合例外を送出する
                    if (this.windecision.equals("y") == false && this.windecision.equals("n") == false){
                        throw new YorN(this.windecision);
                    }
                    // ゲームを続ける場合
                    if (this.windecision.equals("y") == true){
                        // roopをtrueに設定しなおす
                        this.roop = true;
                    }
                    // 続けない場合
                    else if (this.windecision.equals("n") == true){
                        // もし2回以上連続正解していた場合何回連続正解したか表示
                        if (this.winstreek > 1){
                            System.out.println("あなたは" + this.winstreek + "回連続で正解しました！");
                        }
                        // ループを終了
                        break;
                    }
                }
                // メッセージを表示
                System.out.println("0~100までの数字が格納されました");
                System.out.println("許されるミスは4回までです");
                // 4回ミスをするまでループ
                while (this.cnt < 5){
                    // 数値の入力を促す
                    System.out.print("0~100までの数字を入力してください：");
                    // 最初はString型で入力を促す
                    this.strnum = stdIn.next();
                    // int型に変換
                    this.num = Integer.valueOf(this.strnum);
                    // 入力された数字が0以上100未満だった場合例外を送出する
                    if (this.num < 0 || this.num > 100){
                        throw new NumericLimit(this.num);
                    }
                    // もし正解していた場合
                    if (this.num == this.randomnum){
                        this.cnt += 1;
                        System.out.println("正解です！");
                        //　何回目で正解したか表示
                        System.out.println("あなたは" + this.cnt + "回目で正解しました！");
                        // ループしないようにfalseに設定
                        this.roop = false;
                        // 連続正解数を1増やす
                        this.winstreek += 1;
                        // カウントを0に戻す
                        this.cnt = 0;
                        // ループ終了
                        break;
                    }
                    // 入力された数値が正解の数字が小さくて4回ミスをしていない場合(4回ミスしていた場合もう入力を受け付けないため)
                    else if( this.num > this.randomnum && this.cnt != 4){
                        // 小さいと表示
                        System.out.println("正解の数字は" + this.num + "よりも小さいです！");
                    }
                    // 入力された数値が正解の数字が大きくて4回ミスをしていない場合(4回ミスしていた場合もう入力を受け付けないため)
                    else if( this.num < this.randomnum && this.cnt != 4){
                        // 大きいと表示
                        System.out.println("正解の数字は" + this.num + "よりも大きいです！");
                    }
                    // ミスカウントを増やす
                    this.cnt += 1;
                }
                // もし5回ミスした場合
                if (this.cnt == 5){
                    // 正解の数字を表示
                    System.out.println("正解の数字は" + this.randomnum + "でした");
                    // 2問連続正解以上していた場合
                    if(this.winstreek > 1){
                        // 連続正解数を表示
                        System.out.println("あなたは" + this.winstreek + "回連続で正解していました");
                    }
                    // 連続正解数を0に戻す
                    this.winstreek = 0;
                    System.out.println("あなたは数あてゲームに失敗しました。");
                    // もう一度挑戦するか促す
                    System.out.print("もう一度挑戦しますか？ y / n");     //yかn以外の入力を受け付けない例外作りたい
                    // 入力してもらう
                    this.defeatdecision = stdIn.next();
                    // 入力された値が y か n 以外だった場合例外を送出する
                    if (this.defeatdecision.equals("y") == false && this.defeatdecision.equals("n") == false){
                        throw new YorN(this.defeatdecision);
                    }
                    // もし続けない場合
                    if (this.defeatdecision.equals("n") == true){
                        // ループ終了
                        break;
                    }
                    // 続ける場合
                    else if(this.defeatdecision.equals("y") == true){
                        // カウントを0に戻しループを続ける
                        this.cnt = 0;
                    }
        }}catch(NumberFormatException e){   // 入力されたものがintに変換できなかった場合
            System.out.println("数字以外の値が入力されました");
            System.out.println("ゲームの開始時に戻ります");
        }catch(NumericLimit e){             // 入力されたものが指定された範囲ではなかった場合
            System.out.println("0~100以外の値が入力されました");
            System.out.println("ゲームの開始時に戻ります");
        }catch(YorN e){                     // 入力されたものが y か n ではなかった場合
            System.out.println("y か n で入力してください");
            System.out.println("ゲームの開始時に戻ります");
        }
        }
}
}
