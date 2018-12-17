package com.example.belemaogan.notekeepereventslistener;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseEventsDisplayCallbacks{

    ArrayList<String> mCourseEvents;
    ArrayAdapter<String> mCourseEventsAdapter;
    private CourseEventReceiver mCourseEventReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCourseEvents = new ArrayList<>();
        mCourseEventsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mCourseEvents);

        ListView listView = findViewById(R.id.list_course_events);
        listView.setAdapter(mCourseEventsAdapter);

        setUpCourseEventReceiver();
    }

    private void setUpCourseEventReceiver() {
        mCourseEventReceiver = new CourseEventReceiver();
        mCourseEventReceiver.setCourseEventsDisplayCallbacks(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CourseEventReceiver.ACTION_COURSE_EVENT);
        registerReceiver(mCourseEventReceiver, intentFilter);
    }

    @Override
    public void onEventReceived(String courseId, String courseMessage) {
        String displayText = courseId + ": "+ courseMessage;
        mCourseEvents.add(displayText);
        mCourseEventsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCourseEventReceiver);
    }
}
