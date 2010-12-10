package org.eclipse.php.internal.debug.core.launching;

import java.net.MalformedURLException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.php.internal.debug.core.IPHPDebugConstants;

/**
 * @author Pavel Petrochenko
 *
 */
public final class ScriptLocator
{

	private ScriptLocator(){
		
	}
	
	
	/**
	 * @param configuration
	 * @return
	 * @throws CoreException 
	 */
	public static String getScriptFile(ILaunchConfiguration configuration) throws CoreException{
		boolean attribute = configuration.getAttribute(IPHPDebugConstants.ATTR_USE_SPECIFIC_FILE, false);
		if (attribute){
			return configuration.getAttribute(IPHPDebugConstants.ATTR_FILE, (String) null);
		}
		try
		{
			IResource currentEditorResource = new ScriptLocator().getCurrentEditorResource();
			if (currentEditorResource==null){
				return null;
			}
			String string = currentEditorResource.getFullPath().toOSString();			
			return string;
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException();
		}
	}
	
	/**
	 * Gets script file name.
	 * @param configuration - configuration.
	 * @param fileConfKey - file attribute configuration key.
	 * @return script file name.
	 * @throws CoreException  IF core exception occurs
	 */
	public static String getScriptFile(ILaunchConfiguration configuration, String fileConfKey)
		throws CoreException
	{
		boolean attribute = configuration.getAttribute(IPHPDebugConstants.ATTR_USE_SPECIFIC_FILE, false);
		if (attribute){
			return configuration.getAttribute(fileConfKey, (String) null);
		}
		try
		{
			IResource currentEditorResource = new ScriptLocator().getCurrentEditorResource();
			if (currentEditorResource==null){
				return null;
			}
			String string = currentEditorResource.getFullPath().toOSString();			
			return string;
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException();
		}
	}
	
	/**
	 * @return
	 * @throws MalformedURLException
	 */
	protected IResource getCurrentEditorResource() throws MalformedURLException {
		IActiveResourcePathGetterAdapter adapter = (IActiveResourcePathGetterAdapter) getContributedAdapter(IActiveResourcePathGetterAdapter.class);
		if ( adapter != null ) {
			return adapter.getActiveResource();
		}
		return null;
	}

	/**
	 * @return
	 * @throws MalformedURLException
	 */
	protected IPath getCurrentEditorPath() throws MalformedURLException {
		IActiveResourcePathGetterAdapter adapter = (IActiveResourcePathGetterAdapter) getContributedAdapter(IActiveResourcePathGetterAdapter.class);
		if ( adapter != null ) {
			return adapter.getActiveResourcePath();
		}
		return null;
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	protected Object getContributedAdapter( Class clazz ) {		
		return new ActiveResourcePathGetterAdapter();
	}
}