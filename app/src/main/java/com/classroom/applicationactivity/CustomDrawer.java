package com.classroom.applicationactivity;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import util.ToastMessages;

/**
 * Created by arms0077 on 11/9/2015.
 */
public class CustomDrawer extends DrawerLayout {

    public CustomDrawer(Context context) {
        super(context);
    }

    public CustomDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onDrawerStateChanged(int newState) {
        if (newState == DrawerLayout.STATE_DRAGGING) {
            ToastMessages.shortToast("DRAWER ACCESS", 16, getContext());
        }
    }

}
