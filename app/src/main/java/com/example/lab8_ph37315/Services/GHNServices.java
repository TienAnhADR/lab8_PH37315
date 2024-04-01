package com.example.lab8_ph37315.Services;

import com.example.lab8_ph37315.models.District;
import com.example.lab8_ph37315.models.DistrictRequest;
import com.example.lab8_ph37315.models.Province;
import com.example.lab8_ph37315.models.ResponeGHN;
import com.example.lab8_ph37315.models.Ward;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GHNServices {
    public static String GHN_URL = "https://dev-online-gateway.ghn.vn/";

    @GET("shiip/public-api/master-data/province")
    Call<ResponeGHN<ArrayList<Province>>> getListProvince();
    @POST("shiip/public-api/master-data/district")
    Call<ResponeGHN<ArrayList<District>>> getListDistrict(@Body DistrictRequest districtRequest);

    @GET("shiip/public-api/master-data/ward")
    Call<ResponeGHN<ArrayList<Ward>>> getListWard(@Query("district_id") int district_id);
}
