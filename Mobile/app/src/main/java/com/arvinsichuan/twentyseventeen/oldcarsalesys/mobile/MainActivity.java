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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.arvinsichuan.resttool.RestTemplateWithCookie;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MAINACTIVITY";

    private Button testButton = null;
    private MainActivity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = (Button) findViewById(R.id.button_session_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Toast.makeText(thisActivity, "Sending Request", Toast.LENGTH_LONG).show();
                SessionTestRequest request = new SessionTestRequest();
                request.execute();
            }
        });
    }


    private class SessionTestRequest extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            RestTemplateWithCookie template = new RestTemplateWithCookie();
            return template.getForObject("http://192.168.137.1:8080/testSession/csrf", Map.class).toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(thisActivity, s, Toast.LENGTH_LONG).show();
            TestSessionPostWithCSRF testSessionPostWithCSRF = new TestSessionPostWithCSRF();
            testSessionPostWithCSRF.execute("TEST-Message");
            Toast.makeText(thisActivity, "posting", Toast.LENGTH_LONG).show();
        }
    }

    private class TestSessionPostWithCSRF extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            RestTemplateWithCookie template = new RestTemplateWithCookie();
            return template.postForObject("http://192.168.137.1:8080/testSession/post?info={info}",
                    null, Map.class, "MyMessage").toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(thisActivity, s, Toast.LENGTH_LONG).show();
        }
    }
}
