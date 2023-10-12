package cl.santos.pedrofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText textoEnviar;
    Button botonEnviar;
    TextView recibir;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoEnviar=findViewById(R.id.txtEnviar);
        botonEnviar=findViewById(R.id.btnEnviar);
        recibir=findViewById(R.id.txtRecibir);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference  = firebaseDatabase.getReference("message");

       botonEnviar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               databaseReference.child("mensaje").setValue(textoEnviar.getText().toString());

           }
       });
       databaseReference.child("mensaje").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
            String datos=snapshot.getValue(String.class);
            recibir.setText(datos);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }
}