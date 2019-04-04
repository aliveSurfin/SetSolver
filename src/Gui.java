import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * GUI CONTAINER CLASS
 */
public class Gui{
    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String theme1 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                String theme2 = "javax.swing.plaf.metal.MetalLookAndFeel";
                String theme3 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                String theme4 = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
                String theme5 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
                MainFrame frame = new MainFrame("Solve set");
                frame.setSize(1500,800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                try{
                    UIManager.setLookAndFeel(theme4);
                }catch(Exception E){
                    System.err.println(E.getMessage());
                }

                String url = "https://www.setgame.com/set/puzzle";
                if(Desktop.isDesktopSupported()){
                    Desktop d = Desktop.getDesktop();
                    try{
                        d.browse(new URI(url));
                    }catch (IOException | URISyntaxException e){
                        e.printStackTrace();
                    }
                }else{
                    Runtime r = Runtime.getRuntime();
                    try{
                        r.exec("xdg-open " + url);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}




