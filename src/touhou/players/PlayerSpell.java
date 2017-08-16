package touhou.players;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public PlayerSpell() {
        super();
        boxCollider = new BoxCollider(20, 20);    // todo
        this.children.add(boxCollider);                        // todo
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/0.png"));
        //this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/1.png"));
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.colliderWithEnemy(this.boxCollider);
        if(enemy!= null ){
            enemy.setActive(false);
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
