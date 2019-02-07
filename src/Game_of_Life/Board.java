package Game_of_Life;

import java.util.Arrays;

/**
 * Class Board to create a Board object with the required variables. 
 * A board object is a matrix of Cell objects which will be required to make easier the logic of the game.
 * In addition, this class contains the methods and functions to manage and work with Board objects. 
 * @author David Mendez Merino
 *
 */
public class Board {

	protected int rows; //Number of rows
	protected int columns; //Number of columns
	protected Cell [][] matrix; //Matrix of Cells

	/**
	 * Default constructor for Board (no parameters)
	 * By default the board will have 60 rows and 120 columns
	 * The constructor calls "inicialise_board" to inicialise the Cells of the matrix
	 */
	public Board() {

		rows = 60;
		columns = 120;
		matrix = new Cell[rows][columns];

		inicialise_board(matrix);
	}

	/**
	 * Parameterised constructor for Board
	 * The constructor calls "inicialise_board" to inicialise the Cells of the matrix
	 * @param r int value to indicates the number of rows
	 * @param c int value to indicates the number of columns
	 */
	public Board(int r, int c) {

		rows = r;
		columns = c;
		matrix = new Cell[rows][columns];

		inicialise_board(matrix);
	}

	/**
	 * Function to get the value of the variable rows
	 * @return int value from the variable rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Method to change the value of the variable rows
	 * @param rows new int value for the variable
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Function to get the value of the variable columns
	 * @return int value from the variable columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Method to change the value of the variable columns
	 * @param columns new int value for the variable
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Function to get an specific Cell of the board 
	 * @param x int value for the row
	 * @param y int value for the column
	 * @return Cell object located in row 'x' and column 'y'
	 */
	public Cell getMatrixPosition(int x, int y) {
		return matrix[x][y];
	}

	/**
	 * Method to change an specific Cell of the board
	 * @param x int value for the row
	 * @param y int value for the column
	 * @param cell Cell object which will be the new Cell
	 */
	public void setMatrixPosition(int x, int y, Cell cell) {
		this.matrix[x][y]=cell;
	}

	/**
	 * Function to get the matrix of Cells
	 * @return the matrix of Cell objects
	 */
	public Cell[][] getMatrix() {
		return matrix;
	}

	/**
	 * Method to change the value of the matrix of Cells
	 * @param matrix New matrix of Cells objects
	 */
	public void setMatrix(Cell[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * The automatically generated 'equals' function.
	 * It compares two Board objects, returning true when they are the
	 * exactly the same object and false when they are not.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (columns != other.columns)
			return false;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}

	/**
	 * Function to return a String which contains the information about
	 * the variables of a Board object
	 */
	public String toString() {
		return "Board [rows=" + rows + ", columns=" + columns + ", matrix=" + Arrays.toString(matrix) + "]";
	}

	/**
	 * Method to inicialise the matrix of Cell objects.
	 * It fills the board with 'dead Cells'
	 * @param matrix Cell matrix
	 */
	public void inicialise_board(Cell[][] matrix) {

		for (int x = 0; x<rows; x++) {
			for (int y = 0; y<columns; y++) {

				matrix[x][y] = new Cell(false, false, x, y);
			}
		}
	}

}
