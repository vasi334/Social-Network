package com.example.lab4try.repository;


import com.example.lab4try.domain.CurrentUser;
import com.example.lab4try.domain.User;
import com.example.lab4try.domain.validators.ValidatorUser;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RepositoryDatabase implements Repository<Integer, User>{

    private String url;
    private String username;
    private String password;
    private ValidatorUser validator;


    public RepositoryDatabase(String url, String username, String password, ValidatorUser validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public void saveFriendship(Integer firstFriendID, Integer secondFriendID)
    {
        String sql = "insert into friendships (firstFriendID, secondFriendID) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFriendship(Integer firstFriendID, Integer secondFriendID)
    {
        String sql = "delete from friendships where (id_friend_1 = ? " +
                "and id_friend_2 = ?) or (id_friend_2 = ? and id_friend_1 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);
            ps.setInt(3, secondFriendID);
            ps.setInt(4, firstFriendID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFriendshipRequest(Integer firstFriendID, Integer secondFriendID) {
        if (!checkAlreadySentFriendRequest(firstFriendID, secondFriendID)) {
            String sql = "insert into friend_requests (id_friend_1, id_friend_2) values (?, ?)";

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, firstFriendID);
                ps.setInt(2, secondFriendID);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkAlreadySentFriendRequest(Integer firstFriendID, Integer secondFriendID){
        String sql = "select 1\n" +
                "from friend_requests\n" +
                "where (id_friend_1 = ? and id_friend_2 = ?)" +
                "or (id_friend_2 = ? and id_friend_1 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);
            ps.setInt(3, secondFriendID);
            ps.setInt(4, firstFriendID);

            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next())
            {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkAlreadyFriends(Integer firstFriendID, Integer secondFriendID){
        String sql = "SELECT 1\n" +
                "FROM friendships\n" +
                "WHERE (id_friend_1 = ? AND id_friend_2 = ?)" +
                "OR (id_friend_2 = ? AND id_friend_1 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);
            ps.setInt(3, secondFriendID);
            ps.setInt(4, firstFriendID);

            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next())
            {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void deleteFriendshipRequest(Integer firstFriendID, Integer secondFriendID)
    {
        String sql = "delete from friend_requests where (id_friend_1 = ? " +
                "and id_friend_2 = ?) OR (id_friend_2 = ? and id_friend_1 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);
            ps.setInt(3, secondFriendID);
            ps.setInt(4, firstFriendID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void acceptFriendshipRequest(Integer firstFriendID, Integer secondFriendID)
    {
        String sql = "insert into friendships (id_friend_1, id_friend_2) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, firstFriendID);
            ps.setInt(2, secondFriendID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cascadeDeleteFriendships(Integer firstFriendID)
    {
        String sql = "delete from friendships where firstFriendID == firstFriendID";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(User user) {
        String sql = "insert into users (first_name, last_name, age, password, email) values (?, ?, ?, ?, ?)";
        validator.validate(user);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, User> getAll() {

        HashMap<Integer, User> users = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = Math.toIntExact(resultSet.getLong("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                User utilizator = new User(firstName, lastName, age, password, email, validator);
                utilizator.setId(id);
                users.put(utilizator.getId(), utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public HashMap<Integer, User> getAllFriends(CurrentUser user)
    {
        HashMap<Integer, User> friends = new HashMap<>();

        Integer userID = user.getId();
        String sql = "SELECT * from friendships WHERE (id_friend_1 = ?) OR (id_friend_2 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userID);
            ps.setInt(2, userID);

            //ps.executeUpdate();
            ResultSet resultSet = ps.executeQuery(); {

            while (resultSet.next()) {
                Integer idFriend1 = resultSet.getInt("id_friend_1");
                Integer idFriend2 = resultSet.getInt("id_friend_2");
                Integer idFriend = idFriend1.equals(userID) ? idFriend2 : idFriend1;

                User utilizator = findOne(idFriend);
                utilizator.setId(idFriend);
                friends.put(utilizator.getId(), utilizator);
            }
            return friends;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

    @Override
    public HashMap<Integer, User> getAllFriendRequests(CurrentUser user) {
        HashMap<Integer, User> friends = new HashMap<>();

        Integer userID = user.getId();
        String sql = "SELECT * from friend_requests WHERE (id_friend_1 = ?) OR (id_friend_2 = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userID);
            ps.setInt(2, userID);

            //ps.executeUpdate();
            ResultSet resultSet = ps.executeQuery(); {

                while (resultSet.next()) {
                    Integer idFriend1 = resultSet.getInt("id_friend_1");
                    Integer idFriend2 = resultSet.getInt("id_friend_2");
                    Integer idFriend = idFriend1.equals(userID) ? idFriend2 : idFriend1;

                    User utilizator = findOne(idFriend);
                    utilizator.setId(idFriend);
                    friends.put(utilizator.getId(), utilizator);
                }
                return friends;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User findOne(Integer integer) {

        HashMap<Integer, User> allUsers = this.getAll();

        List<User> values = allUsers.values()
                .stream().toList();

        User userToReturn = null;

        for (User user: values) {
            if(Objects.equals(user.getId(), integer)){
                userToReturn = user;
            }
        }

        return userToReturn;
    }

    @Override
    public void delete(Integer integer) {}

    @Override
    public User update(User entity) {
        return null;
    }
}
