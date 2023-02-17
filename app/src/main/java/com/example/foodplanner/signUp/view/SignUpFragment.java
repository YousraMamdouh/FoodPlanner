package com.example.foodplanner.signUp.view;

import static android.app.ProgressDialog.show;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    Button signUpButton;
    EditText name;
    EditText Email;
    EditText password;
    EditText password2;

    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signUpButton = view.findViewById(R.id.fav);
        name = view.findViewById(R.id.nameSignuptxt);
        Email = view.findViewById(R.id.emailSigntxt);
        password = view.findViewById(R.id.passwordSignup);
        password2 = view.findViewById(R.id.passwordSignup2);


        // In your sign-in activity's onCreate method, get the shared instance of the FirebaseAuth object:
        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registering User...");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // email and password input
                String email = Email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String confirmPassword = password2.getText().toString().trim();
                String userName = name.getText().toString().trim();

                // validate
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // set error focus
                    Email.setError("Invalid Email");
                    Email.setFocusable(true);
                } else if (Password.length() < 6 ) {
                    password.setError("Password length at least 6 characters ");
                    password.setFocusable(true);
                } else if ( !Password.equals(confirmPassword)) {
                    password2.setError("Password not match");
                    password2.setFocusable(true);
                }else if ( userName.isEmpty()) {
                    name.setError("you have to add your name");
                    name.setFocusable(true);
                }

                else {
                    registerUser(email, String.valueOf(password)); // register user
                }



            }
        });

        return view;
    }

    private void registerUser(String email, String password) {
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Registered "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            navigateToHome();

                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Fail to sign up", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
});

 }
 public void navigateToHome(){
     Navigation.findNavController(this.getView()).navigate(R.id.action_signUpFragment_to_homeScreen);

 }

}