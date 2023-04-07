package com.example.checksup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int active=3;
    List<Switch> switchList;
    List<Integer> ostatnie = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        TextView tv = (TextView) findViewById(R.id.logo);


        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        Switch switch4 = (Switch) findViewById(R.id.switch4);
        Switch switch5 = (Switch) findViewById(R.id.switch5);
        Button buttonPlus = (Button) findViewById(R.id.buttonplus);
        Button buttonMinus = (Button) findViewById(R.id.buttonminus);



//        switchList = Arrays.asList(switch1, switch2, switch3, switch4, switch5);
        switchList = generateSwitches(switch1,switch2,switch3,switch4,switch5);



        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(active<5 && active>=2){
                    active+=1;
                    if(active==3){switch3.setVisibility(View.VISIBLE);switch3.setChecked(false);}
                    if(active==4){switch4.setVisibility(View.VISIBLE);switch4.setChecked(false);}
                    if(active==5){switch5.setVisibility(View.VISIBLE);switch5.setChecked(false);}

                    reset();
                    refreshStates();

                }


            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(active<=5 && active>2){
                    active-=1;
                    if(active==2){switch3.setVisibility(View.INVISIBLE);switch3.setChecked(false);}
                    if(active==3){switch4.setVisibility(View.INVISIBLE);switch4.setChecked(false);}
                    if(active==4){switch5.setVisibility(View.INVISIBLE);switch5.setChecked(false);}

                    reset();
                    refreshStates();
                }
            }

        });


        refreshStates();


    }
    protected void refreshStates() {
        try {
            for (int i = 0; i < switchList.size(); i++) {
                switchList.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        for (int j = 0; j < switchList.size(); j++) {
                            if (V.getId() == switchList.get(j).getId()) {
                                if (switchList.get(j).isChecked()) {
                                    int counter = 0;
                                    for (int k = 0; k < ostatnie.size(); k++) {
                                        if (ostatnie.get(k) == j) {
                                            counter += 1;
                                            break;
                                        }
                                    }
                                    if (counter == 0) {
                                        ostatnie.add(j);
                                    }
                                    if (ostatnie.size() >= switchList.size()) {
                                        ostatnie.remove(0);
                                    }

                                    List<Integer> nowe = new ArrayList<>();

                                    for (int l = 0; l < switchList.size(); l++) {
                                        nowe.add(l);

                                    }
                                    for (int i1 = 0; i1 < ostatnie.size(); i1++) {
                                        switchList.get(ostatnie.get(i1)).setChecked(true);
                                        for (int i2 = 0; i2 < nowe.size(); i2++) {
                                            if (ostatnie.get(i1) == nowe.get(i2)) {
                                                nowe.remove(i2);
                                            }
                                        }
                                    }
                                    switchList.get(nowe.get(0)).setChecked(false);


                                } else {
                                    try{
                                    ostatnie.remove(ostatnie.indexOf(j));
                                    }catch (Exception x){}
                                }
                            }
                        }
                    }
                });
            }
        }catch(Exception x){}
    }

    protected List<Switch> generateSwitches(Switch s1, Switch s2, Switch s3, Switch s4, Switch s5){
        List<Switch> switchList = new ArrayList<>();
        if(s1.getVisibility()==View.VISIBLE){
            switchList.add(s1);
        }
        if(s2.getVisibility()==View.VISIBLE){
            switchList.add(s2);
        }
        if(s3.getVisibility()==View.VISIBLE){
            switchList.add(s3);
        }
        if(s4.getVisibility()==View.VISIBLE){
            switchList.add(s4);
        }
        if(s5.getVisibility()==View.VISIBLE){
            switchList.add(s5);
        }

        return switchList;
    }

    public void promptSaveOnClick(View V){
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        Switch switch4 = (Switch) findViewById(R.id.switch4);
        Switch switch5 = (Switch) findViewById(R.id.switch5);
        EditText _promptcontent = (EditText) findViewById(R.id.promptcontent);
        TextView _itemvalue = (TextView) findViewById(R.id.itemvalue);

        String promptcontent = String.valueOf(_promptcontent.getText());
        _promptcontent.setText("");
        String val= String.valueOf(_itemvalue.getText());

        switch(val){
            case "1":
                switch1.setText(promptcontent);
                break;
            case "2":
                switch2.setText(promptcontent);
                break;
            case "3":
                switch3.setText(promptcontent);
                break;
            case "4":
                switch4.setText(promptcontent);
                break;
            case "5":
                switch5.setText(promptcontent);
                break;
        }
    }

    public void promptIncrease(View V){
        TextView _itemvalue = (TextView) findViewById(R.id.itemvalue);
        int val= Integer.parseInt(String.valueOf(_itemvalue.getText()));
        val=(val%5)+1;
        _itemvalue.setText(String.valueOf(val));

    }

    public void promptDecrease(View V){
        TextView _itemvalue = (TextView) findViewById(R.id.itemvalue);
        int val= Integer.parseInt(String.valueOf(_itemvalue.getText()));
        if(--val==0){
            val=5;
        };
        _itemvalue.setText(String.valueOf(val));

    }

    public void promptHide(View V){
        Button _hideprompt = (Button) findViewById(R.id.hideprompt);
        LinearLayout _prompt = (LinearLayout) findViewById(R.id.prompt);

        if(_prompt.getVisibility()==View.VISIBLE){
            _prompt.setVisibility(View.INVISIBLE);
            _hideprompt.setText("SHOW");
        }else{
            _prompt.setVisibility(View.VISIBLE);
            _hideprompt.setText("HIDE");
        }



    }

    protected void reset(){
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        Switch switch4 = (Switch) findViewById(R.id.switch4);
        Switch switch5 = (Switch) findViewById(R.id.switch5);

        List<Switch> switchInfo= Arrays.asList(switch1, switch2, switch3, switch4, switch5);
        for(int i=0;i<switchInfo.size();i++){
            switchInfo.get(i).setChecked(false);
        }
        ostatnie=new ArrayList<>();
        switchList = generateSwitches(switch1,switch2,switch3,switch4,switch5);



    }

}