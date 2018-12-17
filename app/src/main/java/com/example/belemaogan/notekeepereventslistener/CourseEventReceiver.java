package com.example.belemaogan.notekeepereventslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CourseEventReceiver extends BroadcastReceiver {

    //my app defined action
    public static final String ACTION_COURSE_EVENT = "com.secureidltd.belemaogan.notekeeper.action.COURSE_EVENT";

    //the extras for the intent that will be passed to sendBroadcast
    public static final String EXTRA_COURSE_ID = "com.secureidltd.belemaogan.notekeeper.extras.COURSE_ID";
    public static final String EXTRA_COURSE_MESSAGE = "com.secureidltd.belemaogan.notekeeper.extras.COURSE_MESSAGE";

    private CourseEventsDisplayCallbacks mCourseEventsDisplayCallbacks;

    public void setCourseEventsDisplayCallbacks(CourseEventsDisplayCallbacks courseEventsDisplayCallbacks) {
        mCourseEventsDisplayCallbacks = courseEventsDisplayCallbacks;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null){
            if (ACTION_COURSE_EVENT.equals(intent.getAction())){
                String courseId = intent.getStringExtra(EXTRA_COURSE_ID);
                String message = intent.getStringExtra(EXTRA_COURSE_MESSAGE);

                if (mCourseEventsDisplayCallbacks != null){
                    mCourseEventsDisplayCallbacks.onEventReceived(courseId, message);
                }
            }
        }

    }
}
