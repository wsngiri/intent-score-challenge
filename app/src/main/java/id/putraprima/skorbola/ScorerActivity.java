package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import id.putraprima.skorbola.model.Score;
import id.putraprima.skorbola.model.Scorer;

public class ScorerActivity extends AppCompatActivity {

    private EditText scorerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);

        scorerName = findViewById(R.id.nameScorer);

    }


    public void BacktoMatch(View view) {

        String nameScorer = scorerName.getText().toString();
        int Score = 1;
        Score++;
        Scorer scorer = new Scorer(nameScorer, Score);
        Intent intent = new Intent();
        intent.putExtra("scorer", scorer);
        setResult(Activity.RESULT_OK,intent);
        finish();

    }
}
