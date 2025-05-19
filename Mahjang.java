import java.util.Arrays;
import java.util.Scanner;

public class Mahjang {
    public static void main(String[] args) {
        // ここからテスト用のコード
        // Scannerを使ってユーザーからの入力を受け取る
        Scanner scanner = new Scanner(System.in);
        Hai[] haiArray = new Hai[14];// Hai型の配列を作成する

        for (int i = 0; i < 14; i++) {// 手牌の入力
            System.out.println("put the " + (i + 1) + "th tile:");

            System.out.println("Enter the type of the tile (1 for Number, 2 for Honour):"); // タイルの種類を選択
            int type = scanner.nextInt();

            if (type == 1) {// 数牌と字牌で聞く内容を分岐
                System.out.println("Enter the number of the tile (1-9):");
                int number = scanner.nextInt();// 数

                System.out.println("Enter the suit of the tile (1 for Man, 2 for Pin, 3 for Sou):");
                int suitInput = scanner.nextInt();
                String suit;// スート

                switch (suitInput) {
                    case 1:
                        suit = "Man";
                        break;
                    case 2:
                        suit = "Pin";
                        break;
                    case 3:
                        suit = "Sou";
                        break;
                    default:
                        System.out.println("Invalid suit entered. Please re-enter the information for this tile.");
                        i--; // Retry the current tile
                        continue;
                }

                boolean isRed;// 赤ドラかどうか

                if (number == 5) {
                    System.out.println("Is the tile red? (1 for true, 0 for false):");
                    int isRedInput = scanner.nextInt();
                    isRed = (isRedInput == 1);
                } else {
                    isRed = false; // Default to false for non-red tiles
                }

                haiArray[i] = new NumberHai((i + 1), false, number, suit, isRed);// Hai型の配列にNumberHai型のインスタンスを格納
            } else if (type == 2) {
                System.out.println("Enter the type of the honour tile:");
                System.out.println("1: East");
                System.out.println("2: South");
                System.out.println("3: West");
                System.out.println("4: North");
                System.out.println("5: White");
                System.out.println("6: Green");
                System.out.println("7: Red");
                int honourInput = scanner.nextInt();
                String honourType;// 字牌の種類

                switch (honourInput) {
                    case 1:
                        honourType = "East";
                        break;
                    case 2:
                        honourType = "South";
                        break;
                    case 3:
                        honourType = "West";
                        break;
                    case 4:
                        honourType = "North";
                        break;
                    case 5:
                        honourType = "White";
                        break;
                    case 6:
                        honourType = "Green";
                        break;
                    case 7:
                        honourType = "Red";
                        break;
                    default:
                        System.out
                                .println("Invalid honour type entered. Please re-enter the information for this tile.");
                        i--; // Retry the current tile
                        continue;
                }

                haiArray[i] = new HonourHai((i + 1), false, honourType);// Hai型の配列にHonourHai型のインスタンスを格納
            } else {
                System.out.println("Invalid tile type entered. Please re-enter the information for this tile.");
                i--; // Retry the current tile
            }
        }

        Hand h = new Hand(haiArray);
        Hai[] disp = h.getHandSort();

        System.out.println("All tiles have been entered:");

        for (Hai hai : disp) {// haiArrayの中身を表示
            if (hai instanceof NumberHai) {// haiの型がNumberHaiの場合
                NumberHai numberHai = (NumberHai) hai;// キャスト用のインスタンスnumberHaiを作成
                // NumberHaiクラスのHaiの情報を表示
                System.out.println("NumberHai: ID=" + numberHai.getId() + ", Number=" + numberHai.getNumber()
                        + ", Suit=" + numberHai.getSuit() + ", IsRed=" + numberHai.getRed() + ", Weight="
                        + numberHai.getWeight());
            } else if (hai instanceof HonourHai) {// haiの型がHonourHaiの場合
                HonourHai honourHai = (HonourHai) hai;// キャスト用のインスタンスhonourHaiを作成
                // HonourHaiクラスのHaiの情報を表示
                System.out.println("HonourHai: ID=" + honourHai.getId() + ", Type=" + honourHai.getType() + ", Weight="
                        + honourHai.getWeight());
            }
        }

        Hai[][] disp2 = h.splitHand();// 手牌を近接グループに分けるメソッドを呼び出す
        for (Hai[] haigrp : disp2) {// 近接グループの中身を表示
            System.out.println("Group:");
            for (Hai hai : haigrp) {// haiArrayの中身を表示
                if (hai instanceof NumberHai) {// haiの型がNumberHaiの場合
                    NumberHai numberHai = (NumberHai) hai;// キャスト用のインスタンスnumberHaiを作成
                    // NumberHaiクラスのHaiの情報を表示
                    System.out.println("NumberHai: ID=" + numberHai.getId() + ", Number=" + numberHai.getNumber()
                            + ", Suit=" + numberHai.getSuit() + ", IsRed=" + numberHai.getRed() + ", Weight="
                            + numberHai.getWeight());
                } else if (hai instanceof HonourHai) {// haiの型がHonourHaiの場合
                    HonourHai honourHai = (HonourHai) hai;// キャスト用のインスタンスhonourHaiを作成
                    // HonourHaiクラスのHaiの情報を表示
                    System.out.println("HonourHai: ID=" + honourHai.getId() + ", Type=" + honourHai.getType()
                            + ", Weight=" + honourHai.getWeight());
                }
            }
        }

        scanner.close();
        // ここまでテスト用のコード
        // Yakuのインスタンスを作成する例
    }
}

