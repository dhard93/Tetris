package com.example.tetris;

import java.net.URL;
import java.sql.*;

/**
 * This class is used to create and interact with the database containing the top 3 player names and scores.
 * It can be used to edit and retrieve database entries.
 */

public class Database
{
    private final String FILENAME = "/com/example/tetris/Top_Scores/tetrisDatabase.db";
    private String dbPath = "";

    /**
     * The Database constructor checks to see if a top players database already exists, and if not, creates one
     * with default entry values.
     */
    public Database()
    {
        int defaultFirstPlace  = 10000;
        int defaultSecondPlace = 5000;
        int defaultThirdPlace  = 2500;

        try
        {
            URL resourceUrl = Database.class.getResource(FILENAME);

            assert resourceUrl != null;
            dbPath = resourceUrl.getPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath))
        {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS top_players " +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "high_score BIGINT" +
                    ")";
            String existenceCheckSQL = "SELECT COUNT(*) FROM top_players";
            String insertSQL = "INSERT INTO top_players (name, high_score) VALUES (?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL);
            preparedStatement.executeUpdate();

            System.out.println("Table 'top_players' created successfully.");

            preparedStatement = connection.prepareStatement(existenceCheckSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                int rowCount = resultSet.getInt(1);
                System.out.println("Number of rows in the table: " + rowCount);

                if (rowCount == 0)
                {
                    preparedStatement = connection.prepareStatement(insertSQL);

                    // First Entry
                    preparedStatement.setString(1, "AAA");
                    preparedStatement.setInt(2, defaultFirstPlace);
                    preparedStatement.executeUpdate();

                    // Second Entry
                    preparedStatement.setString(1, "BBB");
                    preparedStatement.setInt(2, defaultSecondPlace);
                    preparedStatement.executeUpdate();

                    // Third Entry
                    preparedStatement.setString(1, "CCC");
                    preparedStatement.setInt(2, defaultThirdPlace);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Replace the name and score of an existing database entry using 'place' to determine
     * which entry to edit.
     * @param name The new name of the database entry.
     * @param playerScore The new score of the database entry.
     * @param place What place the current player came in. Used to determine which entry to edit.
     */
    public void editEntry(String name, int playerScore, int place)
    {
        try
        {
            URL resourceUrl = Database.class.getResource(FILENAME);

            assert resourceUrl != null;
            dbPath = resourceUrl.getPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath))
        {
            // Update the database to reflect changes to player data.
            String updateSQL = "UPDATE top_players SET name = ?, high_score = ? WHERE id = ?";

            try (PreparedStatement prepStatement = connection.prepareStatement(updateSQL))
            {
                prepStatement.setString(1, name);
                prepStatement.setInt(2, playerScore);
                prepStatement.setInt(3, place);
                prepStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve all database entries in the form of a PlayerData object.
     * @return A PlayerData object containing the names and scores of all database entries.
     */
    public PlayerData[] retrieveEntries()
    {
        PlayerData[] playerData = new PlayerData[3];
        int counter = 0;

        try
        {
            URL resourceUrl = Database.class.getResource(FILENAME);

            assert resourceUrl != null;
            dbPath = resourceUrl.getPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath))
        {
            String selectSQL = "SELECT * FROM top_players ORDER BY high_score DESC";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                int score = resultSet.getInt("high_score");

                playerData[counter] = new PlayerData(name, score);
                counter++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerData;
    }

}
