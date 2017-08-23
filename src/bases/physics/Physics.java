package bases.physics;

import bases.Vector2D;
import touhou.Items.Item;
import touhou.enemies.Enemy;
import touhou.players.Player;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>(); //Generics


    public static <T extends  PhysicsBody> T colliderWith (BoxCollider boxCollider, Class<T> classz) { // add List<T>
        for( PhysicsBody body : bodies) {
            if(body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }

        return null;
    }

    //todo

    public static Player colliderWithPlayer(BoxCollider boxCollider) {
        for( PhysicsBody body : bodies) {
            if(body.isActive())  {
                if(body instanceof Player && body.getBoxCollider().intersects(boxCollider)) {

                    return (Player) body;
                }
            }
        }

        return null;
    }

    public static Item colliderWithItem(BoxCollider boxCollider){
        for(PhysicsBody body : bodies) {
            if (body.isActive()) {
                if(body instanceof Item && body.getBoxCollider().intersects(boxCollider)) {
                    return (Item) body;
                }
            }
        }
        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static void clearAll() {
        bodies.clear();
    }
}
