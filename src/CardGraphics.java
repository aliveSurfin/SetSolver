import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
 */
class CardGraphics extends JPanel {
    GeneralPath squiggle;
    GeneralPath diamond;
    GeneralPath ellipse;
    Stroke stroke;
    Card card;
    Dimension dimension;
    // Override paintComponent to perform your own painting
    private void createPaths(){
        int width = dimension.width;
        int height = dimension.height;
        int centX = width/2;
        int centY = height/2;
        squiggle = new GeneralPath();
        squiggle.moveTo(width/6,height/2);
        squiggle.curveTo(centX,centY-(height/5),centX+(width/20),centY+(height/10),(width/6)*5,centY-(height/20));
        squiggle.quadTo(width,centY,(width/6)*5,centY+(height/10));
        //squiggle.moveTo(width/6,centY+(height/5));
        squiggle.curveTo(centX+(width/20),centY+(height/5),centX,centY+(height/20),(width/6),centY+(height/5));
        squiggle.moveTo(width/6,height/2);
        int diff = (centY+(height/5))-centY;
        squiggle.quadTo(0,centY+diff,width/6,centY+(height/5));
        //squiggle.closePath(); // do not close

        diamond = new GeneralPath();
        diamond.moveTo(centX-(width/5),centY);
        diamond.lineTo(centX,centY+(height/10));
        diamond.lineTo(centX+(width/5),centY);
        diamond.lineTo(centX,centY-(height/10));
        diamond.closePath();


        ellipse = new GeneralPath();
        ellipse.moveTo(centX-(width/6),centY-(height/10));
        ellipse.lineTo(centX+(width/6),centY-(height/10));
        ellipse.append(new Arc2D.Double(),true);
        //ellipse.quadTo(centX+(width/3),centY,centX+(width/6),centY+(height/10));



    }
    public CardGraphics(Card card,Dimension dimension){
        this.card = card;
        stroke = new BasicStroke(4.0f);
        setPreferredSize(dimension);
        this.dimension = dimension;
        createPaths();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);     // paint parent's background
        Color color;
        Graphics2D g2d = (Graphics2D) g.create();
        //colour
        switch (card.col){
            case 1:color = Color.RED; // red
                break;
            case 2:color = Color.GREEN; // green
                break;
            default: color = Color.BLUE; // blue
            break;
        }
        System.out.println(color.toString());
        //shading
        boolean outline = false;
        switch (card.shad){
            case 1: g2d.setColor(color); // full shade
                break;
            case 2: GradientPaint grad = new GradientPaint(10,25,color,12,25,Color.WHITE,true);
                g2d.setPaint(grad);
                System.out.println("SHADED");
            break;
            case 3: //g2d.setStroke(Stroke);
            break;
        }
        //Symbol
        GeneralPath symbol;
        switch (card.sym){
            case 1: symbol = diamond;
            break;
        }
        //NUMBER & SYMBOL
        switch (card.num){
            case 1:
        }
        g2d.setStroke(stroke);


       // g2d.scale(.9,.9);
       g2d.draw(ellipse);
    }
}