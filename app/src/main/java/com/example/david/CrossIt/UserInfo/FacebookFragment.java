package com.example.david.CrossIt.UserInfo;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.david.CrossIt.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {

    CallbackManager callbackManager;
    Button fb;
    LoginButton loginButton;
    View view;
    Fragment f;
    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        f = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_facebook, container, false);

        fb = (Button) view.findViewById(R.id.fb);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);



        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("== My activity ===","Logged in to FB");
                Profile p = Profile.getCurrentProfile();

                Toast.makeText(getApplicationContext(), "Welcome "+ p.getName(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("== My activity ===","Error : "+exception.toString());
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isLoggedIn()){
                    LoginManager.getInstance().logOut();
                    Log.d("== My activity ===","FB is logged out");

                }else{
                    LoginManager.getInstance().logInWithReadPermissions(f, Arrays.asList("user_photos", "email","public_profile", "user_friends"));
                    Log.d("== My activity ===","FB is logged in");

                }
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

}
