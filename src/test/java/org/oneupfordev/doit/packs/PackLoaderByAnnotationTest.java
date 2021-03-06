/*
* This file is part of DoIt.
* 
* DoIt is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.

* DoIt is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public License
* along with DoIt.  If not, see <http://www.gnu.org/licenses/>.
* 
* Copyright 2009 Roger Leite
 */

/**
 * 
 */
package org.oneupfordev.doit.packs;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;
import org.oneupfordev.doit.packs.descriptors.ArgumentType;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutConstructor;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutInnerCmds;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutInnerCmds2;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Test for loading and validating ExpressionPacks.
 * @author Roger Leite
 */
public class PackLoaderByAnnotationTest {

	private Class<?>[] getValidClass() {
		return new Class<?>[] {ExpressionValid.class};
	}

	@Test
	public void loadOfExpressionWithoutCommandsAndEmptyConstructor() {

		PackLoader packLoader = new PackLoaderByAnnotation();
		RootCmdDescriptor descr = packLoader.validateAndLoad(ExpressionWithoutConstructor.class);
		assertNotNull("ExprCmdDescriptor cannot be null.", descr);
		assertEquals("expressionwithoutconstructor", descr.getName());
		assertNotNull("ArgumentType of ExprCmdDescriptor cannot be null.", descr.getArgumentType());
		assertEquals(ArgumentType.NO_ACCEPT, descr.getArgumentType());
	}

	@Test
	public void loadOfExpressionWithoutInnercmdsShouldThrowException() {
		validateOfExpression(ExpressionWithoutInnerCmds.class,
				ExpressionWithoutInnerCmds.class.getName(),
				"Inner command 'test' not found");
	}

	@Test
	public void loadOfExpressionWithoutInnercmdsDeeptwoShouldThrowException() {
		validateOfExpression(ExpressionWithoutInnerCmds2.class,
				ExpressionWithoutInnerCmds2.class.getName(),
				"Inner command 'testdeep2' not found");
	}

	@Test
	public void shouldAttributesOfCmdDescriptorIsOk() {
		PackLoader packLoader = new PackLoaderByAnnotation();
		RootCmdDescriptor descr = packLoader.validateAndLoad(ExpressionValid.class);
		assertNotNull("ExprCmdDescriptor cannot be null.", descr);
		assertEquals("expressionvalid", descr.getName());
		assertNotNull("ArgumentType cannot be null.", descr.getArgumentType());
		assertEquals(ArgumentType.OPTIONAL, descr.getArgumentType());

		org.oneupfordev.doit.packs.descriptors.CmdDescriptor cmdTest = descr.getInnerCmd(0);
		assertNotNull("ExprCmdDescriptor cannot be null.", cmdTest);
		assertEquals("test", cmdTest.getName());
		assertNotNull("ArgumentType cannot be null.", cmdTest.getArgumentType());
		assertEquals(ArgumentType.NO_ACCEPT, cmdTest.getArgumentType());

		org.oneupfordev.doit.packs.descriptors.CmdDescriptor cmdTestInner = cmdTest.getInnerCmd(0);
		assertNotNull("ExprCmdDescriptor cannot be null.", cmdTestInner);
		assertEquals("testinner", cmdTestInner.getName());
		assertNotNull("ArgumentType cannot be null.", cmdTestInner.getArgumentType());
		assertEquals(ArgumentType.OPTIONAL, cmdTestInner.getArgumentType());

		org.oneupfordev.doit.packs.descriptors.CmdDescriptor cmdTestInner2 = cmdTest.getInnerCmd(1);
		assertNotNull("ExprCmdDescriptor cannot be null.", cmdTestInner2);
		assertEquals("testinner2", cmdTestInner2.getName());
		assertNotNull("ArgumentType cannot be null.", cmdTestInner2.getArgumentType());
		assertEquals(ArgumentType.REQUIRED, cmdTestInner2.getArgumentType());
	}

	private void validateOfExpression(Class<? extends CallableExpression> clazz, String nameClass, String messageExStartsWith) {
		try {
			packLoaderValidate(clazz);
			fail("Validate of " + nameClass + " do not throw any Exception.");
		} catch (ExpressionNotValidException notValidEx) {
			String m = "Exception message not startsWith(\"" + messageExStartsWith + "\")";
			assertTrue(m, notValidEx.getMessage().startsWith(messageExStartsWith));
		} catch (Throwable t) {
			fail("Validate of " + nameClass + " throw an unexpected Exception: " + t);
		}
	}

	private void packLoaderValidate(Class<? extends CallableExpression> clazz) {
		PackLoader ml = new PackLoaderByAnnotation();
		ml.validateAndLoad(clazz);
	}

	@Test
	public void loadOfInvalidExpressionPackShouldThrowException() {
		PackLoader pl = new PackLoaderByAnnotation();
		try {
			pl.load(null);
			fail("Load null have to throw an ExpressionIllegalArgumentException.");
		} catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
		try {
			ExampleExpressionPack exPackWithNullExpressions = new ExampleExpressionPack("mock");
			pl.load(exPackWithNullExpressions);
			fail("Load with list null have to throw an ExpressionIllegalArgumentException.");
		} catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
		try {
			ExampleExpressionPack exPackWithZeroExpressions = new ExampleExpressionPack(null, new Class<?>[] {});
			pl.load(exPackWithZeroExpressions);
			fail("Load with list \"zero\" have to throw an ExpressionIllegalArgumentException.");
		} catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}

		try {
			ExampleExpressionPack exPackWithNameNull = new ExampleExpressionPack(null, getValidClass());
			pl.load(exPackWithNameNull);
			fail("Load with name null have to throw an ExpressionIllegalArgumentException.");
		} catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
	}

	@Test
	public void shouldPackLoadDescriptors() {
		PackLoader pl = new PackLoaderByAnnotation();

		ExampleExpressionPack exPackOneValid = new ExampleExpressionPack("exPackOneValid", getValidClass());
		ExprPackDescriptor packDescrValid = pl.load(exPackOneValid);
		assertEquals("exPackOneValid", packDescrValid.getName());
		assertEquals(true, packDescrValid.isOk());

		ExampleExpressionPack exPackOneInvalid = new ExampleExpressionPack("exPackOneInvalid", new Class<?>[] {ExpressionWithoutInnerCmds2.class});

		ExprPackDescriptor packDescrInvalid = pl.load(exPackOneInvalid);
		assertEquals("exPackOneInvalid", packDescrInvalid.getName());
		assertEquals(false, packDescrInvalid.isOk());

		ExampleExpressionPack exPack = new ExampleExpressionPack("exPack", new Class<?>[] {ExpressionValid.class, ExpressionWithoutInnerCmds2.class});
		ExprPackDescriptor packDescr = pl.load(exPack);
		assertEquals("exPack", packDescr.getName());
		assertEquals(false, packDescr.isOk());
		assertEquals(1, packDescr.getErrors().size());
		assertEquals(1, packDescr.getDescriptors().size());
		assertEquals("expressionvalid", packDescr.getDescriptors().get(0).getName());
	}

}
