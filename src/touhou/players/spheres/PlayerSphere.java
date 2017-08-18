package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.inputs.InputManager;

import java.awt.*;

public class PlayerSphere extends  GameObject{
    private Animation animation;
    private InputManager inputManager;
    private FrameCounter coolDownCounter;
    private boolean spherelock;

    public PlayerSphere() {
        super();
        this.animation = new Animation(
                7,
                false,
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png"));
        this.renderer = animation;
        this.spherelock = false;
        this.coolDownCounter = new FrameCounter(3);

    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);

    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }
}
