package com.example.admin.lecture05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class NextActivity extends AppCompatActivity {
    private TextView display_user_name_tv;
    private String userNameReceived;
    private GraphView display_graph;
    private LineGraphSeries<DataPoint> series;
    private double x,y;
    private Button go_back;
    private EditText secret_key_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        display_user_name_tv=(TextView)this.findViewById(R.id.display_userName);
        display_graph=(GraphView)this.findViewById(R.id.display_graph);
        go_back=(Button)this.findViewById(R.id.btn_go_back);
        secret_key_value=(EditText)this.findViewById(R.id.display_secret_key);

        //define line series object
        series=new LineGraphSeries<>();

        //pass user name to next activity
        Intent intent=getIntent();
        userNameReceived=intent.getExtras().getString("UserNameToSend");
        display_user_name_tv.append(userNameReceived);
        x=-10;
        for(int i=0; i< 500; i++) {
            x=x+0.1;
            y=Math.cos(x);
            series.appendData(new DataPoint(x, y), true, 500);
        }
        //graph series
        display_graph.addSeries(series);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secret_key_value.getText().toString().equals("")){
                    Toast.makeText(NextActivity.this, "Enter Key",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent();
                    intent.putExtra("secret_key",secret_key_value.getText().toString());
                    Random random=new Random();
                    int x1=random.nextInt(1000);
                    intent.putExtra("random_num",x1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
