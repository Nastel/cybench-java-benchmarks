package com.gocypher.cybench.launcher.scores;

import com.gocypher.cybench.core.model.BaseScoreConverter;

import java.util.Map;

public class ListScoreConverter  extends BaseScoreConverter {

    @Override
    public Double convertScore(Double score, Map<String, Object> metaData) {
        return 1/score;
    }

    @Override
    public Double getOperationTimeMilliseconds(Double score, Map<String, Object> metaData) {
        return 1/(score/1000);
    }

    @Override
    public String getUnits() {
        return "op/s";
    }
}
