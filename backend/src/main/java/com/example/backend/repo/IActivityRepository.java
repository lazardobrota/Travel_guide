package com.example.backend.repo;

import com.example.backend.entities.Activity;

import java.util.List;

public interface IActivityRepository {

    List<Activity> getAllActivities();
    Activity getActivityById(int id);
    Activity getActivityByName(String name);
    Activity addActivity(Activity activity);
}
