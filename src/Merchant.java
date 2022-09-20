public class Merchant implements Seller {
    public Merchant() {
    }

    public String sell(Goods goods) {
        String result = "";
        if (goods == Merchant.Goods.POTION) {
            result = "potion";
        }

        return result;
    }

    public static enum Goods {
        POTION;

        private Goods() {
        }
    }
}