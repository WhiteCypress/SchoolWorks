package assignment4_spaceinvaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

public class AssetManager {

    static private Background backgroundImage = null;
    static private ArrayList<ImagePattern> enemy = new ArrayList<>();

    static private Media backgroundMusic = null;
    static private AudioClip victorySound = null;
    static private AudioClip gameOverSound = null;
    static private AudioClip shootingSound = null;
    static private AudioClip enemyShotSound = null;
    static private AudioClip playerShotSound = null;

    static private String fileURL(String relativePath) {
        return new File(relativePath).toURI().toString();
    }

    static public void preloadAllAssets() {
        // Preload all images
        Image background = new Image(fileURL("./assets/images/background.png"));
        backgroundImage = new Background(
                new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT));

        enemy.add(new ImagePattern(new Image(fileURL("./assets/images/mercury.png"))));
        enemy.add(new ImagePattern(new Image(fileURL("./assets/images/venus.png"))));
        enemy.add(new ImagePattern(new Image(fileURL("./assets/images/earth.png"))));
        enemy.add(new ImagePattern(new Image(fileURL("./assets/images/jupiter.png"))));
        enemy.add(new ImagePattern(new Image(fileURL("./assets/images/saturn.png"))));

        // Preload all music tracks
        backgroundMusic = new Media(fileURL("./assets/music/backgroundMusic.mp3"));

        // Preload all sound effects
        victorySound = new AudioClip(fileURL("./assets/soundfx/victorySound.wav"));
        gameOverSound = new AudioClip(fileURL("./assets/soundfx/gameOverSound.wav"));
        gameOverSound = new AudioClip(fileURL("./assets/soundfx/gameOverSound.wav"));
        shootingSound = new AudioClip(fileURL("./assets/soundfx/shootingSound.wav"));
        enemyShotSound = new AudioClip(fileURL("./assets/soundfx/enemyShotSound.wav"));
        playerShotSound = new AudioClip(fileURL("./assets/soundfx/playerShotSound.wav"));
    }

    static public Background getBackgroundImage() {
        return backgroundImage;
    }

//    static public ImagePattern getRandomPlanet() {
//        Random rng = new Random();
//        int i = rng.nextInt(planets.size());
//        return planets.get(i);
//    }
    static public Media getBackgroundMusic() {
        return backgroundMusic;
    }

    public static ArrayList<ImagePattern> getEnemy() {
        return enemy;
    }

    public static AudioClip getVictorySound() {
        return victorySound;
    }

    public static AudioClip getGameOverSound() {
        return gameOverSound;
    }

    public static AudioClip getShootingSound() {
        return shootingSound;
    }

    public static AudioClip getEnemyShotSound() {
        return enemyShotSound;
    }

    public static AudioClip getPlayerShotSound() {
        return playerShotSound;
    }

}
