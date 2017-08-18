package touhou;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Constraints;
import touhou.BackGrounds.BackGround;
import touhou.Items.ItemSpawner;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.PlayerSpawner;
import touhou.players.spheres.SphereSpawner;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    //private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    Player player = new Player();

    BackGround backGround = new BackGround();

    EnemySpawner enemySpawner = new EnemySpawner(); // TODO: Viec cua lop: sua thanh game object

    //SphereSpawner sphereSpawner = new SphereSpawner();

    //ItemSpawner itemSpawner = new ItemSpawner();

    InputManager inputManager = new InputManager();

    public GameWindow() {
        pack();
        addBackGround();
        addPlayer();
        setupGameLoop();
        setupWindow();
    }

    private void addBackGround() {
        backGround.getPosition().set(384/2, 0);

        GameObject.add(backGround);
    }

    private void addPlayer() {
        player.setInputManager(this.inputManager);
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.getPosition().set(384 / 2, 580);

        GameObject.add(player);
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by TuanRab");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        //this.windowGraphics = (Graphics2D) this.getGraphics();

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
        GameObject.runAll();
        enemySpawner.spawn();
        //sphereSpawner.spawner();
        //itemSpawner.spawn();
        //playerSpawner.spawn();
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backbufferImage, 0, 0, null);
    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);

        GameObject.renderAll(backbufferGraphics);

        repaint(); // ham lam cho window ve anh trong thoi gian phu hop
    }
}
