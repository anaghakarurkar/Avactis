package storefront.utilityclasses;

//singleton class 
public final class BaseSingleton {

	private static Base properties;

	private BaseSingleton() {
	}

	public static Base getInstance(Base.Browser brName) {
		properties = new Base(brName);
		return properties;
	}

}
