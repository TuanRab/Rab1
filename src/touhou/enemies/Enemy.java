package touhou.enemies;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.pools.GameObjectPool;
import touhou.players.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject {
    private float SPEED = 2;
    private FrameCounter coolDownBullet;
    private boolean bulletLock;
    private BoxCollider boxCollider;
    private Constraints constraints;
    private EnemyAnimator animator;

    public Enemy() {
        super();

        ArrayList<BufferedImage> images = new ArrayList<>();

        this.bulletLock = false;
//        boxCollider = new BoxCollider(20, 20);
//        this.children.add(boxCollider);

        this.animator = new EnemyAnimator();
        this.renderer = animator;
        //this.total = 0;

        this.coolDownBullet = new FrameCounter(5);
    }

    // Controller
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        enemyShoot();
        hitPlayer();
        animator.bossEnemy(this);
    }

    private void hitPlayer() {
        Player player = Physics.colliderWithPlayer(boxCollider);
        if(player != null){
            player.setActive(false);
            this.isActive = false;
        }
    }

    private void shoot(float dx, float dy, float X, float Y) {
        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        if(enemyBullet == null) {
            enemyBullet = new EnemyBullet(X, Y);
            enemyBullet.getPosition().set(this.position.add(dx, dy));
            enemyBullet.getX();
            enemyBullet.getY();
            GameObject.add(enemyBullet);
        }

    }

    private void typeShoot1() {
            shoot(0, 20, 3, 5);
            shoot(0, 20, 2, 5);
            shoot(0, 20, 1, 5);
            shoot(0, 20, 0, 5);
            shoot(0, 20, -1, 5);
            shoot(0, 20, -2, 5);
            shoot(0, 20, -3, 5);

            shoot(-20, -30, -5, 3);
            shoot(-20, -15, -5, 3);
            shoot(-20, 0, -5, 3);
            shoot(-20, 15, -5, 3);
            shoot(-20, 30, -5, 3);

            shoot(20, -30, 5, 3);
            shoot(20, -15, 5, 3);
            shoot(20, 0, 5, 3);
            shoot(20, 15, 5, 3);
            shoot(20, 30, 5, 3);

            shoot(0, -20, 3, -5);
            shoot(0, -20, 2, -5);
            shoot(0, -20, 1, -5);
            shoot(0, -20, 0, -5);
            shoot(0, -20, -1, -5);
            shoot(0, -20, -2, -5);
            shoot(0, -20, -3, -5);

    }

    private void enemyShoot() {
        // TODO: create enemy bullet and shoot
        if (!bulletLock) {
            if(position.y < 100 || position.y > 300)
            typeShoot1();
            bulletLock = true;
        }

    unlockbullet();

    }

    private void unlockbullet() {
        if (bulletLock && coolDownBullet.run()) {
                bulletLock = false;
                coolDownBullet.reset();
            }
    }

    private void fly() {
        if(position.y > 0) {
            position.addUp(0, SPEED);
        }
        if (position.y < 100 || position.y > 500){
            position.addUp(0, -SPEED);
        }


    }
//    @Override
//    public BoxCollider getBoxCollider() {
//        return this.boxCollider;
//

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public Vector2D getPosition(){
        return position;
    }

    public void getHit(int damage) {
        // todo: decteease HP;
        this.setActive(false);
        EnemyExplosion explosion = GameObjectPool.recycle(EnemyExplosion.class);
        explosion.getPosition().set(this.position);
        //explosion.getScreenPosition().set(this.position);
    }

}
