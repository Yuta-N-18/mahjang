public class Mentsu {
    private int type;// メンツの種類 1:Shuntsu, 2:Koutsu, 3:Toitsu
    private int suit;// メンツのスート 1:Man, 2:Pin, 3:Sou, 4:Honour
    private boolean isOpen;// 面子が公開かどうか（明か暗か）
    private Hai[] haiList;// メンツに含まれる牌のリスト

    Mentsu(int type, boolean isOpen, Hai[] haiList) {// メンツのコンストラクタ
        this.type = type;
        this.isOpen = isOpen;
        this.haiList = haiList;
        if (haiList[0] instanceof HonourHai) {
            this.suit = 4;// スートを4に設定
        } else {
            NumberHai numberHai = (NumberHai) haiList[0];// キャスト用のインスタンスnumberHaiを作成
            this.suit = numberHai.suitInt();// スートを取得
        }
    }

    int getType() {// メンツの種類を取得するメソッド
        return this.type;
    }

    int getSuit() {// メンツのスートを取得するメソッド
        return this.suit;
    }

    boolean getOpen() {// メンツが公開かどうかを取得するメソッド
        return this.isOpen;
    }

    Hai[] getHaiList() {// メンツに含まれる牌のリストを取得するメソッド
        return this.haiList;
    }

    boolean existYaochu() {// この面子に公九牌が含まれているかを判定するメソッド
        for (Hai hai : haiList) {// 面子の牌を一つずつ確認
            if (hai instanceof NumberHai) {// 数牌の場合
                NumberHai numberHai = (NumberHai) hai;// キャスト用のインスタンスnumberHaiを作成
                if (numberHai.getNumber() == 1 || numberHai.getNumber() == 9) {// 1か9ならtrue
                    return true;
                }
            } else if (hai instanceof HonourHai) {// 字牌の場合
                HonourHai honourHai = (HonourHai) hai;// キャスト用のインスタンスhonourHaiを作成
                if (honourHai.getType().equals("East") || honourHai.getType().equals("South")
                        || honourHai.getType().equals("West") || honourHai.getType().equals("North")) {
                    return true;
                }
            }
        }
        return false;// 公九牌が含まれていない場合はfalseを返す
    }

    boolean existChunchan() {// この面子に中張牌が含まれているかを判定するメソッド
        // 基本的には、公九牌のみかどうかの判定に用いる（裏の条件）
        for (Hai hai : haiList) {// 面子の牌を一つずつ確認
            if (hai instanceof NumberHai) {// 数牌の場合
                NumberHai numberHai = (NumberHai) hai;// キャスト用のインスタンスnumberHaiを作成
                if (numberHai.getNumber() >= 2 && numberHai.getNumber() <= 8) {// 2-8ならtrue
                    return true;
                }
            }
        }
        return false;// 中張牌が含まれていない場合はfalseを返す
    }

    boolean existSpecificHai(Hai hai) {// この面子に特定の牌が含まれているかを判定するメソッド
        for (Hai mentsuHai : haiList) {// 面子の牌を一つずつ確認
            if (hai instanceof NumberHai && mentsuHai instanceof NumberHai) {
                NumberHai h1 = (NumberHai) hai;
                NumberHai h2 = (NumberHai) mentsuHai;
                if (h1.getNumber() == h2.getNumber() && h1.getSuit().equals(h2.getSuit())) {
                    return true;
                }
            } else if (hai instanceof HonourHai && mentsuHai instanceof HonourHai) {
                HonourHai h1 = (HonourHai) hai;
                HonourHai h2 = (HonourHai) mentsuHai;
                if (h1.getType().equals(h2.getType())) {
                    return true;
                }
            }
        }
        return false;// 特定の牌が含まれていない場合はfalseを返す
    }
}