package com.manzil.bikepollingnepal;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    Button register, cancel;
    EditText password, confirmpw, username, email, phone, address;
    RadioGroup gender;
    DatePicker dob;
    DatabaseHelper databaseHelper;
    int id;

    ImageView imageView;
    AQuery aquery;
    String posturl =Constants.BASE_URL+"insert.php";
    String uname, upassword, uconfirmpw, uemail, uaddress, uphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        id = getIntent().getIntExtra("id", 0);
        databaseHelper = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        gender = (RadioGroup) findViewById(R.id.gender);
        dob = (DatePicker) findViewById(R.id.dob);
        register = (Button) findViewById(R.id.register);
        cancel = (Button) findViewById(R.id.cancel);
        password = (EditText) findViewById(R.id.password);
        confirmpw = (EditText) findViewById(R.id.confirmpw);
        imageView = (ImageView) findViewById(R.id.imageView);
        aquery = new AQuery(this);


        if (id != 0) {
            register.setText("Update");

            UserInfo info = databaseHelper.getUserInfo(id + "");
            username.setText(info.username);
            email.setText(info.email);
            phone.setText(info.phone);
            address.setText(info.address);
            password.setText(info.password);
            if (info.image != null) {
                bitmap = getBitmap(info.image);
                imageView.setImageBitmap(bitmap);
            }
            if (info.gender != null)
                if (info.gender.equals("male")) {
                    ((RadioButton) findViewById(R.id.male)).setChecked(true);

                } else {
                    ((RadioButton) findViewById(R.id.female)).setChecked(true);
                }
        }

        register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ragister();

                                            if (validate()) {

                                                postData();
//                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                                startActivity(intent);
//                                                Toast.makeText(RegisterActivity.this, "Use Your Phone Number And Password For Login... Thank You :)", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
        );

        cancel.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this, "You Need To Register To Login..", Toast.LENGTH_SHORT).show();
            }
        });


        imageView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });
    }


    public void ragister() {

        intialize();
        if (!validate()) {
            Toast.makeText(this, "Register failed...Please fill form properly ", Toast.LENGTH_SHORT).show();
        } else {
            onSignUpSuccess();
        }
    }

    public void onSignUpSuccess() {

        saveValues();
        // TODO: 1/28/2018 Start login activity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(RegisterActivity.this, "Use Your Phone Number And Password For Login... Thank You :)", Toast.LENGTH_SHORT).show();
    }

    public boolean validate() {

        boolean valid = true;
        if (uname.isEmpty() || uname.length() > 15) {
            username.setError("Please Enter Valid Username...");
            valid = false;
        }
        if (uemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            email.setError("Please Enter Valid Email Address");
            valid = false;
        }
        if (uaddress.isEmpty() || uaddress.length() > 15) {
            address.setError("Please Enter Valid Address");
            valid = false;
        }
        if (upassword.isEmpty()) {
            valid = false;
            password.setError("Enter Password");
        }
        if (uphone.isEmpty()) {
            valid= false;
            phone.setError("Enter Valid Phone Number");
        }
        return valid;
    }

    public void intialize() {
        uname = username.getText().toString().trim();
        upassword = password.getText().toString().trim();
        uaddress = address.getText().toString().trim();
        uemail = email.getText().toString().trim();
        uphone = phone.getText().toString().trim();
        uconfirmpw = confirmpw.getText().toString().trim();

//        HashMap<String, Object> param = new HashMap<String, Object>();
//        param.put("username", uname);
//        param.put("password", upassword);
//        param.put("address", uaddress);
//        param.put("phone", uphone);
////        param.put("email","username ko vlaue");
//        aquery.ajax(posturl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
//            @Override
//            public void callback(String url, JSONArray object, AjaxStatus status) {
//                super.callback(url, object, status);
//                Log.i("response", url + "response" + object);
//            }
//        });

    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                bitmap = (Bitmap) data.getExtras().get("data");

                imageView.setImageBitmap(bitmap);
            }
        }
    }


    public static byte[] getBlob(Bitmap bitmap) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        return bArray;
    }

    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }


    public void saveValues() {
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        String addressValue = address.getText().toString();
        String confirmpwValue = confirmpw.getText().toString();
        String emailValue = email.getText().toString();
        String phoneValue = phone.getText().toString();
        RadioButton checkedRb = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
        String genderValue = checkedRb.getText().toString();
        String dateValue = dob.getYear() + "-" + (dob.getMonth() + 1) + "-" + dob.getDayOfMonth();

//        HashMap<String, Object> param = new HashMap<String, Object>();
//        param.put("username", usernameValue);
//        param.put("password", passwordValue);
//        param.put("address", addressValue);
//        param.put("phone", phoneValue);
////        param.put("email","username ko vlaue");
//        aquery.ajax(posturl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
//            @Override
//            public void callback(String url, JSONArray object, AjaxStatus status) {
//                super.callback(url, object, status);
//                Log.i("response", url + "response" + object);
//            }
//        });

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", usernameValue);
        contentValues.put("password", passwordValue);
        contentValues.put("address", addressValue);
        contentValues.put("email", emailValue);
        contentValues.put("phone", phoneValue);
        contentValues.put("gender", genderValue);
        contentValues.put("dob", dateValue);
        if (bitmap != null)
            contentValues.put("image", getBlob(bitmap));
        if (id == 0) {
            databaseHelper.insertUser(contentValues);
            Toast.makeText(this, "User inserted Successfully", Toast.LENGTH_SHORT).show();


        } else {
            databaseHelper.updateuser(String.valueOf(id), contentValues);
            Toast.makeText(this, "User updated Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void postData() {
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        String addressValue = address.getText().toString();
        String confirmpwValue = confirmpw.getText().toString();
        String emailValue = email.getText().toString();
        String phoneValue = phone.getText().toString();
        RadioButton checkedRb = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
        String genderValue = checkedRb.getText().toString();
        String dateValue = dob.getYear() + "-" + (dob.getMonth() + 1) + "-" + dob.getDayOfMonth();

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("username", usernameValue);
        param.put("password", passwordValue);
        param.put("address", addressValue);
        param.put("phone", phoneValue);
        param.put("email",emailValue);
        aquery.ajax(posturl, param, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.i("response", url + "response" + object);
            }
        });
    }
}


//    public void register() {
//        saveValues();
//        if (!validate()) {
//            Toast.makeText(this, "Please fill form correctly...", Toast.LENGTH_SHORT).show();
//        } else {
//            saveValues();
//        }
//    }
//
//
//    public boolean validate() {
//
//        boolean valid = true;
//        if (usernameValue.isEmpty() || usernameValue.length() > 15) {
//            username.setError("Please Enter Valid name");
//            valid = false;
//        }
//        if (emailValue.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
//            email.setError("Please Enter Valid Email Address");
//            valid = false;
//        }
//        if (addressValue.isEmpty() || addressValue.length() > 15) {
//
//            address.setError("Please Enter Valid Address");
//            valid = false;
//        }
//
//        return valid;
//    }
//}
