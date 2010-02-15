package org.promasi.ui.promasiui.promasidesktop.programs.marketplace;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.promasi.model.Employee;
import org.promasi.shell.Shell;
import org.promasi.ui.promasiui.promasidesktop.programs.AbstractProgram;
import org.promasi.ui.promasiui.promasidesktop.resources.ResourceManager;

import com.jidesoft.swing.JideButton;


/**
 * 
 * Program for browsing and hiring available employees.
 * 
 * @author eddiefullmetal
 * 
 */
public class MarketPlaceProgram
        extends AbstractProgram
{

    /**
     * The {@link JList} that contains all the available employees.
     */
    private JList _employeeList;

    /**
     * Button that when clicked it hires the selected employee.
     */
    private JideButton _hireButton;

    /**
     * Initializes the object.
     */
    public MarketPlaceProgram( )
    {
        super( "marketplace", "Marketplace, browse and hire employees" );
        initializeComponents( );
        initializeLayout( );
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents ( )
    {
        _employeeList = new JList( Shell.getInstance( ).getAllEmployees( ).toArray( ) );
        _employeeList.setCellRenderer( new MarketPlaceEmployeeListRenderer( ) );
        // Due to nimbus look and feel if this is not done the empty space from
        // the list will be white.
        Color backgroundColor = getBackground( );
        _employeeList.setBackground( new Color( backgroundColor.getRed( ), backgroundColor.getGreen( ), backgroundColor.getBlue( ) ) );
        // Create the hireButton.
        _hireButton = new JideButton( ResourceManager.getString( MarketPlaceProgram.class, "hireButton", "text" ) );
        _hireButton.addActionListener( new ActionListener( )
        {

            @Override
            public void actionPerformed ( ActionEvent e )
            {
                hireEmployee( );
            }

        } );
    }

    /**
     * Initializes the layout.
     */
    private void initializeLayout ( )
    {
        setLayout( new MigLayout( new LC( ).fillX( ) ) );
        JScrollPane scrollPane = new JScrollPane( _employeeList );
        scrollPane.setBorder( BorderFactory.createEmptyBorder( ) );
        add( _hireButton, new CC( ).alignX( "center" ).wrap( ) );
        add( scrollPane, new CC( ).growX( ).alignX( "center" ) );
    }

    /**
     * Hires the selected employee from the {@link #_employeeList}.
     */
    private void hireEmployee ( )
    {
        Employee employee = (Employee) _employeeList.getSelectedValue( );
        if ( employee != null )
        {
            if ( !employee.isHired( ) )
            {
                Shell.getInstance( ).hireEmployee( employee );
                invalidate( );
                repaint( );
                validate( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, ResourceManager.getString( MarketPlaceProgram.class, "employeeAlreadyHired", "text" ),
                        ResourceManager.getString( MarketPlaceProgram.class, "employeeAlreadyHired", "title" ), JOptionPane.WARNING_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, ResourceManager.getString( MarketPlaceProgram.class, "noSelectedEmployee", "text" ), ResourceManager
                    .getString( MarketPlaceProgram.class, "noSelectedEmployee", "title" ), JOptionPane.WARNING_MESSAGE );
        }
    }

    @Override
    public void closed ( )
    {

    }

    @Override
    public Icon getIcon ( )
    {
        return ResourceManager.getIcon( getName( ) );
    }

    @Override
    public void opened ( )
    {

    }

}