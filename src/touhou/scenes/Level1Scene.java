package touhou.scenes;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import touhou.BackGrounds.BackGround;
import touhou.Settings.Settings;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;

public class Level1Scene {
    Player player = new Player();

    Enemy enemy = new Enemy();

    EnemySpawner enemySpawner = new EnemySpawner(); // TODO: Viec cua lop: sua thanh game object

    Settings settings = Settings.instance;

    FrameCounter spawnCounter = new FrameCounter(100);

    private void addBackGround() {
        GameObject.add(new BackGround());

    }

    public void init(){
        addBackGround();
        addPlayer();
        addEnemy();
    }


    private void addPlayer() {
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
                );
        player.getPosition().set(
                settings.getGamePlayWidth() / 2,
                settings.getGamePlayHeight() * 3 /4);

        GameObject.add(player);
    }

    //Random random = new Random();
    private void addEnemy() {
        enemy.setConstraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth()
        ));

        enemy.getPosition().set(
                settings.getGamePlayWidth() / 2,
                settings.getGamePlayHeight() / 6
        );
        GameObject.add(enemy);

    }






//            Enemy enemy = new Enemy();
//            enemy.getPosition().set(random.nextInt(384), 40);
//            GameObject.add(enemy);
//        }

    }
