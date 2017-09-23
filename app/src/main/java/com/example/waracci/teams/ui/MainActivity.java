package com.example.waracci.teams.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waracci.teams.Constants;
import com.example.waracci.teams.ui.PlayersActivity;
import com.example.waracci.teams.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.waracci.teams.R.id.team;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    //declare firebase instance
    private DatabaseReference mSearchedTeamReference;

    //ValueEventListener
    private ValueEventListener mSearchedTeamReferenceListener;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.searchBtn) Button mSearchBtn;
    @Bind(R.id.savedPlayers) Button mSavedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                } else {
                    Toast.makeText(MainActivity.this, "no username!", Toast.LENGTH_LONG).show();
                }
            }
        };

        mSearchBtn.setOnClickListener(this);
        mSavedButton.setOnClickListener(this);
    }


        @Override
        public void onClick (View view) {
            if (view == mSearchBtn) {
                Intent intent = new Intent(MainActivity.this, PlayersActivity.class);
                startActivity(intent);
            }

            if (view == mSavedButton) {
                Intent intent = new Intent(MainActivity.this, SavedPlayersActivity.class);
                startActivity(intent);
            }
        } //end of onClick

    //oncreateoptions for overflow menu
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        if (id == R.id.about) {
            Toast.makeText(MainActivity.this, "About app was created by waracci", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
        }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    }

