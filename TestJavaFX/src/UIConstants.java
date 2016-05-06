import java.awt.Image;
import java.awt.Toolkit;

/**
 * StringStaticsクラス
 * 
 * @author yanagisawa
 * @version $Revision$
 */
public class UIConstants {

	static final public String COMMNA = ",";

	static final public String NULL = "null";

	static final public String NOTNULL = "not null";

	static final public String TRUE = "true";

	static final public String FALSE = "false";

	/* 中間ファイルの出力先フォルダ名 */
	static final public String OUT_DIR_LOG = "log";

	/* 原本XMLのコピー出力フォルダ名 */
	static final public String OUT_DIR_GENPON_XML = "genponXml";

	/* DB初期データ(UI1ページ目)のコピー出力フォルダ名 */
	static final public String OUT_DIR_INIT_DB = "initDB";

	/* inputXMLのコピー出力フォルダ名 */
	static final public String OUT_DIR_INPUT_XML = "inputXml";

	/* ViSC本体のコピー出力フォルダ名 */
	static final public String OUT_DIR_MAIN_VISC = "mainViSC";

	/* 差分比較用XMLのコピー出力フォルダ名 */
	static final public String OUT_DIR_DIFF_REPORT_XML = "diffReportXml";

	/* SR原本XMLのコピー出力フォルダ名 */
	static final public String OUT_DIR_SR_GENPON_XML = "srGenponXml";

	/* スタブデータのコピー出力フォルダ名 */
	static final public String OUT_DIR_STUB_DATA = "stubData";

	/* 定数定義書のコピー出力フォルダ名 */
	static final public String OUT_DIR_CONSTANT_VALUES = "constantValues";

	/* validationファイルのコピー出力フォルダ名 */
	static final public String OUT_DIR_VALIDATION = "validation";

	/* DB初期データ(UI2ページ目：ケース毎)のコピー出力フォルダ名 */
	static final public String OUT_DIR_CASE_INIT_DB = "caseInitDb";

	static final public Image ICON = Toolkit.getDefaultToolkit().getImage(
			"icon/appbar.tux.png");
}
