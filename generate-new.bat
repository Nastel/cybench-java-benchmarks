@setlocal
@echo off
set LF=^


rem TWO empty lines are required

set MVN_EXEC=mvn
set /P MVN_EXEC= Enter Maven executable path: [%MVN_EXEC%] :

cd cybench-java-benchmark-archetype
call %MVN_EXEC% install
cd ..

echo "Enter the name. The ^"atifactId^" becomes cybench-java-benchmark-[name]"
set /P PROJECT_NAME= Benchmark module name:

call %MVN_EXEC% archetype:generate -DarchetypeGroupId=io.cybench  -DarchetypeArtifactId=cybench-java-benchmark-archetype -DarchetypeVersion=1.0.0 -DartifactId=cybench-java-benchmark-%PROJECT_NAME% -DgroupId=io.cybench.client -Dversion=1.0.0 -DshortName=%PROJECT_NAME%

copy pom.xml pom.tmp
if exist pom.updated del pom.updated

for /f "tokens=* delims=" %%a in (pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="		<!--PROPERTIES PLACEHOLDER DO NOT DELETE -->"  type cybench-java-benchmark-!PROJECT_NAME!\src\pomUpdates\rootProperties >>pom.updated
   >>pom.updated echo(!write!
   endlocal
)
del pom.tmp
move pom.updated pom.xml

copy pom.xml pom.tmp
if exist pom.updated del pom.updated

for /f "tokens=* delims=" %%a in (pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="        <!--PROFILES PLACEHOLDER DO NOT DELETE -->"  type cybench-java-benchmark-!PROJECT_NAME!\src\pomUpdates\rootProfiles >>pom.updated
   >>pom.updated echo(!write!
   endlocal
)
del pom.tmp
move pom.updated pom.xml

copy cybench-java-benchmarks-distribution\pom.xml cybench-java-benchmarks-distribution\pom.tmp
if exist cybench-java-benchmarks-distribution\pom.updated del cybench-java-benchmarks-distribution\pom.updated

for /f "tokens=* delims=" %%a in (cybench-java-benchmarks-distribution\pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="                        <!--FILESET PLACEHOLDER DO NOT DELETE -->"  type cybench-java-benchmark-!PROJECT_NAME!\src\pomUpdates\distributionFilesets >>cybench-java-benchmarks-distribution\pom.updated
   >>cybench-java-benchmarks-distribution\pom.updated echo(!write!
   endlocal
)

del cybench-java-benchmarks-distribution\pom.tmp
move cybench-java-benchmarks-distribution\pom.updated cybench-java-benchmarks-distribution\pom.xml

copy cybench-java-benchmarks-distribution\pom.xml cybench-java-benchmarks-distribution\pom.tmp
if exist cybench-java-benchmarks-distribution\pom.updated del cybench-java-benchmarks-distribution\pom.updated

for /f "tokens=* delims=" %%a in (cybench-java-benchmarks-distribution\pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="                    <!--EXECUTION PLACEHOLDER DO NOT DELETE -->"  type cybench-java-benchmark-!PROJECT_NAME!\src\pomUpdates\distributionExecution >>cybench-java-benchmarks-distribution\pom.updated
   >>cybench-java-benchmarks-distribution\pom.updated echo(!write!
   endlocal
)
del cybench-java-benchmarks-distribution\pom.tmp
move cybench-java-benchmarks-distribution\pom.updated cybench-java-benchmarks-distribution\pom.xml
move cybench-java-benchmarks-distribution\pom.updated cybench-java-benchmarks-distribution\pom.xml

setlocal EnableDelayedExpansion
if not exist cybench-java-benchmarks-distribution\src\assembly-!PROJECT_NAME!\ mkdir cybench-java-benchmarks-distribution\src\assembly-!PROJECT_NAME!\
move /y cybench-java-benchmark-!PROJECT_NAME!\src\distribution\assembly-!PROJECT_NAME!\*.* cybench-java-benchmarks-distribution\src\assembly-!PROJECT_NAME!\
rem rd /s /q cybench-java-benchmark-!PROJECT_NAME!\src\distribution
rem rd /s /q cybench-java-benchmark-!PROJECT_NAME!\src\pomUpdates
endlocal
