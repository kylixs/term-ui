package org.termui.layout;

/**
 * +----+-------+----+
 * | LT |  TOP  | RT |
 * +----+-------+----+
 * | L  | CENTER|  R |
 * +----+-------+----+
 * | LB | BOTTOM| RB |
 * +----+-------+----+
 */
public enum Position {
    LEFT_TOP,
    TOP,
    RIGHT_TOP,
    LEFT,
    CENTER,
    RIGHT,
    LEFT_BOTTOM,
    BOTTOM,
    RIGHT_BOTTOM;

    public static Position[] getLeftPositions() {
        return new Position[]{LEFT_TOP, LEFT, LEFT_BOTTOM};
    }

    public static Position[] getTopPositions() {
        return new Position[]{LEFT_TOP, TOP, RIGHT_TOP};
    }

    public static Position[] getCenterPositions() {
        return new Position[]{LEFT, CENTER, RIGHT};
    }

    public static Position[] getRightPositions() {
        return new Position[]{RIGHT_TOP, RIGHT, RIGHT_BOTTOM};
    }

    public static Position[] getBottomPositions() {
        return new Position[]{LEFT_BOTTOM, BOTTOM, RIGHT_BOTTOM};
    }

    public Position[] getAdjacentPositions() {
        switch (this) {
            case LEFT_TOP:
                return new Position[]{TOP, LEFT};
            case TOP:
                return new Position[]{LEFT_TOP, RIGHT_TOP};
            case RIGHT_TOP:
                return new Position[]{TOP, RIGHT};
            case LEFT:
                return new Position[]{LEFT_TOP, LEFT_BOTTOM};
            case CENTER:
                return new Position[]{LEFT, RIGHT, TOP, BOTTOM};
            case RIGHT:
                return new Position[]{RIGHT_TOP, RIGHT_BOTTOM};
            case LEFT_BOTTOM:
                return new Position[]{BOTTOM, LEFT};
            case BOTTOM:
                return new Position[]{LEFT_BOTTOM, RIGHT_BOTTOM};
            case RIGHT_BOTTOM:
                return new Position[]{BOTTOM, RIGHT};
            default:
                return new Position[]{};
        }
    }
}