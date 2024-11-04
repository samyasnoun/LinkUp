package entity;

import java.util.Calendar;
import java.util.List;

public class CommonUser implements User{

    private String name;
    private String password;
    private List<User> friends;
    private Calendar calendar;
    private String language;

    public CommonUser (String name, String password, String language) {
        this.name = name;
        this.password = password;
        this.language = language;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<User> getFriends() {
        return friends;
    }

    @Override
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public Calendar getCalendar() {
        return calendar;
    }

    @Override
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}