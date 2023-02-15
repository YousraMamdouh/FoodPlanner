package com.example.foodplanner.login.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    Button loginButton;
    EditText emailLog;
    EditText txtPassword;
    TextView recoverPassTv;



GoogleSignInOptions gso = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail().build();

    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    SignInButton mGoogleLoginBtn;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        loginButton=view.findViewById(R.id.btnLogin);
       emailLog = view.findViewById(R.id.emailTxt);
        txtPassword = view.findViewById(R.id.txtPassword);
        recoverPassTv = view.findViewById(R.id.forgotxt);
        mGoogleLoginBtn = view.findViewById(R.id.googleLoginBtn);

        // recover pass text
       recoverPassTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showRecoverPasswordDialog();
           }
       });

       // handle google click btn

    mGoogleLoginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // begin google login process
            Intent signInIntent = mGooglrSingnInClient.getSignInIntent();
            startActivityForResult(signInIntent,RC_SIGN_IN);

        }
    });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailLog.getText().toString().trim();
                String passw = txtPassword.getText().toString().trim();
                // validate
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // set error focus
                    emailLog.setError("Invalid Email");
                    emailLog.setFocusable(true);
                }
                else {
                    loginUser(email,passw);
                }
            }
        });
        return view;
    }

    private void showRecoverPasswordDialog() {
        // alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Recover password");

        // set layout linear layout
        ConstraintLayout constraintLayout = new ConstraintLayout(getContext());
        // views to set in dialog
        EditText emailEt = new EditText(getContext());
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEt.setMinEms(15);

        constraintLayout.addView(emailEt);
       constraintLayout.setPadding(10,10,10,10);

        builder.setView(constraintLayout);
        // button recover
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            // input email
                String email = emailEt.getText().toString().trim();
                beginRecovery(email);
            }
        });
        // button cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        // show dialog
        builder.create().show();


    }

    private void beginRecovery(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if(task.isSuccessful()){
                Toast.makeText(getContext(), "Email Sent", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(getContext(), "failed...", Toast.LENGTH_SHORT).show();
            }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            // get and show proper error msg
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String passw) {
        mAuth.signInWithEmailAndPassword(email,passw)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            FirebaseUser user = mAuth.getCurrentUser();

                            navigationToHome();

                        }else {

                            Toast.makeText(getContext(), "Fail to log in ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // error get and error msg.
                      // progressDialog.dismiss();
                        Toast.makeText(getContext(), "Failure"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void navigationToHome(){
        Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_homeScreen);

    }
}