::
:: Copyright (c) 2018-2020 K2N.IO. All Rights Reserved.
::
:: This software is the confidential and proprietary information of
:: K2N.IO. ("Confidential Information").  You shall not disclose
:: such Confidential Information and shall use it only in accordance with
:: the terms of the license agreement you entered into with K2N.IO.
::
:: K2N.IO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
:: THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
:: THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
:: PURPOSE, OR NON-INFRINGEMENT. K2N.IO SHALL NOT BE LIABLE FOR ANY DAMAGES
:: SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
:: THIS SOFTWARE OR ITS DERIVATIVES.
::
:: CopyrightVersion 1.0

@echo off
@setlocal enableextensions enabledelayedexpansion
echo.
:: Read user input to set the path to java to execute and if there is a
:: need a path to user configurations file for JVM properties and benchmarks functionality

set argument1=%1
set argument2=%2


:detectConfigurationFlag
    set resConfig=F
    IF ["%1%"] EQU ["-c"] set resConfig=T
    IF ["%1%"] EQU ["-conf"] set resConfig=T
    IF ["%1%"] EQU ["-config"] set resConfig=T
    IF ["%resConfig%"] EQU ["T"] set CONFIGURATION_PATH=%2%
    IF ["%resConfig%"] EQU ["F"] set CONFIGURATION_PATH=config/cybench-launcher.properties

:detectHelpFlag
    set resHelp=F
    IF ["%1%"] EQU ["-h"] set resHelp=T
    IF ["%1%"] EQU ["-help"] set resHelp=T
    IF ["%resHelp%"] EQU ["T"] (
        echo     USAGE: cybench.bat [-options]
        echo     where options include:
        echo.
        echo     "-c -conf -config <path to configuration file>        CyBench launcher configuration file"
        echo.
        echo.
        goto :eof
    )
    IF ["%resHelp%"] EQU ["F"] (
        goto :setConfigurationProperties
    )
:setConfigurationProperties
    :: Read properties file to set JVM properties for .jar run
    echo.
    for /f "eol=# delims== tokens=1,*" %%A in (%CONFIGURATION_PATH%) do (
        Echo."%%A" | findstr /C:"javaOptions">nul && (
            set JVM_PROPERTIES=!JVM_PROPERTIES!%%B
            set JVM_PROPERTIES=!JVM_PROPERTIES!
        )
    )
    :: If no java path input during runtime provided try to take it from configuration
    for /f "eol=# delims== tokens=1,2" %%A in (%CONFIGURATION_PATH%) do (
        Echo."%%A" | findstr /C:"javaToUsePath">nul && (
            IF ["%JAVA_PATH%"] EQU [""] (
                set JAVA_PATH=%%B
            )
        )
    )

    IF ["%JAVA_PATH%"] EQU [""] (
        IF ["%JAVA_HOME%"] EQU [""] (
            set JAVA_PATH=java
        ) else (
            set JAVA_PATH="%JAVA_HOME%\bin\java"
        )
    )

    :: Get and add all benchmark jars defined in configuration file
    for /f "eol=# delims== tokens=1,2" %%A in (%CONFIGURATION_PATH%) do (
        Echo."%%A" | findstr /C:"benchmarks">nul && (
            set CYB_LIBS=%%B
        )
    )
    :: Read the folder and add benchmark jars to execution
    set MasterFolder=benchmarks
    for /f "eol=# delims=" %%f IN ('dir /b /s "%MasterFolder%\*"') do (
        set CYB_LIBS_FOLDER=!CYB_LIBS_FOLDER!%%f;
        set CYB_LIBS_FOLDER=!CYB_LIBS_FOLDER!
    )

    for /f tokens^=2-5^ delims^=.+-_^" %%j in ('%JAVA_PATH% -fullversion 2^>^&1') do set "jver=%%j%%k"
    rem for early access versions replace "ea" part with "00" to get comparable number
    set jver=%jver:ea=00%

    IF %jver% GTR 18 (
        set JVM_PROPERTIES=!JVM_PROPERTIES! --add-exports=java.management/sun.management=ALL-UNNAMED
        set JVM_PROPERTIES=!JVM_PROPERTIES! --add-opens=java.management/sun.management=ALL-UNNAMED
    )

    set JVM_PROPERTIES=%JVM_PROPERTIES% -Dlog4j2.configurationFile=file:config/log4j2.xml

    call :executeBaseRun

:executeBaseRun
    IF ["%JAVA_PATH%"] EQU [""] set JAVA_PATH=java
    :: Execute the benchmarks with set default or user defined properties
    echo EXECUTE: "%JAVA_PATH%" %JVM_PROPERTIES% -cp "%CYB_LIBS%";"%CYB_LIBS_FOLDER%" com.gocypher.cybench.launcher.BenchmarkRunner cfg=%CONFIGURATION_PATH%
    "%JAVA_PATH%" %JVM_PROPERTIES% -cp "%CYB_LIBS%";"%CYB_LIBS_FOLDER%" com.gocypher.cybench.launcher.BenchmarkRunner cfg=%CONFIGURATION_PATH%
@endlocal
cmd /k