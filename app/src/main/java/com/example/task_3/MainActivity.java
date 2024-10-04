package com.example.task_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, phoneEditText, addressEditText, passwordEditText;
    private Spinner itemSpinner;
    private String name, email, phone, address, item, password;
    private Button submit;
    private Pattern namePattern = Pattern.compile("[a-zA-Z._ ]+");
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+$"); // For basic email validation
    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        addressEditText = findViewById(R.id.address);
        passwordEditText = findViewById(R.id.pass);
        itemSpinner = findViewById(R.id.item_spinner);
        submit = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        String[] items = new String[]{"Select Item", "Pizza", "Burger", "Pasta", "Salad"};
        itemSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = itemSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submit.setOnClickListener(v -> {
            name = nameEditText.getText().toString();
            email = emailEditText.getText().toString();
            phone = phoneEditText.getText().toString();
            address = addressEditText.getText().toString();
            password = passwordEditText.getText().toString();

            // Validate the fields
            if (name.isEmpty()) {
                nameEditText.setError("Please enter your name");
                nameEditText.requestFocus();
            } else if (!namePattern.matcher(name).matches()) {
                nameEditText.setError("Name can only contain alphabets");
                nameEditText.requestFocus();
            } else if (email.isEmpty()) {
                emailEditText.setError("Please enter your email");
                emailEditText.requestFocus();
            } else if (!emailPattern.matcher(email).matches()) {
                emailEditText.setError("Invalid email format");
                emailEditText.requestFocus();
            } else if (phone.isEmpty()) {
                phoneEditText.setError("Please enter your phone number");
                phoneEditText.requestFocus();
            } else if (phone.length() != 11) {
                phoneEditText.setError("Phone number must be 11 digits");
                phoneEditText.requestFocus();
            } else if (address.isEmpty()) {
                addressEditText.setError("Please enter your address");
                addressEditText.requestFocus();
            } else if (password.isEmpty()) {
                passwordEditText.setError("Please enter your password");
                passwordEditText.requestFocus();
            } else if (password.length() < 4) {
                passwordEditText.setError("Password must be at least 4 characters");
                passwordEditText.requestFocus();
            } else if (Objects.equals(item, "Select Item")) {
                Toast.makeText(getApplicationContext(), "Please select an item", Toast.LENGTH_SHORT).show();
            } else {
                inputLayout.setVisibility(View.GONE);
                outputLayout.setVisibility(View.VISIBLE);
                String result = "Name: " + name + "\nEmail: " + email + "\nMobile Number: " + phone + "\nAddress: " + address + "\nItem: " + item;
                outputText.setText(result);
            }
        });
    }
}
