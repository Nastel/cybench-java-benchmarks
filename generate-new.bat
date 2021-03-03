@setlocal
@echo off
set LF=^


rem TWO empty lines are required

cd gocypher-cybench-archetype 
call mvn install
cd ..

echo "Enter the name. The ^"atifactId^" becomes gocypher-cybench-[name]"
set /P PROJECT_NAME=Benchmark module name:

call mvn archetype:generate -DarchetypeGroupId=com.gocypher.cybench  -DarchetypeArtifactId=gocypher-cybench-archetype -DarchetypeVersion=1.0.0 -DartifactId=gocypher-cybench-%PROJECT_NAME% -DgroupId=com.gocypher.cybench.client -Dversion=1.0.0 -DshortName=%PROJECT_NAME%

copy pom.xml pom.tmp
if exist pom.updated del pom.updated

for /f "tokens=* delims=" %%a in (pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="		<!--PROPERTIES PLACEHOLDER DO NOT DELETE -->"  type gocypher-cybench-!PROJECT_NAME!\src\pomUpdates\rootProperties >>pom.updated
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
   if "%%a"=="        <!--PROFILES PLACEHOLDER DO NOT DELETE -->"  type gocypher-cybench-!PROJECT_NAME!\src\pomUpdates\rootProfiles >>pom.updated
   >>pom.updated echo(!write!
   endlocal
)
del pom.tmp
move pom.updated pom.xml

copy gocypher-cybench-distribution\pom.xml gocypher-cybench-distribution\pom.tmp
if exist gocypher-cybench-distribution\pom.updated del gocypher-cybench-distribution\pom.updated

for /f "tokens=* delims=" %%a in (gocypher-cybench-distribution\pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="                        <!--FILESET PLACEHOLDER DO NOT DELETE -->"  type gocypher-cybench-!PROJECT_NAME!\src\pomUpdates\distributionFilesets >>gocypher-cybench-distribution\pom.updated
   >>gocypher-cybench-distribution\pom.updated echo(!write!
   endlocal
)

del gocypher-cybench-distribution\pom.tmp
move gocypher-cybench-distribution\pom.updated gocypher-cybench-distribution\pom.xml

copy gocypher-cybench-distribution\pom.xml gocypher-cybench-distribution\pom.tmp
if exist gocypher-cybench-distribution\pom.updated del gocypher-cybench-distribution\pom.updated

for /f "tokens=* delims=" %%a in (gocypher-cybench-distribution\pom.tmp) do (

   set write=%%a
   setlocal EnableDelayedExpansion
   if "%%a"=="                    <!--EXECUTION PLACEHOLDER DO NOT DELETE -->"  type gocypher-cybench-!PROJECT_NAME!\src\pomUpdates\distributionExecution >>gocypher-cybench-distribution\pom.updated
   >>gocypher-cybench-distribution\pom.updated echo(!write!
   endlocal
)
del gocypher-cybench-distribution\pom.tmp
move gocypher-cybench-distribution\pom.updated gocypher-cybench-distribution\pom.xml
move gocypher-cybench-distribution\pom.updated gocypher-cybench-distribution\pom.xml

setlocal EnableDelayedExpansion
move /y gocypher-cybench-!PROJECT_NAME!\src\distribution\assembly-!PROJECT_NAME!\*.* gocypher-cybench-distribution\src\assembly-!PROJECT_NAME!
rd /s /q gocypher-cybench-!PROJECT_NAME!\src\distribution 
rd /s /q gocypher-cybench-!PROJECT_NAME!\src\pomUpdates  
endlocal
