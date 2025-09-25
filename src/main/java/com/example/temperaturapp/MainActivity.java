package com.example.temperaturapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

  private EditText etInput;
  private Spinner spinnerFrom, spinnerTo;
  private Button btnConvert, btnSwap, btnClear;
  private TextView tvResult;
  private LinearLayout resultCard;

  private String[] scales = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Réaumur", "Delisle"};
  private String[] scaleSymbols = {"°C", "°F", "K", "°R", "°Ré", "°De"};
  private DecimalFormat decimalFormat = new DecimalFormat("#.##");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
    setupSpinners();
    setupListeners();
  }

  private void initViews() {
    etInput = findViewById(R.id.etInput);
    spinnerFrom = findViewById(R.id.spinnerFrom);
    spinnerTo = findViewById(R.id.spinnerTo);
    btnConvert = findViewById(R.id.btnConvert);
    btnSwap = findViewById(R.id.btnSwap);
    btnClear = findViewById(R.id.btnClear);
    tvResult = findViewById(R.id.tvResult);
    resultCard = findViewById(R.id.resultCard);
  }

  private void setupSpinners() {
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, scales);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerFrom.setAdapter(adapter);
    spinnerTo.setAdapter(adapter);
    spinnerFrom.setSelection(0);
    spinnerTo.setSelection(1);
  }

  private void setupListeners() {
    btnConvert.setOnClickListener(v -> convertTemperature());
    btnSwap.setOnClickListener(v -> swapScales());
    btnClear.setOnClickListener(v -> clearAll());
    tvResult.setOnClickListener(v -> copyResult());
  }

  private void convertTemperature() {
    String inputStr = etInput.getText().toString().trim();
    if (inputStr.isEmpty()) {
      Toast.makeText(this, "Ingresa un valor", Toast.LENGTH_SHORT).show();
      hideResult();
      return;
    }

    try {
      double inputValue = Double.parseDouble(inputStr);
      String fromScale = scales[spinnerFrom.getSelectedItemPosition()];
      String toScale = scales[spinnerTo.getSelectedItemPosition()];

      if (fromScale.equals("Kelvin") && inputValue < 0) {
        Toast.makeText(this, "Kelvin no puede ser negativo", Toast.LENGTH_SHORT).show();
        hideResult();
        return;
      }

      double result = performConversion(inputValue, fromScale, toScale);
      showResult(inputValue, fromScale, result, toScale);

    } catch (NumberFormatException e) {
      Toast.makeText(this, "Ingresa un número válido", Toast.LENGTH_SHORT).show();
      hideResult();
    }
  }

  private double performConversion(double value, String from, String to) {
    if (from.equals(to)) return value;

    double celsius;
    switch (from) {
      case "Celsius": celsius = value; break;
      case "Fahrenheit": celsius = (value - 32) * 5.0/9.0; break;
      case "Kelvin": celsius = value - 273.15; break;
      case "Rankine": celsius = (value - 491.67) * 5.0/9.0; break;
      case "Réaumur": celsius = value * 5.0/4.0; break;
      case "Delisle": celsius = 100 - value * 2.0/3.0; break;
      default: celsius = value; break;
    }

    switch (to) {
      case "Celsius": return celsius;
      case "Fahrenheit": return celsius * 9.0/5.0 + 32;
      case "Kelvin": return celsius + 273.15;
      case "Rankine": return (celsius + 273.15) * 9.0/5.0;
      case "Réaumur": return celsius * 4.0/5.0;
      case "Delisle": return (100 - celsius) * 3.0/2.0;
      default: return celsius;
    }
  }

  private void showResult(double input, String fromScale, double result, String toScale) {
    String fromSymbol = getSymbolForScale(fromScale);
    String toSymbol = getSymbolForScale(toScale);
    String resultText = decimalFormat.format(input) + " " + fromSymbol + " = " + decimalFormat.format(result) + " " + toSymbol;
    tvResult.setText(resultText);
    resultCard.setVisibility(View.VISIBLE);
  }

  private void hideResult() {
    resultCard.setVisibility(View.GONE);
  }

  private void swapScales() {
    int fromSelection = spinnerFrom.getSelectedItemPosition();
    int toSelection = spinnerTo.getSelectedItemPosition();
    spinnerFrom.setSelection(toSelection);
    spinnerTo.setSelection(fromSelection);
    Toast.makeText(this, "Escalas intercambiadas", Toast.LENGTH_SHORT).show();
    if (!etInput.getText().toString().trim().isEmpty()) {
      convertTemperature();
    }
  }

  private void clearAll() {
    etInput.setText("");
    hideResult();
    spinnerFrom.setSelection(0);
    spinnerTo.setSelection(1);
    Toast.makeText(this, "Limpiado", Toast.LENGTH_SHORT).show();
  }

  private void copyResult() {
    if (tvResult.getText().toString().isEmpty()) return;
    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText("Resultado", tvResult.getText().toString());
    clipboard.setPrimaryClip(clip);
    Toast.makeText(this, "¡Resultado copiado!", Toast.LENGTH_SHORT).show();
  }

  private String getSymbolForScale(String scale) {
    for (int i = 0; i < scales.length; i++) {
      if (scales[i].equals(scale)) {
        return scaleSymbols[i];
      }
    }
    return "";
  }
}
