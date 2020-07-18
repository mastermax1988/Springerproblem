package Springerproblem;

import java.util.ArrayList;
import java.util.List;

public class Solver
{
    private int board[][];
    private final int w, h;

    private class koord
    {
        public koord(int x, int y)
        {
            this.x=x;
            this.y=y;
        }
        public int x, y;
    };
    public Solver(int w, int h)
    {
        board = new int[w][h];
        this.w=w;
        this.h=h;
    }
    public void getAllSolutions()
    {
        for(int i=0;i<w;i++)
            for(int j=0;j<h;j++)
                backTrack(i,j,0);
    }
    private void backTrack(int x, int y, int depth)
    {
        board[x][y] = depth+1;
        if(depth+1==w*h)
        {
            printBoard();
            return;
        }
        List<koord> possibleJumps = getPossibleJumps(x,y);
        if(possibleJumps.isEmpty())
        {
            board[x][y]=0;
            return;
        }
        for (koord jump : possibleJumps)
        {
            backTrack(jump.x, jump.y, depth+1);
        }
        board[x][y]=0;


    }
    private void  printBoard()
    {
        for(int i=0;i<w;i++)
        {
            for (int j = 0; j < h; j++)
                System.out.print(board[i][j]+"\t");
            System.out.print("\n");
        }
        System.out.println("\n");
    }
    private List<koord> getPossibleJumps(int x, int y)
    {
        List <koord> ret = new ArrayList<koord>();
        koord[] jumps = new koord[]
                {
                        new koord(x+1,y+2),  new koord(x+1,y-2),
                        new koord(x-1,y+2),  new koord(x-1,y-2),
                        new koord(x+2,y+1),  new koord(x+2,y-1),
                        new koord(x-2,y+1),  new koord(x-2,y-1)
                };
        for (koord k :jumps)
        {
            if(k.x>=0 && k.x<w && k.y>=0 && k.y<h && board[k.x][k.y]==0)
                ret.add(k);

        }
        return ret;
    }
}
