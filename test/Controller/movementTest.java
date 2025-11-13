package Controller;

import Model.chess;
import Model.map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class movementTest {

    private map gameMap;
    private movement mover;

    @BeforeEach
    void setUp() {
        gameMap = new map();
        mover = new movement(gameMap);
    }

    @Test
    void check_target_location() {
        // Test normal move to empty land
        chess rat = new chess("rat", 1, 6, 2);
        gameMap.setChess(6, 2, rat);
        assertTrue(mover.check_target_location(rat, 6, 3));

        // Test move out of boundary
        assertFalse(mover.check_target_location(rat, -1, 2));
        assertFalse(mover.check_target_location(rat, 9, 2));
        assertFalse(mover.check_target_location(rat, 6, -1));
        assertFalse(mover.check_target_location(rat, 6, 7));

        // Test rat entering river
        chess riverRat = new chess("rat", 1, 2, 3);
        gameMap.setChess(2, 3, riverRat);
        assertTrue(mover.check_target_location(riverRat, 3, 3));

        // Test non-rat chess trying to enter river
        chess elephant = new chess("elephant", 1, 3, 0);
        gameMap.setChess(3, 0, elephant);
        assertFalse(mover.check_target_location(elephant, 3, 1));

        // Test other chess eat riverRat
        riverRat.setRow(3);
        riverRat.setColumn(1);
        gameMap.setChess(3,1,riverRat);
        assertFalse(mover.check_target_location(elephant, 3, 1));

        // Test rat eat other chess from river
        assertFalse(mover.check_target_location(riverRat,3,0));

        // Test eat other rat in river
        chess riverRat2 = new chess("rat",2,3,2);
        gameMap.setChess(3,2,riverRat2);
        assertTrue(mover.check_target_location(riverRat2,3,1));

        // Test capture enemy piece
        chess player1Rat = new chess("rat", 1, 6, 4);
        chess player2Cat = new chess("cat", 2, 6, 5);
        gameMap.setChess(6, 4, player1Rat);
        gameMap.setChess(6, 5, player2Cat);
        assertTrue(mover.check_target_location(player2Cat, 6, 4));

        // Test enter own den
        chess player1Chess = new chess("lion", 1, 1, 3);
        gameMap.setChess(1, 3, player1Chess);
        assertFalse(mover.check_target_location(player1Chess, 0, 3));

        // Test enter enemy den
        assertTrue(mover.check_target_location(player1Chess, 8, 3));
    }

    @Test
    void update_map_after_move() {

        chess lion = new chess("lion", 1, 7, 2);
        gameMap.setChess(7, 2, lion);
        mover.update_map_after_move(lion, 7, 3);

        assertNull(gameMap.getChess(7, 2));
        assertEquals(lion, gameMap.getChess(7, 3));
        assertEquals(7, lion.getRow());
        assertEquals(3, lion.getColumn());
    }

    @Test
    void check_jump_over() {
        // Test lion horizontal jump over river (successful)
        chess lion = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, lion);
        assertTrue(mover.check_jump_over(lion, 3, 0, 3, 1));

        // Test tiger vertical jump over river (successful)
        chess tiger = new chess("tiger", 1, 5, 6);
        gameMap.setChess(5, 6, tiger);
        assertTrue(mover.check_jump_over(tiger, 5, 6, 4, 6));

        // Test jump blocked by rat in river
        chess blockingRat = new chess("rat", 2, 4, 2);
        gameMap.setChess(4, 2, blockingRat);
        chess jumpingLion = new chess("lion", 1, 4, 0);
        gameMap.setChess(4, 0, jumpingLion);
        assertFalse(mover.check_jump_over(jumpingLion, 4, 0, 4, 1));

        // Test jump blocked by rat in river (vertical)
        chess blockingRatVertical = new chess("rat", 2, 4, 1);
        gameMap.setChess(4, 1, blockingRatVertical);
        chess jumpingTiger = new chess("tiger", 1, 2, 1);
        gameMap.setChess(2, 1, jumpingTiger);
        assertFalse(mover.check_jump_over(jumpingTiger, 2, 1, 3, 1)); // Rat at (4,1) blocks

        // Test jump with capture at destination
        chess player1Tiger = new chess("tiger", 1, 3, 0);
        chess player2Wolf = new chess("wolf", 2, 3, 3);
        gameMap.setChess(3, 0, player1Tiger);
        gameMap.setChess(3, 3, player2Wolf);
        assertTrue(mover.check_jump_over(player1Tiger, 3, 0, 3, 1));

    }
}