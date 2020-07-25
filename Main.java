package cc.retzlaff.timon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Main {
    //      static Vector2 test = new Vector2(1, 1);
//      static Vector2 compareTo = new Vector2(1, 2);
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenSizeX = (int) screenSize.getWidth() - 100;
    public static int screenSizeY = (int) screenSize.getHeight() - 80;
    public static int windowSizeX = screenSizeX;
    public static int windowSizeY = screenSizeY;
    public static int windowY = (int) (screenSize.getHeight()/2) - windowSizeY/2;
    public static int windowX = (int) (screenSize.getWidth()/2) - windowSizeX/2;
    public static  int numberOfGuys = 100;
    public static Ai ai = new Ai(0, windowSizeX, 0, windowSizeY, numberOfGuys);
    public static float maxDistance = Float.POSITIVE_INFINITY;
    public static float playerSpeed = 2f;
    public static float botSpeed = 1f;
    public static final long startTime = System.nanoTime();
    public static Player player = new Player(new Vector2(Main.windowSizeX/2, Main.windowSizeY/2), playerSpeed);
    private static boolean gameOver = false;
    private static final String[] messages = new String[7];

    public static void setGameOver(boolean gameOver) {
        Main.gameOver = gameOver;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setup() {
        ai.start();
        player.pos = new Vector2(windowSizeX/2, windowSizeY/2);
        JFrame frame = new JFrame("Walk or Die");
        JPanel canvas = new MyCanvas();
        canvas.setSize(windowSizeX, windowSizeY);
        canvas.setDoubleBuffered(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(windowX, windowY, windowSizeX, windowSizeY);
        frame.setVisible(true);
        windowSizeX = canvas.getBounds().width;
        windowSizeY = canvas.getBounds().height;
        System.out.println(canvas.getBounds());
    }

    public static void reset() {
        Main.ai = new Ai(0, windowSizeX, 0, windowSizeY, numberOfGuys);
        Main.ai.start();
        Main.ai.guys.reset();
        Main.player.reset();
    }

    public static boolean update(Graphics g) {
        player.update(g);
//        return ai.update(g, maxDistance);
        return ai.update(g);
    }

    public static void main(String[] args) {
        messages[0] = "You move with WASD.";
        messages[1] = "You are the red point.";
        messages[2] = "The enemies are the black points.";
        messages[3] = "You have to dodge the enemies.";
        messages[4] = "You can pass through green points.";
        messages[5] = "How many enemies do you want?";
        messages[6] = "100 or above is difficult and they have to be at least 2.";
        Stringy.print(messages);
        System.out.print(">>> ");
        Scanner input = new Scanner(System.in);
        numberOfGuys = input.nextInt();
        Main.ai = new Ai(0, windowSizeX, 0, windowSizeY, numberOfGuys);
        System.out.println(Main.windowSizeX + " / " + Main.windowSizeY);

        setup();
//        List<Vector2> list = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            list.add(test.generateRandomVector2InRange(0,10,0, 10));
//        }
//        System.out.println("Before: " + list);
//        Collections.sort(list);
//        System.out.println("After: " + list);
//        System.out.println(list.get(0).posToString());

//        System.out.println(test.compareTo(compareTo));
    }
}

class MyCanvas extends JPanel {

    private static final Font f = new Font("Arial", Font.PLAIN, 100);

    public void paint(Graphics g) {
        if (Main.isGameOver()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.setGameOver(false);
            Main.reset();
        }
        g.clearRect(0,0,Main.windowSizeX, Main.windowSizeY);
        if (Main.update(g)) {
            Main.setGameOver(true);
            g.setFont(f);
            String text = "You won";
            int width = g.getFontMetrics().stringWidth(text);
            g.setColor(Color.blue);
            g.drawString(text, (Main.windowSizeX - width) / 2, (Main.windowSizeY) / 2);
            Stringy.print("You won");
        }

        this.repaint();

        if(Main.player.isDead()) {
            Main.setGameOver(true);
            g.setFont(f);
            String text = "You lost";
            int width = g.getFontMetrics().stringWidth(text);
            g.setColor(Color.red);
            g.drawString(text, (Main.windowSizeX - width) / 2, (Main.windowSizeY - 100) / 2);
            Stringy.print("You lost");
        }
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

