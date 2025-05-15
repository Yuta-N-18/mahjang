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
                        + ", Suit=" + numberHai.getType() + ", IsRed=" + numberHai.getRed() + ", Weight="
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
                            + ", Suit=" + numberHai.getType() + ", IsRed=" + numberHai.getRed() + ", Weight="
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
    private String type;// スート
    private boolean isRed;// 赤ドラかどうか

    NumberHai(int id, boolean isDora, int number, String type, boolean isRed) {
        super(id, isDora);
        this.number = number;
        this.type = type;
        this.isRed = isRed;
        // weightをsetWeightで初期化
        super.setWeight(this.setWeight());
    }

    int getNumber() {// 数を取得
        return this.number;
    }

    String getType() {// スートを取得
        return this.type;
    }

    boolean getRed() {// 赤ドラかどうかを取得
        return this.isRed;
    }

    int typeInt() {// スートを整数に変換するメソッド
        switch (this.type) {
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
        weight += typeInt() * 100 + this.number * 10;
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
    private int yakuId;
    private String yakuName;
    private Hai[] haiList;
    private Hai[][] haiGroupList;

    Yaku(Hai[] haiList, Hai[][] haiGroupList) {
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

}