package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.david.myapplication.GameBoard.GameBoard;
import com.example.david.myapplication.ToolBar.Energy;
import com.example.david.myapplication.ToolBar.Points;

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
    }


}
