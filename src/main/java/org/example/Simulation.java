package org.example;

public class Simulation {
    public int width;
    public int height;
    public int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
    }

    public void setAlive(int x, int y) {
        this.setState(x,y,1);
    }

    public void setDead(int x, int y) {
        this.setState(x,y,0);
    }

    public void setState(int x, int y, int state) {
        if (x < 0 || x >= width) {
            return;
        }
        if (y < 0 || y >= height) {
            return;
        }

        this.board[x][y] = state;
    }

    public void printBoard() {
        for (int y = 0; y < height; y++) {
            String line = "| ";
            for (int x = 0; x < width; x++) {
                if(this.board[x][y] == 0) {
                    line += ". ";
                } else {
                    line += "x ";
                }
            }
            line += "| ";
            System.out.println(line);
        }
        System.out.println();
    }

    public int neighbours(int x, int y) {
        int count = 0;
        count += getState(x - 1,y - 1);
        count += getState(x,y - 1);
        count += getState(x + 1,y - 1);

        count += getState(x - 1,y);
        count += getState(x + 1,y);

        count += getState(x - 1,y + 1);
        count += getState(x,y + 1);
        count += getState(x + 1,y + 1);

        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }
        return this.board[x][y];
    }

    public void step() {
        int[][] nextBoard = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getState(x, y) == 1) {
                    if (this.neighbours(x,y) < 2) {
                        nextBoard[x][y] = 0;
                    } else if (this.neighbours(x,y) == 2 || this.neighbours(x,y) == 3) {
                        nextBoard[x][y] = 1;
                    } else {
                        nextBoard[x][y] = 0;
                    }
                } else if (getState(x, y) == 0) {
                    if (this.neighbours(x,y) == 3) {
                        nextBoard[x][y] = 1;
                    } else {
                        nextBoard[x][y] = 0;
                    }
                }
            }
        }
        this.board = nextBoard;
    }

    public void fillBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[x][y] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Simulation grid = new Simulation(7,5);
        grid.fillBoard();

        grid.board[2][2] = 1;
        grid.board[3][2] = 1;
        grid.board[4][2] = 1;
        grid.printBoard();
        grid.step();
        grid.printBoard();
        grid.step();
        grid.printBoard();
        grid.step();
        grid.printBoard();
    }
}
