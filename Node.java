//package matrixcomputation;

/*Do not change this file.*/
public class Node
{
	public double value;
	public int row;
	public int col;
	/** The next Node along the row of the matrix. The row value should be the same and the column different */
	public Node rowLink;
	/** The next Node along the column of the matrix. The column value should be the same and the row different */
	public Node colLink;
	public Node()
	{
	}
	public Node(double value, int row, int col)
	{
		this.value = value;
		this.row = row;
		this.col = col;
	}
	public Node(Node rowLink, Node colLink, double value, int row, int col)
	{
		this(value, row, col);
		this.rowLink = rowLink;
		this.colLink = colLink;
	}
	public String toString() 
	{
		return String.format("Node{value=%.2f, r = %d, c = %d}", value, row, col);
	}
}
