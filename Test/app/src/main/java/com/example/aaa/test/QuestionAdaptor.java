package com.example.aaa.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aaa.test.model.Question;

import java.util.List;

/**
 * Created by phatt on 12/2/2560.
 */

public class QuestionAdaptor extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Object> questions;
    private final int QUESTION =0, BUTTON = 1;
    LayoutInflater inflater;

    public QuestionAdaptor(Context context, List<Object> questions) {
        super();
        this.mContext= context;
        this.questions = questions;
        this.inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView titleText;
        RadioButton choice1;
        RadioButton choice2;
        RadioButton choice3;
        RadioButton choice4;
        RadioGroup choiceGroup;
        ViewHolder1(View v){
            super(v);
            titleText = (TextView)v.findViewById(R.id.question_title);
            choiceGroup = (RadioGroup)v.findViewById(R.id.choice_group);
            choice1 = (RadioButton)v.findViewById(R.id.choice1);
            choice2 = (RadioButton)v.findViewById(R.id.choice2);
            choice3 = (RadioButton)v.findViewById(R.id.choice3);
            choice4 = (RadioButton)v.findViewById(R.id.choice4);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {

        Button button;
        ViewHolder3(View v){
            super(v);
            button = (Button)v.findViewById(R.id.done_button);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case QUESTION:
                View v1 = inflater.inflate(R.layout.question_row,parent,false);
                viewHolder = new ViewHolder1(v1);
                break;
            case BUTTON:
                View v2 = inflater.inflate(R.layout.recycler_button,parent,false);
                viewHolder = new ViewHolder3(v2);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case QUESTION:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                Question qObj = (Question)questions.get(position);
                vh1.titleText.setText(qObj.getTitle());
                List<String> choice = qObj.getItems();
                if (!choice.isEmpty()) {
                    vh1.choice1.setText(choice.get(0));
                    vh1.choice2.setText(choice.get(1));
                    vh1.choice3.setText(choice.get(2));
                    vh1.choice4.setText(choice.get(3));
                }
                break;
            case BUTTON:
                ViewHolder3 vh2 = (ViewHolder3) holder;
                vh2.button.setClickable(true);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (questions.get(position) instanceof Question){
            return QUESTION;
        }else if (questions.get(position) instanceof String){
            return BUTTON;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}