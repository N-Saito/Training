package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UIUtil {
	// private static final Logger log = Logger.getLogger(UIUtil.class);
	//
	// /**
	// * 原本XMLからVilを生成する
	// * @param genponXmlPath
	// * @param vilFilePath
	// * @param outputDir
	// * @return
	// * @throws Exception
	// */
	// public static PGroup parseVilFile(String genponXmlPath, String
	// vilFilePath,
	// String outputDir) throws TranslatorException, ParserException {
	// Translator translator = new Translator();
	// translator.addXmlFile(genponXmlPath, outputDir, true,
	// Lang.Encoding.MS932);
	// translator.execute();
	// Parser parser = new Parser();
	// parser.addVilFile(vilFilePath);
	// return parser.execute();
	// }
	//
	// /**
	// * @param collect
	// * @param vilFilePath
	// * @return
	// */
	// public static String saveInputXML(SimDataCollect collect, String
	// vilFilePath) {
	// File vilFile = new File(vilFilePath);
	// return saveInputXML(collect, vilFile);
	// }
	//
	// /**
	// * InputXMLを保存する
	// * @param collect InputXMLのオブジェクトデータ
	// * @param vilFile vilファイル：vilファイルと同じ場所に出力するため
	// * @return
	// */
	// public static String saveInputXML(SimDataCollect collect, File vilFile) {
	// File dir = vilFile.getParentFile();
	// String name = vilFile.getName();
	// name = name.substring(0, name.length() - ".xml".length());
	//
	// String inputXmlPath = dir + "\\" + UIConstants.OUT_DIR_INPUT_XML;
	// File inputXmlDir = new File(inputXmlPath);
	// if (!inputXmlDir.exists()) {
	// inputXmlDir.mkdir();
	// }
	// File inputXmlFile = new File(inputXmlPath, name + "_SimData.xml");
	//
	// JAXB.marshal(collect, inputXmlFile);
	//
	// return inputXmlFile.getAbsolutePath();
	// }
	//
	// /**
	// * vilファイルをロードして、PGroupを返却する
	// * @param absVilFilePath vilファイルの絶対パス
	// * @return
	// */
	// public static PGroup loadVilFile(String absVilFilePath) {
	// File vilFile = new File(absVilFilePath);
	// return loadVilFile(vilFile);
	// }
	//
	// /**
	// * vilファイルをロードしてPGroupを返却
	// * @param vilFile
	// * @return
	// */
	// public static PGroup loadVilFile(File vilFile) {
	// try {
	// Parser parser = new Parser();
	// parser.addVilFile(vilFile.getAbsolutePath());
	// return parser.execute();
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }
	//
	// /**
	// * InputXMLをロードする
	// * @param xmlPath
	// * @return {@link SimDataCollect}
	// */
	// public static SimDataCollect loadInputXML(String xmlPath) {
	// if (!(xmlPath == null || xmlPath.isEmpty())) {
	// File file = new File(xmlPath);
	// if (file.exists()) {
	// try {
	// SimDataCollect simData = SimUtil.readXML(
	// SimDataCollect.class, new File(xmlPath));
	// return simData;
	// } catch (SimulatorException e) {
	// log.error(e.getLocalizedMessage(), e);
	// throw new RuntimeException(String.format(
	// "InputXML:%sの読み込みに失敗しました。", xmlPath));
	// }
	// }
	// }
	// return null;
	// }

	/**
	 * 処理IDを取得する
	 * 
	 * @param genponXmlPath
	 * @return
	 */
	public static String getSyoriId(String genponXmlPath) {
		String syoriId = "";
		if (!(genponXmlPath == null || genponXmlPath.isEmpty())) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document doc = builder.parse(new FileInputStream(new File(
						genponXmlPath)));

				NodeList nodeList = doc.getElementsByTagName("Sheet");

				Node cfNode = getNode(nodeList, "Name", "CF");

				if (cfNode != null) {
					NodeList children = cfNode.getChildNodes();
					for (int i = 0; i < children.getLength(); i++) {
						Node node = children.item(i);
						if (!node.getNodeName().equals("Data")) {
							continue;
						}
						syoriId = getAttribute(node, "CFGyoid");
					}
				}

			} catch (Exception e) {
				// log.error(e.getLocalizedMessage(), e);
			}

		}
		return syoriId;
	}

	/**
	 * NodeListから指定したAttribute名と値を持つNodeを返却する
	 * 
	 * @param nodeList
	 * @param name
	 * @param value
	 * @return
	 */
	private static Node getNode(NodeList nodeList, String name, String value) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			if (hasAttribute(item, name, value)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * ノードから指定したAttribute名と値を持つかを判定する
	 * 
	 * @param node
	 * @param name
	 * @param value
	 * @return
	 */
	private static boolean hasAttribute(Node node, String name, String value) {
		String attrValue = getAttribute(node, name);
		if (attrValue != null && attrValue.equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * ノードから指定したAttribute名の値を取得
	 * 
	 * @param node
	 * @param name
	 * @return
	 */
	private static String getAttribute(Node node, String name) {
		if (!node.hasAttributes()) {
			return "";
		}
		NamedNodeMap attrMap = node.getAttributes();
		for (int j = 0; j < attrMap.getLength(); j++) {
			Node attr = attrMap.item(j);
			if (!(attr instanceof Attr)) {
				continue;
			}
			if (((Attr) attr).getName().equals(name)) {
				return ((Attr) attr).getValue();
			}
		}
		return null;
	}

	/**
	 * ファイルコピー（単体）をする
	 * 
	 * @param copyFilePath
	 *            コピー対象のファイルパス(拡張子付き)
	 * @param copyToPath
	 *            コピー先のディレクトリパス
	 */
	public static void copyFile(String copyFilePath, String copyToPath) {
		File copyFrom = new File(copyFilePath);
		File copyTo = new File(copyToPath, copyFrom.getName());
		FileOutputStream copyToStream;
		try {
			copyToStream = new FileOutputStream(copyTo);
			Files.copy(copyFrom.toPath(), copyToStream);
		} catch (IOException e) {
			// log.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * ファイルコピー(複数)をする
	 * 
	 * @param copyFilesPath
	 *            コピー対象のファイルパス群(拡張子付き)
	 */
	public static void copyFiles(List<String> copyFilesPaths, String copyToPath) {
		for (String copyFilePath : copyFilesPaths) {
			copyFile(copyFilePath, copyToPath);
		}
	}

	/**
	 * カレントディレクトリのパスを返します。
	 * 
	 * @return
	 */
	public static String getCurrentDir() {
		String path = "";
		File f = new File("");
		try {
			path = f.getCanonicalPath();
		} catch (IOException e) {
			// log.error(e.getLocalizedMessage(), e);
		}
		return path;
	}

	public static String getParentDir(String path) {
		String ret = "";
		if (path != null && !path.equals("")) {
			File f = new File(path);
			File dir = f.getParentFile();
			try {
				if (dir != null) {
					ret = dir.getCanonicalPath();
				}
			} catch (IOException e) {
				// log.error(e.getLocalizedMessage(), e);
			}
		} else {
			ret = getCurrentDir();
		}
		return ret;
	}

	public static String getParentDir(JList<Object> jList) {
		ListModel<Object> lm = jList.getModel();
		String path = "";
		if (lm.getSize() > 0) {
			path = lm.getElementAt(0).toString();
		}
		String ret = getParentDir(path);
		return ret;
	}
}
