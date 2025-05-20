public abstract class Hai implements Comparable<Hai> {// 牌全体のスーパークラスの定義
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

    protected void setWeight(int weight) {// 重みをセットするメソッド
        this.weight = weight;
    }

    abstract boolean isEqual(Hai hai);// 同じ牌かどうかを判定するメソッド

    @Override
    public int compareTo(Hai other) {
        return Integer.compare(this.weight, other.weight); // weightで昇順
    }
}