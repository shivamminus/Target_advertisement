package com.sourabh.www.serv;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    ListView lst;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ArrayList<String> st;
    SwipeRefreshLayout srl;
    int a=0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Menu) {
            promptSpeechInput();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        startService(new Intent(MainActivity.this,service.class));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst=(ListView)findViewById(R.id.list);
        srl=(SwipeRefreshLayout)findViewById(R.id.swip);
        st=new ArrayList<>();
        promptSpeechInput();
        for(int i=1;i<3;i++){
            st.add("download"+i);
        }
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(a==0 ){
                    st.add("ad1");
                    st.add("download3");

                }
                if(a==1){
                    st.add("ad2" );
                    st.add("download4");


                }
                if(a==2){
                    st.add("ad3");


                }
                adapter adp1=new adapter(getApplicationContext(),st);
                lst.setAdapter(adp1);
                srl.setRefreshing(false);
                a++;
            }
        });
        adapter adp=new adapter(getApplicationContext(),st);
        lst.setAdapter(adp);


    }
    public void  load(final String st1){

                if(st1.equals("shoes")){
                    st.add("ad1");
                    st.add("download3");

                }
                if(st1.equals("watch")){
                    st.add("ad2" );
                    st.add("download4");


                }
                if( st1.equals("voot")){
                    st.add("ad3");


                }
                adapter adp1=new adapter(getApplicationContext(),st);
                lst.setAdapter(adp1);
                srl.setRefreshing(false);
                a++;


        adapter adp=new adapter(getApplicationContext(),st);
        lst.setAdapter(adp);


    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(MainActivity.this,result.get(0),Toast.LENGTH_SHORT).show();
                    load(result.get(0));
                }
                break;
            }

        }
    }




}