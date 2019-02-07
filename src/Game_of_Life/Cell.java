package Game_of_Life;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * Class Cell to create a Cell object with the required variables. 
 * A Cell object will represent every one of the alive/dead cells of the game
 * Moreover, the class contains the methods and functions to manage and work with Cell objects. 
 * @author David Mendez Merino 
 *
 */
public class Cell {

	protected boolean state; //¿Has the cell life? True = yes // False = no
	protected boolean new_state; //¿Will the cell have life in the next generation? True = yes // False = no
	protected JPanel cell_panel; //Visual part of the cell
	protected int x; //Cell row within the matrix
	protected int y; //Cell column within the matrix

	/**
	 * Default constructor for Cell (no parameters)
	 */
	public Cell() {

		state = false;
		new_state = false;
		cell_panel = new JPanel();
		cell_panel.setBackground(Color.WHITE);
		x = 0;
		y = 0;
	}

	/**
	 * Parameterised constructor for Cell
	 * @param state Boolean to indicate the state of the cell (True = alive // False = dead)
	 * @param new_state Boolean to indicate the state of the cell in the next generation
	 * @param x int value which indicates the row of the cell within the board
	 * @param y int value which indicates the colum of the cell within the board
	 */
	public Cell(boolean state, boolean new_state, int x, int y) {
		super();
		this.state = state;
		this.new_state = new_state;
		this.cell_panel = new JPanel();
		this.cell_panel.setBackground(Color.WHITE);
		this.x = x;
		this.y = y;
	}

	/**
	 * Function to get the value of the variable new_state
	 * @return Boolean value from the variable new_state
	 */
	public boolean isNew_state() {
		return new_state;
	}

	/**
	 * Method to change the value of the variable new_state
	 * @param new_state new boolean value for the variable
	 */
	public void setNew_state(boolean new_state) {
		this.new_state = new_state;
	}

	/**
	 * Method to change the value of the variable cell_panel
	 * @param cell_panel new value for the variable
	 */
	public void setCell_panel(JPanel cell_panel) {
		this.cell_panel = cell_panel;
	}

	/**
	 * Function to get the JPanel object of the variable cell_panel
	 * @return JPanel object from the the variable cell_panel
	 */
	public JPanel getCell_panel() {
		return cell_panel;
	}

	/**
	 * Function to get the value of the variable x
	 * @return int value from the variable x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method to change the value of the variable x
	 * @param x int with the new value for the variable
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Function to get the value of the variable y
	 * @return int value from the variable y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Method to change the value of the variable y
	 * @param y int with the new value for the variable
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Function to get the value of the variable state
	 * @return boolean value from the variable state
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Method to change the value of the variable state
	 * @param state new boolean value for the variable
	 */
	public void setState(boolean state) {
		this.state = state;
	}


	/**
	 * The automatically generated 'equals' function.
	 * It compares two Cell objects, returning true when they are the
	 * exactly the same object and false when they are not.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (cell_panel == null) {
			if (other.cell_panel != null)
				return false;
		} else if (!cell_panel.equals(other.cell_panel))
			return false;
		if (new_state != other.new_state)
			return false;
		if (state != other.state)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * Function to return a String which contains the information about
	 * the variables of a Cell object
	 */
	public String toString() {
		return "Cell [state=" + state + ", new_state=" + new_state + ", cell_panel=" + cell_panel + ", x=" + x + ", y="
				+ y + "]";
	}

}
