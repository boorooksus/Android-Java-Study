package com.example.systeminfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView[] info;
    Map<String, String> props = new HashMap<String, String>();
    List <String> propNames = new ArrayList<String>();
    int[] ids;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propNames.add("OS name");
        propNames.add("OS architecture");
        propNames.add("OS version");
        propNames.add("User name");

        props.put(propNames.get(0), "os.name");
        props.put(propNames.get(1), "os.arch");
        props.put(propNames.get(2), "os.version");
        props.put(propNames.get(3), "user.name");


        info = new TextView[5];
        ids = new int[5];

        ids[0] = R.id.osName;
        ids[1] = R.id.osArch;
        ids[2] = R.id.osVersion;
        ids[3] = R.id.userName;

        for(int i = 0; i < propNames.size(); i++){
            info[i] = findViewById(ids[i]);

            res = propNames.get(i) + ": ";
            res += System.getProperty(Objects.requireNonNull(props.get(propNames.get(i))));

            info[i].setText(res);


        }

        //listView = findViewById(R.id.listView);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,an)




    }
}