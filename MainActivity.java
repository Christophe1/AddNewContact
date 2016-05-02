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

//    private String[] names = {"Brahma", "Vishnu", "Mahesh"};
//    private String[] numbers = {"7359753876","3865986365","5286746529"};
    public String contactname;
    public String contactnumber;
    //line27 gives nullpointerexception error, why doesn't it work?
    EditText nameofcontact = (EditText) findViewById(R.id.edittextname);
    EditText numberofcontact = (EditText) findViewById(R.id.edittextname);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button view = (Button) findViewById(R.id.viewButton);
        Button add = (Button) findViewById(R.id.createButton);
//        Button modify = (Button)findViewById(R.id.updateButton);
//        Button delete = (Button)findViewById(R.id.deleteButton);


//        EditText name = (EditText) findViewById(R.id.edittextname);
//        EditText number = (EditText) findViewById(R.id.edittextnumber);


//        contactname = nameofcontact.getText().toString();



//        contactnumber = numberofcontact.getText().toString();

//        for(int i=0; i< names.length;i++){
//            writeContact(names[i], numbers[i]);
//        }
//        TextView view = (TextView)findViewById(R.id.out);
//        view.setText("Contact saved");

    }

public void createButton(View view) {
    contactname = nameofcontact.getText().toString();
    contactnumber = numberofcontact.getText().toString();

Log.d("ffff","wwww");
    ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<ContentProviderOperation>();
    //insert raw contact using RawContacts.CONTENT_URI
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
    //insert contact display name using Data.CONTENT_URI
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,nameofcontact ).build());
    //insert mobile number using Data.CONTENT_URI
    contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, numberofcontact).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
    try {
        //apply the changes
        getApplicationContext().getContentResolver().
                applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
    } catch (RemoteException e) {
        e.printStackTrace();
    } catch (OperationApplicationException e) {
        e.printStackTrace();
    }
}


    private void writeContact(String displayName, String number) {
//        ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<ContentProviderOperation>();
//        //insert raw contact using RawContacts.CONTENT_URI
//        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
//        //insert contact display name using Data.CONTENT_URI
//        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName).build());
//        //insert mobile number using Data.CONTENT_URI
//        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
//        try {
//            //apply the changes
//            getApplicationContext().getContentResolver().
//                    applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
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

//    private void displayContacts() {
//
//        ContentResolver cr = getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null);
//        if (cur.getCount() > 0) {
//            while (cur.moveToNext()) {
//                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                if (Integer.parseInt(cur.getString(
//                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//                    Cursor pCur = cr.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
//                            new String[]{id}, null);
//                    while (pCur.moveToNext()) {
//                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        Toast.makeText(MainActivity.this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
//                    }
//                    pCur.close();
//                }
//            }
//        }
//    }


