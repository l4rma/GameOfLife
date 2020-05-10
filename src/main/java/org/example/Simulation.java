package org.example;

public class Simulation {
    public static int DEAD = 0;
    public static int ALIVE = 1;

    public int width;
    public int height;
    public int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
    }

    public void setAlive(int x, int y) {
        this.setState(x,y,ALIVE);
    }

    public void setDead(int x, int y) {
        this.setState(x,y,DEAD);
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
                if(this.board[x][y] == DEAD) {
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
                if (getState(x, y) == ALIVE) {
                    if (this.neighbours(x,y) < 2) {
                        nextBoard[x][y] = 0;
                    } else if (this.neighbours(x,y) == 2 || this.neighbours(x,y) == 3) {
                        nextBoard[x][y] = ALIVE;
                    } else {
                        nextBoard[x][y] = DEAD;
                    }
                } else if (getState(x, y) == DEAD) {
                    if (this.neighbours(x,y) == 3) {
                        nextBoard[x][y] = ALIVE;
                    } else {
                        nextBoard[x][y] = DEAD;
                    }
                }
            }
        }
        this.board = nextBoard;
    }

    public void fillBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[x][y] = DEAD;
            }
        }
    }

    public static void main(String[] args) {
        Simulation grid = new Simulation(7,5);
        grid.fillBoard();

        grid.board[2][2] = ALIVE;
        grid.board[3][2] = ALIVE;
        grid.board[4][2] = ALIVE;
        grid.printBoard();
        grid.step();
        grid.printBoard();
        grid.step();
        grid.printBoard();
        grid.step();
        grid.printBoard();
    }
}
