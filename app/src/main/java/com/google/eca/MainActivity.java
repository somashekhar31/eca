package com.google.eca;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.firebase.client.Firebase;




public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner Session_NAme,Session_no,Year,Branch,Section,Tutor;
    RatingBar presentation,Doubts;
    EditText email,suggestion;
    Button submit;
    String item1,value1,value2,item2;
    float presentation_value,doubts_value;
    Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Session_NAme=findViewById(R.id.session);
        Session_no=findViewById(R.id.week);
        Year=findViewById(R.id.yos);
        Branch=findViewById(R.id.branch);
        Section=findViewById(R.id.section);
        Tutor=findViewById(R.id.tut);
        presentation=findViewById(R.id.presentation1);
        Doubts=findViewById(R.id.doubts);
        presentation.setNumStars(5);
        Doubts.setNumStars(5);
        email=findViewById(R.id.email1);
        suggestion=findViewById(R.id.suggest);
        submit=findViewById(R.id.submit);
        Firebase.setAndroidContext(this);
        String UniqueId =
        Settings.Secure.getString(getApplicationContext().getContentResolver(),
        Settings.Secure.ANDROID_ID);
        firebase =new Firebase("https://eca-app-6afbd.firebaseio.com/Users"+UniqueId);
        ArrayAdapter adapter_session_name=ArrayAdapter.createFromResource(MainActivity.this,R.array.SessionName,R.layout.support_simple_spinner_dropdown_item);
        adapter_session_name.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Session_NAme.setAdapter(adapter_session_name);
        Session_NAme.setOnItemSelectedListener(this);
        ArrayAdapter adapter_session_no=ArrayAdapter.createFromResource(MainActivity.this,R.array.yos,R.layout.support_simple_spinner_dropdown_item);
        adapter_session_no.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Session_no.setAdapter(adapter_session_no);
        Session_no.setOnItemSelectedListener(this);
        ArrayAdapter adapter_year=ArrayAdapter.createFromResource(MainActivity.this,R.array.yos,R.layout.support_simple_spinner_dropdown_item);
        adapter_year.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Year.setAdapter(adapter_year);
        Year.setOnItemSelectedListener(this);
        ArrayAdapter adapter_section=ArrayAdapter.createFromResource(MainActivity.this,R.array.Section,R.layout.support_simple_spinner_dropdown_item);
        adapter_section.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Section.setAdapter(adapter_section);
        Section.setOnItemSelectedListener(this);
        ArrayAdapter adapter_tut=ArrayAdapter.createFromResource(MainActivity.this,R.array.Tutor,R.layout.support_simple_spinner_dropdown_item);
        adapter_tut.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Tutor.setAdapter(adapter_tut);
        Tutor.setOnItemSelectedListener(this);
        presentation.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,"Stars:"+rating,Toast.LENGTH_SHORT).show();
                presentation_value=rating;
                value1=Float.toString(presentation_value);
            }
        });
        Doubts.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,"Stars:"+rating,Toast.LENGTH_SHORT).show();
                doubts_value=rating;
                value2=Float.toString(doubts_value);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_enter=email.getText().toString();
                Firebase child_email=firebase.child("Email");
                child_email.setValue(email_enter);
                if(email_enter.isEmpty())
                {
                    email.setError("Enter correct email");
                    submit.setEnabled(false);

                }
                else{
                    email.setError(null);
                    submit.setEnabled(true);
                }
                Firebase child_tutor=firebase.child("tutor");
                child_tutor.setValue(item1);
                Firebase child_topic=firebase.child("topic");
                child_topic.setValue(item2);
                Firebase child_presentation= firebase.child("presentation");
                child_presentation.setValue(value1);
                Firebase child_doubts= firebase.child("Doubts");
                child_doubts.setValue(value2);
               new AlertDialog.Builder(MainActivity.this)
                               .setTitle("Details:")
                       .setMessage("Tutor: "+item1+"\n\nCourse: "+item2+"\n\nEmail: "+email_enter+"\n\nPresentation: "+value1+"\n\nDoubts: "+value2)
                       .show();

            }
        });
    }//end of the oncreatem bracket

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getItemAtPosition(position).equals("Srimath")){
            item1 = parent.getItemAtPosition(position).toString();
        }
        if(parent.getItemAtPosition(position).equals("Bhaswanth")){
            item1 = parent.getItemAtPosition(position).toString();
        }
        if(parent.getItemAtPosition(position).equals("Bharath")){
            item1 = parent.getItemAtPosition(position).toString();
        }

        if(parent.getItemAtPosition(position).equals("Python with data structures")){
            item2 = parent.getItemAtPosition(position).toString();
        }
        if(parent.getItemAtPosition(position).equals("DataScience")){
            item2 = parent.getItemAtPosition(position).toString();
        }
        if(parent.getItemAtPosition(position).equals("Android")) {
            item2 = parent.getItemAtPosition(position).toString();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}//end of class
