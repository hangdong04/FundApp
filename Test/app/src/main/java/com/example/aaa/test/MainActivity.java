package com.example.aaa.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaa.test.EventBus.FABButtonSetupEvent;
import com.example.aaa.test.EventBus.LTFMessageEvent;
import com.example.aaa.test.EventBus.RMFMessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    TextView titleText;
    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    String mode = "LTF";

    public boolean FAB_Status = false;

    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleText = (TextView)findViewById(R.id.toolbar_title);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("LTF Fund"));
        tabLayout.addTab(tabLayout.newTab().setText("RMF Fund"));
        setSupportActionBar(toolbar);
        replaceFragment(new LTFFundFragment());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleText.setText("กองทุนรวม");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
        ButtonLTF();
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RMFMessageEvent("3"));
                titleText.setText("กองทุนรวม RMF ความเสี่ยงสูง");
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RMFMessageEvent("2"));
                EventBus.getDefault().post(new LTFMessageEvent("3"));
                if (mode.equals("RMF")){
                    titleText.setText("กองทุนรวม RMF ความเสี่ยงกลาง");
                }else if (mode.equals("LTF")){
                    titleText.setText("กองทุนรวม LTF ความเสี่ยงสูง");
                }
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RMFMessageEvent("1"));
                EventBus.getDefault().post(new LTFMessageEvent("2"));
                if (mode.equals("RMF")){
                    titleText.setText("กองทุนรวม RMF ความเสี่ยงต่ำ");
                }else if (mode.equals("LTF")){
                    titleText.setText("กองทุนรวม LTF ความเสี่ยงกลาง");
                }
            }
        });

        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(FAB_Status){
                    hideFAB();
                    FAB_Status = false;
                }
                if (tab.getPosition() == 0){
                    titleText.setText("กองทุนรวม LTF");
                    replaceFragment(new LTFFundFragment());
                }else {
                    titleText.setText("กองทุนรวม RMF");
                    replaceFragment(new RMFFundFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }

    private void ButtonLTF(){
        fab1.setVisibility(View.GONE);
    }

    private void ButtonRMF(){
        fab1.setVisibility(View.VISIBLE);
    }
    private void expandFAB() {
        if (mode.equals("RMF")){
            //Floating Action Button 1
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin += (int) (fab1.getWidth() * 0.20);
            layoutParams.bottomMargin += (int) (fab1.getHeight() * 4.3);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(show_fab_1);
            fab1.setClickable(true);
        }
        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 0.20);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 3.0);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.20);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }

    public void hideFAB() {
        if (mode.equals("RMF")) {
            //Floating Action Button 1
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams.rightMargin -= (int) (fab1.getWidth() * 0.20);
            layoutParams.bottomMargin -= (int) (fab1.getHeight() * 4.3);
            fab1.setLayoutParams(layoutParams);
            fab1.startAnimation(hide_fab_1);
            fab1.setClickable(false);
        }

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 0.20);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 3.0);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.20);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }

    @Subscribe
    public void onMessageEvent(FABButtonSetupEvent event) {
        mode = event.mode;
        if (mode.equals("LTF")){
            ButtonLTF();
        }
        if (mode.equals("RMF")){
            ButtonRMF();
        }
    }
}
