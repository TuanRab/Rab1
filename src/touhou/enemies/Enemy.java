package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private FrameCounter coolDownBullet;
    private boolean bulletLock;
    private BoxCollider boxCollider;

    public Enemy() {
        super();
        this.bulletLock = false;
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        this.coolDownBullet = new FrameCounter(40);
    }

    // Controller
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
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
        //System.out.println(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
