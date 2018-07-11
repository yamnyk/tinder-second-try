package ua.danit.dao;

import ua.danit.entity.User;
import ua.danit.utils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        ResultSet rSet = getResultSet("SELECT * FROM zozich_users WHERE liked IS NULL LIMIT 1");

        try{
            return getUserIfRSetNotNull(rSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveLike(int id, boolean liked){
        try(PreparedStatement statement =
                    getPreparedStatement("UPDATE zozich_users SET liked=? WHERE id =?")){

            statement.setBoolean(1, liked);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getLikedUsers(){
        List<User> users = new ArrayList<>();
        ResultSet rSet = getResultSet("SELECT * FROM zozich_users WHERE liked IS TRUE");

        try{
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
        try(PreparedStatement preparedStatement = getPreparedStatement("SELECT * FROM zozich_users" +
                "WHERE name=? AND password=?")){
            if (preparedStatement != null) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, pass);
                preparedStatement.execute();

                ResultSet rSet = preparedStatement.executeQuery();

                return getUserIfRSetNotNull(rSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet getResultSet(String sql){
        try(Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement()){

            return statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private PreparedStatement getPreparedStatement(String sql){
        try(Connection connection = DBConnector.getConnection();
        PreparedStatement pStatement = connection.prepareStatement(sql)){

            return pStatement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
         return null;
    }

    private User getUserIfRSetNotNull(ResultSet rSet) throws SQLException {
        if(rSet.next()){
            return getUserFromResultSet(rSet);
        }
        return null;
    }
}
