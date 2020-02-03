package com.metamorphosis.metapos.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.metamorphosis.metapos.Activity.DashBoardActivity;
import com.metamorphosis.metapos.Api.ApiClient;
import com.metamorphosis.metapos.Api.ApiInterface;
import com.metamorphosis.metapos.Model.BarChartPojo;
import com.metamorphosis.metapos.Model.PieChartPojo;
import com.metamorphosis.metapos.Model.SalesPojo;
import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AppConstant;
import com.metamorphosis.metapos.session.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private BarChart mBarChart;
    private PieChart mPieChart;

    private ApiInterface apiInterface;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvBar, tvPie, tvTotalSaleAmount, tvTotalQuanty, tvSummary;

    private String totalSale, totalQty, strDate, startDateStr, date, amount;
    private Integer totalQty2;
    private LinearLayout layout1, layout2, barLayout, pieLayout;
    private ArrayList<String> date2;
    private ArrayList<String> amount2;
    private String storeId, shopName;

    private String TAG = "FragmentHome";


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mBarChart = (BarChart) view.findViewById(R.id.barChart);
        mPieChart = (PieChart) view.findViewById(R.id.pieChart);
        tvBar = (TextView) view.findViewById(R.id.tvBar);
        tvPie = (TextView) view.findViewById(R.id.tvPie);
        tvTotalSaleAmount = (TextView) view.findViewById(R.id.tvTotalSaleAmount);
        tvTotalQuanty = (TextView) view.findViewById(R.id.tvTotalQuantity);
        tvSummary = (TextView) view.findViewById(R.id.tvSummary);
        layout1 = (LinearLayout) view.findViewById(R.id.layout1);
        layout2 = (LinearLayout) view.findViewById(R.id.layout2);
        barLayout = (LinearLayout) view.findViewById(R.id.barLayout);
        pieLayout = (LinearLayout) view.findViewById(R.id.pieLayout);

        tvBar.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);

        tvPie.setVisibility(View.GONE);
        mPieChart.setVisibility(View.GONE);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yyyy");
        strDate = mdformat.format(calendar.getTime());
        Log.d(TAG,"Today" +strDate);


        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthLastDay = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        startDateStr = df.format(monthFirstDay);
        String endDateStr = df.format(monthLastDay);

        Log.e(TAG,"Days: " +startDateStr+" "+endDateStr);

        storeId = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_STORE_ID);
        shopName = Session.getStringFromSharePreference(getContext(), AppConstant.LOGIN_RESPONSE_SHOPNAME);

        Log.d(TAG, "Store Id: " +storeId);
        Log.d(TAG, "ShopName: " +shopName);


        getSummaryValue(startDateStr, strDate, storeId, shopName);
        getBarChart(storeId, shopName);
        getPieChart(storeId, shopName);



        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSummaryValue(startDateStr, strDate, storeId, shopName);
                getBarChart(storeId, shopName);
                getPieChart(storeId, shopName);
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    private void getSummaryValue(String start, String end , String StoreId, String ShopName){

        String startDate = start;
        String endDate = end;
        String storeId = StoreId;
        String shopName = ShopName;

        Log.d(TAG, "Start Sales: " +startDate);
        Log.d(TAG, "End Sales: " +endDate);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<SalesPojo>> call2 = apiInterface.getSalesGrowth(startDate, endDate, storeId, shopName);
        call2.enqueue(new Callback<List<SalesPojo>>(){
            @Override
            public void onResponse(Call<List<SalesPojo>> call2, Response<List<SalesPojo>> response) {
                try{
                    Log.d(TAG, "response_new sales: " + response.body());
                    if (response.isSuccessful() && response.body() !=null){
                        Log.d(TAG, "Successfull1 sales");

                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status Summary: " +status);

                        if(status.equals("200")) {
                            tvSummary.setVisibility(View.VISIBLE);
                            layout1.setVisibility(View.VISIBLE);
                            layout2.setVisibility(View.VISIBLE);
                            totalSale = String.valueOf(response.body().get(0).getSalesData().get(0).getAmount());
                            totalQty2 = response.body().get(0).getSalesData().get(6).getAmount().intValue();
                            Log.d(TAG, "Total Sale: " + totalSale);
                            Log.d(TAG, "Total Qty: " + totalQty2);


                            totalQty = String.valueOf(totalQty2);

                            tvTotalSaleAmount.setText(totalSale);
                            tvTotalQuanty.setText(totalQty);
                        }else {

                        }


                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<SalesPojo>> call, Throwable t) {

            }
        });

    }

    private void getBarChart(String StoreId, String ShopName){

        String storeId = StoreId;
        String shopName = ShopName;


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<BarChartPojo>> call = apiInterface.getBarGrowth(storeId, shopName);
        call.enqueue(new Callback<List<BarChartPojo>>() {
            @Override
            public void onResponse(Call<List<BarChartPojo>> call, Response<List<BarChartPojo>> response) {
                try{
                    Log.d(TAG, "response_new: " + response.body());

                    if(response.isSuccessful() && response.body() !=null){
                        Log.d(TAG, "Successfull1");

                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status: " +status);

                        if(status.equals("200")) {

                            date2 = new ArrayList<String>();
                            amount2 = new ArrayList<String>();
                            for (int i = 0; i < response.body().size(); i++) {
                                date2.add(response.body().get(i).getData().get(i).getDate());
                                //amount2.add(response.body().get(i).getSaleAmt());
                            }

                            StringBuilder strbul = new StringBuilder();
                            Iterator<String> iter = date2.iterator();
                            while (iter.hasNext()) {
                                strbul.append(iter.next());
                                if (iter.hasNext()) {
                                    strbul.append(", ");
                                }
                            }
                            //attrStList = strbul.toString();
                            date = strbul.toString();

                            StringBuilder strbul2 = new StringBuilder();
                            Iterator<String> iter2 = amount2.iterator();
                            while (iter2.hasNext()) {
                                strbul2.append(iter2.next());
                                if (iter2.hasNext()) {
                                    strbul2.append(", ");
                                }
                            }
                            //attrStList = strbul.toString();
                            amount = strbul2.toString();

                            Log.d(TAG, "Date Value: " + date);
                            Log.d(TAG, "Amount Value: " + amount);

                            List<BarEntry> barEntries = new ArrayList<>();

                            final ArrayList<String> xAxisLabel = new ArrayList<>();
                            final ArrayList<Float> yAxisLabel = new ArrayList<>();

                            XAxis xAxis = mBarChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setDrawGridLines(false);
                            xAxis.setGranularity(1f);
                            xAxis.setGranularityEnabled(true);
                            //xAxis.setLabelRotationAngle(0f);
                            xAxis.setCenterAxisLabels(false);
//                            xAxis.setSpaceMin(0.9f);
//                            xAxis.setSpaceMax(0.9f);
                            //xAxis.setLabelCount(xAxisLabel.size(), true);
                            xAxis.setAxisMinimum(0);
//
                            YAxis rightAxis = mBarChart.getAxisRight();
                            rightAxis.setEnabled(false);
//
                            YAxis leftAxis = mBarChart.getAxisLeft();
                            leftAxis.setDrawAxisLine(false); // to remove y axis left vertical line

                            xAxisLabel.add("0");

                                for (int i = 0; i < response.body().get(0).getData().size(); i++) {
                                    xAxisLabel.add(response.body().get(0).getData().get(i).getDate());
                                    //yAxisLabel.add(Float.valueOf(response.body().get(0).getData().get(i).getSaleamount()));
                                }



                            for (BarChartPojo bc : response.body()) {
                                for (int i = 0; i < response.body().get(0).getData().size(); i++) {
                                    barEntries.add(new BarEntry(bc.getData().get(i).getId(), Float.parseFloat(bc.getData().get(i).getSaleamount())));
                                }
                            }
                            //barEntries.add(new BarEntry(xAxisLabel, yAxisLabel));





                            BarDataSet barDataSet = new BarDataSet(barEntries, "Sales");
                            barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);


                            BarData barData = new BarData(barDataSet);
                            barData.setBarWidth(.9f);


                            barLayout.setVisibility(View.VISIBLE);
                            tvBar.setVisibility(View.VISIBLE);
                            mBarChart.setVisibility(View.VISIBLE);
                            mBarChart.animateY(2000);
                            mBarChart.setData(barData);
                            //mBarChart.getXAxis().setLabelCount(xAxisLabel.size());

                            //mBarChart.getXAxis().setAxisMinimum(0.5f);
                            mBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
                            mBarChart.setFitBars(true);


                            Description description = new Description();
                            description.setText("Sales Per 6 Month");
                            mBarChart.setDescription(description);
                            mBarChart.invalidate();
                        }

                    }else{
                        tvBar.setVisibility(View.GONE);
                        mBarChart.setVisibility(View.GONE);
                        barLayout.setVisibility(View.GONE);

                    }


                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<BarChartPojo>> call, Throwable t) {
                Log.d(TAG, "Failure: " +t.getMessage());
            }
        });
    }

    private void getPieChart(String StoreId, String ShopName){


        String storeId = StoreId;
        String shopName = ShopName;

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<PieChartPojo>> call = apiInterface.getPieGrowth(storeId, shopName);
        call.enqueue(new Callback<List<PieChartPojo>>() {
            @Override
            public void onResponse(Call<List<PieChartPojo>> call, Response<List<PieChartPojo>> response) {
                try{
                    Log.d(TAG, "response_new: " + response.body());

                    if(response.isSuccessful() && response.body() !=null) {
                        Log.d(TAG, "Successfull1");

                        String status = response.body().get(0).getStatus();
                        Log.d(TAG, "Status: " + status);

                        if (status.equals("200")) {

                            List<PieEntry> pieEntries = new ArrayList<>();

                            for (PieChartPojo pc : response.body()) {
                                for (int j = 0; j < response.body().get(0).getData().size(); j++) {
                                    pieEntries.add(new PieEntry(pc.getData().get(j).getTotalamount(), pc.getData().get(j).getProductname()));
                                }
                            }

                            // add many colors
                            ArrayList<Integer> colors = new ArrayList<Integer>();

                            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                                colors.add(c);

                            for (int c : ColorTemplate.JOYFUL_COLORS)
                                colors.add(c);

                            for (int c : ColorTemplate.COLORFUL_COLORS)
                                colors.add(c);

                            for (int c : ColorTemplate.LIBERTY_COLORS)
                                colors.add(c);

                            for (int c : ColorTemplate.PASTEL_COLORS)
                                colors.add(c);

                            colors.add(ColorTemplate.getHoloBlue());

                            PieDataSet pieDataSet = new PieDataSet(pieEntries, "Products");
                            pieDataSet.setColors(colors);

                            PieData pieData = new PieData(pieDataSet);

                            // enable hole and configure
//                            mPieChart.setDrawHoleEnabled(true);
//                            mPieChart.setHoleRadius(20);
//                            mPieChart.setTransparentCircleRadius(10);

                            pieLayout.setVisibility(View.VISIBLE);
                            tvPie.setVisibility(View.VISIBLE);
                            mPieChart.setVisibility(View.VISIBLE);
                            mPieChart.animateXY(2000, 2000);
                            mPieChart.setData(pieData);

//                            // set a chart value selected listener
//                            mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//
//                                @Override
//                                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//                                    // display msg when value selected
//                                    if (e == null)
//                                        return;
//
//                                }
//
//                                @Override
//                                public void onNothingSelected() {
//
//                                }
//                            });

//                            // customize legends
//                            Legend l = mPieChart.getLegend();
//                            l.setPosition(LegendPosition.RIGHT_OF_CHART);
//                            l.setXEntrySpace(7);
//                            l.setYEntrySpace(5);

                            Description description = new Description();
                            description.setText("Popular Products");
                            mPieChart.setDescription(description);
                            pieData.setValueFormatter(new PercentFormatter());
                            pieData.setValueTextSize(15f);
                            pieData.setValueTextColor(Color.BLACK);

                            mPieChart.invalidate();
                        }

                    }else {
                        tvPie.setVisibility(View.GONE);
                        mPieChart.setVisibility(View.GONE);
                        pieLayout.setVisibility(View.GONE);
                    }


                }catch (Exception e){


                }
            }

            @Override
            public void onFailure(Call<List<PieChartPojo>> call, Throwable t) {
                Log.d(TAG, "Failure: " +t.getMessage());
            }
        });
    }

}
