public class HonourHai extends Hai {// 字牌クラスの定義
    private int type;// 字牌の種類
    /*
     * 1: 東、2: 南、3: 西、4: 北、5: 白、6: 發、7: 中
     */

    HonourHai(int id, boolean isDora, int type) {
        super(id, isDora);
        this.type = type;
        super.setWeight(this.setWeight());
    }

    int getType() {// 種類の取得
        return this.type;
    }

    int setWeight() {// 重みをセットするメソッド
        int weight = 1000;
        weight += getType() * 100;
        if (this.getDora()) {
            weight += 1;
        }
        return weight;
    }

    boolean isEqual(Hai hai) {// 同じ牌かどうかを判定するメソッド
        if (hai instanceof HonourHai) {// 字牌の場合
            HonourHai honourHai = (HonourHai) hai;// キャスト用のインスタンスhonourHaiを作成
            return this.type == honourHai.getType();
        }
        return false;
    }
}
