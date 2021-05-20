package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLdata {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
    }

    public static String getVerificationCode(DataHelper.AuthInfo authInfo) {
        val runner = new QueryRunner();
        val verifCode = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";

        try (
                val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass")) {
            val Code = runner.query(connection, verifCode, new ScalarHandler<String>());
            return Code;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void clearSQL() {
        val runner = new QueryRunner();
        val auth_codes = "DELETE FROM auth_codes";
        val card_transactions = "DELETE FROM card_transactions";
        val cards = "DELETE FROM cards";
        val users = "DELETE FROM users";

        try (
                val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
                val Codes = conn.prepareStatement(auth_codes);
                val Transactions = conn.prepareStatement(card_transactions);
                val Cards = conn.prepareStatement(cards);
                val Users = conn.prepareStatement(users);
        ) {
            Codes.executeUpdate(auth_codes);
            Transactions.executeUpdate(card_transactions);
            Cards.executeUpdate(cards);
            Users.executeUpdate(users);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}