/*
 * Copyright (C) 2020, K2N.IO.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

package io.cybench.launcher.scores;


import com.gocypher.cybench.core.model.BaseScoreConverter;
import com.gocypher.cybench.core.utils.IOUtils;

import java.util.Map;

public class IOReadWriteScoreConverter extends BaseScoreConverter {
    @Override
    public Double convertScore(Double score , Map<String,Object> metaData) {
        if (score != null){
            double oldScore = score / 1000;
            long mb = 1_048_576 ;
            double benchmarkFileSize = (double) IOUtils.getHugeRandomBinaryFileSizeInBytes()/mb ;
            double newScore = benchmarkFileSize/oldScore ;
            return newScore;
        }
        return score ;
    }
    @Override
    public Double getOperationTimeMilliseconds(Double score, Map<String, Object> metaData) {
        Double opsMSScore = 1/(score/1_000_000_000);
        return opsMSScore;
    }
    @Override
    public String getUnits() {
        return "MB/s";
    }
}
