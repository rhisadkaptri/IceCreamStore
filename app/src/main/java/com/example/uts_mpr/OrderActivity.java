package com.example.uts_mpr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uts_mpr.Domain.OrderDomain;
import com.example.uts_mpr.Helper.ManagementCart;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {

    TextView txtJumlah, hasilnama, hasilharga;
    ImageView hasilphoto;
    int jumlah, harga;
    Button add,tambah,kurang;
    String textt2;

    private OrderDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
        
//        TextView text = (TextView)findViewById(R.id.nama);
//        Bundle bundle = getIntent().getExtras();
//
//            if(bundle.getString("id")!= null)
//            {
//                String textt = bundle.getString("id");
//                String textt1 = bundle.getString("nama");
//                textt2 = bundle.getString("harga");
//                String textt3 = bundle.getString("photo");
//
//                harga = Integer.parseInt(textt2);
//
//                hasilnama.setText(textt1);
//                hasilharga.setText(textt2);
//                hasilphoto.setImageResource(Integer.parseInt(textt3));
//            }
//
//        txtJumlah =(TextView) findViewById(R.id.txt_jumlah);

//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int basePrace = harga;
//                jumlah++;
//                tampilharga();
//                int priceView = basePrace*jumlah;
//                txtJumlah.setText("" +jumlah);
//                String setnewPrice = String.valueOf(priceView);
//                tampilharga.setText("Total : Rp"+setnewPrice);
//            }
//        });
//
//        kurang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int basePrace = harga;
//                if (jumlah == 0) {
//                } else {
//                    jumlah--;
//                    tampilharga();
//                    int priceView = basePrace*jumlah;
//                    String setnewPrice = String.valueOf(priceView);
//                    tampilharga.setText("Total : Rp"+setnewPrice);
//                }
//            }
//        });
    }

    private void getBundle() {
        object = (OrderDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(hasilphoto);

        hasilnama.setText(object.getTitle());
        hasilharga.setText("Rp" + object.getFee());
        txtJumlah.setText(String.valueOf(numberOrder));

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                txtJumlah.setText(String.valueOf(numberOrder));
            }
        });

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                txtJumlah.setText(String.valueOf(numberOrder));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView() {
        hasilnama = findViewById(R.id.hasilnama);
        hasilharga  = findViewById(R.id.hasilharga);
        hasilphoto = findViewById(R.id.hasilphoto);
        add = findViewById(R.id.add);
        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        txtJumlah = findViewById(R.id.txt_jumlah);
    }

//    private void tampilharga() {
//        txtJumlah.setText(String.valueOf(jumlah));
//    }
}