/**
 *
 */
package com.zhixiangli.gomoku.core.common;

import java.awt.Point;
import java.util.Random;

/**
 * constant.
 *
 * @author lizhixiang
 */
public class GomokuConst {

    /**
     * four directions.
     */
    public static final Point[] DIRECTIONS = {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(-1, 1)};

    /**
     * continuous number to win the game.
     */
    public static final int CONSECUTIVE_NUM = 5;

    /**
     * the number of grid in the chessboard.
     */
    public static final int CHESSBOARD_SIZE = 15;

    public static final Random RANDOM = new Random();

    private GomokuConst() {
    }
}
