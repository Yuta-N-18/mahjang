public class Yaku {// 役クラスの定義（未完成）
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