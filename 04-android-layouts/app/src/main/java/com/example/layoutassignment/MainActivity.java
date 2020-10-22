package com.example.layoutassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.NO_ID;

public class MainActivity extends AppCompatActivity {
    private final double discountAmt = 0.50;
    private boolean isDiscountActive = false;
    private double invoiceTotal = 00.00;

    public double getDiscountAmt() {
        return discountAmt;
    }
    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public boolean isDiscountActive() {
        return isDiscountActive;
    }

    public void setDiscountActive(boolean discountActive) {
        isDiscountActive = discountActive;
    }

    private double getInputTotal(List<TextInputLayout> inputs, List<AppCompatCheckBox> checkboxes) {
        double total = getInvoiceTotal();

        for (int i = 0; i < inputs.size(); i++) {
           String fieldContents = "";
           if (checkboxes.get(i).isChecked()) {
               fieldContents = inputs.get(i).getEditText().getText().toString();
               total += Double.parseDouble(fieldContents);
           }
        }
        return total;
    }
    private  List<AppCompatCheckBox> getCheckboxes(ViewGroup viewGroup) {
        int children = viewGroup.getChildCount();
        List<AppCompatCheckBox> checkboxes = new ArrayList<>();
        for (int i = 0; i < children; i++) {
            AppCompatCheckBox cb = (AppCompatCheckBox) viewGroup.getChildAt(i);
            checkboxes.add(cb);
        }
        return checkboxes;
    }
    private  List<TextInputLayout> getTextInputs(ViewGroup viewGroup) {
        int children = viewGroup.getChildCount();
        List<TextInputLayout> inputs = new ArrayList<>();
        for (int i = 0; i < children; i++) {
            TextInputLayout tl = (TextInputLayout) viewGroup.getChildAt(i);
            inputs.add(tl);
        }
        return inputs;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDiscountActive(false);
        final MaterialButtonToggleGroup discountBtnGroup = findViewById(R.id.discount_toggle);
        MaterialButton calculateTotalBtn = findViewById(R.id.calculate_total_btn);

        final TextInputLayout displayTotal = findViewById(R.id.invoice_total);
        displayTotal.setEnabled(false);

        ViewGroup checkboxContainer = findViewById(R.id.checkbox_layout);
        ViewGroup priceInputContainer = findViewById(R.id.price_input_layout);
        final List<AppCompatCheckBox> checkboxes = getCheckboxes(checkboxContainer);
        final List<TextInputLayout> productPriceInputs = getTextInputs(priceInputContainer);

        discountBtnGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    MaterialButton checkedBtn = findViewById(checkedId);
                    String btnText = checkedBtn.getText().toString();

                    if (btnText.equals("Discount")) {
                        setDiscountActive(true);
                    }
                    else {
                        setDiscountActive(false);
                    }
                }
            }
        });

        calculateTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textField = displayTotal.getEditText();
                setInvoiceTotal(00.00);

                if (isDiscountActive) {
                    double discounted = (getInputTotal(productPriceInputs, checkboxes)) * getDiscountAmt();
                    textField.setText(String.format("%.2f", discounted));
                }
                else {
                    setInvoiceTotal(getInputTotal(productPriceInputs, checkboxes));
                    textField.setText(String.format("%.2f", getInvoiceTotal()));
                }
            }
        });


    }
}