package touhou.players;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.pools.GameObjectPool;
import touhou.inputs.InputManager;
import touhou.players.spheres.PlayerSphere;
import touhou.players.spheres.SphereBullet;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject {
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private BoxCollider boxCollider;

    private Vector2D velocity;
    private  PlayerAnimator animator;

    public Player() {
        super();
        boxCollider = new BoxCollider(5, 5);
        this.children.add(boxCollider);
        this.spellLock = false;

        this.animator = new PlayerAnimator();
        this.renderer = animator;

        this.coolDownCounter = new FrameCounter(3);
        this.velocity = new Vector2D();
        addSphere();
    }

    private void addSphere() {
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-25,0);

        PlayerSphere rightSphere = new PlayerSphere();
        rightSphere.getPosition().set(25,0);
        rightSphere.setReverse(true);

        this.children.add(leftSphere);
        this.children.add(rightSphere);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        velocity.set(0, 0);

        if (inputManager.upPressed) {
            velocity.y -= SPEED;

        }
        if (inputManager.downPressed) {
            velocity.y += SPEED;

        }
        if (inputManager.leftPressed) {
            velocity.x -= SPEED;
        }
        if (inputManager.rightPressed) {
            velocity.x += SPEED;

        }

        if (constraints != null) {
            constraints.make(position);
        }

        position.addUp(velocity);
        animator.update(this);

        castSpell();

    }

    public Vector2D getVelocity() {
        return velocity;
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell1 = GameObjectPool.recycle(PlayerSpell.class);
            newSpell1.getPosition().set(this.position.add(-10, -20));

            PlayerSpell newSpell2 = GameObjectPool.recycle(PlayerSpell.class);
            newSpell2.getPosition().set(this.position.add(10,-20));

            SphereBullet leftSphere = GameObjectPool.recycle(SphereBullet.class);
            leftSphere.getPosition().set(this.position.add(-25, 0));

            SphereBullet rightSphere = GameObjectPool.recycle(SphereBullet.class);
            rightSphere.getPosition().set(this.position.add(25, 0));

            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }



    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
//    @Override
//    public BoxCollider getBoxCollider() {
//        return boxCollider;
//    }
}
