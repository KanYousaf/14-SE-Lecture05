package com.example.admin.lecture05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE=777;
    private EditText userNameET,userPasswordET;
    private Button enter_btn;
    private TextView display_received_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET=(EditText)this.findViewById(R.id.userName);
        userPasswordET=(EditText)this.findViewById(R.id.userPassword);
        enter_btn=(Button)this.findViewById(R.id.enter_button);
        display_received_data=(TextView)this.findViewById(R.id.display_passed_result_tv);
        display_received_data.setVisibility(View.GONE);
        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userNameET.getText().toString().equals("")||userPasswordET.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter User name and password",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(MainActivity.this, NextActivity.class);
                    intent.putExtra("UserNameToSend",userNameET.getText().toString());
                    startActivityForResult(intent, REQ_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE && resultCode==RESULT_OK){

            display_received_data.setVisibility(View.VISIBLE);
            display_received_data.setText("Data: "+data.getExtras().getString("secret_key")
                                        +"Key: "+data.getExtras().getInt("random_num"));

        }
    }
}
