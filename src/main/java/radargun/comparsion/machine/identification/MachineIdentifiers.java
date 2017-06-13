package radargun.comparsion.machine.identification;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Throwables;

/**
 * @author Sören Henning
 *
 */
public final class MachineIdentifiers {

	private static final String IDENTIFIER_PATH = "radargun.comparsion.machine.identification";

	private MachineIdentifiers() {
	}

	public static MachineIdentifier getByClassName(final String name, final String[] parameters) {
		if (name.indexOf('.') < 0) {
			// It seems that just the class name were passed
			try {
				return getByFullClassName(IDENTIFIER_PATH + '.' + name, parameters);
			} catch (final IllegalArgumentException exception) {
				return getByFullClassName(name, parameters);
			}
		} else {
			// It seems that a fully qualified name were passed
			try {
				return getByFullClassName(name, parameters);
			} catch (final IllegalArgumentException exception) {
				return getByFullClassName(IDENTIFIER_PATH + '.' + name, parameters);
			}
		}
	}

	public static MachineIdentifier getByFullClassName(final String name, final String[] parameters) {

		try {
			// get machine identifier class by using reflection
			final Class<?> identifierClass = Class.forName(name);

			final Class<?>[] constructorParameterClasses = new Class[] { String[].class };
			final Object[] constructorParameterObjects = new Object[] { parameters };

			final Constructor<?> constructor = identifierClass.getConstructor(constructorParameterClasses);

			return (MachineIdentifier) constructor.newInstance(constructorParameterObjects);
		} catch (final ClassNotFoundException e) {
			throw new IllegalArgumentException("The Machine Identifier \"" + name + "\" could not be found.", e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException(
					"The Machine Identifier \"" + name + "\" is declared as abstract and cannot be instantiated", e);
		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("The constructor of \"" + name + "\" could not be accessed.", e);
		} catch (final IllegalArgumentException e) {
			// should not happen at all
			throw new IllegalArgumentException(
					"The constructor of \"" + name + "\" has not been called with the correct amount of arguments.", e);
		} catch (final InvocationTargetException e) {
			throw new IllegalArgumentException("The constructor of \"" + name + "\" has thrown an exception:\n"
					+ Throwables.getStackTraceAsString(e), e);
		} catch (final NoSuchMethodException e) {
			throw new IllegalArgumentException("The Machine Identifier \"" + name
					+ "\" does not have any constructor with an string array as its own parameter.", e);
		} catch (final SecurityException e) {
			throw new IllegalArgumentException(
					"A Security Manager is present and \"" + name + "\"does not have the correct class loader.", e);
		}
	}

}