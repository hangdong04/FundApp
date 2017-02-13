package com.example.aaa.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.example.aaa.test.EventBus.NotifyDialogEvent;
import com.example.aaa.test.model.Question;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class InterviewActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView titleText;
    List<Object> items;
    QuestionAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        titleText = (TextView)findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleText.setText("แบบสอบถามการลงทุน");
        items = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        prepareData();
    }

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

    private void prepareData(){
        String title = getString(R.string.question_1);
        List<String> choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q11));
        choice.add(getString(R.string.choice_q12));
        choice.add(getString(R.string.choice_q13));
        choice.add(getString(R.string.choice_q14));
        Question question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_2);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q21));
        choice.add(getString(R.string.choice_q22));
        choice.add(getString(R.string.choice_q23));
        choice.add(getString(R.string.choice_q24));
        question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_3);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q31));
        choice.add(getString(R.string.choice_q32));
        choice.add(getString(R.string.choice_q33));
        choice.add(getString(R.string.choice_q34));
        question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_4);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q41));
        choice.add(getString(R.string.choice_q42));
        choice.add(getString(R.string.choice_q43));
        choice.add(getString(R.string.choice_q44));
        question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_5);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q51));
        choice.add(getString(R.string.choice_q52));
        choice.add(getString(R.string.choice_q53));
        choice.add(getString(R.string.choice_q54));
        question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_6);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q61));
        choice.add(getString(R.string.choice_q62));
        choice.add(getString(R.string.choice_q63));
        choice.add(getString(R.string.choice_q64));
        question = new Question(title,choice);
        items.add(question);
        title = getString(R.string.question_7);
        choice = new ArrayList<>();
        choice.add(getString(R.string.choice_q71));
        choice.add(getString(R.string.choice_q72));
        choice.add(getString(R.string.choice_q73));
        choice.add(getString(R.string.choice_q74));
        question = new Question(title,choice);
        items.add(question);
        items.add("button");
        adaptor = new QuestionAdaptor(getApplicationContext(),items);
        recyclerView.setAdapter(adaptor);
    }

    private void goMain(){
        Intent intent = new Intent(InterviewActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Subscribe
    public void onMessageEvent(NotifyDialogEvent event) {
        int score = event.score;
        String message = getString(R.string.dialog_text);
        String riskMessage;
        if (score <=7){
            riskMessage = getString(R.string.low_risk);
        }
        else if (score<=14){
            riskMessage = getString(R.string.medium_risk);
        }else {
            riskMessage = getString(R.string.high_risk);
        }
        SpannableString text = new SpannableString(message + riskMessage);
        text.setSpan(new StyleSpan(Typeface.BOLD), message.length(),message.length()+riskMessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(InterviewActivity.this,R.style.AppCompatAlertDialogStyle);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(text);
        builder.setPositiveButton(R.string.dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goMain();
            }
        });
        builder.show();
    }

}
