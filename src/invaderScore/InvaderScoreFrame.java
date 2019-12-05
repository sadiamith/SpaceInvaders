package invaderScore;

import java.awt.Color;
import javax.swing.JFrame;

import model.GameInfoProvider;
import java.awt.BorderLayout;





/**
 * Create an Invader scores panel, with the invader scores, 
 * @param width   	the width of the panel
 * @param height  	the height of the panel
 * @param listener	the class listening for the event 
 *                	that signals the button was pressed
 */
public class InvaderScoreFrame extends JFrame 
{
	/**
	 * The object that provides information about the game.
	 */
	@SuppressWarnings("unused")
	private GameInfoProvider gameInfo;
	
	/** The printout of Invaders and their Scores*/
	@SuppressWarnings("unused")
	private String invaderScore;
	
	
	public InvaderScoreFrame(GameInfoProvider gameInfo)
	{
	setFocusableWindowState(false);
	setSize(400, 672);
	setLocation(805,0);
	setBackground(Color.BLACK);
	setLayout(new BorderLayout());
	this.gameInfo = gameInfo;
	
	
	InvaderScorePanel scorePanel = new InvaderScorePanel(gameInfo);
	this.add(scorePanel);
	this.setVisible(true);
	gameInfo.addObserver(scorePanel);
	

	
	}
	public static final long serialVersionUID = 1;



}
