package com.example.lab8_ph37315;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8_ph37315.Adapter.product_Adapter;
import com.example.lab8_ph37315.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> list = new ArrayList<>();
    private product_Adapter adapter;
    private RecyclerView rcvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        rcvProduct = findViewById(R.id.rcv_Product);
        list.add(new Product("1", "fruit", "Apple", "Fresh and juicy apple", 4.5, 2.99, R.drawable.apple));
        list.add(new Product("2", "fruit", "Banana", "Ripe banana", 4.2, 1.99, R.drawable.bana));
        list.add(new Product("3", "fruit", "Orange", "Sweet and tangy orange", 4.0, 3.49, R.drawable.oranges));
        list.add(new Product("4", "fruit", "Grape", "Delicious grapes", 4.3, 4.99, R.drawable.grapes));
        adapter = new product_Adapter(list, this);
        rcvProduct.setLayoutManager(new GridLayoutManager(this, 2));
        rcvProduct.setAdapter(adapter);
    }
}