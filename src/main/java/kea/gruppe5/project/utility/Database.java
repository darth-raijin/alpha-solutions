package kea.gruppe5.project.utility;

import java.sql.*;

public class Database {

    /*private static Connection conn;

     */
        /*

        //maxId (skal nok ikke bruges)
        public int createMaxId (int id) {
            String result = "";
            String getMaxId = "SELECT max(id) FROM alphasolutions.projects";
            PreparedStatement preparedStatement;
            try {
                preparedStatement = conn.prepareStatement(getMaxId, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.executeUpdate();
                ResultSet idResult = preparedStatement.getResultSet();
                if (idResult.next()) {
                    id = idResult.getInt(1);
                    id++;
                }
            } catch (SQLException err) {
                System.out.println("bad happened:" + err.getMessage());
                return 400;
            }

            System.out.println("good happened");
            return id;
        }
        //
         */

    }



