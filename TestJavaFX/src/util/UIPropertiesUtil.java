package util;

import java.util.ResourceBundle;

/**
 * simulator.propertiesを扱うためのユーティリティクラス
 * 
 * @author saito_naoko
 * @version $Revision$
 */
public class UIPropertiesUtil {
	/**
	 * プロパティファイルのkey値を定義したenumｸﾗｽです。<br>
	 * keyの追加削除等を行う場合はここを修正してください。
	 * 
	 * @author nagaki
	 * @version $Revision$
	 */
	public enum UIPropertieKey {
		WIZARD_TITLE("wizardTitle");

		private String key;

		private UIPropertieKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}

	/** domain properties file name */
	private static final String PROPERTIES = "ui";

	/** resource bundle */
	private static ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES);

	public static String get(UIPropertieKey key) {
		return bundle.getString(key.getKey());
	}

	public static String getString(UIPropertieKey key) {
		return bundle.getString(key.getKey());
	}

	public static String getString(UIPropertieKey key, String defaultValue) {
		String ret = getString(key);
		if (ret == null) {
			ret = defaultValue;
		}
		return ret;
	}

	public static ResourceBundle get() {
		return bundle;
	}
}
