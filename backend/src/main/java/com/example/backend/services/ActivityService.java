package com.example.backend.services;

import com.example.backend.entities.Activity;
import com.example.backend.repo.IActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {
    @Inject
    private IActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.getAllActivities();
    }
    public Activity getActivityById(int id) {
        return activityRepository.getActivityById(id);
    }
    public Activity addActivity(Activity activity) {
        return activityRepository.addActivity(activity);
    }

    public Activity getActivityByName(String name) {
        return activityRepository.getActivityByName(name);
    }
}
