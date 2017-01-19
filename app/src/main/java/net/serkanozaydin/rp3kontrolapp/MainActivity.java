package net.serkanozaydin.rp3kontrolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button isik_kontrol,mp3_kontrol;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        db=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef=db.getReference("kontroller");


        isik_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isik_kontrol.getText().toString().equals("ON")){
                    isik_kontrol.setText("OFF");
                    dbRef.child("isik_kontrol").setValue("off");

                }
                else {
                    isik_kontrol.setText("ON");
                    dbRef.child("isik_kontrol").setValue("on");

                }
            }
        });



        mp3_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mp3_kontrol.getText().toString().equals("ON")){
                    mp3_kontrol.setText("OFF");
                    dbRef.child("mp3_kontrol").setValue("off");

                }
                else {
                    mp3_kontrol.setText("ON");
                    dbRef.child("mp3_kontrol").setValue("on");

                }



            }
        });


    }

    public void init(){
        isik_kontrol= (Button) findViewById(R.id.isik_kontrol);
        mp3_kontrol= (Button) findViewById(R.id.mp3_kontrol);
    }
}
