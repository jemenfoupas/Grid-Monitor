import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Rich B
 *
 */
public class GridMonitor implements GridMonitorInterface
{

	//FIELDS
	private int sizeRow;
	private int sizeCol;
	private double[][] grid;
	private double[][] gridCopy;
	private double[][] surroundingSumGrid;
	private double[][] surroundingAvgGrid;
	private double[][] deltaGrid;
	private boolean[][] dangerGrid;
	
	//CONSTRUCTOR
	/**
	 * it reads the file and sets the size of the grid.
	 * @param the file name that contain the values
	 */
	public GridMonitor(String filename) throws FileNotFoundException
	{
		//file reader
		File file = new File(filename);
		Scanner fileScan = new Scanner(file);
		
		sizeRow = fileScan.nextInt();
		sizeCol = fileScan.nextInt();
		
		this.grid = new double[sizeRow][sizeCol];
		this.gridCopy = new double[sizeRow][sizeCol];
		this.surroundingSumGrid = new double[sizeRow][sizeCol];
		this.surroundingAvgGrid = new double[sizeRow][sizeCol];
		this.deltaGrid = new double[sizeRow][sizeCol];
		this.dangerGrid = new boolean[sizeRow][sizeCol];
		
		for( int row = 0; row < grid.length; row++)
			for(int col = 0; col < grid[row].length; col++)
				grid[row][col] = fileScan.nextDouble();
	}

	//METHODS
	/**
	 * @return a string of the copy grid to be display.
	 */
	public String toString()
	{ 
		String a = "The grid is: \n";
		getBaseGrid();
		for( int row = 0; row < sizeRow; row++)
		{
			for(int col = 0; col < sizeCol; col++)
			{
				a += String.valueOf(gridCopy[row][col]);
				a += " ";
			}
			a += "\n";
		}
		return a;
	}
	
	/**
	 * @return a copy of the original grid
	 */
	@Override
	public double[][] getBaseGrid() 
	{
		for( int row = 0; row < sizeRow; row++)
			for(int col = 0; col < sizeCol; col++)
				gridCopy[row][col] = grid[row][col];
		return gridCopy;
	}
	
	/**
	 * @return the sum of the surrounding values
	 */
	@Override
	public double[][] getSurroundingSumGrid() 
	{
		for( int row = 0; row < sizeRow; row++)
			for(int col = 0; col < sizeCol; col++)
			{
				double up;
				double down;
				double left;
				double right;
				
				//up
				if(row == 0)
					up = grid[row][col];
				else
					up = grid[row - 1][col];
				
				//down
				if(row == sizeRow - 1)
					down = grid[row][col];
				else
					down = grid[row + 1][col];
				
				//left
				if(col == 0)
					left = grid[row][col];	
				else
					left =  grid[row][col - 1];
				
				//right
				if(col == sizeCol - 1)
					right = grid[row][col];
				else
					right = grid[row][col + 1];
					
				surroundingSumGrid [row][col] = up + down + left + right;
			}
		return surroundingSumGrid;
	}
	
	/**
	 * @return the surrounding average values
	 */
	@Override
	public double[][] getSurroundingAvgGrid() 
	{
		getSurroundingSumGrid();
		for( int row = 0; row < sizeRow; row++)
			for(int col = 0; col < sizeCol; col++)
				this.surroundingAvgGrid[row][col] = this.surroundingSumGrid[row][col] / 4;
		
		return surroundingAvgGrid;
	}
	
	/**
	 * @return the delta grid
	 */
	@Override
	public double[][] getDeltaGrid() 
	{
		getSurroundingAvgGrid();
		for( int row = 0; row < sizeRow; row++)
			for(int col = 0; col < sizeCol; col++)
				this.deltaGrid[row][col] = Math.abs( this.surroundingAvgGrid[row][col] / 2);
		
		return deltaGrid;
	}
	
	/**
	 * @return a boolean value that determines if the surrounding values are to high or to low
	 */
	@Override
	public boolean[][] getDangerGrid() 
	{
		getDeltaGrid();
		for( int row = 0; row < sizeRow; row++)
			for(int col = 0; col < sizeCol; col++)
			{
				if( grid[row][col] < surroundingAvgGrid[row][col] - deltaGrid[row][col] || grid[row][col] >surroundingAvgGrid[row][col] + deltaGrid[row][col])
					dangerGrid[row][col] = true;
				else 
					dangerGrid[row][col] = false;
			}
		return dangerGrid;
	}
}
