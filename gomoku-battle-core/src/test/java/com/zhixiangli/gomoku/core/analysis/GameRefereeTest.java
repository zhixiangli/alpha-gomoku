/**
 *
 */
package com.zhixiangli.gomoku.core.analysis;

import com.zhixiangli.gomoku.core.chessboard.ChessType;
import com.zhixiangli.gomoku.core.chessboard.Chessboard;
import com.zhixiangli.gomoku.core.common.GomokuConst;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

/**
 *
 * @author lizhixiang
 */
public class GameRefereeTest {

    private Chessboard chessboard;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
    }

    @Test
    public void isWin() {
        for (int i = 1; i <= GomokuConst.CONSECUTIVE_NUM; ++i) {
            final Point point = new Point(0, i);
            chessboard.setChess(point, ChessType.BLACK);
            Assert.assertEquals(i == GomokuConst.CONSECUTIVE_NUM, GameReferee.isWin(chessboard, point));
        }
    }

    @Test
    public void isDraw() {
        for (int i = 0; i < GomokuConst.CHESSBOARD_SIZE; ++i) {
            for (int j = 0; j < GomokuConst.CHESSBOARD_SIZE; ++j) {
                final Point point = new Point(i, j);
                ChessType chessType = ((j & 1) == 0) ? ChessType.BLACK : ChessType.WHITE;
                if (((i >> 1) & 1) == 0) {
                    chessType = GameReferee.nextChessType(chessType);
                }
                chessboard.setChess(point, chessType);
                Assert.assertFalse(GameReferee.isWin(chessboard, point));
                Assert.assertEquals(((i + 1) == GomokuConst.CHESSBOARD_SIZE) && ((j + 1) == GomokuConst.CHESSBOARD_SIZE),
                        GameReferee.isDraw(chessboard, point));
            }
        }
        for (int i = 0; i < GomokuConst.CHESSBOARD_SIZE; ++i) {
            for (int j = 0; j < GomokuConst.CHESSBOARD_SIZE; ++j) {
                final Point point = new Point(i, j);
                Assert.assertTrue(GameReferee.isDraw(chessboard, point));
            }
        }
    }

    @Test
    public void nextChessType() {
        Assert.assertEquals(ChessType.BLACK, GameReferee.nextChessType(ChessType.WHITE));
        Assert.assertEquals(ChessType.WHITE, GameReferee.nextChessType(ChessType.BLACK));
    }

    @Test
    public void isInChessboard() {
        Assert.assertFalse(GameReferee.isInChessboard(0, GomokuConst.CHESSBOARD_SIZE));
        Assert.assertTrue(GameReferee.isInChessboard(0, GomokuConst.CHESSBOARD_SIZE - 1));
    }

}
