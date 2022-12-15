package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * Contains all the queries used in this system.<br>
 * For usage, call getQueryObject() method to get the object which deals with
 * all the queries. Query object connects itself to the database and performs
 * the already built in queries, returning the results if there are any.<br>
 * This class implements the Singleton pattern in order to avoid multiple connections
 * to the database, and also for easier system use.
 * @author M&F
 */
public class Query {
    private static Query q;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Specifies the database url.
    // By setting autoReconnect=true, even if the connection is idle for many hours and
    // the database closes it, it's automaticaly restored on access.
    private static final String DATABASE_URL = "jdbc:mysql://localhost/magic_fly" +
            "?autoReconnect=true";

    private static Connection con;

    /**
     * @return the Query object without creating duplicates. (Singleton Pattern)
     */
    public static Query getQueryObject() {
        if (q == null) {
            q = new Query();
        }

        return q;
    }

    /**
     * Loads the JDBC Driver and opens the connection.
     */
    private Query() {
        // Loads the JDBC Driver class
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error at loading JDBC Driver Class");
        }

        // Creates the connection to the database.
        try {
            con = DriverManager.getConnection(DATABASE_URL, "root", "root");
        } catch (SQLException ex) {
            System.err.println("Error at creating connection to the database" +
                    "\n" + ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @return a ResultSet containing all rank levels from database
     * Returned elements: rankName
     */
    public ResultSet getUserRanks() {
        String query = "SELECT name " +
                "FROM user_ranks " +
                "ORDER BY name";

        // Tries to get data from database and to return it
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getUserRanks\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // Returns null if the trying failed
        return null;
    }

    /**
     * @return a ResultSet containing all users with their details.<br>
     * Returned elements: fName, lName, rank, password.
     */
    public ResultSet getUsers() {
        String query = "SELECT U.ID, U.fName, U.lName, UR.name, U.password " +
                "FROM users U, user_ranks UR " +
                "WHERE UR.ID = U.rankID " +
                "ORDER BY U.fName";

        // Tries to get data from database and to return it
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getUsers\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // Returns null if the trying failed
        return null;
    }

    /**
     * Removes a user from user table based on the userID.
     * @param userId
     */
    public void removeUser(String userId) {
        String query = "DELETE FROM users " +
                "WHERE ID='" + userId + "';";

        // Tries to execute the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method removeUser\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Updates a user (based on ID) with the new data.
     * @param userId
     * @param fName
     * @param lName
     * @param rankName
     * @param pass
     */
    public void updateUser(String userId, String fName, String lName, String rankName,  String pass) {
        String query = "UPDATE users " +
                "SET fName='" + fName + "', lName='" + lName + "', " +
                "rankID=(SELECT ID " +
                "FROM user_ranks " +
                "WHERE name='" + rankName + "'), " +
                "password='" + pass + "' " +
                "WHERE ID='" + userId + "';";

        // Tries to execute the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method updateUser\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Inserts a new user with the specified parameters.
     * @param fName
     * @param lName
     * @param rankName
     * @param pass
     */
    public void insertUser(String fName, String lName, String rankName, String pass) {
        String query = "INSERT INTO users (fName, lName, password, rankID) " +
                "VALUES ('" + fName + "', '" + lName + "', '" + pass + "', " +
                "(SELECT ID " +
                "FROM user_ranks " +
                "WHERE name='" + rankName + "'));";

        // Tries to execut the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method insertUser\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Checks if a user exists, based on his first and last name.
     * @param fName
     * @param lName
     * @return check result.
     */
    public boolean checkIfUserExists(String fName, String lName) {
        String query = "SELECT ID " +
                "FROM users " +
                "WHERE fName='" + fName + "' AND lName='" + lName + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method checkIfUserExists\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true (the user exists) even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * @param fName
     * @param lName
     * @return the id of the user which has these fName and lName.<br>
     * Returns -1 if user was not founded.
     */
    public int getUserId(String fName, String lName) {
        String query = "SELECT ID " +
                "FROM users " +
                "WHERE fname='" + fName + "' AND lName='" + lName + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            if (rs != null && rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getUserID\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return -1;
    }

    /**
     * Checks a user with these details exists into the database.
     * @param fName
     * @param lName
     * @param password
     * @return the rank of the user which has these fName, lName and correct password.<br>
     * Returns null if user was not found, or password was incorrect.
     */
    public String verifyUserLogin(String fName, String lName, String password){
        String query = "SELECT UR.name " +
                "FROM users U, user_ranks UR " +
                "WHERE U.fName = '" + fName + "' AND U.lName='" + lName +
                "' AND U.password ='" + password +
                "' AND UR.ID=U.rankID;";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query " +
                    "on method verifyUserLogin\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * @return ResultSet with all different flight class types.<br>
     * Returned elements: flight class types.
     */
    public ResultSet getFlightClassTypes(){
        String query ="SELECT DISTINCT name " +
                "FROM flight_class_types " +
                "ORDER BY name;";

        // Tries to get data from database
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error on method getFlightClassTypes\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * Remove unused flight class types from DB.<br>
     * Unused means when no flight class points to a flight class type.
     */
    public void removeUnusedFlightClassTypes() {
        String query = "DELETE FROM flight_class_types " +
                "WHERE ID NOT IN (" +
                "SELECT DISTINCT flightClassTypeID " +
                "FROM flight_classes);";

        // Tries to execut the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method removeUnusedFlightClassTypes\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @param flightClassTypeName
     * @return ID of specified FCT, or null if flightClassType is not founded.
     */
    public String getFlightClassTypeId(String flightClassTypeName) {
        String query = "SELECT ID " +
                "FROM flight_class_types " +
                "WHERE name='" + flightClassTypeName + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // If result set is valid, it means that the FCT with the given name was
            // founded, so we return its ID
            if (rs != null && rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getFlightClassTypeId\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // FCT wasn't founded, so we return null
        return null;
    }

    /**
     * Adds the specified flight class type into database.
     * @param flightClassTypeName
     */
    public void addFlightClassType(String flightClassTypeName) {
        String query = "INSERT INTO flight_class_types (name) " +
                "VALUES ('" + flightClassTypeName + "');";

        // Tries to perform the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method addFlightClassType\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @return a ResultSet containing all flight classes with their details.<br>
     * Returned elements: ID, type, name, fcSeatsNo, ccSeatsNo, ecSeatsNo.
     */
    public ResultSet getFlightClasses(){
        String query = "SELECT FC.ID, FCT.name, FC.name, FC.fcSeatsNo, FC.ccSeatsNo, ecSeatsNo " +
                "FROM flight_classes FC, flight_class_types FCT " +
                "WHERE FCT.ID=FC.flightClassTypeID " +
                "ORDER BY FCT.name;";

        // Tries to get data from database
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getFlightClasses\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * @param id
     * @return a ResultSet of the specified flight class.<br>
     * Returned elements: type, name.
     */
    public ResultSet getFlightClass(String id) {
        String query = "SELECT FCT.name, FC.name " +
                "FROM flight_classes FC, flight_class_types FCT " +
                "WHERE FC.ID='" + id + "' AND FCT.ID=FC.flightClassTypeID";

        // Tries to get data from database
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method getFlightClass\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * @param flightClassTypeId
     * @param flightClassName
     * @return ID of specified flight class, or null if it isn't into database.
     */
    public String getFlightClassId(String flightClassTypeId, String flightClassName) {
        String query = "SELECT ID " +
                "FROM flight_classes " +
                "WHERE flightClassTypeID='" + flightClassTypeId + "' AND name='" + flightClassName + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error on method getFlightClassId\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * @param fcTypeId
     * @param name
     * @param fcSeatsNo
     * @param ccSeatsNo
     * @param ecSeatsNo
     * Adds FlightClass into database. Checking if the FlightClass exist
     * is not nesessary because Airline can contain many planes of the same type.
     */
    public void addFlightClass(String fcTypeId, String name, String fcSeatsNo,
                               String ccSeatsNo, String ecSeatsNo){
        String query ="INSERT INTO flight_classes (flightClassTypeID, name, fcSeatsNo, ccSeatsNo, ecSeatsNo) " +
                "VALUES ('" + fcTypeId + "', '" + name + "', '" + fcSeatsNo +
                "', '" + ccSeatsNo + "', '" + ecSeatsNo + "');";

        // Tries to perform the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method addFlightClass\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Updates the flight class info in the database, changing the name, type
     * and seatsNo(fc, cc, ec) with those passed as parameters.
     * @param id
     * @param fcTypeId
     * @param name
     * @param fcSeatsNo
     * @param ccSeatsNo
     * @param ecSeatsNo
     */
    public void updateFlightClass(String id, String fcTypeId, String name, String fcSeatsNo,
                                  String ccSeatsNo, String ecSeatsNo){
        String query = "UPDATE flight_classes " +
                "SET flightClassTypeID ='" + fcTypeId + "', name = '" + name + "', fcSeatsNo = '" +
                fcSeatsNo + "', ccSeatsNo = '" + ccSeatsNo + "', ecSeatsNo = '" +ecSeatsNo+ "' " +
                "WHERE ID = '" + id + "';";
        //Tries to execute statements.
        try{
            con.createStatement().executeUpdate(query);
        }
        catch(SQLException ex){
            System.err.println("Error on method updateFlightClass\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Gets the newest FlightClass ID added to the Fleet by selecting the one with the highest ID.<br>
     * It means the latest one, because ID is auto_incremented, so the last one will always be the biggest.
     */
    public String getLastAddedFlightClassId() {
        String query ="SELECT MAX(ID) " +
                "FROM flight_classes;";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error on method getLastClassFlightID\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * Removes the Flight Class whith specified ID from the database.
     * @param id - of the flight class to be deleted
     */
    public void removeFlightClass(String id){
        String query = "DELETE FROM flight_classes " +
                "WHERE ID = '" + id + "';";

        // Tries to execut the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method removeFlightClass\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Checks if a flight class has planes, based on flight class id.
     * @param flightClassId
     * @return check result
     */
    public boolean checkIfFlightClassHasPlanes(String flightClassId) {
        String query = "SELECT ID " +
                "FROM planes " +
                "WHERE flightClassID='" + flightClassId + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method checkIfFlightClassHasPlanes\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true (flight class has planes) even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * @return ResultSet with all schedules.
     * Returned elements: id, airport1Id, airport2Id, departure, arrival,
     * planeAvailability, planeFullPrice.
     */
    public ResultSet getFlightSchedules(){
        String query = "SELECT FS.ID, P.ID, P.airport1ID, P.airport2ID, FS.departure, FS.arrival " +
                "FROM planes P, flight_schedules FS " +
                "WHERE FS.planeID = P.ID;";

        // Tries to execute the query
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error on method getSchedules\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * Checks if there are reservations for the specified flight schedule.
     * @param flightScheduleId
     * @return
     */
    public boolean checkIfFlightScheduleHasReservations(String flightScheduleId) {
        String query = "SELECT ID " +
                "FROM reservations " +
                "WHERE flightScheduleID='" + flightScheduleId + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method checkIfFlightScheduleHasReservations\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true (flight schedule has reservations) even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * Adds new flight schedule and takes care about time zone differences.
     * @param planeId
     * @param departureTimeStamp
     */
    public void addFlightSchedule(String planeId, String departureTimeStamp) {
        // Calculates the time zone difference between departure and arrival airports
        int timeZoneDifference = getDestinationTimeZone(planeId) - getDepartureTimeZone(planeId);

        // Prepares the SQL method for calibrating the time zones
        // If timeZoneDifference is negative, substracts from arrival time.
        // If it's positive, adds to arrival time.
        String sqlMethod = null;
        if (timeZoneDifference > 0) {
            sqlMethod = "ADDTIME";
        } else {
            sqlMethod = "SUBTIME";
        }

        // Makes the timeZoneDif positive.
        timeZoneDifference = Math.abs(timeZoneDifference);

        // Creates the query which adds a new flight schedule and also takes care about
        // the time zone differences when calculates the arrival time
        String query = "INSERT INTO flight_schedules (planeID, departure, arrival) " +
                "VALUES('" + planeId + "', '" + departureTimeStamp + "', " +
                "ADDTIME('" + departureTimeStamp + "', " +
                sqlMethod + "((SELECT flightLength " +
                "FROM planes " +
                "WHERE ID = '" + planeId + "'), '" +
                timeZoneDifference + ":00')));";

        // Tries to execute query
        try {
            con.createStatement().executeUpdate(query);
        } catch(SQLException ex) {
            System.err.println("Error on method addFlightSchedule\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Updates the flight schedule with given parameters.
     * @param flightScheduleId
     * @param planeId
     * @param departureTimeStamp
     */
    public void updateFlightSchedule(String flightScheduleId, String planeId, String departureTimeStamp) {
        // Calculates the time zone difference between departure and arrival airports
        int timeZoneDifference = getDestinationTimeZone(planeId) - getDepartureTimeZone(planeId);

        // Prepares the SQL method for calibrating the time zones
        // If timeZoneDifference is negative, substracts from arrival time.
        // If it's positive, adds to arrival time.
        String sqlMethod = null;
        if (timeZoneDifference > 0) {
            sqlMethod = "ADDTIME";
        } else {
            sqlMethod = "SUBTIME";
        }

        // Makes the timeZoneDif positive.
        timeZoneDifference = Math.abs(timeZoneDifference);

        // Creates the query which updates the specified flight schedule and also takes care about
        // the time zone differences when calculates the arrival time
        String query = "UPDATE flight_schedules " +
                "SET planeID = '" + planeId + "', departure = '" + departureTimeStamp + "', " +
                "arrival = ADDTIME('" + departureTimeStamp + "', " +
                sqlMethod + "((SELECT flightLength " +
                "FROM planes " +
                "WHERE ID = '" + planeId + "'), '" +
                timeZoneDifference + ":00')) " +
                "WHERE ID = '" + flightScheduleId + "';";

        // Tries to execute query
        try {
            con.createStatement().executeUpdate(query);
        } catch(SQLException ex) {
            System.err.println("Error on method updateFlightSchedule\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Removes flight schedule with the specified id.
     * @param ID
     */
    public void removeFlightSchedule(String ID){
        String query ="DELETE FROM flight_schedules " +
                "WHERE ID = '" + ID + "';";

        // Tries to execute query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method removeFligth\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @param planeId
     * @return destination airport's time zone of the specified plane.
     */
    private int getDestinationTimeZone(String planeId){
        String query =" SELECT value"
                + " FROM time_zones "
                + " WHERE ID = "
                + "(SELECT timeZoneID "
                + " FROM cities "
                + " WHERE ID = "
                + "(SELECT cityID "
                + " FROM airports "
                + " WHERE ID = "
                + "(SELECT airport2ID "
                + " FROM planes"
                + " WHERE ID ='" + planeId + "')));";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data
            if (rs != null && rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("Error on method getDestinationTimeZone\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return -999;
    }

    /**
     * @param planeId
     * @return departure airport's time zone of the specified plane.
     */
    private int getDepartureTimeZone(String planeId){
        String query =" SELECT value"
                + " FROM time_zones "
                + " WHERE ID = "
                + "(SELECT timeZoneID "
                + " FROM cities "
                + " WHERE ID = "
                + "(SELECT cityID "
                + " FROM airports "
                + " WHERE ID = "
                + "(SELECT airport1ID "
                + " FROM planes"
                + " WHERE ID ='" + planeId + "')));";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null && rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("Error on method getDepartureTimeZone\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return -999;
    }

    /**
     * Searches for flights that respect the conditions specified by parameters.<bn>
     * If departure date = current day, only flights which depart after at least
     * one hour are returned.<br>
     * Method cancels unconfirmed reservations if there are not enough seats to
     * satisfy the requested number.
     * @param departureDate
     * @param departureAirportId
     * @param arrivalAirportId
     * @param classType
     * @param requestedSeatsNo
     * @return a LinkedList with found flights, contained by a String[] each.
     * @see #addFlightToList(java.sql.ResultSet, java.util.LinkedList) for String[] elements details.
     */
    public LinkedList<String[]> searchFlights(String departureDate, String departureAirportId,
                                              String arrivalAirportId, String classType,
                                              String requestedSeatsNo){
        // Figures out which column will be used as number of tickets source,
        // based on class type.
        String ticketsNoColumnName = null;
        switch (classType) {
            case "First"   : ticketsNoColumnName = "fcSeatsNo"; break;
            case "Coach"   : ticketsNoColumnName = "ccSeatsNo"; break;
            case "Economy" : ticketsNoColumnName = "ecSeatsNo"; break;
            default : throw new IllegalArgumentException("Invalid flight class");
        }

        // Gets the current date
        // Adds +1 to month because the February is 0, not 1.
        Calendar c = Calendar.getInstance();
        String currentYear  = c.get(Calendar.YEAR) + "";
        String currentMonth = c.get(Calendar.MONTH) + 1 + "";
        String currentDay	= c.get(Calendar.DAY_OF_MONTH) + "";

        // Makes sure month and day have the right format (2 digits)
        if (currentMonth.length() == 1) {
            currentMonth = "0" + currentMonth;
        }
        if (currentDay.length() == 1) {
            currentDay = "0" + currentDay;
        }

        // Contains the SQL that will validate the departure date
        String departureDateValidation = "FS.departure LIKE '" + departureDate + "%'";

        // Checks if parameter "departureDate" is current day.
        // If it is, adds the current time to the date variable, because we'll
        // check only for flights that depart after at least one hour from now.
        if (departureDate.equals(currentYear + "-" + currentMonth + "-" + currentDay)) {
            // Appends hour to date variable and adds 12 if it's PM.
            if (c.get(Calendar.AM_PM) == 0) {
                departureDate += " " + c.get(Calendar.HOUR);
            } else {
                departureDate += " " + (c.get(Calendar.HOUR) + 12);
            }

            // Adds minutes to date variable
            departureDate += ":" + c.get(Calendar.MINUTE);

            // Adds SQL that will add 1 more hour to departureDate and that will also check if
            // flights depart after this hour.
            // We're using SQL because MySQL has a built in function which adds
            // time to a given time, and also takes care about years/months/days...everything
            departureDateValidation += " AND FS.departure >= ADDTIME('" + departureDate + "', '1:00:00')";
        }

        // Creates the query that searches for all possible flights.
        // Only flights that have at least the required seatsNo in the specified class are searched.
        // We don't check now if the seats are free, just if they exist.
        String query = "SELECT FS.ID, FC." + ticketsNoColumnName + " AS seatsNo, P.fullPrice, " +
                "FS.departure, FS.arrival, P.flightLength ,FCT.name AS planeManufacturer, FC.name AS planeModel " +
                "FROM flight_schedules FS, planes P, flight_classes FC, flight_class_types FCT " +
                "WHERE " + departureDateValidation + " AND " +
                "P.ID = FS.planeID AND " +
                "P.airport1ID = '" + departureAirportId + "' AND " +
                "P.airport2ID = '" + arrivalAirportId + "' AND " +
                "FC.ID = P.flightClassID AND " +
                "FC." + ticketsNoColumnName + " >=" + requestedSeatsNo + " AND " +
                "FCT.ID = FC.flightClassTypeID " +
                "ORDER BY P.fullPrice;";

        ResultSet foundFlights = null;

        // Tries to get data from database.
        try {
            foundFlights = con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query on method searchFlights\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // Creates a linkedList which will store flights that have enough seats.
        LinkedList<String[]> validFlights = new LinkedList<>();

        // Now we have the possible flights, but we have to check if they have free seats
        // as much as we need ("seatsNo").
        if (foundFlights != null) {
            try {
                // Prepares variables for later use.
                // It doesn't make sense to create them each loop iteration, so we make them here.
                int requestedSeatsNoInt = Integer.parseInt(requestedSeatsNo);;
                int totalSeatsNoInt		= 0;
                int occupiedSeatsNoInt	= 0;
                int freeSeatsNoInt		= 0;
                ResultSet occupiedSeatsNoRS = null;
                ResultSet unconfRS			= null;

                while (foundFlights.next()) {
                    // Resets the result set object for later use.
                    occupiedSeatsNoRS = null;

                    // Prepares integer for later use.
                    totalSeatsNoInt = Integer.parseInt(foundFlights.getString("seatsNo"));

                    // Creates the query that checks number of occupied seats (tickets).
                    // Here we check both for confirmed and unconfirmed reservations.
                    query = "SELECT COUNT(T.ID) " +
                            "FROM reservations R, tickets T " +
                            "WHERE R.flightScheduleID = '" + foundFlights.getString("ID") + "' AND " +
                            "R.status IN ('confirmed', 'unconfirmed') AND " +
                            "R.seatClass = '" + classType + "' AND " +
                            "T.reservationID = R.ID;";

                    // Executes the query which looks for occupied seats
                    occupiedSeatsNoRS = con.createStatement().executeQuery(query);

                    // Takes the occupied seats number from result set.
                    if (occupiedSeatsNoRS != null && occupiedSeatsNoRS.next()) {
                        occupiedSeatsNoInt = Integer.parseInt(occupiedSeatsNoRS.getString(1));
                    } else {
                        System.err.println("Problem at fetching occupiedSeatsNoRS");
                    }

                    // Calculates the free seats, by removing the occupiedSeatsNo from
                    // the total seatsNo the plane support for the specified flight class
                    freeSeatsNoInt = totalSeatsNoInt - occupiedSeatsNoInt;

                    // Checks if freeSeatsNo satisfies the no. of requested seats.
                    // If it does, adds the flight to validFlights linked list and go to next flight.
                    if (freeSeatsNoInt >= requestedSeatsNoInt) {
                        addFlightToList(foundFlights, validFlights);

                        // freeSeatsNo doesn't satisfy requestedSeatsNoInt, so we have to check if
                        // there are some unconfirmed reservations from which we can take seats.
                        // An unconfirmed reservation is protected the first 2 hours after creation.
                        // After that, it can be any time marked as "expired", so the reserved seats
                        // can be directed to a new customer.
                        // By doing this, we respect the project requirement of reallocating unpaid
                        // seats when there are not enough left, but we also protect the customer by
                        // giving him the assurance that he has it's own reservation for
                        // at least 2 hours, enough time to get money and pay his reservation.
                    } else {
                        // Creates query which gets all unconfirmed reservations and counts
                        // how many tickets has each.
                        String unconfQ = "SELECT R.ID, COUNT(T.ID) " +
                                "FROM reservations R, tickets T " +
                                "WHERE R.flightScheduleID = '" + foundFlights.getString("ID") + "' AND " +
                                "R.status = 'unconfirmed' AND " +
                                "R.seatClass = '" + classType + "' AND " +
                                "ADDTIME(R.date, '2:00:00') < LOCALTIMESTAMP() AND " +
                                "T.reservationID = R.ID " +
                                "GROUP BY R.ID " +
                                "ORDER BY R.date;";

                        // Executes query.
                        unconfRS = con.createStatement().executeQuery(unconfQ);

                        // Checks if there are some unconfirmed reservations found.
                        if (unconfRS != null && unconfRS.next()) {
                            // Puts back the pointer because it was moved in the if statement above
                            unconfRS.previous();

                            int unconfirmedSeatsNoInt = 0;

                            // Counts all tickets blocked by the unconfirmed reservations.
                            while (unconfRS.next()) {
                                unconfirmedSeatsNoInt += Integer.parseInt(unconfRS.getString(2));
                            }

                            // Checks if unconfirmedSeatsNoInt + freeSeatsNo are enough in order to
                            // satisfy the requestedSeatsNoInt.
                            // This check is useful because otherwise we'll cancel all unconfirmed
                            // reservations but we will still not be able to satisfy the necessary
                            // requested number of seats.
                            // We want to protect our clients, so we won't cancel their reservations useless.
                            if ((freeSeatsNoInt + unconfirmedSeatsNoInt) >= requestedSeatsNoInt) {
                                // Puts back the pointer because it was moved in the while loop above
                                unconfRS.absolute(1);
                                unconfRS.previous();

                                // Cancels reservations, one by one, until the requestedSeatsNo is fulfilled.
                                // Reservations are canceled this order: the oldest ones first.
                                // A canceled unconfirmed reservation is marked as "expired".
                                while (unconfRS.next()) {
                                    String cancelQ = "UPDATE reservations " +
                                            "SET status = 'expired' " +
                                            "WHERE ID = '" + unconfRS.getString("ID") + "';";

                                    // Executes query
                                    con.createStatement().executeUpdate(cancelQ);

                                    // Adds number of seats contained by the canceled reservation
                                    // to the "freeSeatsNoInt" variable.
                                    freeSeatsNoInt += Integer.parseInt(unconfRS.getString(2));

                                    // Removes number of seats contained by the canceled reservation
                                    // from the "unconfirmedSeatsNoInt" variable.
                                    unconfirmedSeatsNoInt -= Integer.parseInt(unconfRS.getString(2));

                                    // Checks if freeSeatsNo are enough for satisfying the requestedSeatsNoInt.
                                    if (freeSeatsNoInt >= requestedSeatsNoInt) {
                                        // There are enough seats, so adds flight to valid list
                                        addFlightToList(foundFlights, validFlights);

                                        // and stops canceling unconfirmed reservations
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Cannot iterate over foundFlights in searchFlights\n" +
                        ex.getSQLState() + " " + ex.getMessage());
            }
        }

        return validFlights;
    }

    /**
     * Adds current flight from "foundFlights" ResultSet into the validFlights LinkedList.<br>
     * String[] format = ID, fullPrice, departure, arrival, flightLength, planeManufacturer, planeModel.
     * @param foundFlights
     * @param validFlights
     * @throws SQLException
     */
    private static void addFlightToList(ResultSet foundFlights, LinkedList<String[]> validFlights) throws SQLException {
        validFlights.add(new String[]{foundFlights.getString("ID"),
                foundFlights.getString("fullPrice"),
                foundFlights.getString("departure"),
                foundFlights.getString("arrival"),
                foundFlights.getString("flightLength"),
                foundFlights.getString("planeManufacturer"),
                foundFlights.getString("planeModel")});
    }

    /**
     * @param justAvailablePlanes - if it's true, returns just available planes.
     * @return a ResultSet with all the planes.<br>
     * Returned elements: planeId, fcId, fcType, fcName, airport1Id, airport2Id,
     * flightLength, planeAvailability, planeFullPrice.
     */
    public ResultSet getPlanes(boolean justAvailablePlanes) {
        String availablePlanesSQLCheck = null;

        // Checks if only available planes are requested (parameter is true)
        if (justAvailablePlanes) {
            availablePlanesSQLCheck = "AND P.available = 'yes' ";
        } else {
            availablePlanesSQLCheck = "";
        }

        String query = "SELECT P.ID, P.flightClassID, FCT.name, FC.name, P.airport1ID, P.airport2ID, " +
                "P.flightLength, P.available, P.fullPrice " +
                "FROM planes P, flight_classes FC, flight_class_types FCT " +
                "WHERE P.flightClassID = FC.ID AND FCT.ID = FC.flightClassTypeID " +
                availablePlanesSQLCheck +
                "ORDER BY FCT.name;";

        // Tries to get data from database
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error on method getPlanes\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * Removes from database the plane with the specified id.
     * @param id
     */
    public void removePlane(String id) {
        String query = "DELETE FROM planes " +
                "WHERE ID='" + id + "';";

        // Tries to execute the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method removePlane\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Checks if there are flight schedules for the specified plane.
     * @param planeId
     * @return checking result
     */
    public boolean checkIfPlaneHasSchedules(String planeId) {
        String query = "SELECT ID " +
                "FROM flight_schedules " +
                "WHERE planeID='" + planeId + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method checkIfPlaneHasSchedules\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true (plane has schedules) even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * Updates the database entry of the plane with the specified id.
     * @param planeId
     * @param flightClassId
     * @param airport1Id
     * @param airport2Id
     * @param flightLength
     * @param available
     * @param fullPrice
     */
    public void updatePlane(String planeId, String flightClassId, String airport1Id,
                            String airport2Id, String flightLength, String available, String fullPrice) {
        String query = "UPDATE planes " +
                "SET flightClassID='" + flightClassId + "', airport1ID='" + airport1Id +
                "', airport2ID='" + airport2Id + "', flightLength='" + flightLength +
                "', available='" + available + "', fullPrice='" + fullPrice + "' " +
                "WHERE ID='" + planeId + "';";

        // Tries to execute statements.
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method updatePlane\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Inserts a new plane into the database, with the specified details.
     * @param flightClassId
     * @param airport1Id
     * @param airport2Id
     * @param flightLength
     * @param available
     * @param fullPrice
     */
    public void insertPlane(String flightClassId, String airport1Id, String airport2Id,
                            String flightLength, String available, String fullPrice) {
        String query = "INSERT INTO planes (flightClassID, airport1ID, airport2ID, " +
                "flightLength, available, fullPrice) " +
                "VALUES ('" + flightClassId + "', '" + airport1Id + "', '" + airport2Id +
                "', '" + flightLength + "', '" + available + "', '" + fullPrice + "');";

        // Tries to perform the query
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method insertPlane\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @return the id of the last added plane, needed on Plane object construction,
     * in order to be shown in the planes table.<br>
     * Returns -1 if there is no entry inside "planes" table.
     */
    public String getLastAddedPlaneId() {
        String query = "SELECT MAX(ID) " +
                "FROM planes;";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error on method getLastAddedPlaneId\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * @return a ResultSet with all the airports available in the database.<br>
     * Returned elements: airportId, airportName, countryName, cityName.
     */
    public ResultSet getAirports() {
        String query = "SELECT A.ID, A.name, Co.name, Ci.name " +
                "FROM airports A, countries Co, cities Ci " +
                "WHERE A.cityID=Ci.ID AND Ci.countryID=Co.ID;";

        // Tries to get data from database
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error on method getPlanes\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
        return null;
    }

    /**
     * Creates a new reservation with the specified details.
     * @param flightScheduleId
     * @param price
     * @param status
     * @param clientId
     * @return id of the new reservation
     */
    public String createReservation(String flightScheduleId, String price, String seatClass,
                                    String status, String clientId) {
        String query = "INSERT INTO reservations (flightScheduleID, date, price, seatClass, status, clientID) " +
                "VALUES ('" + flightScheduleId + "', LOCALTIME(), '" + price + "', '" +
                seatClass + "', '" + status + "', '" + clientId + "');";

        // Tries to execute statement.
        try {
            // Executes insertion query
            con.createStatement().executeUpdate(query);

            // Gets and reservation ID
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(ID) FROM reservations;");

            // Fetches and returns reservation ID
            if (rs !=null && rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error on method createReservation\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * Checks if flight reserved by the specified reservation is completed.
     */
    public boolean isReservedFlightCompleted(String reservationId) {
        String query = "SELECT R.ID " +
                "FROM reservations R, flight_schedules FS " +
                "WHERE FS.departure > LOCALTIME() AND " +
                "R.flightScheduleID = FS.ID AND " +
                "R.ID = '" + reservationId + "';";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return !rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method isReservedFlightCompleted\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * Pays the specified reservation.<br>
     * More precisely, it's marked as "confirmed".
     * @param reservationId
     */
    public void payReservation(String reservationId) {
        String query = "UPDATE reservations " +
                "SET status = 'confirmed' " +
                "WHERE ID = '" + reservationId + "';";

        // Tries to execute statements.
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method payReservation\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * Cancel the specified reservation.<br>
     * More precisely, it's marked as "canceled".
     * @param reservationId
     */
    public void cancelReservation(String reservationId) {
        String query = "UPDATE reservations " +
                "SET status = 'canceled' " +
                "WHERE ID = '" + reservationId + "';";

        // Tries to execute statements.
        try {
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method cancelReservation\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }

    /**
     * @param reservationId
     * @return true if there are more than 14 days (24 hours * 14) until reserved flight departs.
     */
    public boolean areMoreThan14DaysUntilDeparture(String reservationId) {
        String query = "SELECT R.ID " +
                "FROM reservations R, flight_schedules FS " +
                "WHERE R.date < SUBDATE(FS.departure, 14) AND " +
                "R.ID = '" + reservationId + "' AND " +
                "FS.ID = R.flightScheduleID;";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);

            // Checks if result set has data in it
            if (rs != null) {
                return rs.next();
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query" +
                    " on method areMoreThan14DaysUntilDeparture\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        // If you are here, it means that ResultSet check failed because of an error.
        // So, we return true even if we couldn't check this.
        // Just for security reasons.
        return true;
    }

    /**
     * @param userId
     * @return reservations made by the specified user, or all reservations if userId is <b>null</b>.<br>
     * Returned elements: reservationId, creationDate, departureDate, arrivalAirport, seatClass,
     * price, reservation status, user fName, user lName
     */
    public ResultSet getReservations(String userId) {
        // Prepares SQL for getting user or not, depending if parameter is null or not
        String userSQL = null;
        if (userId != null) {
            userSQL = "R.clientID = '" + userId + "' AND ";
        } else {
            userSQL = "";
        }

        String query = "SELECT R.ID, R.date AS creationDate, FS.departure AS departureDate, " +
                "A.name AS arrivalAirport, R.seatClass, R.price, R.status, U.fName, U.lName " +
                "FROM reservations R, flight_schedules FS, planes P, airports A, users U " +
                "WHERE " + userSQL +
                "FS.ID = R.flightScheduleID AND " +
                "FS.planeID = P.ID AND " +
                "A.ID = P.airport2ID AND " +
                "U.ID = R.clientID " +
                "ORDER BY R.date DESC;";

        // Tries to execute statement.
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("Error on method createReservation\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * @param date
     * @return income for specified month
     */
    public int getMonthlyIncome(String date) {
        String query = "SELECT SUM(R.price) " +
                "FROM reservations R " +
                "WHERE R.status = 'confirmed' AND " +
                "R.date >= '" + date + "' AND " +
                "R.date < ADDDATE('" + date + "', INTERVAL 1 MONTH);";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        } catch (NumberFormatException ne) {
            return 0;
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query " +
                    "on method getMonthlyIncome\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return 0;
    }

    /**
     * @param date
     * @return - date (YYYY-MM) of the month before the given date.
     */
    public String getLastMonthDate(String date) {
        String query = "SELECT SUBDATE('" + date + "', INTERVAL 1 MONTH);";

        // Tries to get data from database
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs !=null && rs.next()){
                return rs.getString(1).substring(0, 10);
            }
        } catch (SQLException ex) {
            System.err.println("Cannot create statement or execute query " +
                    "on method getLastMonthDate\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }

        return null;
    }

    /**
     * Creates a new ticket with the specified information.
     * @param gender
     * @param name
     * @param surname
     * @param reservationId
     */
    public void createTicket(String gender, String name, String surname, String reservationId) {
        String query = "INSERT INTO tickets (gender, name, surname, reservationID) " +
                "VALUES ('" + gender + "', '" + name + "', '" + surname + "', '" + reservationId + "');";

        // Tries to execute statement.
        try {
            // Executes insertion query
            con.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error on method createTicket\n" +
                    ex.getSQLState() + " " + ex.getMessage());
        }
    }
}
