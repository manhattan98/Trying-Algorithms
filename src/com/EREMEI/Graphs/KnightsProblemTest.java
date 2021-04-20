package com.EREMEI.Graphs;

public class KnightsProblemTest implements Runnable {
    int BOARD_PARAM = 5;
    String[] PRE_VISITED_CELLS = new String[] {"B5", "A3"};
    String START_CELL = "B2";

    public KnightsProblemTest() {
        PRE_VISITED_CELLS = new String[0];
    }

    @Override
    public void run() {
        System.out.println("Testing knight problem...");

        KnightProblem problem = new KnightProblem(BOARD_PARAM);
        problem.preVisitCells(PRE_VISITED_CELLS);
        String[] solution = problem.getSolution(START_CELL);

        if (solution.length == 0)
            System.out.println("Solution not found");
        else {
            for (String cell : solution)
                System.out.print(cell + " ");
            System.out.println();
            //displayFilledBoard(solution);
        }
    }

    public void displayFilledBoard(String[] path) {
        String[][] board = new String[BOARD_PARAM][BOARD_PARAM];

        String EMPTY_CELL = "#";
        String VISITED_CELL = "K";
        String PRE_VISITED_CELL = "X";

        // all cells is empty
        for (int i = 0; i < BOARD_PARAM; i++)
            for (int j = 0; j < BOARD_PARAM; j++)
                board[i][j] = EMPTY_CELL;

        // mark all pre visited cells
        for (int i = 0; i < PRE_VISITED_CELLS.length; i++) {
                int X = PRE_VISITED_CELLS[i].charAt(0) - 'A';
                int Y = BOARD_PARAM - Integer.parseInt(PRE_VISITED_CELLS[i].substring(1));

                board[X][Y] = PRE_VISITED_CELL;
        }

        // mark all movies according by path
        for (int i = 0; i < path.length; i++) {
            if (path[i] != null) {
                int X = path[i].charAt(0) - 'A';
                int Y = BOARD_PARAM - Integer.parseInt(path[i].substring(1));

                board[X][Y] = VISITED_CELL;
            }
        }

        // final display the board
        for (int i = 0; i < BOARD_PARAM; i++) {
            for (int j = 0; j < BOARD_PARAM; j++) {
                if (j == 0)
                    System.out.print((BOARD_PARAM - i) + " ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < BOARD_PARAM; i++)
            System.out.print(((char) ('A' + i)) + " ");
        System.out.println();
    }
}