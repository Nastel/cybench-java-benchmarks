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

package com.gocypher.cybench.jmh.jvm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {
    private static Logger LOG = LoggerFactory.getLogger(JSONUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<?,?> parseJsonIntoMap(String jsonString) {
        try {
            return mapper.readValue(jsonString, HashMap.class);
        } catch (Exception e) {
            LOG.error("Error on parsing json into map", e);
            return new HashMap<>();
        }
    }
    public static List<?> parseJsonIntoList(String jsonString) {
        try {
            return mapper.readValue(jsonString, ArrayList.class);
        } catch (Exception e) {
            LOG.error("Error on parsing json into map", e);
            return new ArrayList<>();
        }
    }
    public static String marshalToJson(Object item) {
        try {
            return mapper.writeValueAsString(item);
        } catch (Exception e) {
            LOG.error ("Error on marshaling to json",e) ;
            return "";
        }
    }
    public static String marshalToPrettyJson(Object item) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        } catch (Exception e) {
           LOG.error ("Error on marshal to pretty json",e) ;
           return "";
        }
    }

}
