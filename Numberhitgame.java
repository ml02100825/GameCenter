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
        boolean roop = true;
        while (true){
            int randomnum = rand.nextInt(0, 101); // 正解の数字
            System.out.println(randomnum);
        if (roop == false){
            System.out.println("まだ続けますか？ y / n ：");
            String windecision = stdIn.next();
            if (windecision.equals("y")){
                roop = true;
            }
            else{
                System.out.println("あなたは" + winstreek + "回連続で正解しました！");
                break;
            }

        }

        System.out.println("0~100までの数字が格納されました");
        System.out.println("4回ミスをする前に数字を当てましょう！");
        while (cnt < 4){
            System.out.print("0~100までの数字を入力してください：");
            int num = stdIn.nextInt();
            if (num == randomnum){
                cnt += 1;
                System.out.println("正解です！");
                System.out.println("あなたは" + cnt + "回目で正解しました！");
                roop = false;
                winstreek += 1;
                cnt = 0;
                break;
            }
            else if( num > randomnum &&cnt != 3){
                System.out.println("正解の数字は" + num + "よりも小さいです！");
            }
            else if( num < randomnum && cnt != 3){
                System.out.println("正解の数字は" + num + "よりも大きいです！");
            }
            cnt += 1;
        }
        if (cnt == 4){
            if(winstreek != 0 && winstreek != 1){
                System.out.println("あなたは" + winstreek + "回連続で正解していました");
            }
            winstreek = 0;

            System.out.println("あなたは数あてゲームに失敗しました。");
            System.out.print("もう一度挑戦しますか？ y/n");     //yかn以外の入力を受け付けない例外作りたい
            String defeatdecision = stdIn.next();
            if (defeatdecision.equals("n")){
                break;
            }
            else if(defeatdecision.equals("y")){
                cnt = 0;
            }

        }


        }
    }



        
      }
