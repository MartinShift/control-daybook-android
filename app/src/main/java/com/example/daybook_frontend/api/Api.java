package com.example.daybook_frontend.api;

import com.example.daybook_frontend.model.Task;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api {

    @POST("/tasks")
    Call<Task> addTask(@Body Task task);

    @PUT("/tasks/{id}")
    Call<Task> updateTask(@Path("id") Long id, @Body Task task);

    @DELETE("/tasks/{id}")
    Call<Void> deleteTask(@Path("id") Long id);

    @GET("/tasks/{id}")
    Call<Task> getTaskById(@Path("id") Long id);

    @GET("/tasks")
    Call<List<Task>> getAllTasks();

    @GET("/tasks/dayOfWeek/{dayOfWeek}")
    Call<List<Task>> getByDayOfWeek(@Path("dayOfWeek") int dayOfWeek);
}