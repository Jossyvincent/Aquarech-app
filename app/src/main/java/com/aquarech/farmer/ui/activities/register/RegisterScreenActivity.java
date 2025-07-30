package com.aquarech.farmer.ui.activities.register;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.aquarech.farmer.R;
import com.aquarech.farmer.db.providers.UserProvider;
import com.aquarech.farmer.ui.activities.login.LoginScreenActivity;
import com.aquarech.farmer.utils.Config;
import com.aquarech.farmer.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URI;
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
            // get user input
            String phone = Objects.toString(phoneNumber.getText(), "").trim();
            String password = Objects.toString(passwordInput.getText(),"").trim();
            String confirmPwd = Objects.toString(confirmPassword.getText(),"").trim();

            // check if the user has already registered in database
            if (UserProvider.isUserRegistered(this,phone)){
                Toast.makeText(this,getString(R.string.account_registered_msg),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginScreenActivity.class));
                finish();
                return;
            }
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
            // removed the code to save the account to SharedPreferences

            // Create ContentValues to hold user data
            ContentValues values = new ContentValues();
            values.put(Config.COLUMN_PHONE, phone);
            values.put(Config.COLUMN_PASSWORD, password);

            try {
                Uri uri = getContentResolver().insert(UserProvider.USER_CONTENT_URI, values);
                if (uri != null) {
                    Toast.makeText(this, getString(R.string.registration_successful_msg), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,LoginScreenActivity.class));
                    finish();
                } else {
                    Toast.makeText(this,getString(R.string.registration_failed_msg), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}