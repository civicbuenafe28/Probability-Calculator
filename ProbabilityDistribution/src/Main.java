import Panels.*;

public class Main {

    public void startMain() {

        Window window = new Window();
        WelcomePanel welcomePanel = new WelcomePanel();
        MainPanel mainPanel = new MainPanel();

        window.getWindow().add(welcomePanel.getPanel());
        window.getWindow().setVisible(true);

        welcomePanel.getStartButton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                window.getWindow().remove(welcomePanel.getPanel());
                window.getWindow().add(mainPanel.getPanel());
                window.getWindow().revalidate();
                window.getWindow().repaint();
            }
        });

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startMain();
    }
}