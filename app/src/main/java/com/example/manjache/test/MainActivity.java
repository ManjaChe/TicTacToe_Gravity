package com.example.manjache.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.Console;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    int[] score;
    int m=5;   // размер поля по горизонтали
    int n=5;   // размер поля по веритикали
    int col=4; // кол-во подряд для выйгрыша
    int[][] futureMoves;
    int[][] pole; // pole[m][n] ==> -1 = null; 0 = X; 1 = O;
    LinearLayout llt;
    OnClickListener btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        score = new int[]{0,0};

        this.setTitle("Вы: 0; Комп: 0;");

        //drawGrid(m, n);
        pole = new int[m][n];

        //pole = new int[][]{{-1,-1,-1,-1,1},{-1,-1,0,0,1},{-1,-1,-1,-1,1},{-1,-1,-1,-1,1},{-1,-1,-1,-1,1}};

        btnClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                //button.setText("X");
                int id = button.getId();
                int y = (id-(id%n))/m;
                int x = id%n;
                boolean c = check(x,y, pole);
                if(check(x,y, pole)){
                    setXY(x,y,0);

                    if(isWin(pole)){
                        //Put up the Yes/No message box
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder
                                .setTitle("Конец игры")
                                .setMessage("Ты выйграл!")
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Заново?", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.this.score[0]++;
                                        MainActivity.this.setTitle("Вы: "+MainActivity.this.score[0]+"; Комп: "+MainActivity.this.score[1]+";");
                                        drawGrid(MainActivity.this.m,MainActivity.this.n);
                                        //Yes button clicked, do something
                                        Toast.makeText(MainActivity.this, "Приятной игры!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Нет", null)						//Do nothing on no
                                .show();;
                        return;
                    }

                    goComp(x,y);
                }
            }
        };

        llt = (LinearLayout) findViewById(R.id.container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i = 0; i < m; i++){
            LinearLayout llth = new LinearLayout(this);
            //llth.setLayoutParams(layoutParams);
            for(int j=0; j<n; j++) {
                Button button = new Button(this);
                //button.setLayoutParams(layoutParams);
                button.setMinWidth(5);
                button.setId(i*m+j);
                button.setOnClickListener(btnClick);
                llth.addView(button);
                pole[i][j] = -1;
            }
            llt.addView(llth);
        }
    }

    public boolean check(int x, int y, int[][] pole){
        if(pole[x][y]>-1) return false;
        return (y==n-1)?true: ((y+1<n)? ((pole[x][y+1]>-1)?true:false) :false);
    }

    public boolean isWin(int[][] pole){
        boolean result = false;

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){

                int k = i;
                int l = j;

                int summ = 0;
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k++;
                    if(summ >= col){
                        return true;
                    }
                }
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k--;
                    if(summ >= col){
                        return true;
                    }
                }

                k = i;
                l = j;
                summ = 0;
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    l++;
                    if(summ >= col){
                        return true;
                    }
                }
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    l--;
                    if(summ >= col){
                        return true;
                    }
                }

                k = i;
                l = j;
                summ = 0;
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k++;
                    l++;
                    if(summ >= col){
                        return true;
                    }
                }
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k--;
                    l--;
                    if(summ >= col){
                        return true;
                    }
                }

                k = i;
                l = j;
                summ = 0;
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k--;
                    l++;
                    if(summ >= col){
                        return true;
                    }
                }
                while(summ<col && k<m && k>=0 && l<n && l>=0){
                    if(pole[i][j] > -1 && pole[i][j] == pole[k][l])
                        summ++;
                    else
                        break;
                    k++;
                    l--;
                    if(summ >= col){
                        return true;
                    }
                }
            }
        return false;
    }

    public int[][] getMoves(int[][] pole){
        int[][] moves = new int[m][2];
        int k=0;
        for(int i=0; i<m; i++){
            moves[i]=new int[]{-1,-1};
            for(int j=n-1; j>=0; j--){
                if(check(i,j,pole))
                    moves[k++] = new int[]{i,j};
            }
        }
        return moves;
    }

    public int getMovesLength(int[][] moves){
        int res = 0;
        for(int i=0; i<moves.length; i++){
            if(moves[i][0]!=-1)
                res++;
        }
        return res;
    }

    public int getScore(int[][] pole, boolean isO){
        int result = 0;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){

                int k = i;
                int l = j;

                // x
                // x
                // x
                int summ = 0;
                while(summ<col && l<m){
                    if(pole[i][l] == ((isO)?1:0))
                        summ++;
                    else
                        break;
                    l++;
                }
                //summ = 5^summ;
                result = (result<summ)?summ:result;

                k = i;
                l = j;
                // x x x
                summ = 0;
                while(summ<col && k<m){
                    if(pole[k][j] == ((isO)?1:0))
                        summ++;
                    else
                        break;
                    k++;
                }
                //summ = 5^summ;
                result = (result<summ)?summ:result;

                k = i;
                l = j;
                // x
                //  x
                //   x
                summ = 0;
                while(summ<col && k<m && l<n){
                    if(pole[k][l] == ((isO)?1:0))
                        summ++;
                    else
                        break;
                    k++;
                    l++;
                }
                //summ = 5^summ;
                result = (result<summ)?summ:result;

                k = i;
                l = j;
                //   x
                //  x
                // x
                summ = 0;
                while(summ<col && k>=0 && l<n){
                    if(pole[k][l] == ((isO)?1:0))
                        summ++;
                    else
                        break;
                    k--;
                    l++;
                }
                //summ = 5^summ;
                result = (result<summ)?summ:result;
            }
        return result;
    }



    public int[] ii(int[][] pole, boolean isO, int nn, int alpha, int beta){
        int[][] moves = getMoves(pole);
        int movesLength = getMovesLength(moves);

        int curScore = 0;
        int curScore2 = 0;
        int curScore3 = 0;
        int ibestMoves = 0;
        int[][] bestMoves = new int[movesLength][3];

        for(int i=0; i<movesLength; i++)
            bestMoves[i][2] = -10000;

        int maxMoves=2;

        if(movesLength == 0 || nn>=maxMoves){
            /*for(int i=0; i<movesLength; i++) {
                pole[moves[i][0]][moves[i][1]] = (isO)?1:0;
                curScore3 = getScore(pole, isO);
                pole[moves[i][0]][moves[i][1]] = (!isO)?1:0;
                curScore2 = getScore(pole, !isO);
                curScore += curScore3;
                pole[moves[i][0]][moves[i][1]] = -1;
                if(curScore2>=(4)) curScore = -10000;
                if(curScore3>=(4)) curScore =  10000;
            }*/
            curScore3 = getScore(pole, isO);
            curScore2 = getScore(pole, !isO);
            curScore += curScore3/* - curScore2*/;
            if(curScore2>=(4)) curScore = -10000;
            if(curScore3>=(4)) curScore =  10000;

            return new int[]{curScore,-1,-1};
        }else{
            for(int i=0; i<movesLength; i++) {
                pole[moves[i][0]][moves[i][1]] = (isO)?1:0;
                curScore = ii(pole, !isO, nn+1, alpha, beta)[0];

                if(nn==0){
                    nn=0;
                }
                if(isO && curScore >= alpha){
                    alpha = curScore;
                    bestMoves[ibestMoves][0] = moves[i][0];
                    bestMoves[ibestMoves][1] = moves[i][1];
                    bestMoves[ibestMoves][2] = curScore;
                    ibestMoves++;
                }

                if(!isO && curScore <= beta){
                    beta = curScore;
                    bestMoves[ibestMoves][0] = moves[i][0];
                    bestMoves[ibestMoves][1] = moves[i][1];
                    bestMoves[ibestMoves][2] = curScore;
                    ibestMoves++;
                }


                pole[moves[i][0]][moves[i][1]] = -1;

                //if(alpha>=beta) break; //отсечение
            }
            int rnd = 0;
            int max = -10000;
            int maxs = 0;

            if(nn==0) {
                for (int i = 0; i < ibestMoves; i++) {
                    if (bestMoves[i][2] > max)
                        max = bestMoves[i][2];
                }

                for (int i = 0; i < ibestMoves; i++) {
                    if (bestMoves[i][2] == max) {
                        bestMoves[maxs][0] = bestMoves[i][0];
                        bestMoves[maxs][1] = bestMoves[i][1];
                        maxs++;
                    }
                }

                rnd = (int) Math.round(Math.random() * (maxs - 1));
                rnd = (rnd<0)?0:rnd;
            }

            return new int[]{ (isO)? alpha:beta, bestMoves[rnd][0], bestMoves[rnd][1]};
        }


        //return 0;
        /*
        int[][] winMoves = new int[m][2];
        int[][] goodMoves = new int[m][2];
        int[][] paritiMoves = new int[m][2];

        for(int b=0; b<m;b++){
            winMoves[b] = new int[]{-1,-1};
            goodMoves[b] = new int[]{-1,-1};
            paritiMoves[b] = new int[]{-1,-1};
        }

        int win = 0;
        int good = 0;
        int pariti = 0;

        for(int i=0; i<getMovesLength(moves); i++){
            // если мовес не пуст
            int[][] newPole = new int[m][n];

            for(int k=0; k<pole.length; k++){
                for(int l=0; l<pole[k].length; l++)
                    newPole[k][l] = pole[k][l];
            }

            newPole[moves[i][0]][moves[i][1]] = (isO)?1:0;

            if(isWin(newPole)){
                if(nn==0 && isO){
                    futureMoves = new int[][]{{moves[i][0],moves[i][1]}};
                }
                return (isO)?2:-2;
            }

            // 3 выйгрыш; 2 есть ходы; 1 нет ходов; 0 проигрыш;
            // 2 выйгрыш; 1 есть ходы; 0 нет ходов; -2 проигрыш;
            switch(ii(newPole, !isO, nn+1)){
                case 2:
                    win++;
                    if(nn==0)
                        winMoves[win-1] = new int[]{moves[i][0],moves[i][1]};
                    break;
                case 1:
                    good++;
                    if(nn==0)
                        goodMoves[good-1] = new int[]{moves[i][0],moves[i][1]};
                    break;
                case 0:
                    pariti++;
                    if(nn==0)
                        paritiMoves[pariti-1] = new int[]{moves[i][0],moves[i][1]};
                    break;
            }
        }

        // 3 выйгрыш; 2 есть ходы; 1 нет ходов; 0 проигрыш;
        // 2 выйгрыш; 1 есть ходы; 0 нет ходов; -2 проигрыш;
        if(nn==0){
            if(win != 0){
                return 2*win;
            }
            else if(good != 0){
                return 1*good;
            }
            else if(pariti != 0){
                return 0;
            }
            else {
                return -2*m;
            }
        }
        else {
            if (win != 0) {
                futureMoves = winMoves;
                //console.log("winMoves");
                return 3;
            } else if (good != 0) {
                futureMoves = goodMoves;
                //console.log("goodMoves");
                return 2;
            } else if (pariti != 0) {
                futureMoves = paritiMoves;
                //console.log("paritiMoves");
                return 1;
            } else {
                futureMoves = moves;
                //console.log("moves");
                return 0;
            }
        }*/
    }

    public void goComp(int x, int y){

        int[][] moves = getMoves(pole);
        int[] m = ii(pole, true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    /*
    var k = Math.round(Math.random()*(m-1));
    var l = Math.round(Math.random()*(n-1));
    while(!check(k,l,pole)){
        k = Math.round(Math.random()*(m-1));
        l = Math.round(Math.random()*(n-1));
    }

    setXY(k,l,1);


    if(isWin(pole)){
        alert("Ты проиграл!!!");
        return null;
    }
    */

        //console.log(futureMoves);
        //console.log(n);


        if(m[1]!=-1 && m[2] != -1/*getMovesLength(futureMoves) != 0*/){
            /*int n = (int) Math.round(((getMovesLength(futureMoves)-1)*Math.random()));
            setXY(futureMoves[n][0],futureMoves[n][1],1);
            futureMoves = new int[m][2];
            for(int i=0; i<futureMoves.length; i++){
                futureMoves[i] = new int[]{-1,-1};
            }*/
            setXY(m[1],m[2],1);
        }else{
            //Put up the Yes/No message box
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder
                    .setTitle("Конец игры")
                    .setMessage("Ничья!")
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Заново?", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            drawGrid(MainActivity.this.m,MainActivity.this.n);
                            //Yes button clicked, do something
                            Toast.makeText(MainActivity.this, "Приятной игры!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Нет", null)						//Do nothing on no
                    .show();;
            return;
        }

        if(isWin(pole)){
            //Put up the Yes/No message box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Конец игры")
                    .setMessage("Ты проиграл!")
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Заново?", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.score[1]++;
                            MainActivity.this.setTitle("Вы: "+MainActivity.this.score[0]+"; Комп: "+MainActivity.this.score[1]+";");
                            drawGrid(MainActivity.this.m,MainActivity.this.n);
                            //Yes button clicked, do something
                            Toast.makeText(MainActivity.this, "Удачной игры!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Нет", null)						//Do nothing on no
                    .show();;
            return;
        }

    }

    public void setXY(int x, int y, int isO){
        int id = x+y*m;
        Button button = (Button) findViewById(id);
        pole[x][y] = isO;
        button.setText ((isO==1)?"O":"X");
    }

    public void drawGrid(int m, int n){
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++) {
                Button button = (Button) findViewById(i+j*n);
                pole[i][j] = -1;
                button.setText("");
            }
    }

}