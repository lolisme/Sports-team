package com.example.waracci.teams.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waracci.teams.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.checked;
import static android.R.string.no;
import static android.os.Build.VERSION_CODES.M;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    public boolean checked;
    private FirebaseAuth mAuth;
    //add an authstate listener
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private String mName;

    @Bind(R.id.signUpBtn)
    Button mSignUpButton;
    @Bind(R.id.fullName)
    EditText mFullName;
    @Bind(R.id.userEmailId) EditText mEmail;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.confirmPassword) EditText mConfirmPassword;
    @Bind(R.id.terms_conditions)
    CheckBox mTermsAndConditions;
    @Bind(R.id.already_user)
    TextView mAlreadyUser;

    private ProgressDialog mAuthProgressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        createAuthProgressDialog();

        mAuth = FirebaseAuth.getInstance();

        createAuthStateListener();


        mSignUpButton.setOnClickListener(this);
        mAlreadyUser.setOnClickListener(this);
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        if (view == mTermsAndConditions) {
            checked = ((CheckBox) view).isChecked();
        }

        if (view == mSignUpButton) {
            //sign up logic here
            createNewUser();
        }

        if (view == mAlreadyUser) {
            //intent back to login screen here mate
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void createNewUser() {
        //final String name = mFullName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();
        mName = mFullName.getText().toString().trim();

        //variables for input validation
        boolean isValidEmail = isValidEmail(email);
        boolean isValidName = isValidName(mName);
        boolean isValidPassword = isValidPassword(password, confirmPassword);
        //isTermsAndConditions();


        if (!isValidEmail || !isValidName || !isValidPassword) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();

                if (task.isSuccessful()) {
                    Log.d("auth mate", "Auth successful mate");
                    createFirebaseUserProfile(task.getResult().getUser());
                } else {

                }
            }
        });
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("mate", user.getDisplayName());
                        }
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    //validation of user input
    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());

        if (!isGoodEmail) {
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }
    private boolean isValidName(String name) {
        if (name.equals("")) {
            mFullName.setError("Please enter your name.");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please enter a password at least 6 letters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPassword.setError("passwords do not match");
            mConfirmPassword.setError("passwords do not match");
            return false;
        }
        return true;
    }
//    private boolean isTermsAndConditions() {
//            if (!checked) {
//                Toast.makeText(CreateAccountActivity.this, "Kindly check the terms and conditions", Toast.LENGTH_LONG).show();
//                return false;
//            }
//        return true;
//    }
}
