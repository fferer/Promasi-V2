package org.promasi.shell.ui.playmode;


import org.promasi.shell.UiManager;


/**
 * 
 * Initializes the UI. Registers components to the shell.
 * 
 * @author eddiefullmetal
 * 
 */
public interface IPlayUiModeInitializer
{

    /**
     * Registers the {@link ILoginUi} of the play mode to the {@link UiManager}.
     */
    void registerLoginUi ( );

    /**
     * Registers the {@link IProjectFinishedUi} of the play mode to the
     * {@link UiManager}.
     */
    void registerProjectFinishedUi ( );
}