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


        create.setOnClickListener(view -> {
            // check if the user has already registered
            if (PreferenceManager.hasAccount()){
                Toast.makeText(this,"Account already exist. Please proceed and log in",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginScreenActivity.class));
                finish();
                return;
            }
            String phone = Objects.toString(phoneNumber.getText(), "").trim();
            String password = Objects.toString(passwordInput.getText(),"").trim();
            String confirmPwd = Objects.toString(confirmPassword.getText(),"").trim();

            // validate phone number
            if(phoneNumber.getEditableText().toString().isEmpty()){
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

            // empty password validation
            if(passwordInput.getEditableText().toString().isEmpty()){
                passwordInputLayout.setError(getString(R.string.input_err_msg));
                passwordInput.requestFocus();
                return;
            }
            else{
                passwordInputLayout.setError(null);
            }

            // validate password
            if(password.length() < 8){
                passwordInputLayout.setError(getString(R.string.invalid_pwd_msg));
                passwordInput.requestFocus();
                return;
            }
            else {
                passwordInputLayout.setError(null);
            }

            if (!password.equals(confirmPwd)){
                confirmPasswordInputLayout.setError(getString(R.string.invalid_pwd_match));
                confirmPassword.requestFocus();
                return;
            }
            else {
                confirmPasswordInputLayout.setError(null);
            }
            // save account
            PreferenceManager.saveAccount(phone,password);
            Toast.makeText(this,getString(R.string.create_account_success_msg),Toast.LENGTH_SHORT).show();

            // proceed to log in
            startActivity(new Intent(this,LoginScreenActivity.class));

        });


    }
}