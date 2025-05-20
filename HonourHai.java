public class HonourHai extends Hai {// 字牌クラスの定義
    private String type;// 字牌の種類

    HonourHai(int id, boolean isDora, String type) {
        super(id, isDora);
        this.type = type;
        super.setWeight(this.setWeight());
    }

    String getType() {// 種類の取得
        return this.type;
    }

    int typeInt() {// 種類を整数に変換するメソッド
        switch (this.type) {
            case "East":
                return 1;
            case "South":
                return 2;
            case "West":
                return 3;
            case "North":
                return 4;
            case "White":
                return 5;
            case "Green":
                return 6;
            case "Red":
                return 7;
            default:
                return 0; // 未知のタイプの場合は0を返す
        }
    }

    int setWeight() {// 重みをセットするメソッド
        int weight = 1000;
        weight += typeInt() * 100;
        if (this.getDora()) {
            weight += 1;
        }
        return weight;
    }

    boolean isEqual(Hai hai) {// 同じ牌かどうかを判定するメソッド
        if (hai instanceof HonourHai) {// 字牌の場合
            HonourHai honourHai = (HonourHai) hai;// キャスト用のインスタンスhonourHaiを作成
            return this.type.equals(honourHai.getType());
        }
        return false;
    }
}
