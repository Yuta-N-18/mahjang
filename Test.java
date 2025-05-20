public class Test {// テスト用のクラス
    public static void main(String[] args) {

        Hai[] haiArray = new Hai[14];// Hai型の配列を作成する
        String[] haiStrings = { "1p", "2p", "3p", "5s", "6s", "7s", "White", "White",
                "7m", "8m", "9m", "5s", "5s",
                "5sr" };// 牌の文字列を格納する配列
        for (int i = 0; i < 14; i++) {// 手牌の入力
            haiArray[i] = createNewHai(i + 1, haiStrings[i]);// 牌のインスタンスを生成
        }
        displayHais(haiArray);// 牌のインスタンスを表示
    }

    public static Hai createNewHai(int id, String haiString) {// 文字列から牌インスタンスを生成するメソッド
        /*
         * id : 牌のID
         * haiString : 牌を表す文字列
         * 字牌の場合は"East"、"South"、"West"、"North"、"White"、"Green"、"Red"
         * 数牌は"m","p"、"s"のいずれかで始まり、次に数字が続く
         * さらに赤ドラの場合は"r"が続く
         */
        Hai hai = null;
        switch (haiString) {// 字牌の場合
            case "East":
                hai = new HonourHai(id, false, 1);
                break;
            case "South":
                hai = new HonourHai(id, false, 2);
                break;
            case "West":
                hai = new HonourHai(id, false, 3);
                break;
            case "North":
                hai = new HonourHai(id, false, 4);
                break;
            case "White":
                hai = new HonourHai(id, false, 5);
                break;
            case "Green":
                hai = new HonourHai(id, false, 6);
                break;
            case "Red":
                hai = new HonourHai(id, false, 7);
                break;
            default:// 字牌じゃない場合
                String suit = haiString.substring(1, 2);
                int number = Integer.parseInt(haiString.substring(0, 1));
                boolean red = false;
                if (haiString.length() > 2) {
                    if (haiString.charAt(2) == 'r') {
                        red = true;
                    }
                }
                switch (suit) {
                    case "m":
                        hai = new NumberHai(id, false, number, 1, red);
                        break;
                    case "p":
                        hai = new NumberHai(id, false, number, 2, red);
                        break;
                    case "s":
                        hai = new NumberHai(id, false, number, 3, red);
                        break;
                    default:
                        System.out.println("Invalid suit entered.");

                }
        }

        return hai;
    }

    public static void displayHais(Hai[] disp) {
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