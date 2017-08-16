package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

//import static touhou.players.Player.heartPlayer;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;

    public EnemyBullet() {
        super();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"));
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(0, 10);
        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.colliderWithPlayer(this.boxCollider);
        if(player != null){
                player.setActive(false);
                this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

}
