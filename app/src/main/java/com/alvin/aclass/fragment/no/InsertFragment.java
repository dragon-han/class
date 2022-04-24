package com.alvin.aclass.fragment.no;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.alvin.aclass.R;
import com.alvin.aclass.fragment.HomeFragment;
import com.alvin.aclass.pojo.Course;

public class InsertFragment extends Fragment {

    EditText inputCourseName;
    EditText inputTeacher;
    EditText inputClassRoom;

    Spinner inputDay;
    Spinner inputStart;
    Spinner inputEnd;

    boolean isRevise = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);

        inputCourseName = view.findViewById(R.id.course_name);
        inputTeacher = view.findViewById(R.id.teacher_name);
        inputClassRoom = view.findViewById(R.id.class_room);

        inputDay = view.findViewById(R.id.week);
        inputStart = view.findViewById(R.id.classes_begin);
        inputEnd = view.findViewById(R.id.classes_ends);

        Intent intent = getActivity().getIntent();
        final Course ReviseCourse = (Course) intent.getSerializableExtra("ReviseCourse");
        isRevise = intent.getBooleanExtra("isRevise", false);

        Button okButton = (Button) view.findViewById(R.id.button);

        String courseName;
        String teacher;
        String classRoom;
        String day;
        String start;
        String end;

        if (isRevise) {

            inputCourseName.setText(ReviseCourse.getCourseName());
            inputClassRoom.setText(ReviseCourse.getClassRoom());
            inputTeacher.setText(ReviseCourse.getTeacher());
            setSpinnerDefaultValue(inputDay, String.valueOf(ReviseCourse.getDay()));
            setSpinnerDefaultValue(inputStart, String.valueOf(ReviseCourse.getStart()));
            setSpinnerDefaultValue(inputEnd, String.valueOf(ReviseCourse.getEnd()));


            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String courseName = inputCourseName.getText().toString();
                    String teacher = inputTeacher.getText().toString();
                    String classRoom = inputClassRoom.getText().toString();
                    String day = inputDay.getSelectedItem().toString();
                    String start = inputStart.getSelectedItem().toString();
                    String end = inputEnd.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(getContext(), "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    }

                    Course newCourse = new Course(courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));

                    Intent intent = new Intent();
                    intent.putExtra("PreCourse", ReviseCourse);
                    intent.putExtra("newCourse", newCourse);
                    Log.d("AddCourseActivity", "我进了了");
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
            });

        } else {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String courseName = inputCourseName.getText().toString();
                    String teacher = inputTeacher.getText().toString();
                    String classRoom = inputClassRoom.getText().toString();
                    String day = inputDay.getSelectedItem().toString();
                    String start = inputStart.getSelectedItem().toString();
                    String end = inputEnd.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(getContext(), "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    } else {
                        Course course = new Course(courseName, teacher, classRoom,
                                Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));
                        Intent intent = new Intent(getContext(), HomeFragment.class);
                        intent.putExtra("course", course);

                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                }
            });
        }
//       ininView(view);
//        view.setFinishOnTouchOutside(false);
        return view;
    }

    private void ininView(View view) {

        inputCourseName = (EditText) view.findViewById(R.id.course_name);
        inputTeacher = (EditText) view.findViewById(R.id.teacher_name);
        inputClassRoom = (EditText) view.findViewById(R.id.class_room);

        inputDay = (Spinner) view.findViewById(R.id.week);
        inputStart = (Spinner) view.findViewById(R.id.classes_begin);
        inputEnd = (Spinner) view.findViewById(R.id.classes_ends);

        Button okButton = (Button) view.findViewById(R.id.button);
    }

    private void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {

            if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);
                break;
            }
        }
    }
}