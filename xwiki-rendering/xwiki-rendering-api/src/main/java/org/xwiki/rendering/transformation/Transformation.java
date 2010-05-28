/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.transformation;

import org.xwiki.component.annotation.ComponentRole;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.XDOM;
import org.xwiki.rendering.syntax.Syntax;

/**
 * Performs a transformation on a Document's list of {@link org.xwiki.rendering.block.Block}. This used for example for
 * transforming Macro Blocks into other Blocks corresponding to the execution of the Macros. Another example of
 * transformation would be looking for all words that have an entry on Wikipedia and adding links to them.
 * 
 * @version $Id$
 * @since 1.5M2
 */
@ComponentRole
public interface Transformation extends Comparable<Transformation>
{
    /**
     * The priority of execution relative to the other transformations. The lowest values have the highest priorities
     * and execute first. For example a Transformation with a priority of 100 will execute before one with a priority of
     * 500.
     * 
     * @return the execution priority
     */
    int getPriority();

    /**
     * Look for all Macro Blocks in the passed DOM, find the corresponding macros (using the passed Syntax since Macro
     * components are registered against a given Syntax), execute them and replace the Macro Blocks with the Blocks
     * generated by the Macro executions.
     * 
     * @param dom the AST representing the page in Blocks
     * @param syntax the Syntax for which to do the transformation. This is required since Macro components are
     *            registered against syntaxes. For example "xwiki/2.0", "confluence/1.0", etc.
     * @throws TransformationException if the transformation fails for any reason
     * @deprecated since 2.4M1 use {@link #transform(Block, TransformationContext)} instead
     */
    @Deprecated
    void transform(XDOM dom, Syntax syntax) throws TransformationException;

    /**
     * Look for all Macro Blocks in the passed DOM, find the corresponding macros (using the passed Syntax since Macro
     * components are registered against a given Syntax), execute them and replace the Macro Blocks with the Blocks
     * generated by the Macro executions.
     * 
     * @param block the block to transform
     * @param context the context of the transformation process.
     * @throws TransformationException if the transformation fails for any reason
     * @since 2.4M1
     */
    void transform(Block block, TransformationContext context) throws TransformationException;
}
