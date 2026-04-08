
import org.example.SwingMenuApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

 class Tast {
     private SwingMenuApp app;

     @BeforeEach
     void setUp() throws Exception {
         SwingUtilities.invokeAndWait(()-> {
              app = new SwingMenuApp();
         });

     }
     @Test
     void testMaiPanelHasCardLayout() {
         JPanel mainPanel =(JPanel)app.getContentPane().getComponent(0);
         assertNotNull(mainPanel,"main Panel should exist");
         assertTrue(mainPanel.getLayout()instanceof CardLayout,"main Panel should be CardLayout");
     }
     @Test
     void testPanelSwitching() throws InterruptedException, InvocationTargetException {
         JMenuBar menuBar =app.getJMenuBar();
         JMenu ViewMenu = menuBar.getMenu(1);

         JMenuItem settingsItem = ViewMenu.getItem(1);
         SwingUtilities.invokeAndWait(()->{
             settingsItem.doClick();
         });
         JPanel mainPanel =(JPanel)app.getContentPane().getComponent(0);
         for (Component comp : mainPanel.getComponents()) {
             if (comp.isVisible()) {
                 JLabel label = (JLabel) ((JPanel)comp).getComponent(0);
                 assertTrue(label.getText().contains("Settings"), "The visible panel should be Settings panel");
             }
         }
     }
 }

