package com.example.fitnessproject3fall.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FitnessDAO {
    @Query("select * from User")
    List<User> getAllUser();

    @Query("select * from DirectMsg")
    List<DirectMsg> getAllMessages();

    @Query("select * from DirectMsg where group_id =:group_id")
    List<DirectMsg> searchMsg(int group_id);

    @Query("select * from Goals where date =:date")
    Goals searchGoal(String date);

    @Query("select * from User where user_id =:user_id")
    User searchUser(int user_id);

    @Query("select * from User where username =:username and password =:password")
    User logIn(String username, String password);

    @Query("SELECT * FROM User where username LIKE :search")
    User getUsername(String search);

    @Insert
    void addUser(User user);
    @Insert
    void addMsg(DirectMsg msg);
    @Insert
    void addCoach(Coach msg);
    @Insert
    void addGoals(Goals goal);
    @Insert
    void addLinks(Links link);
    @Insert
    void addVideos(Videos video);

}