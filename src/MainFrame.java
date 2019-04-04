import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GlyphVector;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame{
    Solver s = new Solver();
    ArrayList<CardGraphics> solveSet = new ArrayList<>();
    ArrayList<ArrayList<Card>> foundSets = new ArrayList<>();
    JPanel solvePanel;
    JPanel addCardsPanel;
    JPanel editCardPanel;
    JPanel solveSetPanel;
    JPanel foundSetsPanel;
    Dimension cardD;
    CardGraphics baseCard;

    //lists
    String[] nums = {"one","two","three"};
    String[] syms = {"diamond","ellipse","squiggle"};
    String[] cols = {"red","green","blue"};
    String[] shads = {"full","lined","empty"};

    // Spinners
    JSpinner spinnerNum;
    JSpinner spinnerSym;
    JSpinner spinnerCol;
    JSpinner spinnerShad;

    HashMap<String,Integer> codes = new HashMap<>();
    private void solvedSets(){
        foundSetsPanel.removeAll();
        foundSetsPanel.revalidate();
        foundSetsPanel.repaint();
        if(solveSet.size()<3){
            return;
        }
        foundSets = new ArrayList<ArrayList<Card>>();
        ArrayList<Card> temp = new ArrayList<>();
        for (CardGraphics c:solveSet
             ) {
            temp.add(c.card);
        }
        foundSets = s.getSets(temp);
        JPanel[] panelArray = new JPanel[foundSets.size()];
        int it =0;
        for (ArrayList<Card> cSet: foundSets
             ) {
            System.out.println("size " + cSet.size());
            panelArray[it] = new JPanel();
            panelArray[it].setBorder(new TitledBorder("set number " + (it+1)));
            panelArray[it].add(new CardGraphics(cSet.get(0),cardD));
            panelArray[it].add(new CardGraphics(cSet.get(1),cardD));
            panelArray[it].add(new CardGraphics(cSet.get(2),cardD));
            it++;
        }
        System.out.println("Found sets " + panelArray.length);
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx=1;
        con.gridx=0;
        con.gridy=0;
        for (JPanel jp:panelArray
             ) {
            System.out.println(jp);
            foundSetsPanel.add(jp,con);
            con.gridy++;
        }
        foundSetsPanel.revalidate();
        foundSetsPanel.repaint();

    }
    private void updateCurCards(){
        solveSetPanel.removeAll();
        solveSetPanel.revalidate();
        solveSetPanel.repaint();
        if(solveSet.size()<1){
            return;
        }

        for (CardGraphics c: solveSet
             ) {
            System.out.println("adding " + c.card.toString());
            solveSetPanel.add(c);
        }
        //solveSetPanel.removeAll();
        solveSetPanel.revalidate();
        solveSetPanel.repaint();


        solvePanel.revalidate();
        solvePanel.repaint();



    }
    private boolean removeLast(){
        if(solveSet.size()<1){
            return false;
        }
        else{
            solveSet.remove(solveSet.size()-1);
            return true;
        }
    }
    private boolean solveSetContains(CardGraphics cg){
        if (solveSet.size()==0){
            return false;
        }
        for (CardGraphics c:solveSet
             ) {
            if(c.card.isSame(cg.card)){
                return true;
            }
        }
        return false;
    }
    private void updateEditCards(){
        if(true){
         //   return;
        }
        editCardPanel.removeAll();
        //editCardPanel.remove(0);
         CardGraphics newCard = new CardGraphics(
                new Card(
                        1,
                        getHash(spinnerNum),
                        getHash(spinnerSym),
                        getHash(spinnerCol),
                        getHash(spinnerShad)
                ),
                cardD);
        baseCard = newCard;
        editCardPanel.add(newCard);
        editCardPanel.revalidate();
        editCardPanel.repaint();
    }
    private void addToCurrent(){
        solveSet.add(baseCard);
    }
    private int getHash(JSpinner spinner){
        return (int) (codes.get(spinner.getValue()));
    }
    private void setupHashMap (){
        codes.put("one",1);
        codes.put("two",2);
        codes.put("three",3);

        codes.put("diamond",1);
        codes.put("ellipse",2);
        codes.put("squiggle",3);

        codes.put("red",1);
        codes.put("green",2);
        codes.put("blue",3);

        codes.put("full",1);
        codes.put("lined",2);
        codes.put("empty",3);


    }
    public MainFrame(String title){
        super(title);
        setupHashMap();
        Dimension dimension = new Dimension();
        dimension.setSize(1000,500);
         cardD = new Dimension(dimension.width/10,dimension.height/10);

        setSize(dimension);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
         solvePanel = new JPanel(new GridBagLayout());
         solvePanel.setBorder(new TitledBorder("Current cards"));
         addCardsPanel = new JPanel();
         addCardsPanel.setBorder(new TitledBorder("ADD CARD"));
         //JPanel edtingCardMenu = new JPanel();
         //edtingCardMenu.setBorder(new TitledBorder("edit"));
         gbc.weightx =0.5;
         gbc.fill = GridBagConstraints.VERTICAL;
         gbc.weighty =1;
         gbc.gridx =1;

         //EDITING SPINNERS
        CyclingSpinnerListModel spinNum = new CyclingSpinnerListModel(nums);
        CyclingSpinnerListModel spinSym = new CyclingSpinnerListModel(syms);
        CyclingSpinnerListModel spinCol = new CyclingSpinnerListModel(cols);
        CyclingSpinnerListModel spinShad = new CyclingSpinnerListModel(shads);

        spinnerNum = new JSpinner(spinNum);
        spinnerNum.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateEditCards();
            }
        });

        spinnerSym = new JSpinner(spinSym);
        spinnerSym.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateEditCards();
            }
        });

        spinnerCol = new JSpinner(spinCol);
        spinnerCol.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateEditCards();
            }
        });

        spinnerShad = new JSpinner(spinShad);
        spinnerShad.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateEditCards();
            }
        });
        Font f = new Font("TimesRoman",Font.PLAIN,20);
        JFormattedTextField tf;
         tf= ((JSpinner.DefaultEditor) spinnerNum.getEditor()).getTextField();
         tf.setEditable(false);
         tf.setBackground(Color.white);
         tf.setFont(f);
        addCardsPanel.add(spinnerNum);
        tf= ((JSpinner.DefaultEditor) spinnerCol.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        tf.setFont(f);
        addCardsPanel.add(spinnerCol);
        tf= ((JSpinner.DefaultEditor) spinnerSym.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        tf.setFont(f);
        addCardsPanel.add(spinnerSym);
        tf= ((JSpinner.DefaultEditor) spinnerShad.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.white);
        tf.setFont(f);
        addCardsPanel.add(spinnerShad);
         //gbc.anchor = GridBagConstraints.WEST;
         gbc.fill = GridBagConstraints.BOTH;
         gbc.weighty=1;
         solvePanel.add(addCardsPanel,gbc);
        editCardPanel = new JPanel();
        editCardPanel.setBorder(new TitledBorder("EDITING CARD"));
        baseCard  = new CardGraphics(
                new Card(
                        1,
                        getHash(spinnerNum),
                        getHash(spinnerSym),
                        getHash(spinnerCol),
                        getHash(spinnerShad)
                ),
                cardD);
        editCardPanel.add(baseCard,gbc);
        JButton addButton = new JButton("Add to solve set");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(solveSetContains(baseCard)){
                    return;
                }


                solveSet.add(new CardGraphics(baseCard.card,cardD));
                updateCurCards();
                System.out.println(solveSet.size());
               // System.out.println(baseCard.card.toString());
            }
        });
        gbc.weighty =1;
        gbc.fill = GridBagConstraints.BOTH;
        addCardsPanel.add(editCardPanel,gbc);
        GridBagConstraints buttons = new GridBagConstraints();
        buttons.gridy = 2;

        addCardsPanel.add(addButton,buttons);
        JButton removeLast = new JButton("Remove Last added");
        removeLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(removeLast()){
                    updateCurCards();
                }
            }
        });
        addCardsPanel.add(removeLast,buttons);






        solveSetPanel = new JPanel();
        solveSetPanel.setBorder(new TitledBorder("Set"));
        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solvedSets();
            }
        });
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foundSets.clear();
                solveSet.clear();
                updateCurCards();
                solvedSets();
            }
        });
        gbc.weightx = 0.1;
        gbc.weightx = 0.1;
        gbc.weighty =0;
        solvePanel.add(reset,gbc);
        solvePanel.add(solve,gbc);
        gbc.weightx=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty =2;
        solvePanel.add(solveSetPanel,gbc);
        gbc.gridx = 1;
        gbc.gridy =1;
        gbc.weighty =1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.VERTICAL;



        add(solvePanel,gbc);
        foundSetsPanel = new JPanel(new GridBagLayout());
        foundSetsPanel.setBorder(new TitledBorder("Found sets"));

        gbc.gridx = 2;
        gbc.weighty =1;
        gbc.weightx = 2;
        //gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea test = new JTextArea();
        test.setFont(f);
        test.append("https://www.setgame.com/set/puzzle");
        test.append("\n");
        test.append("https://www.nytimes.com/puzzles/set");
        test.setEditable(false);
        foundSetsPanel.add(test);
        foundSetsPanel.setSize(cardD.width*5,0);
        gbc.weightx =1;
        add(foundSetsPanel,gbc);
        System.out.println(editCardPanel.getAccessibleContext().toString());

    }

}