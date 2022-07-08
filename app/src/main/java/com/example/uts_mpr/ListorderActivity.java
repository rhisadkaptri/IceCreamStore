package com.example.uts_mpr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uts_mpr.Adapter.CartListAdapter;
import com.example.uts_mpr.Domain.OrderDomain;
import com.example.uts_mpr.Helper.ManagementCart;
import com.example.uts_mpr.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ListorderActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, textview12;
    private int tax;
    private ScrollView scrollView;
    private LinearLayout linearLayout;

    ImageView home, order, back;
    private Context context;
    TextView checkout;
    private OrderDomain object;
    private int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listorder);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCard();

        home = findViewById(R.id.home);
        order = findViewById(R.id.order);
        back = findViewById(R.id.back);
        checkout = findViewById(R.id.checkout);
        context = this;

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(ListorderActivity.this, MainActivity.class);
                startActivity(send);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Order will be processed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            textview12.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        int percentTax = (int) 0.01;
        int delivery = 10000;

        tax = (int) (Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100);
        int total = (int) (Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100);
        int itemTotal = (int) (Math.round(managementCart.getTotalFee() * 100) / 100);

        totalFeeTxt.setText("Rp" + itemTotal);
        taxTxt.setText("Rp" + tax);
        deliveryTxt.setText("Rp" + delivery);
        totalTxt.setText("Rp" + total);
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        linearLayout = findViewById(R.id.linearLayout5);
        textview12 = findViewById(R.id.textView12);
    }

}