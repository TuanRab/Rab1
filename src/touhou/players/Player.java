package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.Items.Item;
import touhou.inputs.InputManager;
import touhou.players.spheres.PlayerSphere;
import touhou.players.spheres.SphereBullet;

import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody {
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private BoxCollider boxCollider;
    //public static final int heartPlayer = 15;

    public Player() {
        super();
        boxCollider = new BoxCollider(5, 5);
        this.children.add(boxCollider);
        //this.heartPlayer = 15;
        this.spellLock = false;
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/players/straight/0.png"),
                SpriteUtils.loadImage("assets/images/players/straight/2.png"),
                SpriteUtils.loadImage("assets/images/players/straight/4.png"),
                SpriteUtils.loadImage("assets/images/players/straight/6.png")
        );
        this.coolDownCounter = new FrameCounter(3);
        addSphere();
    }

    private void addSphere() {
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-20,0);

        PlayerSphere rightSphere = new PlayerSphere();
        rightSphere.getPosition().set(20,0);
        rightSphere.setReverse(true);

        this.children.add(leftSphere);
        this.children.add(rightSphere);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (inputManager.upPressed) {
            position.addUp(0, -SPEED);
            this.renderer = new Animation(
                    SpriteUtils.loadImage("assets/images/players/straight/0.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/1.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/3.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/5.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/6.png")
            );
        }
        if (inputManager.downPressed) {
            position.addUp(0, SPEED);
            this.renderer = new Animation(
                    SpriteUtils.loadImage("assets/images/players/straight/0.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/2.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/4.png"),
                    SpriteUtils.loadImage("assets/images/players/straight/6.png")
            );
        }
        if (inputManager.leftPressed) {
            position.addUp(-SPEED, 0);
            this.renderer = new Animation(
                    SpriteUtils.loadImage("assets/images/players/left/0.png"),
                    SpriteUtils.loadImage("assets/images/players/left/1.png"),
                    SpriteUtils.loadImage("assets/images/players/left/3.png"),
                    SpriteUtils.loadImage("assets/images/players/left/5.png")
            );
        }
        if (inputManager.rightPressed) {
            position.addUp(SPEED, 0);
            this.renderer = new Animation(
                    SpriteUtils.loadImage("assets/images/players/right/0.png"),
                    SpriteUtils.loadImage("assets/images/players/right/1.png"),
                    SpriteUtils.loadImage("assets/images/players/right/3.png"),
                    SpriteUtils.loadImage("assets/images/players/right/5.png")
                    );
        }

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell1();

    }
    
    private void castSpell1() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell1 = GameObjectPool.recycle(PlayerSpell.class);
            newSpell1.getPosition().set(this.position.add(0, -30));

            SphereBullet leftSphere = GameObjectPool.recycle(SphereBullet.class);
            leftSphere.getPosition().set(this.position.add(-20, 0));

            SphereBullet rightSphere = GameObjectPool.recycle(SphereBullet.class);
            rightSphere.getPosition().set(this.position.add(20, 0));

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

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
