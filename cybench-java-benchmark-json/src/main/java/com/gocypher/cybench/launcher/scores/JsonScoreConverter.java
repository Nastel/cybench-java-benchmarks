package com.gocypher.cybench.launcher.scores;

import com.gocypher.cybench.core.model.BaseScoreConverter;

import java.util.Map;

public class JsonScoreConverter extends BaseScoreConverter {

    @Override
    public Double convertScore(Double score , Map<String,Object> metaData) {
        if (score != null){
            Double newScore = score/1_000 ;
            return newScore ;
        }
        return null ;
    }
    @Override
    public Double getOperationTimeMilliseconds(Double score, Map<String, Object> metaData) {
        Double opsMSScore = 1/(score/1_000_000);
        return opsMSScore;
    }
    @Override
    public String getUnits() {
        return "k ops/s";
    }
}

