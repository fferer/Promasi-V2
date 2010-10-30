package org.promasi.multiplayer.client.clientstate;

import java.io.IOException;
import java.net.ProtocolException;

import org.apache.commons.lang.NullArgumentException;
import org.promasi.model.ProjectManager;
import org.promasi.multiplayer.AbstractClientState;
import org.promasi.multiplayer.ProMaSiClient;
import org.promasi.network.protocol.client.request.RequestBuilder;
import org.promasi.network.protocol.client.response.InternalErrorResponse;
import org.promasi.network.protocol.client.response.LoginResponse;
import org.promasi.network.protocol.client.response.WrongProtocolResponse;
import org.promasi.shell.Shell;
import org.promasi.shell.playmodes.multiplayerscoremode.MultiPlayerScorePlayMode;

public class LoginClientState extends AbstractClientState {
	/**
	 * 
	 */
	private MultiPlayerScorePlayMode _currentPlayMode;
	
	private Shell _shell;
	
	/**
	 * 
	 * @param client
	 * @param projectManager
	 * @throws NullArgumentException
	 */
	public LoginClientState(Shell shell, MultiPlayerScorePlayMode playMode)throws NullArgumentException{
		if(playMode==null)
		{
			throw new NullArgumentException("Wrong argument playMode==null");
		}
		
		if(shell==null)
		{
			throw new NullArgumentException("Wrong argument shell==null");
		}
		
		_shell=shell;
		_currentPlayMode=playMode;
	}
	
	
	@Override
	public void onReceive(ProMaSiClient client, String recData) throws NullArgumentException 
	{
		if(client==null)
		{
			throw new NullArgumentException("Wrong argument client==null");
		}

		if(recData==null)
		{
			throw new NullArgumentException("Wrong argument client==null");
		}
		
		try
		{
			Object object=RequestBuilder.buildRequest(recData);
			if(object instanceof LoginResponse)
			{
				LoginResponse loginResponse=(LoginResponse)object;
				ProjectManager projectManager=loginResponse.getProjectManager();
				if(projectManager!=null)
				{
					try {
						JoinGameClientState chooseGameClientState=new JoinGameClientState(_shell, _currentPlayMode,projectManager);
						changeClientState(client,chooseGameClientState);
						if(!chooseGameClientState.sendRetreiveGameListRequest(client))
						{
							client.sendMessage(new InternalErrorResponse().toProtocolString());
							client.disconnect();
						}
						
						
						
					} catch (NullArgumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				client.sendMessage(new WrongProtocolResponse().toProtocolString());
				client.disconnect();
			}
		}
		catch(ProtocolException e)
		{
			client.sendMessage(new WrongProtocolResponse().toProtocolString());
			client.disconnect();
		}
		catch(NullArgumentException e)
		{
			client.sendMessage(new InternalErrorResponse().toProtocolString());
			client.disconnect();
		}
		catch(IllegalArgumentException e)
		{
			client.sendMessage(new InternalErrorResponse().toProtocolString());
			client.disconnect();
		}
	}

}
