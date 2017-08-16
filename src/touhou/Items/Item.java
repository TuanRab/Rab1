package touhou.Items;

import bases.Constraints;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;
import touhou.players.PlayerSpawner;

public class Item extends GameObject implements PhysicsBody{
    private static final int SPEEP = 5;
    private BoxCollider boxCollider;
    private Constraints constraints;
    private ItemSpawner itemSpawner;

    public Item() {
        super();
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-blue.png"));
    }

    public void  run(Vector2D parentPosition){
        super.run(parentPosition);
        move();
        //playerItem();

    }

//    private void playerItem() {
//        Player player = Physics.colliderWithPlayer(boxCollider);
//        Item item = new Item();
//            item.setActive(false);
//            item.isActive = false;
//    }

    public void move(){
        position.addUp(0, SPEEP);
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

}
