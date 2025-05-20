import java.util.Arrays;
import java.util.Scanner;

public class Mahjang {
    public static void main(String[] args) {
        // ここからテスト用のコード
        // Scannerを使ってユーザーからの入力を受け取る
        getDisp2();
        // ここまでテスト用のコード
        // Yakuのインスタンスを作成する例
    }

    private static void getDisp2() {
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

        displayHais(disp);

        Hai[][] disp2 = h.splitHand();// 手牌を近接グループに分けるメソッドを呼び出す
        for (Hai[] haigrp : disp2) {// 近接グループの中身を表示
            System.out.println("Group:");
            displayHais(haigrp);
        }

        scanner.close();
    }

    private static void displayHais(Hai[] disp) {
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
    }
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