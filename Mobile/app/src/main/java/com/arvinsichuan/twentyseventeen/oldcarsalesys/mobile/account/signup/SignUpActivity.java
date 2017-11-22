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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.signup;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.arvinsichuan.resttool.JsonObjectParseTool;
import com.arvinsichuan.resttool.RestTemplateWithCookie;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities.User;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.Configurations;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.entities.WebInfoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author ArvinSiChuan
 */
public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SIGN_UP";

    public static final int RESULT_CODE_FOR_SIGN_UP_SUCCESS = 0;
    private static final int RESULT_CODE_FOR_SIGN_UP_CANCEL = 1;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;
    private SignUpActivity thisActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameEditText = (EditText) findViewById(R.id.editText_reg_username);
        passwordEditText = (EditText) findViewById(R.id.editText_reg_password);
        passwordAgainEditText = (EditText) findViewById(R.id.editText_reg_password_again);
        Button signUpButton = (Button) findViewById(R.id.button_reg_sigin_up);
        Button cancelButton = (Button) findViewById(R.id.button_reg_cancel);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordAgain = passwordAgainEditText.getText().toString();
                if (password == null || "".equals(password)) {
                    Toast.makeText(thisActivity, "Password should not be empty.", Toast.LENGTH_LONG).show();
                } else if (password.equals(passwordAgain)) {
                    Toast.makeText(thisActivity, "Submitting...", Toast.LENGTH_LONG).show();
                    signUp(username, password);
                } else {
                    Toast.makeText(thisActivity, "Second password input should be same as the first one.", Toast
                            .LENGTH_LONG)
                            .show();
                }


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CODE_FOR_SIGN_UP_CANCEL, getIntent());
                finish();
            }
        });
    }

    private void signUp(String username, String password) {
        try {
            new RequestForSignUp().execute(username, password);
        } catch (Exception e) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
        }
    }

    private class RequestForSignUp extends AsyncTask<String, String, WebInfoEntity> {

        @Override
        protected WebInfoEntity doInBackground(String... strings) {
            RestTemplateWithCookie template = new RestTemplateWithCookie();
            String url = Configurations.HOST_ROOT + "/users/signUp?username={username}&password={password}";
            WebInfoEntity webInfo = new WebInfoEntity();
            try {
                webInfo = template.postForObject(url, null, WebInfoEntity.class, strings[0], strings[1]);
            } catch (Exception e) {
                e.printStackTrace();
                webInfo.haveException(e);
            }
            return webInfo;
        }

        @Override
        protected void onPostExecute(WebInfoEntity webInfoEntity) {
            super.onPostExecute(webInfoEntity);
            if (webInfoEntity == null || webInfoEntity.isEmpty()) {
                Toast.makeText(thisActivity, R.string.sign_up_failed, Toast.LENGTH_LONG).show();
            } else if ("EXCEPTION".equals(webInfoEntity.get("status"))) {
                Toast.makeText(thisActivity, R.string.user_already_signed_up, Toast.LENGTH_LONG).show();
            } else if (webInfoEntity.get("user") != null) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                Map map = (Map) webInfoEntity.get("user");
                try {
                    User user = JsonObjectParseTool.parse(map, User.class);
                    bundle.putString("username", user.getUsername());
                    Log.d(TAG, "onPostExecute: ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtras(bundle);
                setResult(RESULT_CODE_FOR_SIGN_UP_SUCCESS, getIntent());
                Toast.makeText(thisActivity, "Sign up Successfully.", Toast.LENGTH_LONG).show();
                finish();
                Log.d(TAG, "onPostExecute: " + webInfoEntity);
            }
        }
    }

}
