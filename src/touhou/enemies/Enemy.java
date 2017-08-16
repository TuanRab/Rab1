package touhou.enemies;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;
import touhou.players.Player;

import java.awt.*;
import java.util.Random;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private FrameCounter coolDownBullet;
    private boolean bulletLock;
    private BoxCollider boxCollider;
//    private InputManager inputManager;
//    private int random;
    private Constraints constraints;

    public Enemy() {
        super();
        this.bulletLock = false;
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        this.coolDownBullet = new FrameCounter(70);
    }

    // Controller
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        shoot();
        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.colliderWithPlayer(boxCollider);
        if(player != null){
            player.setActive(false);
            this.isActive = false;
        }
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

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
}
