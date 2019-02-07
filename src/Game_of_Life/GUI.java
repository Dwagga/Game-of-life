package Game_of_Life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class with the Graphical and logical part of the program (main code)
 * Using object from the classes 'Cell' and 'Board' and new variables, methods and 
 * functions, this class provides the main code of the project.
 * @author David Mendez Merino
 *
 */
public class GUI implements Runnable{

	protected JFrame frame; //Graphic JFrame which will content the rest of the panels
	static protected JPanel Board_Panel; // JPanel that contains the board
	static protected Board BoardGame; // Board object that contains the Cell objects
	static protected Thread thread; // Thread to make efficient the execution of the program
	static protected GUI window; // GUI object to be able to work in this class

	protected JButton Execute_Button; //Button to execute the program
	protected JButton Restart_Button; //Button to restart the program
	protected JButton Resume_Button; //Button to resume the program
	protected JButton Stop_Button; //Button to stop the program

	protected JLabel GenerationText; // JLabel that contains the text to indicate the generation number
	static protected JLabel PopulationText; //JLabel that contains the text to indicate the population number
	protected int Generation; // int variable which saves the value of the generations number
	static protected int Population; // int variable which saves the value of the population number

	protected boolean Stop; // Boolean variable to indicate if the user pressed the Stop_Button;
	private JMenuBar menuBar; // JMenuBar which contains the JMenu object
	private JMenu mnNewMenu; // JMenu where the JMenuItems are located
	private JMenuItem mntmExample; //JMenuItem to access the example 1
	private JMenuItem mntmExample_1; //JMenuItem to access the example 2
	private JMenuItem mntmExample_2; //JMenuItem to access the example 3



