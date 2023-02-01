package com.example.lab1a_rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.lab1a_rockpaperscissors.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerScore = 0;
    int computerScore = 0;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.rockButon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                newRound(Weapon.ROCK);
            }
        });

        binding.paperButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                newRound(Weapon.PAPER);
            }
        });

        binding.scissorsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                newRound(Weapon.SCISSORS);
            }
        });

    }

    private Weapon getComputerWeapon(){
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch(randomNumber){
            case 0:
                return Weapon.ROCK;
            case 1:
                return Weapon.PAPER;
            case 2:
                return Weapon.SCISSORS;
        }
        return Weapon.ROCK;
    }

    private String determineWinner(Weapon playerWeapon, Weapon computerWeapon) {
        String result;
        if (playerWeapon == computerWeapon) {
            result = "It's a tie!";
        } else if ((playerWeapon == Weapon.ROCK && computerWeapon == Weapon.SCISSORS) ||
                (playerWeapon == Weapon.PAPER && computerWeapon == Weapon.ROCK) ||
                (playerWeapon == Weapon.SCISSORS && computerWeapon == Weapon.PAPER)) {
            result = "Player wins ... " + playerWeapon + " beats " + computerWeapon;
            playerScore++;
        } else {
            result = "Computer wins ... " + computerWeapon.toString() + " beats " + playerWeapon.toString();
            computerScore++;
        }
        return result;



    }

    private void newRound(Weapon playerWeapon) {
        Weapon computerWeapon = getComputerWeapon();

        String result = determineWinner(playerWeapon, computerWeapon);

        binding.scoreboardTextView.setText(getString(R.string.scoreboardTextView, playerScore, computerScore));
        binding.playerWeaponTextView.setText(getString(R.string.playerWeaponTextView, playerWeapon.name()));
        binding.computerWeaponTextView.setText(getString(R.string.computerWeaponTextView, computerWeapon.name()));
        binding.winnerTextView.setText(result);
    }

}