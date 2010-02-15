package org.promasi.ui.promasiui.promasidesktop;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.promasi.ui.promasiui.promasidesktop.programs.AbstractProgram;
import org.promasi.ui.promasiui.promasidesktop.programs.evolutionbird.EvolutionBirdProgram;
import org.promasi.ui.promasiui.promasidesktop.programs.infogate.InfoGateProgram;
import org.promasi.ui.promasiui.promasidesktop.programs.marketplace.MarketPlaceProgram;
import org.promasi.ui.promasiui.promasidesktop.programs.planner.PlannerProgram;
import org.promasi.ui.promasiui.promasidesktop.resources.ResourceManager;


/**
 * 
 * The main toolbar of the {@link DesktopMainFrame}.
 * 
 * @author eddiefullmetal
 * 
 */
public class DesktopToolbar
        extends JToolBar
        implements ActionListener
{

    /**
     * The {@link ClockButton}.
     */
    private ClockButton _clock;

    /**
     * The {@link ProjectInfoButton}.
     */
    private ProjectInfoButton _projectInfoButton;

    /**
     * A Map that keeps a button->application name relationship.
     */
    private Map<JButton, AbstractProgram> _quickLaunchButtons;

    /**
     * All the {@link IToolbarListener}s.
     */
    private List<IToolbarListener> _listeners;

    /**
     * The panel that contains all the quick launch buttons.
     */
    private JPanel _quickLaunchPanel;

    /**
     * Initializes the object.
     */
    public DesktopToolbar( )
    {
        _quickLaunchButtons = new Hashtable<JButton, AbstractProgram>( );
        _listeners = new Vector<IToolbarListener>( );
        setFloatable( false );
        setBorder( BorderFactory.createEtchedBorder( ) );
        initializeComponents( );
        initializeLayout( );
        addQuickLaunch( new EvolutionBirdProgram( ) );
        addQuickLaunch( new MarketPlaceProgram( ) );
        addQuickLaunch( new PlannerProgram( ) );
        addQuickLaunch( new InfoGateProgram( ) );
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents ( )
    {
        _clock = new ClockButton( );
        _quickLaunchPanel = new JPanel( );
        _quickLaunchPanel.setBorder( BorderFactory.createEmptyBorder( ) );
        _quickLaunchPanel.setLayout( new MigLayout( new LC( ) ) );
        _projectInfoButton = new ProjectInfoButton( );
    }

    /**
     * Initializes the layout.
     */
    private void initializeLayout ( )
    {
        setLayout( new MigLayout( new LC( ).fill( ) ) );
        add( new JLabel( ResourceManager.getString( DesktopToolbar.class, "quickLaunch" ) ), new CC( ).dockWest( ) );
        add( new ExitButton( ), new CC( ).dockEast( ) );
        add( _clock, new CC( ).dockEast( ) );
        add( _projectInfoButton, new CC( ).dockEast( ) );
        add( _quickLaunchPanel, new CC( ).grow( ) );
    }

    /**
     * Adds a button to the quick launch.
     * 
     * @param program
     *            The {@link AbstractProgram}.
     */
    public void addQuickLaunch ( AbstractProgram program )
    {
        JButton button = program.getButton( );
        button.addActionListener( this );
        _quickLaunchButtons.put( button, program );
        _quickLaunchPanel.add( button, new CC( ) );
    }

    /**
     * @param listener
     *            The listener to add to the {@link #_listeners}.
     */
    public void addListener ( IToolbarListener listener )
    {
        if ( !_listeners.contains( listener ) )
        {
            _listeners.add( listener );
        }
    }

    /**
     * @param listener
     *            The listener to remove from the {@link #_listeners}.
     */
    public void removeListener ( IToolbarListener listener )
    {
        if ( _listeners.contains( listener ) )
        {
            _listeners.remove( listener );
        }
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        AbstractProgram program = _quickLaunchButtons.get( e.getSource( ) );
        if ( program != null )
        {
            for ( IToolbarListener listener : _listeners )
            {
                listener.quickLauchButtonClicked( program );
            }
        }
    }
}