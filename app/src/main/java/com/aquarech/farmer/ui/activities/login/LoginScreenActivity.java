package com.aquarech.farmer.ui.activities.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aquarech.farmer.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginScreenActivity extends AppCompatActivity {

    Button sign_in_btn;
    TextInputEditText phone_number, password;

    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        //declaring and instantiating widgets
        sign_in_btn = findViewById(R.id.login);
        phone_number = findViewById(R.id.phone_input);
        password = findViewById(R.id.password_input);

        //button event listener
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = Objects.toString(phone_number.getText(), "");
                String pswd = Objects.toString(password.getText(),"");
                boolean isValid= true;
                //validate phone number
                if (phone.isEmpty() ){
                    phone_number.setError("Field is required");
                    isValid = false;
                } else if (phone.length() != 10 || !phone.matches("\\d+")){
                    phone_number.setError("enter a valid phone number");
                    isValid = false;
                }

                //validate password
                if (pswd.isEmpty()) {
                    password.setError("Field is required");
                    isValid = false;
                } else if (pswd.length() < 8){
                    password.setError("Password must be at least eight characters");
                    isValid = false;
                }

                if (isValid) {
                    //logic to move to the next screen
                }
            }
        });

    }

}
