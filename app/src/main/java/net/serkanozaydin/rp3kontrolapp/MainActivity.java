package net.serkanozaydin.rp3kontrolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button isik_kontrol,mp3_kontrol,mp3_kontrolpause,mp3_kontrolstop,mp3_unpause,havadurumu_kontrol;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        db=FirebaseDatabase.getInstance();
        final DatabaseReference dbRef=db.getReference("kontroller");//kontroller adı altındaki verileri çekmek için kullanıyoruz




        havadurumu_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.child("havadurumu_kontrol").setValue("on");
                Thread loading = new Thread() {
                    public void run() {
                        try {
                            sleep(5000);
                            dbRef.child("havadurumu_kontrol").setValue("off");
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        finally {
                            finish();
                        }
                    }
                };
                loading.start();


            }
        });

        isik_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isik_kontrol.getText().toString().equals("ON")){
                    isik_kontrol.setText("OFF");
                    dbRef.child("isik_kontrol").setValue("off");//Eğer buton texti on ise firebasede bulunan değeri off olarak çeviriyor

                }
                else {
                    isik_kontrol.setText("ON");
                    dbRef.child("isik_kontrol").setValue("on");//Burada ise tam tersi bir işlem var

                }
            }
        });

        mp3_kontrolpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp3_kontrol.getText().equals("ON")){
                    Toast.makeText(getApplicationContext(),"Müzik pause edildil",Toast.LENGTH_SHORT).show();
                    dbRef.child("mp3_durum").setValue("pause");
                }
            }
        });

        mp3_kontrolstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp3_kontrol.getText().equals("ON")){
                    Toast.makeText(getApplicationContext(),"Müzik durduruldu",Toast.LENGTH_SHORT).show();
                    dbRef.child("mp3_durum").setValue("stop");
                    dbRef.child("mp3_kontrol").setValue("off");
                    mp3_kontrol.setText("OFF");


                }
            }
        });

        mp3_unpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp3_kontrol.getText().equals("ON")){
                    dbRef.child("mp3_durum").setValue("unpause");
                    Toast.makeText(getApplicationContext(),"Müzik devam ediyor",Toast.LENGTH_SHORT).show();
                }
            }
        });



        mp3_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mp3_kontrol.getText().toString().equals("ON")){
                    mp3_kontrol.setText("OFF");
                    dbRef.child("mp3_kontrol").setValue("off");
                    dbRef.child("mp3_durum").setValue("off");

                }
                else {
                    mp3_kontrol.setText("ON");
                    dbRef.child("mp3_kontrol").setValue("on");
                    dbRef.child("mp3_durum").setValue("off");

                }



            }
        });


    }

    public void init(){
        isik_kontrol= (Button) findViewById(R.id.isik_kontrol);
        mp3_kontrol= (Button) findViewById(R.id.mp3_kontrol);
        mp3_kontrolpause= (Button) findViewById(R.id.mp3_durumpause);
        mp3_kontrolstop= (Button) findViewById(R.id.mp3_durumstop);
        mp3_unpause= (Button) findViewById(R.id.mp3_unpause);
        havadurumu_kontrol= (Button) findViewById(R.id.havadurumu_kontrol);
    }
}
