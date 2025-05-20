public class NumberHai extends Hai {// 数牌クラスの定義
    private int number;// 数字
    private String suit;// スート
    private boolean isRed;// 赤ドラかどうか

    NumberHai(int id, boolean isDora, int number, String suit, boolean isRed) {
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

    String getSuit() {// スートを取得
        return this.suit;
    }

    boolean getRed() {// 赤ドラかどうかを取得
        return this.isRed;
    }

    int suitInt() {// スートを整数に変換するメソッド
        switch (this.suit) {
            case "Man":
                return 1;
            case "Pin":
                return 2;
            case "Sou":
                return 3;
            default:
                return 0; // 未知のタイプの場合は0を返す
        }
    }

    int setWeight() {// 重みをセットするメソッド
        int weight = 0;
        weight += suitInt() * 100 + this.number * 10;
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
            return this.number == numberHai.getNumber() && this.suit.equals(numberHai.getSuit());
        }
        return false;
    }

}