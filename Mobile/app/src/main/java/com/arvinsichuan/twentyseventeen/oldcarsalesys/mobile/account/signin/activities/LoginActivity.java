/*
 *     This project is one of projects of ArvinSiChuan.com.
 *     Copyright (C) 2017, ArvinSiChuan.com <https://arvinsichuan.com>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.signin.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.arvinsichuan.resttool.RestTemplateWithCookie;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities.AuthoritiesEnum;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.signup.SignUpActivity;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.Configurations;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities.MainContentActivity;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.entities.WebInfoEntity;

/**
 * @author ArvinSiChuan
 */
public class LoginActivity extends AppCompatActivity {


    public static final int REQUEST_CODE_FOR_SIGN_UP_ACTIVITY = 0;
    private static final String TAG = "LOGIN_ACTIVITY";

    private LoginActivity thisActivity = this;
    private EditText usernameText;
    private Button loginButton;
    private EditText passwordText;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = (EditText) findViewById(R.id.editText_loginUsername);
        loginButton = (Button) findViewById(R.id.button_signin);
        passwordText = (EditText) findViewById(R.id.editText_loginPassword);
        signUpButton = (Button) findViewById(R.id.button_signup);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               userLogin(usernameText.getText().toString(), passwordText.getText()
                                                       .toString());
                                           }
                                       }
        );
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toUserSignUpActivity();
            }
        });
    }

    private void toUserSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FOR_SIGN_UP_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SIGN_UP_ACTIVITY) {
            switch (resultCode) {
                case SignUpActivity.RESULT_CODE_FOR_SIGN_UP_SUCCESS:
                    usernameText.setText(data.getStringExtra("username"));
                    break;
                default:
                    break;
            }
        }
    }

    private void userLogin(String username, String password) {
        UserLoginRequest request = new UserLoginRequest();
        Toast.makeText(this, "Logging in...", Toast.LENGTH_LONG).show();
        request.execute(username, password);
    }


    private class UserLoginRequest extends AsyncTask<String, String, WebInfoEntity> {

        @Override
        protected WebInfoEntity doInBackground(String... strings) {
            WebInfoEntity webInfo = new WebInfoEntity();
            RestTemplateWithCookie template = new RestTemplateWithCookie();
            String url = Configurations.HOST_ROOT + "/auth/login?username={username}&password={password}";
            try {
                webInfo = template.postForObject(url, null, WebInfoEntity.class, strings[0], strings[1]);
                template.retrieveCsrfToken();
                webInfo.addInfoAndData("usernameTemp",strings[0]);
                webInfo.addInfoAndData("passwordTemp", strings[1]);
            } catch (Exception e) {
                e.printStackTrace();
                webInfo.exception(e);
            }
            return webInfo;
        }

        @Override
        protected void onPostExecute(WebInfoEntity webInfo) {
            super.onPostExecute(webInfo);
            if (webInfo.get("loginStatus") != null) {
                Log.d("loginInfo", webInfo.get("loginStatus").toString());
                AuthoritiesEnum authoritiesEnum = AuthoritiesEnum.valueOf(webInfo.get("loginStatus").toString());
                if (authoritiesEnum == AuthoritiesEnum.ROLE_USER) {
                    Toast.makeText(thisActivity, "Log In Successfully", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onPostExecute: "+webInfo);
                    Configurations.USERNAME = webInfo.get("usernameTemp").toString();
                    Configurations.PASSWORD = webInfo.get("passwordTemp").toString();
                    Intent intent = new Intent(thisActivity, MainContentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(thisActivity, "Log In Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
