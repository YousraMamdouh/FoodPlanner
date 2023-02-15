package com.example.foodplanner.login.view;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
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
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;


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

    private void loginUser(String email, String passw) {
        mAuth.signInWithEmailAndPassword(email,passw)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            FirebaseUser user = mAuth.getCurrentUser();
                          //  Toast.makeText(getContext(), "Registered "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            navigationToHome();

                        }else {
                            //progressDialog.dismiss();
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