package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.david.myapplication.GameBoard.GameBoard;
import com.example.david.myapplication.ToolBar.Energy;
import com.example.david.myapplication.ToolBar.Points;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private GameBoard game;
    private Points points;
    private Energy energy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = (GameBoard) getFragmentManager().findFragmentById(R.id.gameboard_fragment);
        points = (Points) getFragmentManager().findFragmentById(R.id.points_fragment);
        energy = (Energy) getFragmentManager().findFragmentById(R.id.energy_fragment);


        fillGameboardFromJSONFile();

    }

    public void fillGameboardFromJSONFile(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("items");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("q"));

                String question = jo_inside.getString("q");
                String answer = jo_inside.getString("a");
                String xPos = jo_inside.getString("x");
                String yPos = jo_inside.getString("y");
                String arrType = jo_inside.getString("arrType");
                String arrDir = jo_inside.getString("arrDir");
                String arrPos = jo_inside.getString("arrPos");

                game.setQuestion(Integer.parseInt(xPos),Integer.parseInt(yPos),question,answer,arrPos,arrDir);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("json_example/json.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "Windows-1255");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
