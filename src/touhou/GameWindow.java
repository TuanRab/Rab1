package touhou;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.enemies.Enemy;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.KeyEvent.*;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private BufferedImage background;

    Player player = new Player();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();

    ArrayList<Enemy> enemies = new ArrayList<>();
    FrameCounter enemySpawnCounter = new FrameCounter(70);

    InputManager inputManager = new InputManager();

    public GameWindow() {
        pack();
        background = SpriteUtils.loadImage("assets/images/background/0.png");

        player.setInputManager(this.inputManager);
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.playerSpells = this.playerSpells;

//        enemies.add(new Enemy());
        Enemy enemy = new Enemy();
        enemy.getPosition().set(40, 40);

        enemies.add(enemy);

        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        player.run();

        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.run();
        }

        for (Enemy enemy : enemies) {
            enemy.run();
        }

        if (enemySpawnCounter.run()) {
            enemySpawnCounter.reset();
            Enemy enemy = new Enemy();

            Random random = new Random();

            int x = random.nextInt(384);

            enemy.getPosition().set(x, 40);
            enemies.add(enemy);
        }
    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        backbufferGraphics.drawImage(background, 0, 0, null);
        player.render(backbufferGraphics);

        for (PlayerSpell playerSpell: playerSpells) {
            playerSpell.render(backbufferGraphics);
        }

        for(Enemy enemy : enemies) {
            enemy.render(backbufferGraphics);
        }

        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }
}
