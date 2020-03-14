import Controllers.Controller;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        String database = "jdbc:mysql://127.0.0.1:3306/airlineperformance?useUnicode=true&useJDBCCompliantTimezoneShift" +
                "=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "bdaproject";
        String password = "bdaproject1234";
        String pathToFile = "/Users/rohitkeswani/IdeaProjects/BDATermProject/src/main/resources/FinalDataset.csv";
        String tablename = "airlinestatistics";
        Controller controller = new Controller();
        Connection connection = controller.createConnection(database, user, password);
        controller.createTable(tablename, connection);
        System.out.println("Table "+tablename+" successfully created");
        int numberOfRecordsInserted = controller.insertAllData(pathToFile, connection);
        System.out.println(numberOfRecordsInserted+" records successfully inserted");
    }
}
