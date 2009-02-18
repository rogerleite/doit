/**
 * 
 */
package org.oneupfordev.doit.packs;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;
import org.oneupfordev.doit.packs.descriptors.ArgumentType;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutConstructor;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutInnerCmds;
import org.oneupfordev.doit.packs.descriptors.ExpressionWithoutInnerCmds2;

/**
 * Test for loading and validating ExpressionPacks.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackLoaderByAnnotationTest {

	@Test
	public void load_of_expression_without_commands_and_empty_constructor() {

		PackLoader packLoader = new PackLoaderByAnnotation();
		RootCmdDescriptor descr = packLoader.validateAndLoad(ExpressionWithoutConstructor.class);
		assertNotNull("ExprCmdDescriptor cannot be null.", descr);
		assertEquals("expressionwithoutconstructor", descr.getName());
		assertNotNull("ArgumentType of ExprCmdDescriptor cannot be null.", descr.getArgumentType());
		assertEquals(ArgumentType.NO_ACCEPT, descr.getArgumentType());
	}

	@Test
	public void load_of_expression_without_innercmds_should_throw_exception() {
		validateOfExpression(ExpressionWithoutInnerCmds.class,
				ExpressionWithoutInnerCmds.class.getName(),
				"Inner command 'test' not found");
	}

	@Test
	public void load_of_expression_without_innercmds_deeptwo_should_throw_exception() {
		validateOfExpression(ExpressionWithoutInnerCmds2.class,
				ExpressionWithoutInnerCmds2.class.getName(),
				"Inner command 'testdeep2' not found");
	}

	@Test
	public void should_attributes_of_cmd_descriptor_is_ok() {
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
	public void load_of_invalid_expression_pack_should_throw_exception() {
		PackLoader pl = new PackLoaderByAnnotation();
		try {
			pl.load(null);
			fail("Load null have to throw an ExpressionIllegalArgumentException.");
		} catch (ExpressionIllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
		try {
			ExampleExpressionPack exPackWithNullExpressions = new ExampleExpressionPack("mock");
			pl.load(exPackWithNullExpressions);
			fail("Load with list null have to throw an ExpressionIllegalArgumentException.");
		} catch (ExpressionIllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
		try {
			ExampleExpressionPack exPackWithZeroExpressions = new ExampleExpressionPack(null, 
					new ArrayList<Class<? extends CallableExpression>>());
			pl.load(exPackWithZeroExpressions);
			fail("Load with list \"zero\" have to throw an ExpressionIllegalArgumentException.");
		} catch (ExpressionIllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}

		List<Class<? extends CallableExpression>> validList = new ArrayList<Class<? extends CallableExpression>>();
		validList.add(ExpressionValid.class);

		try {
			ExampleExpressionPack exPackWithNameNull = new ExampleExpressionPack(null, validList);
			pl.load(exPackWithNameNull);
			fail("Load with name null have to throw an ExpressionIllegalArgumentException.");
		} catch (ExpressionIllegalArgumentException ex) {
			assertTrue(ex.getMessage(), true);
		}
	}

	@Test
	public void should_pack_load_descriptors() {
		PackLoader pl = new PackLoaderByAnnotation();

		List<Class<? extends CallableExpression>> validList = new ArrayList<Class<? extends CallableExpression>>();
		validList.add(ExpressionValid.class);
		ExampleExpressionPack exPackOneValid = new ExampleExpressionPack("exPackOneValid", validList);
		ExprPackDescriptor packDescrValid = pl.load(exPackOneValid);
		assertEquals("exPackOneValid", packDescrValid.getName());
		assertEquals(true, packDescrValid.isOk());

		List<Class<? extends CallableExpression>> invalidList = new ArrayList<Class<? extends CallableExpression>>();
		invalidList.add(ExpressionWithoutInnerCmds2.class);
		ExampleExpressionPack exPackOneInvalid = new ExampleExpressionPack("exPackOneInvalid", invalidList);
		ExprPackDescriptor packDescrInvalid = pl.load(exPackOneInvalid);
		assertEquals("exPackOneInvalid", packDescrInvalid.getName());
		assertEquals(false, packDescrInvalid.isOk());

		List<Class<? extends CallableExpression>> sosoList = new ArrayList<Class<? extends CallableExpression>>();
		sosoList.add(ExpressionValid.class);
		sosoList.add(ExpressionWithoutInnerCmds2.class);
		ExampleExpressionPack exPack = new ExampleExpressionPack("exPack", sosoList);
		ExprPackDescriptor packDescr = pl.load(exPack);
		assertEquals("exPack", packDescr.getName());
		assertEquals(false, packDescr.isOk());
		assertEquals(1, packDescr.getErrors().size());
		assertEquals(1, packDescr.getDescriptors().size());
		assertEquals("expressionvalid", packDescr.getDescriptors().get(0).getName());
	}

}
