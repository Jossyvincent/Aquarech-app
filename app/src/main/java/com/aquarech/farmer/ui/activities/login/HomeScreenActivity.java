package com.aquarech.farmer.ui.activities.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.aquarech.farmer.R;
import com.aquarech.farmer.networking.ResponseData;
import com.aquarech.farmer.networking.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity {

    TextView titleTextView, bodyTextView;
    Button getDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // instantiating widgets
        titleTextView = findViewById(R.id.title_textView);
        bodyTextView = findViewById(R.id.body_textView);
        getDataBtn = findViewById(R.id.getData_button);

        getDataBtn.setOnClickListener(view -> getData());
    }

    private void getData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        RetrofitInstance.getApiInterface().getData().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseData responseData = response.body();
                if (responseData != null) {
                    titleTextView.setText(responseData.getTitle());
                    bodyTextView.setText(responseData.getBody());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}