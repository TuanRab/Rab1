package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject {
    private static final float SPEED = 3;
    private FrameCounter coolDownBullet;
    private boolean bulletLock;

    public Enemy() {
        super();
        this.bulletLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        this.coolDownBullet = new FrameCounter(40);
    }

    // Controller
    public void run() {
        super.run();
        fly();
        shoot();
    }

    private void shoot() {
        // TODO: create enemy bullet and shoot
        if(!bulletLock) {
            EnemyBullet enemyBullet = new EnemyBullet();
            enemyBullet.getPosition().set(this.position.add( 0,20));
            GameObject.add(enemyBullet);

            bulletLock = true;
            coolDownBullet.reset();
        }
        unlockbullet();

    }

    private void unlockbullet() {
        if (bulletLock) {
            if (coolDownBullet.run()) {
                bulletLock = false;
            }
        }
    }

    private void fly() {
        position.addUp(0, SPEED);
    }
}
