package com.paulperez.RuneScape_AI.Model.AI_Model.Receiving;

import java.util.List;

public class AskQuestionResponse {

    private List<Candidate> candidates;

    public AskQuestionResponse(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

}
