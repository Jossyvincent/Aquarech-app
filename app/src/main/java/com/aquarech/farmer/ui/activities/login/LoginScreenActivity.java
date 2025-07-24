package com.aquarech.farmer.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.aquarech.farmer.R;
import com.aquarech.farmer.ui.activities.register.RegisterScreenActivity;
import com.aquarech.farmer.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginScreenActivity extends AppCompatActivity {

    Button signInBtn;
    TextInputEditText phoneNumber, password;
    TextInputLayout phoneNumberInputLayout;
    TextInputLayout passwordInputLayout;
    AppCompatTextView joinToday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // declaring and instantiating widgets
        signInBtn = findViewById(R.id.login);
        phoneNumber = findViewById(R.id.phone_input);
        password = findViewById(R.id.password_input);
        phoneNumberInputLayout = findViewById(R.id.phone_layout);
        passwordInputLayout = findViewById(R.id.password_layout);
        joinToday = findViewById(R.id.join);

        // event listener to Registration Screen
        joinToday.setOnClickListener(view -> {
            Intent intent = new Intent(LoginScreenActivity.this, RegisterScreenActivity.class);
            startActivity(intent);
        });

        // button event listener
        signInBtn.setOnClickListener(view -> {
            String phone = Objects.toString(phoneNumber.getText(), "");
            String pwd = Objects.toString(password.getText(),"");

            // validate phone number
            if(phone.isEmpty()){
                phoneNumberInputLayout.setError(getString(R.string.input_err_msg));
                phoneNumber.requestFocus();
                return;
            }
            else if(!Utils.isValidPhoneNumber(phone)){
                phoneNumberInputLayout.setError(getString(R.string.invalid_phone_msg));
                phoneNumber.requestFocus();
                return;
            }
            else{
                phoneNumberInputLayout.setError(null);
            }

            // validate password
            if(pwd.isEmpty()){
                passwordInputLayout.setError(getString(R.string.input_err_msg));
                password.requestFocus();
                return;
            }

            else {
                passwordInputLayout.setError(null);
            }

            Log.d("all check", "passed");
        });
    }
}
