package exampleoncreatingfixedfragment.example.com.tuts;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Button signInMain, signUpMain, signInBtnLinear, signUpBtnLinear;
    EditText signUp_name, signUp_password, signIn_name, signIn_password;
    LinearLayout linearFacebook, linearSignIn, linearSignUp;
    TextView backFromSignIn, backFromSignUp,ErrorTxt;
    RelativeLayout relativContainer;
    private ProfileTracker mProfileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "exampleoncreatingfixedfragment.example.com.tuts",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            //handle exception
        } catch (NoSuchAlgorithmException e) {
            // handle exception
        }



        //final LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        callbackManager = CallbackManager.Factory.create();

        ErrorTxt= (TextView)findViewById(R.id.errorTxtFace);
        // handle methods after login
        /*loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                if (Profile.getCurrentProfile() == null) {

                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            String url = "http://192.168.1.5/FacebookCheck.php";
                            final String name =profile2.getFirstName();
                            final int facebookId = loginButton.getId();
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try{
                                                System.out.println("onSuccess" + "  onResponse  ");
                                                JSONObject result = new JSONObject(response);
                                                JSONArray messageArray = result.getJSONArray("message");
                                                JSONObject userObject = messageArray.getJSONObject(0);
                                                String userID = userObject.getString("userID");
                                                ErrorTxt.setText(userID);
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("userID", userID));
                                            }catch (Exception ex){
                                                System.out.println(ex.getStackTrace());
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> params = new HashMap();
                                    params.put("facebookId",Integer.toString(facebookId));
                                    params.put("firstName",name);
                                    return params;
                                }
                            };
                            mProfileTracker.stopTracking();
                            Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);
                        }
                    };
                }


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
            }


        });*/



        // in main
        signInMain = (Button) findViewById(R.id.signinBtn);
        signUpMain = (Button) findViewById(R.id.signupBtn);
// in linear sign in
        signInBtnLinear = (Button) findViewById(R.id.sign_in_btn);
        signIn_name = (EditText) findViewById(R.id.signin_name);
        signIn_password = (EditText) findViewById(R.id.signin_password);
        backFromSignIn = (TextView) findViewById(R.id.back_from_sign_in);
// in linear sign up
        signUpBtnLinear = (Button) findViewById(R.id.sign_up_btn);
        signUp_name = (EditText) findViewById(R.id.signup_name);
        signUp_password = (EditText) findViewById(R.id.signup_password);
        backFromSignUp = (TextView) findViewById(R.id.back_from_sign_up);

// layouts
        relativContainer = (RelativeLayout) findViewById(R.id.activity_login);
        linearFacebook = (LinearLayout) findViewById(R.id.linear_facebook_btn);
        linearSignIn = (LinearLayout) findViewById(R.id.sign_in_leanrlayout);
        linearSignUp = (LinearLayout) findViewById(R.id.sign_up_leanrlayout);
// hid all layouts except main linear
        linearSignIn.setVisibility(View.GONE);
        linearSignUp.setVisibility(View.GONE);
// action sign in button in main  and back in sign in linear
        signInMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Slide slideLeft = new Slide();
                slideLeft.setSlideEdge(Gravity.LEFT);
               /* slideLeft.setDuration(1000);*/

                Slide slideRight = new Slide();
                slideRight.setSlideEdge(Gravity.RIGHT);
                /*slideRight.setDuration(1000);*/
                TransitionManager.beginDelayedTransition(relativContainer, slideRight);
                linearFacebook.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(relativContainer, slideLeft);


                linearSignIn.setVisibility(View.VISIBLE);
            }
        });
        backFromSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Slide slideLeft = new Slide();
                slideLeft.setSlideEdge(Gravity.LEFT);
                /*slideLeft.setDuration(1000);*/

                Slide slideRight = new Slide();
                slideRight.setSlideEdge(Gravity.RIGHT);
               /* slideRight.setDuration(1000);*/
                TransitionManager.beginDelayedTransition(relativContainer, slideRight);
                linearSignIn.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(relativContainer, slideLeft);
                linearFacebook.setVisibility(View.VISIBLE);
            }
        });
// action sign up button in main
        signUpMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Slide slideLeft = new Slide();
                slideLeft.setSlideEdge(Gravity.LEFT);
                /*slideLeft.setDuration(500);*/

                Slide slideRight = new Slide();
                slideRight.setSlideEdge(Gravity.RIGHT);
                /*slideRight.setDuration(500);*/
                TransitionManager.beginDelayedTransition(relativContainer, slideRight);


                TransitionManager.beginDelayedTransition(relativContainer, slideLeft);


                linearSignUp.setVisibility(View.VISIBLE);
                linearFacebook.setVisibility(View.GONE);
            }
        });
        backFromSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Slide slideLeft = new Slide();
                slideLeft.setSlideEdge(Gravity.LEFT);
                /*slideLeft.setDuration(1000);*/

                Slide slideRight = new Slide();
                slideRight.setSlideEdge(Gravity.RIGHT);
                /*slideRight.setDuration(1000);*/
                TransitionManager.beginDelayedTransition(relativContainer, slideRight);
                linearSignUp.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(relativContainer, slideLeft);
                linearFacebook.setVisibility(View.VISIBLE);
            }
        });

        signUpBtnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameUp = signUp_name.getText().toString();
                final String passwordUp = signUp_password.getText().toString();
                if(usernameUp.equals("") || passwordUp.equals("")){
                    // wrong
                }else{
                    String url = new String("http://192.168.1.5/signUp.php");
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject result = new JSONObject(response);
                                        JSONArray messageArray = result.getJSONArray("message");
                                        JSONObject userObject = messageArray.getJSONObject(0);
                                        String userID = userObject.getString("userID");
                                        TextView show = (TextView) findViewById(R.id.errorTxtUp);
                                        show.setText(userID);
                                    }catch(Exception ex){

                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("username",usernameUp);
                            params.put("password",passwordUp);
                            return params;
                        }
                    };
                    Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);
                }

            }
        });
        signInBtnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameIn = signIn_name.getText().toString();
                final String passwordIn = signIn_password.getText().toString();
                String url = "http://192.168.1.5/Login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject result = new JSONObject(response);
                                    JSONArray messageArray = result.getJSONArray("message");
                                    JSONObject userObject = messageArray.getJSONObject(0);
                                    String userID = userObject.getString("userID");
                                    TextView show = (TextView) findViewById(R.id.errorTxtIn);
                                    show.setText(userID);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("userID", Integer.parseInt(userID)));
                                }catch(Exception ex){

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("username",usernameIn);
                        params.put("password",passwordIn);
                        return params;
                    }
                };
                Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
