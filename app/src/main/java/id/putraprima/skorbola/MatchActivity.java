package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;

import id.putraprima.skorbola.model.Result;
import id.putraprima.skorbola.model.Score;
import id.putraprima.skorbola.model.Scorer;

public class MatchActivity extends AppCompatActivity {

    private TextView homeTxt, awayTxt, homeScore, awayScore;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        homeTxt = findViewById(R.id.txt_home);
        awayTxt = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Score score = extras.getParcelable("score");
            homeTxt.setText(score.getNameHome());
            awayTxt.setText(score.getNameAway());
            homeScore.setText(String.valueOf(0));
            awayScore.setText(String.valueOf(0));
            homeUri = score.getHomeUri();
            awayUri = score.getAwayUri();

            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                try {
                    Scorer scorer  = (Scorer) data.getParcelableExtra("scorer");
                    awayScore.setText(String.valueOf(scorer.getScorer()));
                } catch (IOError e) {
                    Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            else if(requestCode == 2){
                if(resultCode == Activity.RESULT_OK){
                    try {
                        Scorer scorer  = data.getParcelableExtra("scorer");
                        homeScore.setText(String.valueOf(scorer.getScorer()));
                    } catch (IOError e) {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }


    }


    public void addAway(View view) {
        int LAUNCH_SCORE_ACTIVITY = 1;
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i, LAUNCH_SCORE_ACTIVITY);

//        String data = awayScore.getText().toString();
//        int number =  Integer.parseInt(data);
//        number++;
//        awayScore.setText(String.valueOf(number));
    }

    public void addHome(View view) {

        int LAUNCH_SCORE_ACTIVITY = 2;
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i, LAUNCH_SCORE_ACTIVITY);

//        String data = homeScore.getText().toString();
//        int number =  Integer.parseInt(data);
//        number++;
//        homeScore.setText(String.valueOf(number));
    }

    public void handleResult(View view) {

        String nameAway = awayTxt.getText().toString();
        String nameHome = homeTxt.getText().toString();
        int scoreAw = Integer.parseInt(awayScore.getText().toString());
        int scoreHm = Integer.parseInt(homeScore.getText().toString());

        Result result = new Result(scoreAw,scoreHm,nameHome ,nameAway);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);

    }
}
