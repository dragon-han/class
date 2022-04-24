package com.alvin.aclass.fragment.no;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alvin.aclass.MySQLiteOpenHelper;
import com.alvin.aclass.R;
import com.alvin.aclass.fragment.HomeFragment;
import com.alvin.aclass.pojo.Course;

public class SeeFragment extends Fragment {

    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see, container, false);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext());

        Intent intent = getActivity().getIntent();

        final Course seeCourse = (Course) intent.getSerializableExtra("seeCourse");
        final TextView seeCourseName = view.findViewById(R.id.see_course_name);
        final TextView seeDay = view.findViewById(R.id.see_week);
        final TextView seeStart = view.findViewById(R.id.see_classes_begin);
        final TextView seeEnd = view.findViewById(R.id.see_classes_ends);
        final TextView seeTeacher = view.findViewById(R.id.see_teacher_name);
        final TextView seeClassRoom = view.findViewById(R.id.see_class_room);
        seeCourseName.setText(seeCourse.getCourseName());
        seeDay.setText(String.valueOf(seeCourse.getDay()));
        seeStart.setText(String.valueOf(seeCourse.getStart()));
        seeEnd.setText(String.valueOf(seeCourse.getEnd()));
        seeTeacher.setText(seeCourse.getTeacher());
        seeClassRoom.setText(seeCourse.getClassRoom());

        Button delBtn = view.findViewById(R.id.btn_del);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeFragment.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",true);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });


        //修改按钮被按下时
        Button ReviseBtn = (Button)view.findViewById(R.id.btn_revise);
        ReviseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeFragment.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",false);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("SeeCourseActivity","修改的返回来了");
    }
}