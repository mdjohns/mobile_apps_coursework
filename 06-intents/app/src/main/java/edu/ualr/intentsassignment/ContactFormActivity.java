package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity
    public static final String CONTACT = "ContactData";

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText phoneET;
    private EditText emailET;
    private EditText addressET;
    private EditText websiteET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form);

        firstNameET = findViewById(R.id.first_name_input);
        lastNameET = findViewById(R.id.last_name_input);
        phoneET = findViewById(R.id.phone_input);
        emailET = findViewById(R.id.email_input);
        addressET = findViewById(R.id.address_input);
        websiteET = findViewById(R.id.website_input);

        View formBtn = findViewById(R.id.save_contact_btn);

        formBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFormSubmit(view);
            }
        });

    }

    public String getInputText(EditText et) {
        return et.getText().toString();
    }
    public void onFormSubmit(View v) {
        Contact c = new Contact(
                getInputText(firstNameET),
                getInputText(lastNameET),
                getInputText(phoneET),
                getInputText(emailET),
                getInputText(addressET),
                getInputText(websiteET)
        );
        Intent intent = new Intent(this, ContactInfoActivity.class);
        intent.putExtra(CONTACT, c);
        startActivity(intent);
    }
}
