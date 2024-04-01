package com.example.lab8_ph37315;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab8_ph37315.Adapter.Adapter_Item_District_Select_GHN;
import com.example.lab8_ph37315.Adapter.Adapter_Item_Province_Select_GHN;
import com.example.lab8_ph37315.Adapter.Adapter_Item_Ward_Select_GHN;
import com.example.lab8_ph37315.Services.GHNRequest;
import com.example.lab8_ph37315.Services.GHNServices;
import com.example.lab8_ph37315.models.District;
import com.example.lab8_ph37315.models.DistrictRequest;
import com.example.lab8_ph37315.models.Province;
import com.example.lab8_ph37315.models.ResponeGHN;
import com.example.lab8_ph37315.models.Ward;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {
    private GHNRequest request;
    private GHNServices ghnServices;
    private String productId, productTypeId, productName, description, WardCode;
    private double rate, price;
    private int image, DistrictID, ProvinceID ;
    private Adapter_Item_Province_Select_GHN adapter_item_province_select_ghn;
    private Adapter_Item_District_Select_GHN adapter_item_district_select_ghn;
    private Adapter_Item_Ward_Select_GHN adapter_item_ward_select_ghn;
    private Spinner sp_province,sp_district,sp_ward;
    private EditText edt_location;
    private Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        sp_province = findViewById(R.id.sp_province);
        sp_district = findViewById(R.id.sp_district);
        sp_ward = findViewById(R.id.sp_ward);
        btn_next = findViewById(R.id.btn_order);


        request = new GHNRequest();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            productId = bundle.getString("productId");
            productTypeId = bundle.getString("productTypeId");
            productName = bundle.getString("productName");
            description = bundle.getString("description");
            rate = bundle.getDouble("rate");
            price = bundle.getDouble("price");
            image = bundle.getInt("image");
        }
        request.callAPI().getListProvince().enqueue(responseProvice);

        sp_province.setOnItemSelectedListener(onItemSelectedListener);
        sp_ward.setOnItemSelectedListener(onItemSelectedListener);
        sp_district.setOnItemSelectedListener(onItemSelectedListener);

        sp_province.setSelection(1);
        sp_district.setSelection(1);
        sp_ward.setSelection(1);

    }
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent.getId() == R.id.sp_province) {
                ProvinceID = ((Province) parent.getAdapter().getItem(position)).getProvinceID();
                DistrictRequest districtRequest = new DistrictRequest(ProvinceID);
                request.callAPI().getListDistrict(districtRequest).enqueue(responseDistrict);
            } else if (parent.getId() == R.id.sp_district) {
                DistrictID = ((District) parent.getAdapter().getItem(position)).getDistrictID();
                request.callAPI().getListWard(DistrictID).enqueue(responseWard);
            } else if (parent.getId() == R.id.sp_ward) {
                WardCode = ((Ward) parent.getAdapter().getItem(position)).getWardCode();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    Callback<ResponeGHN<ArrayList<Province>>> responseProvice = new Callback<ResponeGHN<ArrayList<Province>>>() {
        @Override
        public void onResponse(Call<ResponeGHN<ArrayList<Province>>> call, Response<ResponeGHN<ArrayList<Province>>> response) {
            if(response.isSuccessful()){
                if (response.body().getCode() == 200){
                    ArrayList<Province> ds = new ArrayList<>(response.body().getData());
                    SetDataSpinProvince(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponeGHN<ArrayList<Province>>> call, Throwable throwable) {

        }
    };
    Callback<ResponeGHN<ArrayList<District>>> responseDistrict = new Callback<ResponeGHN<ArrayList<District>>>() {
        @Override
        public void onResponse(Call<ResponeGHN<ArrayList<District>>> call, Response<ResponeGHN<ArrayList<District>>> response) {
            if(response.isSuccessful()){
                if(response.body().getCode() == 200){
                    ArrayList<District> ds = new ArrayList<>(response.body().getData());
                    SetDataSpinDistrict(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponeGHN<ArrayList<District>>> call, Throwable throwable) {

        }
    };
    Callback<ResponeGHN<ArrayList<Ward>>> responseWard = new Callback<ResponeGHN<ArrayList<Ward>>>() {
        @Override
        public void onResponse(Call<ResponeGHN<ArrayList<Ward>>> call, Response<ResponeGHN<ArrayList<Ward>>> response) {
            if(response.isSuccessful()){
                if(response.body().getCode() == 200){
                    ArrayList<Ward> ds = new ArrayList<>(response.body().getData());
                    if(response.body().getData() == null) return;
                    ds.addAll(response.body().getData());
                    SetDataSpinWard(ds);
                }
            }
        }

        @Override
        public void onFailure(Call<ResponeGHN<ArrayList<Ward>>> call, Throwable throwable) {

        }
    };
    private void SetDataSpinProvince(ArrayList<Province> ds){
        adapter_item_province_select_ghn = new Adapter_Item_Province_Select_GHN(this, ds);
        sp_province.setAdapter(adapter_item_province_select_ghn);
    }

    private void SetDataSpinDistrict(ArrayList<District> ds){
        adapter_item_district_select_ghn = new Adapter_Item_District_Select_GHN(this, ds);
        sp_district.setAdapter(adapter_item_district_select_ghn);
    }

    private void SetDataSpinWard(ArrayList<Ward> ds){
        adapter_item_ward_select_ghn = new Adapter_Item_Ward_Select_GHN(this, ds);
        sp_ward.setAdapter(adapter_item_ward_select_ghn );
    }


}