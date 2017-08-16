package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.Items.Item;
import touhou.inputs.InputManager;

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
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        this.coolDownCounter = new FrameCounter(3);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell1();
        //castSpell2();
//        EatItem();
    }

    private void EatItem() {
        Item item = Physics.colliderWithItem(boxCollider);
        if(item != null){
            item.setActive(false);
            this.isActive = true;
        }

    }

    private void castSpell1() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell1 = new PlayerSpell();
            newSpell1.getPosition().set(this.position.add(0, -30));
            GameObject.add(newSpell1);

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
