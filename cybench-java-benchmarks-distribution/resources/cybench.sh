#
# Copyright (c) 2018-2020 K2N.IO. All Rights Reserved.
#
# This software is the confidential and proprietary information of
# K2N.IO. ("Confidential Information").  You shall not disclose
# such Confidential Information and shall use it only in accordance with
# the terms of the license agreement you entered into with K2N.IO.
#
# K2N.IO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
# THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
# THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
# PURPOSE, OR NON-INFRINGEMENT. K2N.IO SHALL NOT BE LIABLE FOR ANY DAMAGES
# SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
# THIS SOFTWARE OR ITS DERIVATIVES.
#
# CopyrightVersion 1.0
#
#!/bin/bash

# Method to ask for user input and save the response to value

argument1="$1"
argument2="$2"
echo

detect_configuration_path()
{
    local resConfig=F;
    if [[ "$1" = "-c" ]]; then resConfig=T; fi
    if [[ "$1" = "-conf" ]]; then resConfig=T; fi
    if [[ "$1" = "-config" ]]; then resConfig=T; fi
    if [[ "$resConfig" = "T" ]]; then CONFIGURATION_PATH=$2; fi
    if [[ "$resConfig" = "F" ]]; then CONFIGURATION_PATH=conf/cybench-launcher.properties; fi
}
set_configuration_properties()
{
    if [[ "$CONFIGURATION_PATH" = "" ]]; then
        CONFIGURATION_PATH="conf/cybench-launcher.properties"
    fi
    # Read properties file to set JVM properties for .jar run
    JVM_PROPERTIES=""
    while IFS='=' read -r key value; do
        if [[ ${key} == "javaOptions"* ]]; then
            JVM_PROPERTIES+="${value} ";
        fi
    done < ${CONFIGURATION_PATH}

    # Read properties file to try to set JAVA_PATH from configuration file if not provided during runtime.
    while IFS='=' read -r key value; do
        if [[ ${key} == "javaToUsePath"* ]]; then
            if [[ -z "${JAVA_PATH}" ]]; then
                JAVA_PATH="${value}";
            fi
        fi
    done < ${CONFIGURATION_PATH}

    if [[ -z "${JAVA_PATH}" ]]; then
        if [[ -z "${JAVA_HOME}" ]]; then
            JAVA_PATH=java
        else
            JAVA_PATH="${JAVA_HOME}/bin/java"
        fi
    fi

    # Read properties file to set JVM properties for .jar run
    CYB_LIBS="benchmarks/*"
    while IFS='=' read -r key value; do
        if [[ ${key} == "benchmarks"* ]]; then
            CYB_LIBS+="${value} ";
        fi
    done < ${CONFIGURATION_PATH}

    jver=$("${JAVA_PATH}" -version 2>&1 | head -1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1 | cut -d'-' -f1)

    echo "${jver}"

    if [[ $jver -ge 9 ]]; then
        JVM_PROPERTIES+=" --add-exports=java.management/sun.management=ALL-UNNAMED"
        JVM_PROPERTIES+=" --add-opens=java.management/sun.management=ALL-UNNAMED"
    fi
}
detect_help_flag()
{
    local resHelp=F
    if [[ "$1" = "-h" ]]; then
        resHelp=T;
    fi
    if [[ "$1" = "-help" ]]; then
        resHelp=T;
    fi
    if [[ "$resHelp" = "T" ]]; then
        echo     USAGE: cybench.bat [-options]
        echo     where options include:
        echo
        echo     "-c -conf -config <path to configuration file>        CyBench launcher configuration file"
        echo
        echo
        exit 1
    fi
}

detect_help_flag $argument1 $argument2
detect_configuration_path $argument1 $argument2
set_configuration_properties

# Execute the benchmarks with set default or user defined properties
echo EXECUTE: "${JAVA_PATH}" ${JVM_PROPERTIES} -cp ./gocypher-cybench-client.jar:${CYB_LIBS} com.gocypher.cybench.launcher.BenchmarkRunner cfg=${CONFIGURATION_PATH}
"${JAVA_PATH}" ${JVM_PROPERTIES} -cp ./gocypher-cybench-client.jar:${CYB_LIBS} com.gocypher.cybench.launcher.BenchmarkRunner cfg=${CONFIGURATION_PATH}
