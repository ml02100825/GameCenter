import java.util.*;
/**
 * Draw
 */
public interface Draw {
    // 抽象メソッド drawの定義
    // ドロー
    abstract   public int draw(List<Integer> tranp, List<Integer> deletecard);

    // 抽象メソッド　recoverytranpの定義
    // トランプの枚数を初期化
    abstract public void recoverytranp(List<Integer> tranp, List<Integer> deletecard); 
    }


