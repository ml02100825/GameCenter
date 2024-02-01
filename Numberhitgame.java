import java.util.*;
public class Numberhitgame implements IFGames{
    Scanner stdIn = new Scanner(System.in);
    Random rand = new Random();
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

        int cnt = 0; // ミスをした回数
        int winstreek = 0; // 連続正解した数
        boolean roop = true;    // roopをtrueに設定
       // 無限ループ 
        while (true){
            int randomnum = rand.nextInt(0, 101); // 正解の数字
            System.out.println(randomnum);
            // roopがflaseなら
            if (roop == false){
                // まだゲームを続けるか提案する
                System.out.println("まだ続けますか？ y / n ：");
                // 文字を入力
                String windecision = stdIn.next();
                // ゲームを続ける場合
                if (windecision.equals("y")){
                    // roopをtrueに設定しなおす
                    roop = true;
                }
                // 続けない場合
                else{
                    // もし2回以上連続正解していた場合何回連続正解したか表示
                    if (winstreek > 1){
                        System.out.println("あなたは" + winstreek + "回連続で正解しました！");
                    }
                    // ループを終了
                    break;
                }

            }
            // メッセージを表示
            System.out.println("0~100までの数字が格納されました");
            System.out.println("許されるミスは4回までです");
            // 4回ミスをするまでループ
            while (cnt < 5){
                // 数値の入力を促す
                System.out.print("0~100までの数字を入力してください：");
                // 数値を入力
                int num = stdIn.nextInt();
                // もし正解していた場合
                if (num == randomnum){
                    cnt += 1;
                    System.out.println("正解です！");
                    //　何回目で正解したか表示
                    System.out.println("あなたは" + cnt + "回目で正解しました！");
                    // ループしないようにfalseに設定
                    roop = false;
                    // 連続正解数を1増やす
                    winstreek += 1;
                    // カウントを0に戻す
                    cnt = 0;
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
            if (cnt == 5){
                // 2問連続正解以上していた場合
                if(winstreek > 1){
                    // 連続正解数を表示
                    System.out.println("あなたは" + winstreek + "回連続で正解していました");
                }
                // 連続正解数を0に戻す
                winstreek = 0;
                System.out.println("あなたは数あてゲームに失敗しました。");
                // もう一度挑戦するか促す
                System.out.print("もう一度挑戦しますか？ y / n");     //yかn以外の入力を受け付けない例外作りたい
                // 入力してもらう
                String defeatdecision = stdIn.next();
                // もし続けない場合
                if (defeatdecision.equals("n")){
                    // ループ終了
                    break;
                }
                // 続ける場合
                else if(defeatdecision.equals("y")){
                    // カウントを0に戻しループを続ける
                    cnt = 0;
                }

        }


        }
    }



        
      }
