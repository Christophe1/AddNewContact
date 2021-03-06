package com.example.chris.addnewcontact;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameofcontact;
    EditText numberofcontact;
    public String contactname;
    public String contactnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         nameofcontact = (EditText) findViewById(R.id.edittextname);
         numberofcontact = (EditText) findViewById(R.id.edittextnumber);


    }

public void createButton(View view) {
    contactname = nameofcontact.getText().toString();
    contactnumber = numberofcontact.getText().toString();

    if (contactname.length() == 0) {

        Toast.makeText(this, "Please enter a name",
                Toast.LENGTH_LONG).show();
        return;
    }

    ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<ContentProviderOperation>();
    //insert raw contact using RawContacts.CONTENT_URI
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
    //insert contact display name using Data.CONTENT_URI
    Log.d("ffff","wwww");
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,contactname ).build());
    //insert mobile number using Data.CONTENT_URI
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contactnumber).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
    try {
        //apply the changes
        getApplicationContext().getContentResolver().
                applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
    } catch (RemoteException e) {
        e.printStackTrace();
    } catch (OperationApplicationException e) {
        e.printStackTrace();
    }
    Toast.makeText(this, "Contact saved",
            Toast.LENGTH_SHORT).show();
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    }



