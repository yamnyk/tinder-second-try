package ua.danit.dao;

import ua.danit.entity.User;
import ua.danit.utils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private User getUserFromResultSet(ResultSet rSet) throws SQLException {
        int id = rSet.getInt("id");
        String name = rSet.getString("name");
        String photo = rSet.getString("photo");
        String liked = rSet.getString("liked");
        Boolean userLiked = null;

        if(liked != null){
            userLiked = "1".equals(liked);
        }

        return new User(id, name, photo, userLiked);
    }

    public User getNotLikedUser(){
        try (
                Connection connection = DBConnector.getConnectionToDB();
                Statement statement = connection.createStatement();
        ) {
            ResultSet rSet = statement.executeQuery("SELECT * FROM zozich_users WHERE liked IS NULL LIMIT 1");
            if(rSet.next()){
                return getUserFromResultSet(rSet);
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveLike(String id, boolean liked){
        try(Connection connection = DBConnector.getConnectionToDB();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE zozich_users SET liked=? WHERE id =?")){

            preparedStatement.setBoolean(1, liked);
            preparedStatement.setInt(2, Integer.parseInt(id));

            preparedStatement.executeUpdate();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getLikedUsers(){
        List<User> users = new ArrayList<>();
        try (
                Connection connection = DBConnector.getConnectionToDB();
                Statement statement = connection.createStatement();
        ) {
            ResultSet rSet = statement.executeQuery("SELECT * FROM zozich_users WHERE liked IS TRUE");
            if (rSet != null) {
                while(rSet.next()){
                    users.add(getUserFromResultSet(rSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByLoginAndPassword(String login, String pass){
        try(Connection connection = DBConnector.getConnectionToDB();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM zozich_users WHERE name=? AND password=?")){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            preparedStatement.execute();

            ResultSet rSet = preparedStatement.executeQuery();

            if(rSet.next()){
                return getUserFromResultSet(rSet);
            }

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