class Hai implements Comparable<Hai> {// 牌全体のスーパークラスの定義
    private int id;// 牌の持つid（使うかどうかは未定）
    private boolean isDora;// その牌がドラかどうか
    private int weight; // 牌自体の重み

    Hai(int id, boolean isDora) {
        this.id = id;
        this.isDora = isDora;
        this.weight = -1;
    }

    void setIsDora(boolean b) {// ドラかどうかをセットするメソッド
        this.isDora = b;
    }

    boolean getDora() {// ドラかどうかを取得するメソッド}
        return this.isDora;
    }

    int getId() {// IDを取得するメソッド
        return this.id;
    }

    int getWeight() {// 重みを取得するメソッド
        return this.weight;
    }

    void setWeight(int weight) {// 重みをセットするメソッド
        this.weight = weight;
    }

    @Override
    public int compareTo(Hai other) {
        return Integer.compare(this.weight, other.weight); // weightで昇順
    }
}

class NumberHai extends Hai {// 数牌クラスの定義
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

}

class HonourHai extends Hai {// 字牌クラスの定義
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
}

class Yaku {// 役クラスの定義（未完成）
    private int yakuId;// 役のID（未使用）
    private String yakuName;// 役の名前
    private Hai[] haiList;// 役に含まれる牌のリスト
    private Hai[][] haiGroupList;// 役に含まれる牌のグループリスト

    Yaku(int yakuId, String yakuName, Hai[] haiList, Hai[][] haiGroupList) {
        this.haiList = haiList;
        this.haiGroupList = haiGroupList;
    }

    public int getYakuId() {
        return yakuId;
    }

    public String getYakuName() {
        return yakuName;
    }

    boolean excludeException() {
        // 役の例外を除外するメソッド（未実装）
        return false;
    }

}

class Hand {// 手牌をソートするところから役の判別の手前まで行うクラス
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

    boolean isSameHai(Hai hai1, Hai hai2) {
        if (hai1 instanceof NumberHai && hai2 instanceof NumberHai) {// 数牌の場合
            NumberHai numberHai1 = (NumberHai) hai1;// キャスト用のインスタンスnumberHaiを作成
            NumberHai numberHai2 = (NumberHai) hai2;// キャスト用のインスタンスnumberHaiを作成
            return numberHai1.getNumber() == numberHai2.getNumber()
                    && numberHai1.getSuit().equals(numberHai2.getSuit());
        } else if (hai1 instanceof HonourHai && hai2 instanceof HonourHai) {// 字牌の場合
            HonourHai honourHai1 = (HonourHai) hai1;// キャスト用のインスタンスhonourHaiを作成
            HonourHai honourHai2 = (HonourHai) hai2;// キャスト用のインスタンスhonourhaiを作成
            return honourHai1.getType().equals(honourHai2.getType());
        }
        return false;
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
        for (Hai e : group) {// 手牌の数だけループ
            if (i == 0 || !(isSameHai(e, beforeHai))) {// 最初の数か違う牌なら
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

class Mentsu {
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