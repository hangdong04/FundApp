package com.example.aaa.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aaa.test.EventBus.FABButtonSetupEvent;
import com.example.aaa.test.EventBus.RMFMessageEvent;
import com.example.aaa.test.api.APIService;
import com.example.aaa.test.model.Fund;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AAA on 26/1/2560.
 */

public class RMFFundFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    Retrofit retrofit;
    APIService api;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().post(new FABButtonSetupEvent("RMF"));
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:7000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(APIService.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fundall_tab_re, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (((MainActivity) getActivity()).FAB_Status) {
                    ((MainActivity) getActivity()).hideFAB();
                    ((MainActivity) getActivity()).FAB_Status = false;
                }
                return false;
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:7000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService api = retrofit.create(APIService.class);

        Call<List<Fund>> call = api.getRMFFund();

        call.enqueue(new Callback<List<Fund>>() {
            @Override
            public void onResponse(Call<List<Fund>> call, Response<List<Fund>> response) {
                List<Fund> funds = response.body();
                notifyAdapter(funds);
                for (Fund fund : funds) {
                    System.out.println(fund.getName() + " (" + fund.getName_th()+ ")");
                }
                Log.d("data", funds.toString());
            }

            @Override
            public void onFailure(Call<List<Fund>> call, Throwable t) {
                System.out.println(call.request().url() + ": failed: " + t);
            }
        });
        return view;
    }

    private void notifyAdapter(List<Fund> data){
        FundAdapter_RV adapter = new FundAdapter_RV(getActivity(),data);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void onMessageEvent(RMFMessageEvent event){
        Call<List<Fund>> call = api.getRMFbyRisk(event.riskLevel);

        call.enqueue(new Callback<List<Fund>>() {
            @Override
            public void onResponse(Call<List<Fund>> call, Response<List<Fund>> response) {
                List<Fund> funds = response.body();
                notifyAdapter(funds);
                for (Fund fund : funds) {
                    System.out.println(fund.getName() + " (" + fund.getName_th()+ ")");
                }
                Log.d("data", funds.toString());
            }

            @Override
            public void onFailure(Call<List<Fund>> call, Throwable t) {
                System.out.println(call.request().url() + ": failed: " + t);
            }
        });
    }

}