package com.example.foodplanner.login.view;


import com.example.foodplanner.utilities.ChangeNetworkListener;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.authentication.View.AuthenticationFragment;
import com.example.foodplanner.signUp.model.ReadWriteUserDetails;
import com.example.foodplanner.utilities.ChangeNetworkListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {
    Button loginButton;
    EditText emailLog;
    EditText txtPassword;
    TextView recoverPassTv;

    public static void setmAuth(FirebaseAuth mAuth) {
        LoginFragment.mAuth = mAuth;
    }

    GoogleSignInClient mGoogleSingInInClient;
    SharedPreferences preferences;
    ChangeNetworkListener changeNetworkListener = new ChangeNetworkListener();


    private static final int RC_SIGN_IN = 100;

    public static final String FileName = "Login";
    public static final String UserEmail = "Email";
    public static final String Password = "Password";
    private static FirebaseAuth mAuth;

    public static  FirebaseAuth getmAuth() {
        return mAuth;
    }

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

        GoogleSignInOptions gso = new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();


        mGoogleSingInInClient  = GoogleSignIn.getClient(getContext(),gso);

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
            Intent signInIntent = mGoogleSingInInClient.getSignInIntent();
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
                }else if(passw.isEmpty() || passw==null){
                   txtPassword.setError("Invalid password");
                    txtPassword.setFocusable(true);
                }
                else  {
                    loginUser(email,passw);
                    preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(UserEmail,email);
                    editor.putString(Password,passw);
                    editor.putBoolean("login user",true);
                    editor.commit();
                    System.out.println("test "+LoginFragment.getmAuth().getCurrentUser());
                   Toast.makeText(getContext(), "Successfully login", Toast.LENGTH_SHORT).show();
                    navigationToHome();


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
//            else {
//                Toast.makeText(getContext(), "failed...", Toast.LENGTH_SHORT).show();
//            }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            // get and show proper error msg
             //   Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {

        if(mAuth.getCurrentUser()!=null)
        {
            Toast.makeText(getContext(), "Already logged in ", Toast.LENGTH_SHORT).show();
            navigationToHome();
        }
        super.onStart();
    }

    private void loginUser(String email, String passw) {
        mAuth.signInWithEmailAndPassword(email,passw)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                      //  System.out.println("on complete ");


                        if(task.isSuccessful()) {
                          //  System.out.println("is success?");

                            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            System.out.println("login user "+mAuth.getCurrentUser());


                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(email, Password);
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered users");
                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    firebaseUser.sendEmailVerification();
                                    Toast.makeText(getContext(), "User registered successfully, please verify", Toast.LENGTH_SHORT).show();


                                }
                            });

                      //      navigationToHome();
                        }

                        else {

                         //   Toast.makeText(getContext(), "Fail to log in ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // error get and error msg.
                      // progressDialog.dismiss();
                       // Toast.makeText(getContext(), "Failure"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void navigationToHome(){

       // AuthenticationFragment.setAuthChecker(true);
        Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_homeScreen);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
      super.onActivityResult(requestCode,resultCode, data);

      // result returned from launching the intent from google signInApi.getsigninintent
      if (requestCode == RC_SIGN_IN){
          Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

          try {
              // google sign in was successful ,authenticate with firebase
              GoogleSignInAccount account = task.getResult(ApiException.class);
              firebaseAuthWithGoogle(account);

          } catch (ApiException e) {
              // google sign in failed
              Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

          }
      }
    }
    private void  firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                // sign in success update ui with the signed in user's information
                    FirebaseUser user = mAuth.getCurrentUser();

                    // show user email in toat
                    Toast.makeText(getContext(), ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                    // go to home after logged in
                    navigationToHome();

                }else {
                    // if sigin fails
                    Toast.makeText(getContext(), "Login Failed...", Toast.LENGTH_SHORT).show();
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

}