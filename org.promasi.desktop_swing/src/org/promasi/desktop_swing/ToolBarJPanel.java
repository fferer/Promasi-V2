/**
 * 
 */
package org.promasi.desktop_swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.DateTime;
import org.promasi.desktop_swing.application.QuickStartButton;
import org.promasi.utilities.file.RootDirectory;
import org.promasi.utils_swing.GuiException;

/**
 * @author alekstheod
 *
 */
public class ToolBarJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public static final int CONST_TASKBAR_HEIGHT = 30;
	
	/**
	 * 
	 */
	public static final String CONST_START_ICON = "startbutton.png";
	
	/**
	 * 
	 */
	private JButton _startButton;
	
	/**
	 * 
	 */
	public ClockJPanel _clockPanel;
	
	/**
	 * 
	 */
	private IDesktop _desktop;
	
	/**
	 * 
	 */
	private JPanel _quickMenuPanel;
	
	/**
	 * 
	 */
	private JPanel _quickButtonsJPanel;
	
	/**
	 * 
	 */
	public ToolBarJPanel( String username, IDesktop desktop )throws GuiException{
		super();
		
		if( username == null || username.isEmpty() ){
			throw new GuiException("Wrong argument username");
		}
		
		if( desktop == null ){
			throw new GuiException("Wrong argument desktop == null");
		}

		setPreferredSize( new Dimension( 100, CONST_TASKBAR_HEIGHT ) );
		setLayout(new BorderLayout());
		
		try {
			Icon startIcon = new ImageIcon(RootDirectory.getInstance().getImagesDirectory() + "marketplace.png");
			_startButton = new JButton("Menu");
			_startButton.setIcon(startIcon);
			_startButton.setIconTextGap(2);
			_startButton.setFont(new Font("Courier New", Font.BOLD, 15));
		} catch (IOException e1) {
			_startButton = new JButton("Menu");
		}
		
		_startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_desktop.showStartMenu();
			}
		});
		
		add(_startButton, BorderLayout.WEST);
		
		_quickMenuPanel = new JPanel();
		_quickMenuPanel.setLayout(new BorderLayout());
		_clockPanel = new ClockJPanel();
		
		JLabel exitButton = null;
		try {
			Icon exitIcon = new ImageIcon(RootDirectory.getInstance().getImagesDirectory() + "exit.png");
			exitButton = new JLabel(exitIcon);
			exitButton.setPreferredSize(new Dimension( exitIcon.getIconHeight(), exitIcon.getIconHeight() ));
			exitButton.setFocusable(false);
			exitButton.setBorder(BorderFactory.createEmptyBorder());
		} catch (IOException e1) {
			exitButton = new JLabel("Exit");
		}
		
		exitButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e){}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				_desktop.shutDown();
			}
		});
		
		_quickButtonsJPanel = new JPanel();
		_quickButtonsJPanel.setLayout(new FlowLayout());
		_quickButtonsJPanel.setBorder(BorderFactory.createEtchedBorder());
		_quickMenuPanel.add(exitButton, BorderLayout.EAST);
		_quickMenuPanel.add(_clockPanel, BorderLayout.CENTER);
		_quickMenuPanel.add(_quickButtonsJPanel, BorderLayout.WEST);
		
		add(_quickMenuPanel, BorderLayout.EAST);
		
		_desktop = desktop;
	}
	
	/**
	 * 
	 * @param dateTime
	 */
	public void updateTime( DateTime dateTime ){
		_clockPanel.updateTime(dateTime);
	}
	
	/**
	 * 
	 * @param button
	 * @return
	 */
	public boolean addQuickStartButton( QuickStartButton button ){
		boolean result = false;
		if( button != null ){
			_quickButtonsJPanel.add(button);
			result = true;
		}
		
		return result;
	}
}
