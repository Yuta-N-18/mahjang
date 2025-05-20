public class NumberHai extends Hai {// 数牌クラスの定義
    private int number;// 数字
    private int suit;// スート 1:萬子、2:筒子、3:索子
    private boolean isRed;// 赤ドラかどうか

    NumberHai(int id, boolean isDora, int number, int suit, boolean isRed) {
        super(id, isDora);
        this.number = number;
        this.suit = suit;
        this.isRed = isRed;
        // weightをsetWeightで初期化
        super.setWeight(this.setWeight());
    }

    int getNumber() {// 数を取得
        return this.number;
    }

    int getSuit() {// スートを取得
        return this.suit;
    }

    boolean getRed() {// 赤ドラかどうかを取得
        return this.isRed;
    }

    int setWeight() {// 重みをセットするメソッド
        int weight = 0;
        weight += getSuit() * 100 + this.number * 10;
        if (this.isRed) {
            weight += 2;
        } else if (this.getDora()) {
            weight += 1;
        }
        return weight;
    }

    boolean isEqual(Hai hai) {// 同じ牌かどうかを判定するメソッド
        if (hai instanceof NumberHai) {// 数牌の場合
            NumberHai numberHai = (NumberHai) hai;// キャスト用のインスタンスnumberHaiを作成
            return this.number == numberHai.getNumber() && this.suit == numberHai.getSuit();
        }
        return false;
    }

}