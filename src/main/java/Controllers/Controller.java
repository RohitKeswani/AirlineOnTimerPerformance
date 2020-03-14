package Controllers;

import Utility.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controller {
    Utility utility = new Utility();
    String SQLStatement = "INSERT INTO airlinestatistics(Year, Month, Day, DayOfWeek, CarrierID, FlightNumber, " +
            "OriginAirport, DestinationAirport, ScheduledDepartureTime, ActualDepartureTime, DepartureDelay," +
            "TaxiOutTime, TaxiInTime, ScheduledArrivalTime, ActualArrivalTime, ArrivalDelay, ScheduledElapsedTime," +
            "ActualElapsedTime, AirTime, Distance) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public Connection createConnection(String database, String user, String password)
    {
        return utility.createConnection(database, user, password);
    }
    public int insertAllData(String pathToFile, Connection connection)
    {
        int numberOfRecordsInserted = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatement);
            String line ="";
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null)
            {
                String[] record = line.split(",");
                for(int i = 0; i<20;i++)
                {
                    if(i==4||i==6||i==7)
                        preparedStatement.setString(i+1,record[i]);
                    else
                        preparedStatement.setInt(i+1, Integer.parseInt(record[i]));
                }
              numberOfRecordsInserted += preparedStatement.executeUpdate();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRecordsInserted;
    }

    public boolean createTable(String tablename, Connection connection){
        String createTableQuery = "CREATE TABLE "+tablename+" (Year int, Month int, Day int, DayOfWeek int, " +
                "CarrierID varchar(255), FlightNumber int, OriginAirport varchar(255), DestinationAirport varchar(255), " +
                "ScheduledDepartureTime int, ActualDepartureTime int, DepartureDelay int, TaxiOutTime int, TaxiInTime int, " +
                "ScheduledArrivalTime int, ActualArrivalTime int, ArrivalDelay int, ScheduledElapsedTime int, " +
                "ActualElapsedTime int, AirTime int, Distance int);";
        try(PreparedStatement preparedStatement  = connection.prepareStatement(createTableQuery))
        {
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
