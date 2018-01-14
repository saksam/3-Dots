package com.example.saksham_.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    //0 equals yellow and 1 means red
    int active=0;
    //2 means unplayed
    int[] gamestate={2,2,2,2,2,2,2,2,2};

    boolean isactive=true;

    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("#Connect 3");
    }

    public void finish(View view)
    {
        exit(1);
    }

    public void playagain(View view)
    {
        isactive=true;

        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);

        layout.setVisibility(View.INVISIBLE);

        active=0;

        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        GridLayout grid=(GridLayout)findViewById(R.id.grid);

        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }

    public void dropin(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter=Integer.parseInt(counter.getTag().toString());

        if(isactive && gamestate[tappedcounter]==2) {

            gamestate[tappedcounter]=active;

            counter.setTranslationY(-1000f);

            if (active == 0) {
                counter.setImageResource(R.drawable.yellow);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] thewinpos: winpos)
            {
                if(gamestate[thewinpos[0]]==gamestate[thewinpos[1]] && gamestate[thewinpos[1]]==gamestate[thewinpos[2]]
                        && gamestate[thewinpos[0]]!=2)
                {
                    isactive=false;

                    TextView msg=(TextView)findViewById(R.id.display);

                    if(gamestate[thewinpos[0]]==0)
                        msg.setText("Yellow has won");
                    else
                        msg.setText("Red has won");

                    LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);

                    layout.setVisibility(View.VISIBLE);

                }
                else
                {
                    boolean gameover=true;
                    for(int i:gamestate)
                    {
                        if(i==2)
                        {
                            gameover=false;
                        }
                    }
                    if(gameover)
                    {
                        TextView msg=(TextView)findViewById(R.id.display);

                        msg.setText("It's a Draw");

                        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }
}
