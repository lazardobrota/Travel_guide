package com.example.backend.repo;

import com.example.backend.entities.Activity;

import java.util.List;

public interface IActivityArticleRepo {

    List<Activity> getAllActivitiesForArticleId(int articleId);
    void addAllConnections(int articleId, List<Integer> activityIds);
}
