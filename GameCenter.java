import java.util.*;
public class GameCenter {
    public static void main(String[] args) {
    //入力されたコマンドによって、起動するアプリを選択する
    Scanner stdIn = new Scanner(System.in);
    // アプリのインスタンスを管理する変数appを宣言する
    IFGames games;
    //ユーザーに起動するアプリを選択してもらう
    System.out.print("起動したいアプリを選択してください:");
    switch(stdIn.nextLine()){
      case "1":
        //メモアプリのインスタンスを生成する
        games = new Numberhitgame();
        //メモアプリを起動する
        games.startUp();
        break;
      case "2":
        //電卓アプリのインスタンスを生成する
        games = new BlackJack();
        //電卓アプリを起動する
        games.startUp();
        break;
      default:
      System.out.println("そんなアプリは存在しません。");
    }
    }
}
