package GameCenter;
import java.util.InputMismatchException;
// 入力された文字が条件と違ったら例外を起こすクラス
public class YorN extends InputMismatchException{
    YorN(String yorn){
        super();
        }
}
