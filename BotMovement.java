package com.practice;

public class BotMovement
{
    final int N = 4;
 
    void printSolution(int sol[][])
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(" " + sol[i][j] +
                                 " ");
            System.out.println();
        }
    }
 
    boolean isSafe(int grid[][], int x, int y)
    {
        // if (x,y outside grid) return false
        return (x >= 0 && x < N && y >= 0 &&
                y < N && grid[x][y] == 1);
    }
 
    boolean solvegrid(int grid[][])
    {
        int sol[][] = {{0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
 
        if (solvegridUtil(grid, 0, 0, sol) == false)
        {
            System.out.print("Solution doesn't exist");
            return false;
        }
 
        printSolution(sol);
        return true;
    }
 
    boolean solvegridUtil(int grid[][], int x, int y,
                          int sol[][])
    {
        // if (x,y is goal) return true
        if (x == N - 1 && y == N - 1)
        {
            sol[x][y] = 1;
            return true;
        }
 
        // Check if grid[x][y] is valid
        if (isSafe(grid, x, y) == true)
        {
            // mark x,y as part of solution path
            sol[x][y] = 1;
 
            if (solvegridUtil(grid, x + 1, y, sol))
                return true;
 
            if (solvegridUtil(grid, x, y + 1, sol))
                return true;

            if (solvegridUtil(grid, x - 1, y, sol))
                return true;
            
            if (solvegridUtil(grid, x, y - 1, sol))
                return true;

            sol[x][y] = 0; // backtrack
            return false;
        }
 
        return false;
    }
 
    public static void main(String args[])
    {
        BotMovement bot = new BotMovement();
        int grid[][] = {{1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };
        bot.solvegrid(grid);
    }
}
