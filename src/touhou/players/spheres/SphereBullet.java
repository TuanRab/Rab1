package touhou.players.spheres;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class SphereBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private Animation animation;

    public SphereBullet() {
        super();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        animation = new Animation(7, false,
                SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png"));
        this.renderer = animation;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(0, -10);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
