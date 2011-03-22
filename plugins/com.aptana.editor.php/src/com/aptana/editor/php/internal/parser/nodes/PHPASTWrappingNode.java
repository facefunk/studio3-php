/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.php.internal.parser.nodes;

import org2.eclipse.php.internal.core.ast.nodes.Program;

import com.aptana.editor.php.internal.parser.PHPMimeType;
import com.aptana.parsing.ast.ParseNode;

/**
 * A simple parse node that provides a wrapping capability of a {@link Program} (AST) that was generated by the PHP
 * parser.
 * 
 * @author Shalom Gibly <sgibly@aptana.com>
 */
public class PHPASTWrappingNode extends ParseNode
{

	private Program ast;

	/**
	 * Constructs a new https://aptana.lighthouseapp.com/projects/35272-studio/tickets/new
	 * 
	 * @param ast
	 *            A {@link Program} AST to set
	 */
	public PHPASTWrappingNode(Program ast)
	{
		super(PHPMimeType.MIME_TYPE);
		this.ast = ast;
	}

	/**
	 * @param ast
	 *            A {@link Program} AST to set
	 */
	public void setAST(Program ast)
	{
		this.ast = ast;
	}

	/**
	 * @return The {@link Program} AST
	 */
	public Program getAST()
	{
		return ast;
	}
}
