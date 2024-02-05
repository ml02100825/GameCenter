package GameCenter;
import java.util.*;
// 入力された数字が条件と違ったら例外を起こすクラス
public class NumericLimit extends InputMismatchException{
    NumericLimit(int n){
        super();
    }
}
