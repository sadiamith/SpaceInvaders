package invaderScore;

import java.awt.BorderLayout;


import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import view.ViewPanel;

import model.GameInfoProvider;
import model.GameObserver;

/**
 * A panel to display the Invader scores
 */
public class InvaderScorePanel extends ViewPanel implements GameObserver
{
	static final int FONT_SIZE = 72;

	public static final long serialVersionUID = 1;
	
	/**
	 * The object that provides information about the game.
	 */
	private GameInfoProvider gameInfo;
	
	/** The printout of Invaders and their Scores*/
	private String invaderScore;
	
	/** A JTextArea to hold my scores. */
	private JTextArea scoreArea;


	public InvaderScorePanel(GameInfoProvider gameInfo)
	{
		JPanel scorePanel = new JPanel();
		add(scorePanel,BorderLayout.CENTER);
		scoreArea = new JTextArea(invaderScore,40,30);
		scoreArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(scoreArea);
		scorePanel.add(scrollPane);
		this.gameInfo = gameInfo;
	}

	@Override
	public void gameChanged() 
	{
		List<model.InvaderScore> invaderList;
		invaderList = gameInfo.getInvaderScore();
		Collections.sort(invaderList);
		invaderScore = "Invader(Row,Column):Score\n";
		invaderScore = invaderScore + invaderList.toString();
		scoreArea.setText(invaderScore);
	}
	


}
