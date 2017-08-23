package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private int damage = 1;

    public PlayerSpell() {
        super();
        boxCollider = new BoxCollider(20, 20);    // todo
        this.children.add(boxCollider);                        // todo
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/0.png"));
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        //hitEnemy();
        deactiveIfNeeded();

    }

    private void deactiveIfNeeded() {
        if(this.screenPosition.y < 0) {
            this.isActive = false;
        }
    }

//    private void hitEnemy() {
//        // Todo
//        Enemy enemy = Physics.colliderWith(this.boxCollider, Enemy.class);
//        if(enemy!= null ){
//            enemy.getHit(damage);
//            enemy.setActive(false);
//            this.isActive = false;
//        }
//    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

}
