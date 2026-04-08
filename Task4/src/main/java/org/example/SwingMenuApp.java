package org.example;
import javax.swing.*;
import java.awt.*;

public class SwingMenuApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SwingMenuApp() {
        setTitle("Swing Application with Panels & Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel homePanel = createPanel("Welcome to the Home Panel!", Color.LIGHT_GRAY);
        JPanel settingsPanel = createPanel("Settings Panel - Adjust preferences here.", Color.CYAN);
        JPanel aboutPanel = createPanel("About Panel - Application info.", Color.ORANGE);

        mainPanel.add(homePanel, "Home");
        mainPanel.add(settingsPanel, "Settings");
        mainPanel.add(aboutPanel, "About");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("View");
        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem settingsItem = new JMenuItem("Settings");
        JMenuItem aboutItem = new JMenuItem("About");

        homeItem.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        settingsItem.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));
        aboutItem.addActionListener(e -> cardLayout.show(mainPanel, "About"));

        viewMenu.add(homeItem);
        viewMenu.add(settingsItem);
        viewMenu.add(aboutItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutAppItem = new JMenuItem("About App");
        aboutAppItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "This is a sample Swing application with menus and panels.",
                "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutAppItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createPanel(String text, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingMenuApp::new);
    }
}
