 package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.LogUtil;

/**
 * DAO(オートコミット)の基底クラス
 */
public class BaseDao {

    /** JDBCドライバ名 */
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /*"oracle.jdbc.driver.OracleDriver";*/

    /** DBの接続先URL */
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/customersystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            /*"jdbc:oracle:thin:@localhost:1521:XE";*/

    /** DBへ接続時のユーザーID */
    private static final String CONNECTION_USER = "root";/*"customermanager";*/

    /** DBへ接続時のパスワード */
    private static final String CONNECTION_PASSWORD = "qq3g9yp";/*"customer";*/

    /** DBへの接続 */
    protected Connection conn = null;

    /**
     * DBへ接続する
     *
     * @throws ClassNotFoundException JDBCドライバが存在しない
     * @throws SQLException DB接続失敗
     */
    protected void open() throws ClassNotFoundException, SQLException {
        LogUtil.println(this.getClass().getSimpleName() + "#open");

        Class.forName(JDBC_DRIVER_NAME);
        conn = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
        conn.setAutoCommit(true);
    }

    /**
     * DBへの接続を終了する
     *
     * @throws SQLException DBへの接続終了失敗
     */
    protected void close() throws SQLException {
        LogUtil.println(this.getClass().getSimpleName() + "#close");

        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}
