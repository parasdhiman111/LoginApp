package com.example.paras.logindemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profilePic;
    private TextView profileName,profileAge,profileEmail;
    private Button updateButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePic=(ImageView)findViewById(R.id.imProfilePic);
        profileName=(TextView)findViewById(R.id.tvProfileName);
        profileAge=(TextView)findViewById(R.id.tvProfileAge);
        profileEmail=(TextView)findViewById(R.id.tvProfileEmail);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
        profileName.setText("Name : "+userProfile.getUserName());
        profileAge.setText("Age : "+userProfile.getUserAge());
        profileEmail.setText("Email : "+userProfile.getUserEmail());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
    }
});
    }
}
