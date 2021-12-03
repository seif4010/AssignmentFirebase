package com.example.assignmentfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextdob;
    private Button insert, update, delete, view;
    DatabaseReference reference;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextdob = findViewById(R.id.editTextdob);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);

        db = FirebaseDatabase.getInstance();

        //insert data
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = db.getReference("children");
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String dob = editTextdob.getText().toString();

                Children child = new Children(name, age, dob);
                reference.child(child.getName()).setValue(child);
                Toast.makeText(MainActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //update data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = db.getReference("children");
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String dob =  editTextdob.getText().toString();

                Children child = new Children(name, age, dob);

                reference.child(age).setValue(child);
                Toast.makeText(MainActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //delete data
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = db.getReference("children");
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String dob =  editTextdob.getText().toString();

                Children child = new Children(name, age, dob);

                reference.child(age).removeValue();
                Toast.makeText(MainActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //view data
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String dob =  editTextdob.getText().toString();

                Children child = new Children(name, age, dob);

                reference = db.getReference("children");
                StringBuffer buffer = new StringBuffer();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        buffer.append(snapshot.getValue());
                        builder.setCancelable(true);
                        builder.setTitle("Children Ages");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
