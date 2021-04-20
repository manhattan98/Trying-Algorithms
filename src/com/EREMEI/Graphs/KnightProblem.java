package com.EREMEI.Graphs;

import java.util.Stack;

public class KnightProblem {
    public static final int MAX_BOARD_PARAM = 26;

    private int boardParam;
    private Cell[][] cellList;

    public KnightProblem(int boardParam) {
        if (boardParam > MAX_BOARD_PARAM)
            throw new RuntimeException("boardParam can not be greater than " + MAX_BOARD_PARAM);

        this.boardParam = boardParam;
        this.cellList = new Cell[boardParam][boardParam];

        for (int i = 0; i < boardParam; i++)
            for (int j = 0; j < boardParam; j++)
                cellList[i][j] = new Cell(i, j);
    }

    public void preVisitCells(String... cells) {
        for (String cell : cells) {
            int X = cell.charAt(0) - 'A';
            int Y = boardParam - Integer.parseInt(cell.substring(1));

            cellList[X][Y].visited = true;
        }
    }

    public String[] getSolution(String startCell) {
        String[] solutionPath = new String[0];

        int startX = startCell.charAt(0) - 'A';
        int startY = boardParam - Integer.parseInt(startCell.substring(1));

        int totalCount = boardParam * boardParam;
        int visitedCount = 0;//1;

        Stack<Cell> stack = new Stack<>();
        stack.push(cellList[startX][startY]);
        stack.peek().visited = true;

        // count all pre visited cells
        for (int i = 0; i < boardParam; i++)
            for (int j = 0; j < boardParam; j++)
                if (cellList[i][j].visited)
                    visitedCount++;

        while (!stack.isEmpty()) {
            Cell currentCell = stack.peek();
            Cell nextCell = currentCell.nextUnVisited();

            // next step, else deadlock
            if (nextCell != null) {
                stack.push(nextCell);
                nextCell.visited = true;
                visitedCount++;
            }
            // deadlock
            else {
                // deadlock, but not finished yet
                if (visitedCount < totalCount) {
                    currentCell.visited = false;
                    visitedCount--;
                    stack.pop();
                }
                // deadlock, and finished
                else {//if (visitedCount == totalCount) {
                    solutionPath = new String[totalCount];
                    int i = solutionPath.length - 1;
                    while (!stack.isEmpty())
                        solutionPath[i--] = stack.pop().name;
                    break;
                }
            }
        }

        for (int i = 0; i < boardParam; i++)
            for (int j = 0; j < boardParam; j++)
                cellList[i][j].reset();

        return solutionPath;
    }

    private class Cell {
        int posX, posY;
        boolean visited;
        String name;

        public Cell(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
            this.visited = false;
            this.name = String.valueOf((char) ('A' + posX)) + (boardParam - posY);
        }

        boolean[] visitedPaths = new boolean[8];
        public KnightProblem.Cell nextUnVisited() {
            int[][] offsetsXY = new int[][] {
                    {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {1, -2}, {-1, -2}, {1, 2}, {-1, 2}
            };
            //for (int[] offsetXY : offsetsXY) {
            for (int i = 0; i < offsetsXY.length; i++) {
                int X = posX + offsetsXY[i][0];
                int Y = posY + offsetsXY[i][1];
                if (X >= 0 && X < boardParam && Y >= 0 && Y < boardParam)
                    if (!visitedPaths[i])
                        if (!cellList[X][Y].visited) {
                            visitedPaths[i] = true;
                            return cellList[X][Y];
                        }
            }
            // all path visited, then reset paths ability
            visitedPaths = new boolean[visitedPaths.length];
            return null;
        }

        public void reset() {
            visited = false;
            visitedPaths = new boolean[visitedPaths.length];
        }
    }
}