	/**
	 * Launch the application.
	 * It creates a GUI object, initialise the board and set the frame to 'visible'
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window = new GUI();   
					Draw_Initial_Board();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Default constructor for GUI (no parameters)
	 * Initialise the frame and some of the variable values.
	 */
	public GUI() {

		Stop = false;
		BoardGame = new Board(60, 120);
		Generation = 0;
		Population = 0;
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 * In addition, it set the different events for the buttons.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 13));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setTitle("Game of life - David Mendez Merino");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel Generation_Panel = new JPanel();
		frame.getContentPane().add(Generation_Panel, BorderLayout.PAGE_START);
		GridBagLayout gbl_Generation_Panel = new GridBagLayout();
		gbl_Generation_Panel.columnWidths = new int[]{94, 522, 232, 0};
		gbl_Generation_Panel.rowHeights = new int[]{30, 0};
		gbl_Generation_Panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Generation_Panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Generation_Panel.setLayout(gbl_Generation_Panel);

		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setSize(555, 111);
		menuBar.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.VERTICAL;
		gbc_menuBar.insets = new Insets(0, 0, 0, 5);
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		Generation_Panel.add(menuBar, gbc_menuBar);

		mnNewMenu = new JMenu("Menu of Examples");
		mnNewMenu.setHorizontalAlignment(SwingConstants.LEFT);
		mnNewMenu.setBackground(new Color(135, 206, 235));
		menuBar.add(mnNewMenu);

		mntmExample = new JMenuItem("Example 1");
		mntmExample.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				Stop = true;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Restart_board();
				Draw_Example_1();
				Stop = false;

			}
		});
		mnNewMenu.add(mntmExample);

		mntmExample_1 = new JMenuItem("Example 2");
		mntmExample_1.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				Stop = true;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Restart_board();
				Draw_Example_2();
				Stop = false;
			}
		});
		mnNewMenu.add(mntmExample_1);

		mntmExample_2 = new JMenuItem("Example 3");
		mntmExample_2.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				Stop = true;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Restart_board();
				Draw_Example_3();
				Stop = false;
			}
		});
		mnNewMenu.add(mntmExample_2);


		GenerationText = new JLabel();
		GenerationText.setHorizontalAlignment(SwingConstants.CENTER);
		GenerationText.setFont(new Font("Papyrus", Font.BOLD, 16));

		GenerationText.setText("Generation: "+Generation);
		GridBagConstraints gbc_GenerationText = new GridBagConstraints();
		gbc_GenerationText.fill = GridBagConstraints.VERTICAL;
		gbc_GenerationText.anchor = GridBagConstraints.EAST;
		gbc_GenerationText.insets = new Insets(0, 0, 0, 5);
		gbc_GenerationText.gridx = 1;
		gbc_GenerationText.gridy = 0;
		Generation_Panel.add(GenerationText, gbc_GenerationText);

		PopulationText = new JLabel();
		PopulationText.setHorizontalAlignment(SwingConstants.CENTER);
		PopulationText.setFont(new Font("Papyrus", Font.BOLD, 16));

		PopulationText.setText("Population: "+Population);
		GridBagConstraints gbc_PopulationText = new GridBagConstraints();
		gbc_PopulationText.fill = GridBagConstraints.VERTICAL;
		gbc_PopulationText.gridx = 2;
		gbc_PopulationText.gridy = 0;
		Generation_Panel.add(PopulationText, gbc_PopulationText);


		Board_Panel = new JPanel();
		Board_Panel.setBackground(new Color(128, 128, 128));
		frame.getContentPane().add(Board_Panel, BorderLayout.CENTER);
		Board_Panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel Buttons_Panel = new JPanel();
		frame.getContentPane().add(Buttons_Panel, BorderLayout.PAGE_END);
		Buttons_Panel.setLayout(new GridLayout(1,3));

		Execute_Button = new JButton("-- EXECUTE --");
		Execute_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		Execute_Button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(Execute_Button.isEnabled()) {

					Stop_Button.setEnabled(true);
					Restart_Button.setEnabled(true);
					Execute_Button.setEnabled(false);
					thread = new Thread(window);
					thread.start();
				}
			}
		});
		Buttons_Panel.add(Execute_Button);

		Stop_Button = new JButton("Stop!");
		Stop_Button.setForeground(new Color(255, 0, 0));
		Stop_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		Stop_Button.setEnabled(false);
		Stop_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(Stop_Button.isEnabled()) {
					Stop=true;
					Stop_Button.setEnabled(false);
					Resume_Button.setEnabled(true);
				}
			}
		});

		Buttons_Panel.add(Stop_Button);

		Resume_Button = new JButton("Resume");
		Resume_Button.setForeground(new Color(0, 0, 255));
		Resume_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		Resume_Button.setEnabled(false);
		Resume_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(Resume_Button.isEnabled()) {
					Resume_Button.setEnabled(false);
					Stop_Button.setEnabled(true);
					if(Stop==true) {
						Stop=false;
						thread = new Thread(window);
						thread.start();
					}
				}
			}
		});

		Buttons_Panel.add(Resume_Button);

		Restart_Button = new JButton("Restart");
		Restart_Button.setForeground(new Color(128, 0, 128));
		Restart_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		Restart_Button.setEnabled(false);
		Restart_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(Restart_Button.isEnabled()) {

					Stop = true;
					Restart_board();
					JOptionPane.showMessageDialog(null, " GAME RESTARTED \n The board has been cleaned!");
					Stop=false;
				}
			}
		});

		Buttons_Panel.add(Restart_Button);

		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Method to set the initial configuration of the game when it is restarted
	 * It initialise the variables, clean the board and configure the buttons.
	 */
	public void Restart_board() {

		Restart_Button.setEnabled(false);	
		Stop_Button.setEnabled(false);
		Resume_Button.setEnabled(false);
		Generation = 0;
		Population = 0;
		GenerationText.setText("Generation: "+Generation);
		RefreshPopulation();
		Execute_Button.setEnabled(true);
		Clean_board();

	}

	/**
	 * Method to clean the board. 
	 * It sets the alive Cells to died Cells in order to have a clean board.
	 */
	public void Clean_board() {

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				if(BoardGame.getMatrixPosition(x, y).getState() == true) {

					BoardGame.getMatrixPosition(x, y).setState(false);
					BoardGame.getMatrixPosition(x, y).setNew_state(false);
					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.WHITE);

				}
			}
		}
	}

	/**
	 * Method to 'draw' the initial board.
	 * It creates the board using a GridLayout panel and a double for loop to go through
	 * the panel, adding a Cell object in each position.
	 */
	static public void Draw_Initial_Board(){

		Board_Panel.setLayout(new GridLayout(BoardGame.getRows(), BoardGame.getColumns(), 1, 1));

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				int xlocal = x;
				int ylocal = y;

				BoardGame.getMatrixPosition(xlocal, ylocal).getCell_panel().addMouseListener(new MouseAdapter() {

					/* This event make possible the interaction of the user with the board
					 * Clicking and setting the different cells 'alive' or 'dead'
					 * Alive = black background
					 * Dead = white background
					 */
					public void mousePressed(MouseEvent e) {			

						if (BoardGame.getMatrixPosition(xlocal, ylocal).getState() == false){

							BoardGame.getMatrixPosition(xlocal, ylocal).getCell_panel().setBackground(Color.BLACK);
							BoardGame.getMatrixPosition(xlocal, ylocal).setState(true);
							Population++;
							RefreshPopulation();


						}else {
							BoardGame.getMatrixPosition(xlocal, ylocal).getCell_panel().setBackground(Color.WHITE);
							BoardGame.getMatrixPosition(xlocal, ylocal).setState(false);
							Population--;
							RefreshPopulation();
						}
					}
				});		

				//Adding the cell to the panel
				Board_Panel.add(BoardGame.getMatrixPosition(xlocal, ylocal).getCell_panel());
			}
		}
	}

	/**
	 * Method to update the JtextField 'Population'
	 */
	public static void RefreshPopulation() {

		PopulationText.setText("Population: "+(Population));
	}

	/**
	 * Method with the main logic of the game.
	 * It goes through the board, position by position (double for loop), checking every cell and its alive neighboring cells.
	 * Depending on how many alive neighboring cells there are, the logic will determinate if the cell is born, 
	 * dies or keep alive.
	 */
	public void Execute_Game() {

		GenerationText.setText("Generation: "+(Generation));

		int AliveCells;

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				AliveCells=0;
				int xPosition = BoardGame.getMatrixPosition(x, y).getX();
				int yPosition = BoardGame.getMatrixPosition(x, y).getY();

				if(BoardGame.getMatrixPosition(xPosition, yPosition).getState() == true) { //ALIVE CELL
					
					//How many alive neighboring cells it has?
					AliveCells = GetAliveCells(x, y, (BoardGame.getRows()-1), (BoardGame.getColumns()-1));

					if(AliveCells<2 || AliveCells>3) {// Less than 2 or more than 3 alive neighboring cells --> THE CELL DIE 

						BoardGame.getMatrixPosition(xPosition, yPosition).getCell_panel().setBackground(Color.WHITE);
						BoardGame.getMatrixPosition(xPosition, yPosition).setNew_state(false);
						Population--;
						RefreshPopulation();

					}else {// Exactly 2 or 3 --> The cell is still alive

						BoardGame.getMatrixPosition(xPosition, yPosition).getCell_panel().setBackground(Color.BLACK);
						BoardGame.getMatrixPosition(xPosition, yPosition).setNew_state(true);

					}

				}else {//DEAD CELL

					//How many alive neighboring cells it has?
					AliveCells = GetAliveCells(x, y, (BoardGame.getRows()-1), (BoardGame.getColumns()-1));

					if(AliveCells==3) {// Exactly 3 --> The cell is born

						BoardGame.getMatrixPosition(xPosition, yPosition).getCell_panel().setBackground(Color.BLACK);
						BoardGame.getMatrixPosition(xPosition, yPosition).setNew_state(true);
						Population++;
						RefreshPopulation();

					}
				}		
			}
		}

		//Updating the state of the cells
		Update_Cell_States();

		//End of the generation and start of a new generation 
		Generation++;
	}

	/**
	 * Method to update the state of the cells.
	 * 'New_State' will become the current 'state'
	 */
	public void Update_Cell_States() {

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				BoardGame.getMatrixPosition(x, y).setState(BoardGame.getMatrixPosition(x, y).isNew_state());
			}
		}
	}


	/**
	 * Function to calculate how many alive neighboring a particular cell has.
	 * Depending on the position of the cell within the board, some of the neighbors will be checked.
	 * Avoiding issues with the 'ArrayOutOfBound' exceptions.
	 * @param row int value that indicates the row of the cell within the board
	 * @param col int value that indicates the column of the cell within the board
	 * @param MaxRow int value that indicates how many rows the board has
	 * @param MaxCol int value that indicates how many columns the board has
	 * @return The number of alive neighboring cells
	 */
	public int GetAliveCells(int row, int col, int MaxRow, int MaxCol) {

		int cont = 0;
		int r = row;
		int c = col;

		if(row==0) {//First row

			r++;
			if(BoardGame.getMatrixPosition(r,c).getState() == true) {
				cont++; //DOWN	
			}
			r=row;

			if(col==0) {//First column (So, first position)

				r++;
				c++;

				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-RIGN
				}
				r=row;
				c=col;

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT
				}
				c=col;

			}else if(col==MaxCol) {//Last position of first row

				r++;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-LEFT
				}
				r=row;
				c=col;

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;

			}else {

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT
				}
				c=col;

				r++;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-RIGN
				}
				r=row;
				c=col;

				r++;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-LEFT
				}
				r=row;
				c=col;

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;
			}	
		}else if (row==MaxRow) {//Last row

			r--;
			if(BoardGame.getMatrixPosition(r,c).getState() == true) {
				cont++; //UP
			}
			r=row;

			if(col==0) {//First position of last row

				r--;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-RIGHT
				}
				r=row;
				c=col;

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT				
				}
				c=col;

			}else if(col==MaxCol) {//Last position of board

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;

				r--;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-LEFT
				}
				r=row;
				c=col;

			}else {

				r--;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-RIGHT
				}
				r=row;
				c=col;

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT	
				}
				c=col;

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;

				r--;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-LEFT
				}
				r=row;
				c=col;

			}	
		}else {//If row != 0 and row != MaxRow (Any row in the middle)

			r--;
			if(BoardGame.getMatrixPosition(r,c).getState() == true) {
				cont++; //UP
			}
			r=row;

			r++;
			if(BoardGame.getMatrixPosition(r,c).getState() == true) {
				cont++; //DOWN
			}
			r=row;

			if(col == 0) {//The left edge of the board

				r--;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-RIGHT
				}
				r=row;
				c=col;

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT
				}
				c=col;

				r++;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-RIGN				
				}
				r=row;
				c=col;

			}else if (col == MaxCol) {//The right edge of the board

				r--;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-LEFT
				}
				r=row;
				c=col;

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;

				r++;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-LEFT
				}
				r=row;
				c=col;

			}else {//Any other cell

				r--;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-RIGHT
				}
				r=row;
				c=col;

				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //RIGHT
				}
				c=col;

				r++;
				c++;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-RIGN
				}
				r=row;
				c=col;

				r++;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //DOWN-LEFT
				}
				r=row;
				c=col;

				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //LEFT
				}
				c=col;

				r--;
				c--;
				if(BoardGame.getMatrixPosition(r,c).getState() == true) {
					cont++; //UP-LEFT
				}
				r=row;
				c=col;

			}
		}

		return cont;
	}

	/**
	 * Thread run.
	 * It contains the code the thread will execute
	 * The thread will be running inside a while loop until the user
	 * decide to stop the execution pressing Stop_Button.
	 */
	public void run() {
		try {
			while(Stop==false) {
				Execute_Game();
				Thread.sleep(100);
			}	
		} catch (InterruptedException e3) {

			e3.printStackTrace();
		}

	}

	/**
	 * Method to 'draw' the distribution of the example 1 in the board
	 * It goes through the board using a double for loop and setting alive the 
	 * required cells
	 */
	public void Draw_Example_1(){

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				if((x==16 && y==38) || (x==16 && y==40) || (x==17 && y==39) || (x==17 && y==40) || (x==18 && y==39) || (x==19 && y==78) || (x==20 && y==79)
						|| (x==20 && y==80) || (x==21 && y==37) || (x==22 && y==38) || (x==22 && y==81) || (x==23 && y==36) || (x==23 && y==37) 
						|| (x==23 && y==38) || (x==23 && y==42) || (x==23 && y==76) || (x==23 && y==80) || (x==24 && y==40) || (x==24 && y==41)
						|| (x==24 && y==47) || (x==24 && y==76) || (x==24 && y==78) || (x==24 && y==79) || (x==25 && y==41) || (x==25 && y==42)
						|| (x==25 && y==47) || (x==25 && y==49) || (x==25 && y==77) || (x==25 && y==85) || (x==25 && y==86) || (x==26 && y==47)
						|| (x==26 && y==48) || (x==26 && y==54) || (x==26 && y==84) || (x==27 && y==52) || (x==27 && y==53) || (x==27 && y==85)
						|| (x==27 && y==90) || (x==28 && y==35) || (x==28 && y==37) || (x==28 && y==46) || (x==28 && y==53) || (x==28 && y==54)
						|| (x==28 && y==73) || (x==28 && y==85) || (x==28 && y==89) || (x==29 && y==36) || (x==29 && y==37) || (x==29 && y==45)
						|| (x==29 && y==46) || (x==29 && y==71) || (x==29 && y==74) || (x==29 && y==86) || (x==29 && y==89) || (x==30 && y==28)
						|| (x==30 && y==29) || (x==30 && y==36) || (x==30 && y==45) || (x==30 && y==47) || (x==30 && y==71) || (x==30 && y==75)
						|| (x==30 && y==87) || (x==31 && y==29) || (x==31 && y==30) || (x==31 && y==70) || (x==31 && y==75) || (x==32 && y==28)
						|| (x==32 && y==34) || (x==32 && y==35) || (x==32 && y==76) || (x==33 && y==33) || (x==33 && y==35) || (x==33 && y==40)
						|| (x==33 && y==41) || (x==33 && y==74) || (x==33 && y==75) || (x==33 && y==83) || (x==34 && y==35) || (x==34 && y==41)
						|| (x==34 && y==42) || (x==34 && y==81) || (x==34 && y==82) || (x==34 && y==84) || (x==35 && y==40) || (x==35 && y==44)
						|| (x==35 && y==45) || (x==35 && y==46) || (x==35 && y==80) || (x==35 && y==84) || (x==36 && y==44) || (x==36 && y==79)
						|| (x==37 && y==45) || (x==38 && y==80) || (x==38 && y==81) || (x==39 && y==82) || (x==40 && y==43) || (x==41 && y==42)
						|| (x==41 && y==43) || (x==42 && y==42) || (x==42 && y==44)) {

					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.BLACK);
					BoardGame.getMatrixPosition(x, y).setState(true);
					Population++;


				}else {
					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.WHITE);
					BoardGame.getMatrixPosition(x, y).setState(false);

				}
			}
		}
		RefreshPopulation();
	}

	/**
	 * Method to 'draw' the distribution of the example 2 in the board
	 * It goes through the board using a double for loop and setting alive the 
	 * required cells
	 */
	public void Draw_Example_2(){

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				if((x==2 && y==54) || (x==2 && y==55) || (x==3 && y==54) || (x==3 && y==56) || (x==4 && y==56) || (x==4 && y==61) || (x==4 && y==62)
						|| (x==5 && y==52) || (x==5 && y==53) || (x==5 && y==54) || (x==5 && y==55) || (x==5 && y==57) || (x==5 && y==58) 
						|| (x==5 && y==61) || (x==5 && y==64) || (x==6 && y==52) || (x==6 && y==55) || (x==6 && y==57) || (x==6 && y==59) 
						|| (x==6 && y==61) || (x==6 && y==63) || (x==6 && y==64) || (x==7 && y==55) || (x==7 && y==57) || (x==7 && y==59) 
						|| (x==7 && y==61) || (x==8 && y==56) || (x==8 && y==57) || (x==8 && y==59) || (x==8 && y==61) || (x==9 && y==60) 
						|| (x==11 && y==46) || (x==11 && y==47) || (x==12 && y==47) || (x==12 && y==55) || (x==12 && y==56) || (x==13 && y==47)
						|| (x==13 && y==49) || (x==13 && y==55) || (x==13 && y==56) || (x==14 && y==48) || (x==14 && y==49) || (x==16 && y==63) 
						|| (x==17 && y==61) || (x==17 && y==63) || (x==17 && y==78) || (x==18 && y==62) || (x==18 && y==63) || (x==18 && y==76)
						|| (x==18 && y==77) || (x==18 && y==78) || (x==19 && y==75) || (x==20 && y==75) || (x==20 && y==76) || (x==21 && y==58) 
						|| (x==21 && y==59) || (x==22 && y==58) || (x==23 && y==59) || (x==23 && y==60) || (x==23 && y==61) || (x==23 && y==83)
						|| (x==23 && y==84) || (x==24 && y==35) || (x==24 && y==36) || (x==24 && y==61) || (x==24 && y==84) || (x==25 && y==36) 
						|| (x==25 && y==46) || (x==25 && y==47) || (x==25 && y==84) || (x==25 && y==86) || (x==25 && y==87) || (x==26 && y==34)
						|| (x==26 && y==45) || (x==26 && y==47) || (x==26 && y==76) || (x==26 && y==82) || (x==26 && y==83) || (x==26 && y==84)
						|| (x==26 && y==87) || (x==27 && y==34) || (x==27 && y==35) || (x==27 && y==36) || (x==27 && y==37) || (x==27 && y==38)
						|| (x==27 && y==47) || (x==27 && y==53) || (x==27 && y==54) || (x==27 && y==76) || (x==27 && y==77) || (x==27 && y==81)
						|| (x==27 && y==85) || (x==27 && y==86) || (x==28 && y==39) || (x==28 && y==53) || (x==28 && y==81) || (x==28 && y==82)
						|| (x==28 && y==84) || (x==29 && y==36) || (x==29 && y==37) || (x==29 && y==38) || (x==29 && y==51) || (x==29 && y==53) 
						|| (x==29 && y==67) || (x==29 && y==68) || (x==29 && y==84) || (x==30 && y==35) || (x==30 && y==51) || (x==30 && y==52) 
						|| (x==30 && y==66) || (x==30 && y==68) || (x==30 && y==81) || (x==30 && y==82) || (x==30 && y==83) || (x==31 && y==35) 
						|| (x==31 && y==37) || (x==31 && y==38) || (x==31 && y==66) || (x==31 && y==80) || (x==32 && y==33) || (x==32 && y==34) 
						|| (x==32 && y==38) || (x==32 && y==42) || (x==32 && y==43) || (x==32 && y==65) || (x==32 && y==66) || (x==32 && y==72) 
						|| (x==32 && y==81) || (x==32 && y==82) || (x==32 && y==83) || (x==32 && y==84) || (x==32 && y==85) || (x==33 && y==32) 
						|| (x==33 && y==35) || (x==33 && y==36) || (x==33 && y==37) || (x==33 && y==43) || (x==33 && y==72) || (x==33 && y==74) 
						|| (x==33 && y==85) || (x==34 && y==32) || (x==34 && y==33) || (x==34 && y==35) || (x==34 && y==72) || (x==34 && y==73) 
						|| (x==34 && y==83) || (x==35 && y==35) || (x==35 && y==58) || (x==35 && y==83) || (x==35 && y==84) || (x==36 && y==35) 
						|| (x==36 && y==36) || (x==36 && y==58) || (x==36 && y==59) || (x==36 && y==60) || (x==37 && y==61) || (x==38 && y==60)
						|| (x==38 && y==61) || (x==39 && y==43) || (x==39 && y==44) || (x==40 && y==44) || (x==41 && y==41) || (x==41 && y==42) 
						|| (x==41 && y==43) || (x==41 && y==56) || (x==41 && y==57)	|| (x==42 && y==41) || (x==42 && y==56) || (x==42 && y==58) 
						|| (x==43 && y==56) || (x==45 && y==70) || (x==45 && y==71)	|| (x==46 && y==63) || (x==46 && y==64) || (x==46 && y==70) 
						|| (x==46 && y==72)	|| (x==47 && y==63) || (x==47 && y==64) || (x==47 && y==72) || (x==48 && y==72) || (x==48 && y==73) 
						|| (x==50 && y==59) || (x==51 && y==58) || (x==51 && y==60) || (x==51 && y==62) || (x==51 && y==63) || (x==52 && y==58) 
						|| (x==52 && y==60)	|| (x==52 && y==62) || (x==52 && y==64) || (x==53 && y==55) || (x==53 && y==56) || (x==53 && y==58) 
						|| (x==53 && y==60)	|| (x==53 && y==62) || (x==53 && y==64) || (x==53 && y==67) || (x==54 && y==55) || (x==54 && y==58) 
						|| (x==54 && y==61)	|| (x==54 && y==62) || (x==54 && y==64) || (x==54 && y==65) || (x==54 && y==66) || (x==54 && y==67)
						|| (x==55 && y==57) || (x==55 && y==58) || (x==55 && y==63) || (x==56 && y==63) || (x==56 && y==65)	|| (x==57 && y==64) 
						|| (x==57 && y==65)) {

					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.BLACK);
					BoardGame.getMatrixPosition(x, y).setState(true);
					Population++;

				}else {
					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.WHITE);
					BoardGame.getMatrixPosition(x, y).setState(false);
				}

			}
		}
		RefreshPopulation();
	}

	/**
	 * Method to 'draw' the distribution of the example 3 in the board
	 * It goes through the board using a double for loop and setting alive the 
	 * required cells
	 */
	public void Draw_Example_3(){

		for (int x = 0; x<BoardGame.getRows(); x++) {
			for (int y = 0; y<BoardGame.getColumns(); y++) {

				if((x==7 && y==53) || (x==7 && y==66) || (x==8 && y==46) || (x==8 && y==47) || (x==8 && y==72) || (x==8 && y==73) || (x==9 && y==45)
						|| (x==9 && y==46) || (x==9 && y==47) || (x==9 && y==51) || (x==9 && y==52) || (x==9 && y==67) || (x==9 && y==68) 
						|| (x==9 && y==72) || (x==9 && y==73) || (x==9 && y==74) || (x==10 && y==51) || (x==10 && y==52) || (x==10 && y==54) 
						|| (x==10 && y==55) || (x==10 && y==64) || (x==10 && y==65) || (x==10 && y==67) || (x==10 && y==68) || (x==11 && y==53) 
						|| (x==11 && y==66) || (x==15 && y==39) || (x==15 && y==80) || (x==16 && y==38) || (x==16 && y==39) || (x==16 && y==80) 
						|| (x==16 && y==81) || (x==17 && y==38) || (x==17 && y==39) || (x==17 && y==80) || (x==17 && y==81) || (x==21 && y==39)
						|| (x==21 && y==40) || (x==21 && y==79) || (x==21 && y==80) || (x==22 && y==39) || (x==22 && y==40) || (x==22 && y==79) 
						|| (x==22 && y==80) || (x==23 && y==37) || (x==23 && y==41) || (x==23 && y==78) || (x==23 && y==82) || (x==24 && y==40)
						|| (x==24 && y==79) || (x==25 && y==40) || (x==25 && y==79) || (x==34 && y==79) || (x==34 && y==40) || (x==35 && y==79) 
						|| (x==35 && y==40) || (x==36 && y==82) || (x==36 && y==78) || (x==36 && y==41) || (x==36 && y==37) || (x==37 && y==80)
						|| (x==37 && y==79) || (x==37 && y==40) || (x==37 && y==39) || (x==38 && y==80) || (x==38 && y==79) || (x==38 && y==40) 
						|| (x==38 && y==39) || (x==42 && y==81) || (x==42 && y==80) || (x==42 && y==39) || (x==42 && y==38) || (x==43 && y==81)
						|| (x==43 && y==80) || (x==43 && y==39) || (x==43 && y==38) || (x==44 && y==80) || (x==44 && y==39) || (x==48 && y==66) 
						|| (x==48 && y==53) || (x==49 && y==68) || (x==49 && y==67) || (x==49 && y==65) || (x==49 && y==64) || (x==49 && y==55)
						|| (x==49 && y==54) || (x==49 && y==52) || (x==49 && y==51) || (x==50 && y==74) || (x==50 && y==73) || (x==50 && y==72) 
						|| (x==50 && y==68) || (x==50 && y==67) || (x==50 && y==52) || (x==50 && y==51) || (x==50 && y==47) || (x==50 && y==46)
						|| (x==50 && y==45) || (x==51 && y==73) || (x==51 && y==72) || (x==51 && y==47) || (x==51 && y==46) || (x==52 && y==66) 
						|| (x==52 && y==53)) {

					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.BLACK);
					BoardGame.getMatrixPosition(x, y).setState(true);
					Population++;
				}else {
					BoardGame.getMatrixPosition(x, y).getCell_panel().setBackground(Color.WHITE);
					BoardGame.getMatrixPosition(x, y).setState(false);
				}

			}
		}
		RefreshPopulation();
	}

}
