package de.soerenhenning.perftest.test.yaml;

public class YamlParsingException extends Exception {

	private static final long serialVersionUID = 6858720157639850134L;

	public YamlParsingException() {
		super();
	}

	public YamlParsingException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public YamlParsingException(final String message) {
		super(message);
	}

	public YamlParsingException(final Throwable cause) {
		super(cause);
	}

}
