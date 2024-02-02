import java.util.*;
public class Numberhitgame implements IFGames{
    Scanner stdIn = new Scanner(System.in);
    Random rand = new Random();
    int cnt;
    boolean roop;
    int winstreek;
    // メソッド定義 抽象メソッドのオーバーライド
    // アプリの起動 startUpメソッド
    public void startUp(){
        System.out.println("数当てゲームを開始します！");
        //アプリの本機能である、calcメソッドを呼び出す
        numberhit();
    }
    // アプリの起動 endメソッド
    public void end(){
        System.out.println("数当てゲームを終了します。");
    }
      // メソッド定義
      // 数当て機能の実装
      public void numberhit(){

        this.cnt = 0; // ミスをした回数
        this.winstreek = 0; // 連続正解した数
        this.roop = true;    // roopをtrueに設定
        try{
       // 無限ループ 
        while (true){
            int randomnum = rand.nextInt(0, 101); // 正解の数字
            // roopがflaseなら
            if (this.roop == false){
                // まだゲームを続けるか提案する
                System.out.println("まだ続けますか？ y / n ：");
                // 文字を入力
                String windecision = stdIn.next();
                // 入力された値が y か n 以外だった場合例外を送出する
                if (windecision.equals("y") == false && windecision.equals("n") == false){
                    throw new YorN(windecision);
                }
                // ゲームを続ける場合
                if (windecision.equals("y") == true){
                    // roopをtrueに設定しなおす
                    this.roop = true;
                }
                // 続けない場合
                else if (windecision.equals("n") == true){
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
                String strnum = stdIn.next();
                // int型に変換
                int num = Integer.valueOf(strnum);
                // 入力された数字が0以上100未満だった場合例外を送出する
                if (num < 0 || num > 100){
                    throw new NumericLimit(num);
                }
                // もし正解していた場合
                if (num == randomnum){
                    this.cnt += 1;
                    System.out.println("正解です！");
                    //　何回目で正解したか表示
                    System.out.println("あなたは" + cnt + "回目で正解しました！");
                    // ループしないようにfalseに設定
                    roop = false;
                    // 連続正解数を1増やす
                    this.winstreek += 1;
                    // カウントを0に戻す
                    this.cnt = 0;
                    // ループ終了
                    break;
                }
                // 入力された数値が正解の数字が小さくて4回ミスをしていない場合(4回ミスしていた場合もう入力を受け付けないため)
                else if( num > randomnum &&cnt != 4){
                    // 小さいと表示
                    System.out.println("正解の数字は" + num + "よりも小さいです！");
                }
                // 入力された数値が正解の数字が大きくて4回ミスをしていない場合(4回ミスしていた場合もう入力を受け付けないため)
                else if( num < randomnum && cnt != 4){
                    // 大きいと表示
                    System.out.println("正解の数字は" + num + "よりも大きいです！");
                }
                // ミスカウントを増やす
                cnt += 1;
            }
            // もし5回ミスした場合
            if (this.cnt == 5){
                System.out.println("正解の数字は" + randomnum + "でした");
                // 2問連続正解以上していた場合
                if(this.winstreek > 1){
                    // 連続正解数を表示
                    System.out.println("あなたは" + this.winstreek + "回連続で正解していました");
                }
                // 連続正解数を0に戻す
                winstreek = 0;
                System.out.println("あなたは数あてゲームに失敗しました。");
                // もう一度挑戦するか促す
                System.out.print("もう一度挑戦しますか？ y / n");     //yかn以外の入力を受け付けない例外作りたい
                // 入力してもらう
                String defeatdecision = stdIn.next();
                // 入力された値が y か n 以外だった場合例外を送出する
                if (defeatdecision.equals("y") == false && defeatdecision.equals("n") == false){
                    throw new YorN(defeatdecision);
                }
                // もし続けない場合
                if (defeatdecision.equals("n") == true){
                    // ループ終了
                    break;
                }
                // 続ける場合
                else if(defeatdecision.equals("y") == true){
                    // カウントを0に戻しループを続ける
                    this.cnt = 0;
                }

        }


        }
    }catch(NumberFormatException e){
        System.out.println("数字以外の値が入力されました");
        System.out.println("ゲームの選択からやり直してください");
    }catch(NumericLimit e){
        System.out.println("0~100以外の値が入力されました");
        System.out.println("ゲームの選択からやり直してください");
    }catch(YorN e){
        System.out.println("y か n で入力してください");
        System.out.println("ゲームの選択からやり直してください");
    }
}



        
      }
