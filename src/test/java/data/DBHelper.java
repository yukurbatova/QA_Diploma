package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    @SneakyThrows
    public static Connection getConnection() {
        //var file = new File("D:/QualityAssurance/Netology/QA_Diploma/application.properties");
        //var properties = new Properties();
        //properties.load(new FileReader(file));
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
                //properties.getProperty("spring.datasource.url"),
                //properties.getProperty("spring.datasource.username"),
                //properties.getProperty("spring.datasource.password=pass")
        );
    }

    @SneakyThrows
    public static void cleanTables() {
        var creditQuery = "DELETE FROM credit_request_entity";
        var orderQuery = "DELETE FROM order_entity";
        var debitQuery = "DELETE FROM payment_entity";
        var runner = new QueryRunner();

        try ( var connect = getConnection();
        ) {
            runner.update(connect,creditQuery);
            runner.update(connect,orderQuery);
            runner.update(connect,debitQuery);
        }
    }

    @SneakyThrows
    public static String getPaymentDebitStatus() {
        var statusQuery = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();

        try ( var connect = getConnection();
        ) {
            var status = runner.query(connect, statusQuery, new ScalarHandler<>());
            return (String) status;
        }
    }
}
