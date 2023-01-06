package org.example;

public class FinalResult {
    private String finalResult;
    private String finalScore;

    public FinalResult(String finalResult, String finalScore) {
        this.finalResult = finalResult;
        this.finalScore = finalScore;
    }

    public String getFinalResult() {
        return this.finalResult;
    }

    public String getFinalScore() {
        return this.finalScore;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }
}
