package com.example.uts_mpr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.uts_mpr.Adapter.CartListAdapter;
import com.example.uts_mpr.Adapter.OrderAdapter;
import com.example.uts_mpr.Domain.OrderDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView email;
    ImageView home,order,logout,fotoprofile;
    RecyclerView listView;
    EditText search;

//    SimpleAdapter adapter;
//    HashMap<String, String> map;
//    ArrayList<HashMap<String, String>> mylist;
//    String[] id;
//    String[] nama; //deklarasi judul iem
//    String[] harga; //deklarasi keterangan item
//    String[] photo; //deklarasi image item
    private Context context;
    private ArrayList<OrderDomain> orderlist=null;
    private ArrayList<OrderDomain> filteredList = null;
    private OrderAdapter adapter2;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();

        listView = findViewById(R.id.listview);
        email = findViewById(R.id.email);
        home = findViewById(R.id.home);
        order = findViewById(R.id.order);
        logout = findViewById(R.id.logout);
        fotoprofile = findViewById(R.id.fotoprofile);
        context = this;

        firebaseAuth =firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        FirebaseAuth.getInstance().signOut();
        email.setText(firebaseUser.getEmail());
        search = findViewById(R.id.search);

//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence editable, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence editable, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
//            }
//        });

        fotoprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(profile);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(MainActivity.this, MainActivity.class);
                startActivity(send);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(MainActivity.this, ListorderActivity.class);
                startActivity(send);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.logo)
                        .setTitle("Warning")
                        .setMessage("Are you sure want to logout?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String text = id[i];
//                String text1 = nama[i];
//                String text2 = harga[i];
//                String text3 = photo[i];
//
//                Intent send = new Intent(MainActivity.this, OrderActivity.class);
//                send.putExtra("id", text);
//                send.putExtra("nama", text1);
//                send.putExtra("harga", text2);
//                send.putExtra("photo", text3);
//                startActivity(send);
//                //Toast.makeText(getBaseContext(), ""+text, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        id = new String[]{
//                "1", "2", "3", "4", "5", "6", "7", "8", "9"
//        };
//
//        nama = new String[]{
//                "Soft Serve IceCream", "Cone IceCream", "Gelato IceCream", "Strawberry Parfait",
//                "Paddle Pop IceCream", "Aice Mochi", "Vanilla IceCream", "Frozen Yogurt IceCream", "Cornetto Choclate"
//        };
//        harga = new String[]{
//                "25000", "17000", "10000", "22000", "8000", "5000", "21000", "10000", "15000"
//                //jumlahnya harus sama dengan jumlah judul
//        };
//        photo = new String[]{
//                Integer.toString(R.drawable.gbr1), Integer.toString(R.drawable.gbr2), Integer.toString(R.drawable.gbr3),
//                Integer.toString(R.drawable.gbr4), Integer.toString(R.drawable.gbr5), Integer.toString(R.drawable.gbr6),
//                Integer.toString(R.drawable.gbr7), Integer.toString(R.drawable.gbr8), Integer.toString(R.drawable.gbr10)
//        };
//
//        mylist = new ArrayList<HashMap<String, String>>();
//
//        for (int i = 0; i < nama.length; i++) {
//            map = new HashMap<String, String>();
//            map.put("Nama", nama[i]);
//            map.put("Harga", harga[i]);
//            map.put("Gambar", photo[i]);
//            mylist.add(map);
//        }
//        adapter = new SimpleAdapter(this, mylist, R.layout.listicecream,
//                new String[]{"Nama", "Harga", "Gambar"}, new int[]{R.id.nama, (R.id.harga), (R.id.photo)});
//        listView.setAdapter(adapter);
    }

//    private void filter(String text){
//        filteredList = new ArrayList<>();
//
//        for (OrderDomain item : orderlist) {
//            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item);
//            }
//        }
//        adapter2.filterList(filteredList);
//    }

    private void recyclerViewCategory() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategoryList = findViewById(R.id.listview);
        recyclerViewCategoryList.setHasFixedSize(true);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<OrderDomain> orderlist = new ArrayList<>();
        orderlist.add(new OrderDomain("Soft Serve Ice Cream", "gbr1", 25000));
        orderlist.add(new OrderDomain("Cone Ice Cream", "gbr2", 17000));
        orderlist.add(new OrderDomain("Gelato Ice Cream", "gbr3", 10000));
        orderlist.add(new OrderDomain("Strawberry Parfait", "gbr4", 22000));
        orderlist.add(new OrderDomain("Paddle Pop Ice Cream", "gbr5", 8000));
        orderlist.add(new OrderDomain("Aice Mochi", "gbr6", 5000));
        orderlist.add(new OrderDomain("Vanilla Ice Cream", "gbr7", 21000));
        orderlist.add(new OrderDomain("Frozen Yogurt Ice Cream", "gbr8", 10000));
        orderlist.add(new OrderDomain("Cornetto Choclate", "gbr10", 15000));

        adapter2 = new OrderAdapter(orderlist);
        recyclerViewCategoryList.setAdapter(adapter2);

    }

}