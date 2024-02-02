import java.util.*;
public class GameCenter {
    public static void main(String[] args) {
    //入力されたコマンドによって、起動するアプリを選択する
    Scanner stdIn = new Scanner(System.in);
    // アプリのインスタンスを管理する変数appを宣言する
    IFGames games;
    // ループの継続を判断する変数roopを宣言する
    boolean roop = true;  
    // 無限ループ
    while (true){

      System.out.println("1：数あてゲーム");
      System.out.println("2；ブラックジャック");
      System.out.println("99：終了");
      // ユーザーに起動するアプリを選択してもらう
      System.out.print("起動したいアプリを選択してください:");
      switch(stdIn.nextLine()){
        case "1":
          //メモアプリのインスタンスを生成する
          games = new Numberhitgame();
          //メモアプリを起動する
          games.startUp();
          break;
        case "2":
          //ブラックジャックのインスタンスを生成する
          games = new BlackJack();
          //ブラックジャックを起動する
          games.startUp();
          break;
        case "99":
        // roopをfalseに変更
          roop = false;
          break;
        default:
          System.out.println("そんなアプリは存在しません。");
      }
      // roopがflaseなら
      if (roop == false){
        System.out.println("ゲームを終了します");
        // ループを終了
        break;
      }
    }
      }
}
