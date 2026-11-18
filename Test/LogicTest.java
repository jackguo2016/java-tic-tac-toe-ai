import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @org.junit.jupiter.api.Test
    void choosesAnImmediateWin() {
        String[][] board = {{"X", "X", ""}, {"O", "O", ""}, {"", "", ""}};
        assertArrayEquals(new int[] {0, 2}, new Logic("X", "O").chooseMove(board, 3));
        assertArrayEquals(new String[] {"X", "X", ""}, board[0]);
    }

    @org.junit.jupiter.api.Test
    void blocksTheOpponentsImmediateWin() {
        String[][] board = {{"O", "O", ""}, {"X", "", ""}, {"", "X", ""}};
        assertArrayEquals(new int[] {0, 2}, new Logic("X", "O").chooseMove(board, 3));
    }

    String[][] Tic_Tac_Toe = new String[10][10];
    @org.junit.jupiter.api.Test
    void check_full() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "o";


            }
        }


                assertEquals(true,Logic.check_full(Tic_Tac_Toe,10));
    }
    @org.junit.jupiter.api.Test
    void check_full2() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "x";


            }
        }


        assertEquals(true,Logic.check_full(Tic_Tac_Toe,10));
    }
    @org.junit.jupiter.api.Test
    void check_full3() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "o";


            }
            Tic_Tac_Toe[3][4] = "";
        }


        assertEquals(false,Logic.check_full(Tic_Tac_Toe,10));
    }

    @org.junit.jupiter.api.Test
    void check_full4() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "o";


            }
            Tic_Tac_Toe[7][9] = "";
        }


        assertEquals(false,Logic.check_full(Tic_Tac_Toe,10));
    }

    @org.junit.jupiter.api.Test
    void check_X_result() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "";


            }
            Tic_Tac_Toe[3][4] = "X";
            Tic_Tac_Toe[3][5] = "X";
            Tic_Tac_Toe[3][6] = "X";
        }
    }
    @org.junit.jupiter.api.Test
    void check_X_result2() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "";


            }
            Tic_Tac_Toe[9][6] = "X";
            Tic_Tac_Toe[8][6] = "X";
            Tic_Tac_Toe[7][6] = "X";
        }
    }
    @org.junit.jupiter.api.Test
    void check_X_result4() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "";


            }
            Tic_Tac_Toe[7][4] = "X";
            Tic_Tac_Toe[6][5] = "X";
            Tic_Tac_Toe[7][6] = "X";
        }
    }
    @org.junit.jupiter.api.Test
    void check_X_result5() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 10; j++){
                Tic_Tac_Toe[i][j] = "";


            }
            Tic_Tac_Toe[2][4] = "X";
            Tic_Tac_Toe[2][5] = "X";
            Tic_Tac_Toe[2][6] = "X";
        }
    }



}
