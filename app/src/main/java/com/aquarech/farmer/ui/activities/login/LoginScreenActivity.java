package com.aquarech.farmer.ui.activities.login;

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
import com.aquarech.farmer.ui.activities.register.RegisterScreenActivity;
import com.aquarech.farmer.utils.Config;
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

        // instantiating widgets
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
            String phone = Objects.toString(phoneNumber.getText(), "").trim();
            String pwd = Objects.toString(password.getText(), "").trim();

            // validate phone number
            if (phone.isEmpty()) {
                phoneNumberInputLayout.setError(getString(R.string.input_err_msg));
                phoneNumber.requestFocus();
                return;
            } else if (!Utils.isValidPhoneNumber(phone)) {
                phoneNumberInputLayout.setError(getString(R.string.invalid_phone_msg));
                phoneNumber.requestFocus();
                return;
            } else {
                phoneNumberInputLayout.setError(null);
            }

            // validate password
            if (pwd.isEmpty()) {
                passwordInputLayout.setError(getString(R.string.input_err_msg));
                password.requestFocus();
                return;
            } else {
                passwordInputLayout.setError(null);
            }

            // check credentials

            if (UserProvider.isUserAuthenticated(this, phone, pwd)) {
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeScreenActivity.class));
                finish();
            } else {
                // authentication failed, update or insert new user
                ContentValues values = new ContentValues();
                values.put(Config.COLUMN_PHONE, phone);
                values.put(Config.COLUMN_PASSWORD, pwd);

                if (UserProvider.isUserRegistered(this, phone)) {
                    //phone number already present so just update password
                    int rows = getContentResolver().update(UserProvider.USER_CONTENT_URI, values, Config.COLUMN_PHONE + "= ?", new String[]{phone});
                    if (rows > 0) {
                        Toast.makeText(this, getString(R.string.pwd_update_msg), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, getString(R.string.pwd_failed_update_msg), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // insert new user
                    Uri uri = getContentResolver().insert(UserProvider.USER_CONTENT_URI, values);
                    if (uri != null) {
                        Toast.makeText(this, getString(R.string.account_registered_msg), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, getString(R.string.registration_failed_msg), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
