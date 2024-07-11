package com.example.daybook_frontend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.daybook_frontend.R;
import com.example.daybook_frontend.api.Api;
import com.example.daybook_frontend.api.NetworkService;
import com.example.daybook_frontend.model.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherFragment extends Fragment {

    private RadioGroup dayRadioGroup;
    private Spinner subjectSpinner;
    private EditText taskDescriptionEditText;
    private Button saveTaskButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        dayRadioGroup = view.findViewById(R.id.dayRadioGroup);
        subjectSpinner = view.findViewById(R.id.subjectSpinner);
        taskDescriptionEditText = view.findViewById(R.id.taskDescriptionEditText);
        saveTaskButton = view.findViewById(R.id.saveTaskButton);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < days.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(View.generateViewId());
            radioButton.setText(days[i]);
            dayRadioGroup.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
            }
        }
        String[] subjects = {"Math", "Science", "History", "English", "Art", "Music", "Physics", "Chemistry", "Biology", "IT"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, subjects);
        subjectSpinner.setAdapter(adapter);
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedDayOfWeek = dayRadioGroup.indexOfChild(view.findViewById(dayRadioGroup.getCheckedRadioButtonId())) + 1;
                String subject = subjectSpinner.getSelectedItem().toString();
                String description = taskDescriptionEditText.getText().toString();

                if (selectedDayOfWeek > 0 && !subject.isEmpty() && !description.isEmpty()) {
                    Task task = new Task(null, description, selectedDayOfWeek, subject);
                    sendTaskToServer(task);
                } else {
                    // Show error message to the user
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void sendTaskToServer(Task task) {
        Api apiService = NetworkService.getInstance().getApi();
        Call<Task> call = apiService.addTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Task saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to save task", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}