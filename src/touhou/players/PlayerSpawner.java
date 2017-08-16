package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import touhou.BackGrounds.BackGround;

public class PlayerSpawner extends GameObject {
    private FrameCounter spawnCounter;
    int spell;
    private Physics playerItem;

    public PlayerSpawner() {
        super();
        spawnCounter = new FrameCounter(30);
        spell = 1;
    }

    public void cast(int x, int y){
        PlayerSpell playerSpell = new PlayerSpell();
        playerSpell.getPosition().set(this.position.add(x, y));
        GameObject.add(playerSpell);
    }

    public void castSpell1(){
        cast(0, 30);
    }

    public void castSpell2(){
        cast(10, 30);
        cast(10, 30);
    }

    public void spawn(){
//        if(!playerItem.colliderWithPlayer()){
//            spell = 1;
//        }
//        if(playerItem.colliderWithPlayer()){
//            spell = 2;
//        }

        switch(spell){
            case 1: {
                castSpell1();
                break;
            }

            case 2: {
                castSpell2();
                break;
            }
        }
    }

}
