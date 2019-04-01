import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solver {
    // a card can have 3 numbers
    // 3 colours
    // 3 shadings
    // 3 numbers
    public Card[] createDeck(){
        Card[] returnArray = new Card[81];
        Integer id = 0;
        for(int num=0; num<3; num++){
            for(int sym=0; sym<3; sym++){
                for(int col=0; col<3; col++){
                    for(int shad=0; shad<3; shad++){
                        Card newCard = new Card(id,num,sym,col,shad);
                        id++;
                    }
                }
            }
        }
        System.out.println(returnArray.length);
        return returnArray;
    }
    private boolean isSet(ArrayList<Card> pSet){

        Card c1 = pSet.get(0);
        Card c2 = pSet.get(1);
        Card c3 = pSet.get(2);
        // all same of 1
        // colour
        if(allSame(c1.col,c2.col,c3.col)){
            if(allDifferent(c1.num,c2.num,c3.num)){
                if(allDifferent(c1.sym,c2.sym,c3.sym)){
                    if(allDifferent(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only colour same");
                        return true;
                    }
                }
            }
        }
        //number
        if(allSame(c1.num,c2.num,c3.num)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.sym,c2.sym,c3.sym)){
                    if(allDifferent(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only num same");
                        return true;
                    }
                }
            }
        }
        // symbol
        if(allSame(c1.sym,c2.sym,c3.sym)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only symbol same");
                        return true;
                    }
                }
            }
        }
        //shading
        if(allSame(c1.shad,c2.shad,c3.shad)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.sym,c2.sym,c3.sym)){
                        System.out.println("all shading same");
                        return true;
                    }
                }
            }
        }

        // ALL DIFFERENT
        if(allDifferent(c1.shad,c2.shad,c3.shad)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.sym,c2.sym,c3.sym)){
                        System.out.println("EVERYTHING DIFFERENT");
                        return true;
                    }
                }
            }
        }
       // ALL SAME BUT ONE

        // colour
        if(allDifferent(c1.col,c2.col,c3.col)){
            if(allSame(c1.num,c2.num,c3.num)){
                if(allSame(c1.sym,c2.sym,c3.sym)){
                    if(allSame(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only colour different");
                        return true;
                    }
                }
            }
        }
        //number
        if(allDifferent(c1.num,c2.num,c3.num)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allSame(c1.sym,c2.sym,c3.sym)){
                    if(allSame(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only colour different");
                        return true;
                    }
                }
            }
        }
        // symbol
        if(allDifferent(c1.sym,c2.sym,c3.sym)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allSame(c1.num,c2.num,c3.num)){
                    if(allSame(c1.shad,c2.shad,c3.shad)){
                        System.out.println("only symbol different");
                        return true;
                    }
                }
            }
        }
        //shading
        if(allDifferent(c1.shad,c2.shad,c3.shad)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allSame(c1.num,c2.num,c3.num)){
                    if(allSame(c1.sym,c2.sym,c3.sym)){
                        System.out.println("only shading different");
                        return true;
                    }
                }
            }
        }


return false;
    }
    private boolean allSame(int c1, int c2, int c3){
        if(c1==c2 && c2==c3){
            return true;
        }
        return false;
    }
    private boolean allDifferent(int c1,int c2, int c3){
        Set<Integer> testSet = new HashSet<>();
        testSet.add(c1);
        testSet.add(c2);
        testSet.add(c3);
        if(testSet.size()==3){
            return true;
        }
        return false;
    }
    public Card[] getSets(Card[] pack){
        ArrayList<ArrayList<Card>> pSets = new ArrayList<>();
        ArrayList<Card> curSet = new ArrayList<>();
        ArrayList<Card> actualSets = new ArrayList<>();
       // actualSets = null;
        for(int x=0; x<pack.length; x++){// for every card
            curSet.add(pack[x]);
            Collections.sort(curSet);
            for (int y=0; y<pack.length; y++){

                if(!curSet.contains(pack[y])){ // skip if already in set
                    curSet.add(pack[y]);
                    Collections.sort(curSet);
                }
                for (int z=0; z<pack.length; z++){
                    if(!curSet.contains(pack[z])){ // skip if already in set
                        curSet.add(pack[z]);
                        if(!pSets.contains(curSet)) {
                            isSet(curSet);
                        }
                        curSet.clear();
                    }
                }
            }
        }
        return pack;
    }
    public static void main(String[] args){
        Solver s = new Solver();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,1,2,3,1));
        cards.add(new Card(2,2,2,3,1));
        cards.add(new Card(3,3,2,3,1));
        System.out.println(s.isSet(cards));
    }
}
