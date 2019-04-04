import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solver {
    // a card can have 3 numbers
    // 3 colours
    // 3 shadings
    // 3 numbers
    public ArrayList<Card> createDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        Integer id = 0;
        for(int num=1; num<=3; num++){
            for(int sym=1; sym<=3; sym++){
                for(int col=1; col<=3; col++){
                    for(int shad=1; shad<=3; shad++){
                        Card newCard = new Card(id,num,sym,col,shad);
                        //System.out.println(newCard.toString());
                        deck.add(newCard);
                        id++;
                    }
                }
            }
        }
        System.out.println(deck.size());
        return deck;
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
//doubles

        //num and sym
        if(allDifferent(c1.shad,c2.shad,c3.shad)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allSame(c1.num,c2.num,c3.num)){
                    if(allSame(c1.sym,c2.sym,c3.sym)){
                        System.out.println("num and sym same");
                        return true;
                    }
                }
            }
        }
        //num and col
        if(allDifferent(c1.shad,c2.shad,c3.shad)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allSame(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.sym,c2.sym,c3.sym)){
                        System.out.println("num and col same");
                        return true;
                    }
                }
            }
        }
        //num and shad
        if(allSame(c1.shad,c2.shad,c3.shad)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allSame(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.sym,c2.sym,c3.sym)){
                        System.out.println("num and shad same");
                        return true;
                    }
                }
            }
        }
        //sym and col
        if(allDifferent(c1.shad,c2.shad,c3.shad)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allSame(c1.sym,c2.sym,c3.sym)){
                        System.out.println("sym and col same");
                        return true;
                    }
                }
            }
        }
        //sym and shad
        if(allSame(c1.shad,c2.shad,c3.shad)){
            if(allDifferent(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allSame(c1.sym,c2.sym,c3.sym)){
                        System.out.println("sym and shade same");
                        return true;
                    }
                }
            }
        }
        //col and shad
        if(allSame(c1.shad,c2.shad,c3.shad)){
            if(allSame(c1.col,c2.col,c3.col)){
                if(allDifferent(c1.num,c2.num,c3.num)){
                    if(allDifferent(c1.sym,c2.sym,c3.sym)){
                        System.out.println("col and shade same");
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
    public ArrayList<Card> cloneSet(ArrayList<Card> set){
        ArrayList<Card> newSet = new ArrayList<>();
        newSet.add(set.get(0));
        newSet.add(set.get(1));
        newSet.add(set.get(2));

        return newSet;
    }
    public ArrayList<ArrayList<Card>> getSets(ArrayList<Card> solveSet){
       ArrayList<ArrayList<Card>> potSets = new ArrayList<>();
       ArrayList<Card> curSet = new ArrayList<>();
       for (int x=0; x<solveSet.size();x++){
           for (int y=x+1; y<solveSet.size();y++){
               for(int z=y+1; z<solveSet.size(); z++){
                   curSet.add(solveSet.get(x));
                   curSet.add(solveSet.get(y));
                   curSet.add(solveSet.get(z));
                   //Collections.sort(curSet);
                   if(curSet.size()==3) {
                       if (isSet(curSet)) {
                           potSets.add(cloneSet(curSet));
                           System.out.println(curSet.size());

                       }
                   }
                   curSet.clear();
               }
           }

       }

        System.out.println("TESTING " + potSets.get(0).size());
       return potSets;
    }
}
