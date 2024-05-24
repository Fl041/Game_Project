package Utilz;

/**
 * class which groups the constants of the game
 */
public class Constants {
    /**
     * the constants of the enemy's animations
     */
    public static class EnnemyConstants {
        public static final int IDLE = 0;
        public static final int ATTACK = 1;
        public static final int DEATH = 2;
        public static final int WALK = 3;
        public static final int Projectile = 4;

        public static int GetSpriteEnnemyAmount(int player_action) {
            return switch (player_action) {
                case WALK -> 11;
                case ATTACK, Projectile -> 7;
                case DEATH -> 5;
                default -> 4;
            };
        }
    }

    /**
     * the constants of the player's animations
     */
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUN = 2;
        public static final int PUSH = 3;
        public static final int JUMP = 4;
        public static final int HIT = 5;
        public static final int DEATH = 6;
        public static final int CRAFT = 7;
        public static final int CLIMB = 8;
        public static final int ATTACK_1 = 9;
        public static final int ATTACK_2 = 10;
        public static final int ATTACK_3 = 11;
        public static final int JUMP_2 = 12;
        public static final int FALL = 13;


        public static int GetSpritePlayerAmount(int player_action) {
            return switch (player_action) {
                case WALK, RUN, PUSH, JUMP, DEATH, CRAFT, CLIMB, ATTACK_1, ATTACK_2, ATTACK_3 -> 6;
                case IDLE -> 4;
                case HIT -> 3;
                default -> 1;
            };
        }
    }

    /**
     * the path to recover resources
     */
    public static class LoadConstants {
        public static final String PLAYER = "/resources/player-sprites.png";
        public static final String LIFE = "/resources/coeur-sprites.png";
        public static final String ENEMY = "/resources/ennemy-sprites.png";
        public static final String ENEMY_PROJECTILE = "/resources/ennemy-sprites.png";
        public static final String WALL = "/resources/outside_sprites.png";
        public static final String BUTTON = "/resources/button-sprites.png";
        public static final String BACKGROUND = "/resources/background.jpg";
        public static final String CLOUD = "/resources/Cloud-sprites.png";
        public static final String MOUNTAIN = "/resources/mountain-sprites.png";
    }
}
