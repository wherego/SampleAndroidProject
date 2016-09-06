package com.app.sampleandroidproject.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.ActivitiesManager;

import java.util.List;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.base
 *
 * @Author: xie
 * @Time: 2016/9/2 9:48
 * @Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private TextView tittle_text;

    protected FragmentManager fragmentManager;

    protected abstract int getContentResource();

    protected abstract void startWork(Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        ActivitiesManager.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        startWork(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager == null) {
            ActivitiesManager.getInstance().finishActivity();
        } else {
            if (fragmentManager.getBackStackEntryCount() == 0) {
                ActivitiesManager.getInstance().finishActivity();
            } else {
                fragmentManager.popBackStack();
            }
        }
        super.onBackPressed();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() != 0) {
            for (Fragment fragment : fragments) fragment = null;
        }
        super.onDestroy();
    }

    private View getContentView() {
        View root = LayoutInflater.from(this).inflate(R.layout.layout_base, null);
        FrameLayout content = (FrameLayout) root.findViewById(R.id.content_frame);
        content.addView(LayoutInflater.from(this).inflate(getContentResource(), null));
        return root;
    }

    /**
     * @return void
     * @Title: setTranslucentStatus
     * @Description: (设置标题栏透明色)
     * @params []
     */
    private void setTranslucentStatus() {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }

    private void headerInit() {
        ViewStub stub = ((ViewStub) findViewById(R.id.vs_title));
        stub.inflate();
        tittle_text = (TextView) findViewById(R.id.tittle_text);
    }

    public void setTittleText(String title) {
        headerInit();
        tittle_text.setText(title);
    }

}