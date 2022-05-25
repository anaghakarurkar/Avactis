package utilities;


public class BaseObject {
	
private static Base baseObj;

	private BaseObject() {}
	
	public  static Base getInstance(Base.Browser browserName) {
		if(baseObj == null) {
			baseObj = new Base(browserName);
		}
		return baseObj;
	}
}
