package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.players.Player;

//import static touhou.players.Player.heartPlayer;

public class EnemyBullet extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private float X, Y;
    private Animation animation;

    public EnemyBullet(float X, float Y) {
        super();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
        animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"));
        renderer = animation;
        this.X = X;
        this.Y = Y;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);

        position.addUp(X, Y);
        isShoot();

        hitPlayer();
    }

    private void isShoot(){
        if (this.screenPosition.y > 768 || this.screenPosition.x < 0 ||this.screenPosition.x > 384){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physics.colliderWithPlayer(this.boxCollider);
        if(player != null){
                player.setActive(false);
                this.isActive = false;
        }
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        this.X = x;
    }

    public void setY(float y) {
        this.Y = y;
    }

    public float getY() {
        return Y;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

}
