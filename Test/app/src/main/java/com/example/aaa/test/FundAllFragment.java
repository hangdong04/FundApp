package com.example.aaa.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by AAA on 26/1/2560.
 */

public class FundAllFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fundall_tab_re, container, false);
//        listView = (ListView)view.findViewById(R.id.fundAllList);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        String[] sName = { "KT-MINING", "KT-MAI", "KFJAPANRMF", "KTMSEQ", "KF-HJAPAND",
        "KFDYNAMIC", "KFDNM-D", "SCBUSSM", "ASP-NGF", "SCBCEH2" };

        String[] fName = { "กองทุนเปิดเคแทม เวิลด์ เมทัล แอนด์ ไมน์นิ่ง ฟันด์", "กองทุนเปิดกรุงไทยหุ้น เอ็ม เอ ไอ", "กองทุนเปิดกรุงศรีเจแปนเพื่อการเลี้ยงชีพ",
        "กองทุนเปิดกรุงไทยหุ้น Mid-Small Cap", "กรุงศรีเจแปนเฮดจ์ปันผล", "กรุงศรีหุ้นไดนามิค", "กรุงศรีหุ้นไดนามิคปันผล", "กองทุนเปิดไทยพาณิชย์ ยูเอสสมอลแคป",
        "กองทุนเปิดแอสเซทพลัสนิปปอนโกรท", "กองทุนเปิดไทยพาณิชย์หุ้นจีน THB เฮ็ดจ์ 2"};

        String[] Nav = { "5.5839", "11.0131", "10.4596", "12.7039", "9.8250", "31.5554",
        "11.1466", "11.8389", "9.1763", "10.0681"};
        FundAdapter_RV adapter = new FundAdapter_RV(getActivity(),sName,fName,Nav);
        recyclerView.setAdapter(adapter);
//        FundAdapter adapter = new FundAdapter(getActivity(), sName, fName, Nav);
//        listView.setAdapter(adapter);
        return view;
    }
}
