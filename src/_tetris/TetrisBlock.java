package _tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock
{
    
    private int[][] shape;
    private Color color;
    private int x, y;
    private int [][][] shapes;
    private int currentRotation;
    
    private Color[] availableColors = {
      Color.red, Color.green, Color.blue, Color.orange
    };
    
    public TetrisBlock(int [] [] shape)
    {
        this.shape = shape;
        
        initShapes();
    }
    
    private void initShapes() 
    {
        shapes = new int [4][][];
        
        for (int i = 0; i < 4; i++) // to store the different rotation of the shape 
        {
            int r = shape[0].length;
            int c = shape.length;
            
            shapes[i] = new int[r][c];
            
            for(int y = 0; y < r; y++)
            {
               for (int x = 0; x < c; x++)
               {
                   shapes[i][y][x] = shape[c - x - 1][y];
               }
            }
            
            shape = shapes[i];
                    
        }
    }
    
    public void spawn(int gridWidth)
    {
        Random r  = new Random(); // randomize spawn points of the blocks
        
        currentRotation = r.nextInt(shapes.length); // randomize rotation
        shape = shapes[currentRotation];
        
        y =  - getHeight();
        x = r.nextInt(gridWidth - getWidth()); // randomize position 
        
               // nextInt(5) -- > 0 1 2 3 4 
               // 
         // make the blocks appear at the center of the game area
//        x = (gridWidth - getWidth()) / 2; 

// call the available colors 
        color = availableColors[r.nextInt(availableColors.length)];
    }
    
    public int [][] getShape(){return shape;}
    
    public Color getColor(){return color;};
    
    public int getHeight(){
        return shape.length;
    }
    
    public int getWidth(){
        return shape[0].length;
    }
    
    public int getX() {
        return x; 
    }
    
    public void setX(int newX) 
    {
        x = newX;
        
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int newY) 
    {
        y = newY;
    }
    
    public void moveDown(){
        y++;
    }
  
    
    public void moveLeft() {
        x--;
    }
    
    public void moveRight(){
        x++;
    }
    
    public void rotate()
    {
        currentRotation++;
        if(currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
        
        
    }
    
    public int getBottomEdge(){
        return y + getHeight();
    }
    
    public int getLeftEdge()
    {
        return x;
    }
    
    public int getRightEdge()
    {
        return x + getWidth()  ;
    }
    
}
