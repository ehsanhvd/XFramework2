package com.hvd.xutils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class XContactPicker {

    public static final int REQ_PHONEBOOK_PERMISSION = 9893;
    public static final int REQ_PHONEBOOK = 9892;

    private ContactPickerListener listener;
    private Activity activity;

    public XContactPicker(Activity activity, ContactPickerListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    public static String getInternalDialectNo(String tel) {
        return tel.trim().replaceAll("([- ()])", "").replace("+98", "0");
    }

    public static boolean hasContactPermission(Activity activity) {
        return XUtils.hasPermissions(activity, Manifest.permission.READ_CONTACTS);
    }

    public void pickContact() {
        if (hasContactPermission(activity)) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            activity.startActivityForResult(intent, REQ_PHONEBOOK);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, REQ_PHONEBOOK_PERMISSION);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_PHONEBOOK_PERMISSION) {
            if (hasContactPermission(activity)) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                activity.startActivityForResult(intent, REQ_PHONEBOOK);
            } else {
                // Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQ_PHONEBOOK) {
            if (resultCode == Activity.RESULT_OK) {
                String contactName = "";
                List<String> resNumbers = new ArrayList<>();

                Uri contactData = intent.getData();
                Cursor c = null;
                if (contactData != null) {
                    c = activity.getContentResolver().query(contactData, null, null, null, null);
                }
                if (c != null) {
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if ("1".equals(hasNumber)) {
                            Cursor numbers = activity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                    null,
                                    null);

                            if (numbers != null) {
                                while (numbers.moveToNext()) {
                                    String num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                                    String fixedNumber = getInternalDialectNo(num);
                                    if (ValidationUtils.isValidIranTel(fixedNumber) && !resNumbers.contains(fixedNumber)) {
                                        resNumbers.add(fixedNumber);
                                    }
                                }

                                numbers.close();
                            }
                        }
                    }
                    c.close();

                }

                onNumbers(contactName, resNumbers);
            }
        }
    }

    private void onNumbers(String contactName, List<String> numbers) {
        if (numbers.size() > 0) {
             listener.onNumberPicked(numbers);
        }
    }

    public interface ContactPickerListener {
        void onNumberPicked(List<String> number);
    }

}
