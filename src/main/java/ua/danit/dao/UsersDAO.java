package ua.danit.dao;

import ua.danit.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDAO {
    private List<User> users = new ArrayList<>();

    public UsersDAO() {
        users.add(new User("SluttySlut", "http://www2.news.ykt.ru/upload/image/2016/10/49270/main_big.jpg?1506411572"));
        users.add(new User("Joanna", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpOGFwFUdNH_n-DT6Cx-AhDGI_0zsSzI3Z4nlCeOAZnh50xJnj7g"));
        users.add(new User("Kurva", "https://www.wikihow.com/images/thumb/4/40/Make-Everyone-Love-You-%28Teen-Girls%29-Step-1.jpg/aid2097423-v4-728px-Make-Everyone-Love-You-%28Teen-Girls%29-Step-1.jpg"));
    }

    public User getUserByIndex(int currentUserIndex) {
        return users.get(currentUserIndex % users.size());
    }

    public List<User> returnAllUsers(){
        return users;
    }

    public List<User> getLikedUsers(){
        return users.stream()
                .filter(user -> Boolean.TRUE.equals(user.getLiked()))
                .collect(Collectors.toList());
    }
}
