package com.bordonave.cuentabar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBillAmount;
    private EditText editTextPeopleCount;
    private RadioGroup radioGroupTip;
    private RadioButton radioButton0Percent;
    private RadioButton radioButton10Percent;
    private RadioButton radioButton15Percent;
    private TextView textViewTotalAmount;
    private TextView textViewPerPersonAmount;

    private DecimalFormat currencyFormat = new DecimalFormat("$0.00");

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

        initializeViews();
        setupEventListeners();
    }

    private void initializeViews() {
        editTextBillAmount = findViewById(R.id.editTextBillAmount);
        editTextPeopleCount = findViewById(R.id.editTextPeopleCount);
        radioGroupTip = findViewById(R.id.radioGroupTip);
        radioButton0Percent = findViewById(R.id.radioButton0Percent);
        radioButton10Percent = findViewById(R.id.radioButton10Percent);
        radioButton15Percent = findViewById(R.id.radioButton15Percent);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewPerPersonAmount = findViewById(R.id.textViewPerPersonAmount);
    }

    private void setupEventListeners() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calculateTip();
            }
        };

        editTextBillAmount.addTextChangedListener(textWatcher);
        editTextPeopleCount.addTextChangedListener(textWatcher);

        radioGroupTip.setOnCheckedChangeListener((group, checkedId) -> calculateTip());
    }

    private void calculateTip() {
        try {
            String billAmountText = editTextBillAmount.getText().toString().trim();
            String peopleCountText = editTextPeopleCount.getText().toString().trim();

            if (billAmountText.isEmpty()) {
                resetResults();
                return;
            }

            double billAmount = Double.parseDouble(billAmountText);
            int peopleCount = 1;

            if (!peopleCountText.isEmpty()) {
                peopleCount = Integer.parseInt(peopleCountText);
                if (peopleCount <= 0) {
                    peopleCount = 1;
                }
            }

            double tipPercentage = getTipPercentage();
            double tipAmount = billAmount * (tipPercentage / 100.0);
            double totalWithTip = billAmount + tipAmount;
            double amountPerPerson = totalWithTip / peopleCount;

            textViewTotalAmount.setText(currencyFormat.format(totalWithTip));
            textViewPerPersonAmount.setText(currencyFormat.format(amountPerPerson));

        } catch (NumberFormatException e) {
            resetResults();
        }
    }

    private double getTipPercentage() {
        int selectedRadioButtonId = radioGroupTip.getCheckedRadioButtonId();
        
        if (selectedRadioButtonId == R.id.radioButton10Percent) {
            return 10.0;
        } else if (selectedRadioButtonId == R.id.radioButton15Percent) {
            return 15.0;
        } else {
            return 0.0;
        }
    }

    private void resetResults() {
        textViewTotalAmount.setText(currencyFormat.format(0.0));
        textViewPerPersonAmount.setText(currencyFormat.format(0.0));
    }
}
