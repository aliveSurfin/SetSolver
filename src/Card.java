public class Card implements Comparable<Card> {
    public Integer id;
    public int num;
    public int sym;
    public int col;
    public int shad;
    public int getId(){
        return this.id;
    }
    public Card(Integer id, int num, int sym, int col, int shad){
        this.id = id; // id of card in pack
        this.num = num; // num 1-3 representing number on card
        this.sym = sym; // 1 = diamond, 2 = ellipse , 3 = squiggle
        this.col = col; // 1 = red , 2 = green , 3 = blue
        this.shad = shad; // 1 = full , 2 = lined , 3 = empty
    }
    @Override
    public int compareTo(Card c){
        return id.compareTo(c.id);
    }
}
