package Model;

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
    void check_target_location_1() {
        // Test normal move to empty land
        chess rat = new chess("rat", 1, 6, 2);
        gameMap.setChess(6, 2, rat);
        assertTrue(mover.check_target_location(rat, 6, 3));


        // Test move out of boundary
        assertFalse(mover.check_target_location(rat, -1, 2));
        assertFalse(mover.check_target_location(rat, 9, 2));
        assertFalse(mover.check_target_location(rat, 6, -1));
        assertFalse(mover.check_target_location(rat, 6, 7));
    }
    @Test
    void check_target_location_2() {
        // Test rat entering river
        chess riverRat = new chess("rat", 1, 2, 3);
        gameMap.setChess(2, 3, riverRat);
        assertTrue(mover.check_target_location(riverRat, 3, 3));
    }
    @Test
    void check_target_location_3() {
        // Test non-rat chess trying to enter river
        chess elephant = new chess("elephant", 1, 3, 0);
        gameMap.setChess(3, 0, elephant);
        assertFalse(mover.check_target_location(elephant, 3, 1));
    }
    @Test
    void check_target_location_4() {
        // Test rat in river trying to capture land piece
        chess riverRat = new chess("rat", 1, 2, 3);
        gameMap.setChess(2, 3, riverRat);
        chess landChess = new chess("cat", 2, 3, 0);
        gameMap.setChess(3, 0, landChess);
        riverRat.setRow(3);
        riverRat.setColumn(1);
        gameMap.setChess(3, 1, riverRat);
        assertFalse(mover.check_target_location(riverRat, 3, 0));
    }
    @Test
    void check_target_location_5() {
        // Test two rats capturing each other in river
        chess riverRat2 = new chess("rat", 2, 3, 2);
        gameMap.setChess(3, 2, riverRat2);
        assertTrue(mover.check_target_location(riverRat2, 3, 1));
    }
    @Test
    void check_target_location_6() {
        // Test capture enemy piece on land
        chess player1Rat = new chess("rat", 1, 6, 4);
        chess player2Cat = new chess("cat", 2, 6, 5);
        gameMap.setChess(6, 4, player1Rat);
        gameMap.setChess(6, 5, player2Cat);
        assertTrue(mover.check_target_location(player2Cat, 6, 4));
    }
    @Test
    void check_target_location_7() {
        // Test elephant cannot capture rat
        chess elephant2 = new chess("elephant", 1, 5, 4);
        chess rat2 = new chess("rat", 2, 5, 5);
        gameMap.setChess(5, 4, elephant2);
        gameMap.setChess(5, 5, rat2);
        assertFalse(mover.check_target_location(elephant2, 5, 5));
    }
    @Test
    void check_target_location_8() {
        // Test enter own den
        chess player1Lion = new chess("lion", 1, 1, 3);
        gameMap.setChess(1, 3, player1Lion);
        assertFalse(mover.check_target_location(player1Lion, 0, 3));

        // Test enter enemy den
        assertTrue(mover.check_target_location(player1Lion, 8, 3));
    }
    @Test
    void check_target_location_9() {
        // Test capture in trap
        chess player1Wolf = new chess("wolf", 1, 8, 2);
        chess player2Dog = new chess("dog", 2, 8, 3);
        gameMap.setChess(8, 2, player1Wolf);
        gameMap.setChess(8, 3, player2Dog);
        assertTrue(mover.check_target_location(player1Wolf, 8, 3));
    }
    @Test
    void check_target_location_10() {
        // Test lion jumping horizontally over empty river
        chess lion = new chess("lion", 1, 4, 0);
        gameMap.setChess(4, 0, lion);
        assertTrue(mover.check_target_location(lion, 4, 1));
    }
    @Test
    void check_target_location_11() {
        // Test tiger jumping vertically over empty river
        chess tiger = new chess("tiger", 1, 6, 1);
        gameMap.setChess(6, 1, tiger);
        assertTrue(mover.check_target_location(tiger, 5, 1));
    }
    @Test
    void check_target_location_12() {
        // Test lion jump blocked by rat in river
        chess blockingRat = new chess("rat", 2, 3, 1);
        gameMap.setChess(3, 1, blockingRat);
        chess lion2 = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, lion2);
        assertFalse(mover.check_target_location(lion2, 3, 1));
    }
    @Test
    void check_target_location_13() {
        // Test capture same player chess
        chess player1Cat = new chess("cat", 1, 7, 1);
        chess player1rat = new chess("rat", 1, 7, 2);
        gameMap.setChess(7, 1, player1Cat);
        gameMap.setChess(7, 2, player1rat);
        assertFalse(mover.check_target_location(player1Cat, 7, 2));
    }@Test
    void check_target_location_14() {
        // Test rat moving within river
        chess ratInRiver = new chess("rat", 1, 3, 4);
        gameMap.setChess(3, 4, ratInRiver);
        assertTrue(mover.check_target_location(ratInRiver, 3, 5));
    }@Test
    void check_target_location_15() {
        // Test elephant capturing lion
        chess elephant3 = new chess("elephant", 1, 7, 4);
        chess lion3 = new chess("lion", 2, 7, 5);
        gameMap.setChess(7, 4, elephant3);
        gameMap.setChess(7, 5, lion3);
        assertTrue(mover.check_target_location(elephant3, 7, 5));
    }
    @Test
    void check_target_location_16() {
        // Test move to trap square
        chess dog = new chess("dog", 1, 8, 4);
        gameMap.setChess(8, 4, dog);
        assertTrue(mover.check_target_location(dog, 8, 5));
    }

    @Test
    void check_target_location_17() {
        // Test move to trap square
        chess rat = new chess("rat", 1, 3, 0);
        gameMap.setChess(3, 0, rat);
        assertTrue(mover.check_target_location(rat, 3, 1));
    }
    @Test
    void check_target_location_18() {
        // Test move to trap square
        chess rat1 = new chess("rat", 1, 3, 1);
        gameMap.setChess(3, 1, rat1);
        chess rat2 = new chess("rat", 1, 3, 2);
        gameMap.setChess(3, 2, rat2);
        assertTrue(mover.check_target_location(rat1, 3, 2));
    }
    @Test
    void check_target_location_19() {
        chess wolf = new chess("wolf", 1, 0, 1);
        gameMap.setChess(0, 1, wolf);
        chess lion = new chess("lion", 1, 0, 2);
        gameMap.setChess(0, 2, lion);
        assertTrue(mover.check_target_location(wolf, 0, 2));
    }

    @Test
    void update_map_after_move() {
        // Test normal move
        chess lion = new chess("lion", 1, 7, 2);
        gameMap.setChess(7, 2, lion);
        mover.update_map_after_move(lion, 7, 3);

        assertNull(gameMap.getChess(7, 2));
        assertEquals(lion, gameMap.getChess(7, 3));
        assertEquals(7, lion.getRow());
        assertEquals(3, lion.getColumn());

        // Test move with capture
        chess wolf = new chess("wolf", 1, 6, 3);
        chess cat = new chess("cat", 2, 6, 4);
        gameMap.setChess(6, 3, wolf);
        gameMap.setChess(6, 4, cat);
        mover.update_map_after_move(wolf, 6, 4);

        assertNull(gameMap.getChess(6, 3));
        assertEquals(wolf, gameMap.getChess(6, 4));
        assertEquals(6, wolf.getRow());
        assertEquals(4, wolf.getColumn());
    }




    @Test
    void check_jump_over_1() {
        // Test lion horizontal jump over river
        chess lion = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, lion);
        assertTrue(mover.check_jump_over(lion, 3, 0, 3, 1));
    }

    @Test
    void check_jump_over_2() {
        // Test tiger vertical jump over river
        chess tiger = new chess("tiger", 1, 2, 5);
        gameMap.setChess(2, 5, tiger);
        assertTrue(mover.check_jump_over(tiger, 2, 5, 3, 5));
    }
    @Test
    void check_jump_over_3() {
        // Test jump blocked by rat in river
        chess blockingRat = new chess("rat", 2, 3, 2);
        gameMap.setChess(3, 2, blockingRat);
        chess jumpingLion = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, jumpingLion);
        assertFalse(mover.check_jump_over(jumpingLion, 3, 0, 3, 1));
    }
    @Test
    void check_jump_over_4() {
        // Test jump blocked by rat in river
        chess blockingRatVertical = new chess("rat", 2, 4, 1);
        gameMap.setChess(4, 1, blockingRatVertical);
        chess jumpingTiger = new chess("tiger", 1, 2, 1);
        gameMap.setChess(2, 1, jumpingTiger);
        assertFalse(mover.check_jump_over(jumpingTiger, 2, 1, 3, 1));
    }
    @Test
    void check_jump_over_5() {
        // Test jump with successful capture
        chess player1lion = new chess("lion", 1, 3, 0);
        chess player2rat = new chess("rat", 2, 3, 3);
        gameMap.setChess(3, 0, player1lion);
        gameMap.setChess(3, 3, player2rat);
        assertTrue(mover.check_jump_over(player1lion, 3, 0, 3, 1));
    }
    @Test
    void check_jump_over_6() {
        // Test jump with failed capture
        chess player1Tiger2 = new chess("tiger", 1, 3, 0);
        chess player2Elephant = new chess("elephant", 2, 3, 3);
        gameMap.setChess(3, 0, player1Tiger2);
        gameMap.setChess(3, 3, player2Elephant);
        assertFalse(mover.check_jump_over(player1Tiger2, 3, 0, 3, 1));
    }
    @Test
    void check_jump_over_7() {
        // Test lion vertical jump
        chess lionVertical = new chess("lion", 1, 2, 3);
        gameMap.setChess(2, 3, lionVertical);
        assertTrue(mover.check_jump_over(lionVertical, 2, 3, 3, 3));
    }
    @Test
    void check_jump_over_8() {
        // Test tiger horizontal jump
        chess tigerHorizontal = new chess("tiger", 1, 3, 6);
        gameMap.setChess(3, 6, tigerHorizontal);
        assertTrue(mover.check_jump_over(tigerHorizontal, 3, 6, 3, 5));
    }
    @Test
    void check_jump_over_9() {
        // Test invalid jump direction
        chess invalidJump = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, invalidJump);
        assertFalse(mover.check_jump_over(invalidJump, 3, 0, 4, 1));
    }
    @Test
    void check_jump_over_10() {
        // Test jump over river with empty destination
        chess lionEmptyDest = new chess("lion", 1, 3, 0);
        gameMap.setChess(3, 0, lionEmptyDest);
        // Clear any pieces at destination
        gameMap.setChess(3, 3, null);
        assertTrue(mover.check_jump_over(lionEmptyDest, 3, 0, 3, 1));
    }
    @Test
    void check_jump_over_11() {
        // Test jump over river with empty destination
        chess lionEmptyDest = new chess("lion", 1, 6, 1);
        gameMap.setChess(6, 1, lionEmptyDest);
        chess wolf= new chess("wolf",2,2,1);
        gameMap.setChess(2, 1, wolf);
        assertTrue(mover.check_jump_over(lionEmptyDest, 6, 1, 5, 1));
    }

    @Test
    void check_jump_over_12() {
        chess wolf = new chess("wolf", 1, 6, 1);
        gameMap.setChess(6, 1, wolf);
        chess lion= new chess("lion",2,2,1);
        gameMap.setChess(2, 1, lion);
        assertFalse(mover.check_jump_over(wolf, 6, 1, 5, 1));
    }
}