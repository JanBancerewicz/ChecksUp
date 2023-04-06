package com.example.checksup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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



//        List<Switch> switchList = Arrays.asList(switch1, switch2, switch3, switch4, switch5);
        List<Switch> switchList = generateSwitches(switch1,switch2,switch3,switch4,switch5);

        List<Integer> ostatnie = new ArrayList<>();


        for(int i=0;i<switchList.size();i++){
            switchList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View V) {
                    for(int j=0;j<switchList.size();j++){
                        if(V.getId()==switchList.get(j).getId()){
                            if(switchList.get(j).isChecked()){
                                int counter=0;
                                for(int k=0;k<ostatnie.size();k++){
                                    if(ostatnie.get(k)==j){counter+=1;break;}
                                }
                                if(counter==0){ostatnie.add(j);}
                                if(ostatnie.size()>=switchList.size()){
                                    ostatnie.remove(0);
                                }

//                                for(int l=0;l<switchList.size();l++){
//                                    switchList.get(l).setChecked(false);
//
//                                }
//

//                                for(int i1=0;i1<ostatnie.size();i1++){
//                                        switchList.get(ostatnie.get(i1)).setChecked(true);
//
//                                }

                                List<Integer> nowe = new ArrayList<>();

                                for(int l=0;l<switchList.size();l++){
                                    nowe.add(l);

                                }
                                for(int i1=0;i1<ostatnie.size();i1++){
                                        switchList.get(ostatnie.get(i1)).setChecked(true);
                                        for(int i2=0;i2<nowe.size();i2++){
                                            if(ostatnie.get(i1)==nowe.get(i2)){
                                                nowe.remove(i2);
                                            }
                                        }
                                }
                                switchList.get(nowe.get(0)).setChecked(false);



                            }else{
                                ostatnie.remove(ostatnie.indexOf(j));
                            }
                        }
                    }
                }
            });
        }
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



}