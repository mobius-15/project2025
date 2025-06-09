package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.LogUtil;

/**
 * DAO(トランザクション制御あり)の基底クラス
 */
public class BaseDaoWithTransaction {

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
    Connection conn = null;

    /**
     * DBへ接続し、トランザクションを開始する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String startTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#startTransaction");

        String errMessage = null;

        try {
            Class.forName(JDBC_DRIVER_NAME);
            conn = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            errMessage = e.getMessage();
            LogUtil.printStackTrace(e);
        }
        return errMessage;
    }

    /**
     * トランザクションをコミットし、DBへの接続を終了する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String endTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#endTransaction");

        String errMessage = null;

        if (conn != null) {
            try {
                conn.commit();
                conn.setAutoCommit(true);
                conn.close();
                conn = null;
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }

    /**
     * トランザクションをロールバックし、DBへの接続を終了する
     * @return エラーメッセージ(処理成功時、null)
     */
    public String cancelTransaction() {
        LogUtil.println(this.getClass().getSimpleName() + "#cancelTransaction");

        String errMessage = null;

        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                conn.close();
                conn = null;
            } catch (SQLException e) {
                errMessage = e.getMessage();
                LogUtil.printStackTrace(e);
            }
        }

        return errMessage;
    }
}