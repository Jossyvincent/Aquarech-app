package com.aquarech.farmer.ui.activities.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.aquarech.farmer.R;
import com.aquarech.farmer.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginScreenActivity extends AppCompatActivity {

    Button signInBtn;
    TextInputEditText phone_number, password;
    TextInputLayout phoneNumberInputLayout;
    TextInputLayout passwordInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        // declaring and instantiating widgets
        signInBtn = findViewById(R.id.login);
        phone_number = findViewById(R.id.phone_input);
        password = findViewById(R.id.password_input);
        phoneNumberInputLayout = findViewById(R.id.phone_layout);
        passwordInputLayout = findViewById(R.id.password_layout);

        // button event listener
        signInBtn.setOnClickListener(view -> {
            String phone = Objects.toString(phone_number.getText(), "");
            String pwd = Objects.toString(password.getText(),"");

            // validate phone number
            if(phone.isEmpty()){
                phoneNumberInputLayout.setError(getString(R.string.input_err_msg));
                phone_number.requestFocus();
                return;
            }
            else if(Utils.isPhoneNoValid(phone)){
                phoneNumberInputLayout.setError(getString(R.string.invalid_phone_msg));
                phone_number.requestFocus();
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
            else if(pwd.length() < 8){
                passwordInputLayout.setError(getString(R.string.invalid_pwd_msg));
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
