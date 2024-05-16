package Entities;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class EnnemyConstants {
        public static final int IDLE = 0;
        public static final int ATTACK = 1;
        public static final int DEATH = 2;
        public static final int WALK = 3;
        public static final int Projectile = 4;

        public static int GetSpriteEnnemyAmount(int player_action) {
            switch (player_action) {
                case WALK:
                    return 11;
                case ATTACK , Projectile:
                    return 7;
                case DEATH:
                    return 5;
                case IDLE:
                default:
                    return 4;
            }
        }
    }
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
            switch (player_action) {
                case WALK,RUN,PUSH,JUMP,DEATH,CRAFT,CLIMB,ATTACK_1,ATTACK_2,ATTACK_3:
                    return 6;
                case IDLE:
                    return 4;
                case HIT:
                    return 3;
                case FALL , JUMP_2:
                default:
                    return 1;
            }
        }
    }
}
