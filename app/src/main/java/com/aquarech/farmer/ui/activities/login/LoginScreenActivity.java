package com.aquarech.farmer.ui.activities.login;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aquarech.farmer.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginScreenActivity extends AppCompatActivity {
    //instantiating widgets
    Button sign_in_btn = findViewById(R.id.login);
    TextInputEditText phone_number = findViewById(R.id.phone_input);
    TextInputEditText password = findViewById(R.id.password_input);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}