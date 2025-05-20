import java.util.Arrays;

public class Hand {// 手牌をソートするところから役の判別の手前まで行うクラス
    Hai[] handBase;// 手牌もともとの配列
    Hai[] handSort;// ソートした手牌を保存する配列

    Hand(Hai[] h) {// 引数に手牌の配列を取得
        this.handBase = h;
        this.handSort = h;
        Arrays.sort(handSort);
    }

    Hai[] getHandBase() {// 手牌のもともとの配列を返すメソッド
        return this.handBase;
    }

    Hai[] getHandSort() {// ソートした手牌を返すメソッド
        return this.handSort;
    }

    Hai[][] splitHand() {// 手牌を近接グループに分けるメソッド
        int temp = 0;// 重さ保存用変数
        int groupCount = 0;// 近接グループの数
        int[] groupSplitMark = new int[14];// グループ分けの印をつける配列

        for (int i = 0; i < handSort.length; i++) {// 手牌の数だけループ
            Hai hai = handSort[i];// 手牌を取得
            if (hai.getWeight() - temp > 15) {// 15超過の差なら違うグループ
                groupCount++;// グループ数を増やす
                groupSplitMark[groupCount - 1] = i;// グループ先頭のIDを保存
            }
            temp = hai.getWeight();// 次の比較用に重さを保存
        }

        Hai[][] handNeargroup = new Hai[groupCount][];// 近接グループを保存する配列

        for (int i = 0; i < groupCount; i++) {
            int start = groupSplitMark[i];
            int end = (i == groupCount - 1) ? handSort.length : groupSplitMark[i + 1];
            handNeargroup[i] = Arrays.copyOfRange(handSort, start, end);
        }

        return handNeargroup;// 近接グループを返す
    }

    boolean isStandard(Hai[][] group) {// 4面子1雀頭の標準形かを判定するメソッド
        int countNotJanto = 0;// 雀頭を含まないブロックの数
        int countJanto = 0;// 雀頭を含むブロックの数
        for (Hai[] g : group) {
            int len = g.length;
            if (len % 3 == 0) {// 3の倍数なら雀頭を含まないブロック
                countNotJanto++;
            } else if (len % 3 == 2) {// 3の倍数+2なら雀頭を含むブロック
                countJanto++;
            } else {
                return false;// それ以外は不正なブロック
            }
        }
        if (countNotJanto >= 1 && countJanto == 1) {

            return true;// 正常なブロック
        } else {// 雀頭を含むブロックが2つ以上ある場合
            return false;// 不正なブロック
        }
    }

    Hai[][][] splitBlocks(Hai[] group, boolean includeJanto) {// グループを面子の解釈配列に分けるメソッド
        int[] amount = new int[group.length];// 手牌の牌ごとの数を保存する配列
        Hai beforeHai = null;// 前の牌を保存する変数

        int i = 0;// ループ用変数
        for (Hai e : group) {// 各牌の数を数える
            if (i == 0 || !(e.equals(beforeHai))) {// 最初の数か違う牌なら
                beforeHai = e;// 前の牌を保存
                amount[i++] = 1;// 新たに牌を増やす
            } else {// 同じ牌なら
                amount[i] = amount[i] + 1;// すでにある牌の数を増やす
            }
        }

        return null;// まだ実装できず
    }

    // Mentsu[][]

    /*
     * makeMentsu(Hai[][] groups) {// 標準形であることを前提に面子の解釈配列を返す
     * Mentsu[][] mentsuList = new Mentsu[5][];// 面子の数はグループ数-1
     * for (Hai[] g : groups) {// グループの数だけループ
     * int len = g.length;
     * if (len % 3 == 0) {// 3の倍数なら雀頭を含まないブロック
     * 
     * } else if (len % 3 == 2) {// 3の倍数+2なら雀頭を含むブロック
     * 
     * }
     * }
     * }
     */

}