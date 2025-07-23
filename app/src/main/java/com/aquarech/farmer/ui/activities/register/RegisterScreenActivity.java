package com.aquarech.farmer.ui.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.aquarech.farmer.R;
import com.aquarech.farmer.app.PreferenceManager;
import com.aquarech.farmer.ui.activities.login.LoginScreenActivity;
import com.aquarech.farmer.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterScreenActivity extends AppCompatActivity {
    // declaring widgets
    TextInputEditText phoneNumber, passwordInput, confirmPassword;
    Button create;
    AppCompatTextView logIn;
    TextInputLayout phoneNumberInputLayout, passwordInputLayout, confirmPasswordInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        //instantiating the widgets
        phoneNumber = findViewById(R.id.phone_input);
        passwordInput = findViewById(R.id.password_input);
        confirmPassword = findViewById(R.id.confirm_password_input);
        create = findViewById(R.id.create_account);
        logIn = findViewById(R.id.join);
        phoneNumberInputLayout = findViewById(R.id.phone_layout);
        passwordInputLayout = findViewById(R.id.password_layout);
        confirmPasswordInputLayout = findViewById(R.id.confirm_password_layout);

        // check if the user has already registered
        if (PreferenceManager.hasAccount()){
            Toast.makeText(this,"Account already exist. Please proceed and log in",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginScreenActivity.class));
            finish();
        }
        create.setOnClickListener(view -> {
            String phone = Objects.toString(phoneNumber.getText(), "");
            String password = Objects.toString(passwordInput.getText(),"");
            String confirmPwd = Objects.toString(confirmPassword.getText(),"");

            // validate phone number
            if(phone.isEmpty()){
                phoneNumberInputLayout.setError(getString(R.string.input_err_msg));
                phoneNumber.requestFocus();
                return;
            }
            else if(Utils.isPhoneNoValid(phone)){
                phoneNumberInputLayout.setError(getString(R.string.invalid_phone_msg));
                phoneNumber.requestFocus();
                return;
            }
            else{
                phoneNumberInputLayout.setError(null);
            }
            // save account
            PreferenceManager.saveAccount(phone,password);
            Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show();

            // proceed to log in
            startActivity(new Intent(this,LoginScreenActivity.class));

        });


    }
}