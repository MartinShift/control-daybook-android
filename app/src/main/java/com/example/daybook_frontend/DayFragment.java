package com.example.daybook_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybook_frontend.api.NetworkService;
import com.example.daybook_frontend.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DayFragment extends Fragment {

    private int dayNumber;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;

    public static DayFragment newInstance(int dayNumber) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        args.putInt("dayNumber", dayNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayNumber = getArguments().getInt("dayNumber");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        TextView textView = view.findViewById(R.id.textDay);
        textView.setText(String.format(Locale.getDefault(), "Tasks for Day %d", dayNumber + 1));

        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter(new ArrayList<>());
        tasksRecyclerView.setAdapter(taskAdapter);

        fetchTasksForDay(dayNumber + 1);

        return view;
    }

    private void fetchTasksForDay(int dayNumber) {
        NetworkService.getInstance().getApi().getByDayOfWeek(dayNumber).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    taskAdapter = new TaskAdapter(response.body());
                    tasksRecyclerView.setAdapter(taskAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }
}