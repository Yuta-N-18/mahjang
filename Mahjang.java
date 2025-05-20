import java.util.Scanner;

public class Mahjang {
    public static void main(String[] args) {
        // ここからテスト用のコード
        // getDisp2();
        // ここまでテスト用のコード
        // Yakuのインスタンスを作成する例
    }

    /*
     * private static void getDisp2() {
     * Scanner scanner = new Scanner(System.in);
     * Hai[] haiArray = new Hai[14];// Hai型の配列を作成する
     * 
     * for (int i = 0; i < 14; i++) {// 手牌の入力
     * System.out.println("put the " + (i + 1) + "th tile:");
     * 
     * System.out.println("Enter the type of the tile (1 for Number, 2 for Honour):"
     * ); // タイルの種類を選択
     * int type = scanner.nextInt();
     * 
     * if (type == 1) {// 数牌と字牌で聞く内容を分岐
     * System.out.println("Enter the number of the tile (1-9):");
     * int number = scanner.nextInt();// 数
     * 
     * System.out.
     * println("Enter the suit of the tile (1 for Man, 2 for Pin, 3 for Sou):");
     * int suitInput = scanner.nextInt();
     * String suit;// スート
     * 
     * switch (suitInput) {
     * case 1:
     * suit = "Man";
     * break;
     * case 2:
     * suit = "Pin";
     * break;
     * case 3:
     * suit = "Sou";
     * break;
     * default:
     * System.out.
     * println("Invalid suit entered. Please re-enter the information for this tile."
     * );
     * i--; // Retry the current tile
     * continue;
     * }
     * 
     * boolean isRed;// 赤ドラかどうか
     * 
     * if (number == 5) {
     * System.out.println("Is the tile red? (1 for true, 0 for false):");
     * int isRedInput = scanner.nextInt();
     * isRed = (isRedInput == 1);
     * } else {
     * isRed = false; // Default to false for non-red tiles
     * }
     * 
     * haiArray[i] = new NumberHai((i + 1), false, number, suit, isRed);//
     * Hai型の配列にNumberHai型のインスタンスを格納
     * } else if (type == 2) {
     * System.out.println("Enter the type of the honour tile:");
     * System.out.println("1: East");
     * System.out.println("2: South");
     * System.out.println("3: West");
     * System.out.println("4: North");
     * System.out.println("5: White");
     * System.out.println("6: Green");
     * System.out.println("7: Red");
     * int honourInput = scanner.nextInt();
     * String honourType;// 字牌の種類
     * 
     * switch (honourInput) {
     * case 1:
     * honourType = "East";
     * break;
     * case 2:
     * honourType = "South";
     * break;
     * case 3:
     * honourType = "West";
     * break;
     * case 4:
     * honourType = "North";
     * break;
     * case 5:
     * honourType = "White";
     * break;
     * case 6:
     * honourType = "Green";
     * break;
     * case 7:
     * honourType = "Red";
     * break;
     * default:
     * System.out
     * .println("Invalid honour type entered. Please re-enter the information for this tile."
     * );
     * i--; // Retry the current tile
     * continue;
     * }
     * 
     * haiArray[i] = new HonourHai((i + 1), false, honourType);//
     * Hai型の配列にHonourHai型のインスタンスを格納
     * } else {
     * System.out.
     * println("Invalid tile type entered. Please re-enter the information for this tile."
     * );
     * i--; // Retry the current tile
     * }
     * }
     * 
     * Hand h = new Hand(haiArray);
     * Hai[] disp = h.getHandSort();
     * 
     * System.out.println("All tiles have been entered:");
     * 
     * displayHais(disp);
     * 
     * Hai[][] disp2 = h.splitHand();// 手牌を近接グループに分けるメソッドを呼び出す
     * for (Hai[] haigrp : disp2) {// 近接グループの中身を表示
     * System.out.println("Group:");
     * displayHais(haigrp);
     * }
     * 
     * scanner.close();
     * }
     */

}
