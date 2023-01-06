package org.example;

import java.util.Objects;

public class Player {
    private String playerName;
    private int score;
    private String predictedWinner;
    private String predictedScore;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPredictedWinner() {
        return predictedWinner;
    }

    public void setPredictedWinner(String predictedWinner) {
        this.predictedWinner = predictedWinner;
    }

    public String getPredictedScore() {
        return predictedScore;
    }

    public void setPredictedScore(String predictedScore) {
        this.predictedScore = predictedScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                '}';
    }

    public void awardPoints(FinalResult finalResult) {
        if(Objects.equals(predictedWinner, finalResult.getFinalResult())){
            if(Objects.equals(predictedScore, finalResult.getFinalScore())){
                this.score += 3;
            } else {
                this.score += 1;
            }
        }
    }

    public void bet(String predictedWinner, String predictedScore) {
        this.predictedScore = predictedScore;
        this.predictedWinner = predictedWinner;
    }


}
