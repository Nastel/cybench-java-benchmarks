# What is CyBench Benchmarks

**CyBench Benchmarks** java based benchmarks that uses [CyBench Launcher](https://github.com/K2NIO/gocypher-cybench-java) to execute JMH based benchmarks. It's designed to help developers build high performance Java apps. 
Speed-test Java classes, collections, modules, libraries and other application building blocks under varying runtime conditions. 
Share your results with the community, compare and choose the right libraries for your application. **CyBench** helps you be a better developer.

Download and run CyBench Benchmarks [here](https://github.com/K2NIO/cybench-java-benchmarks/releases).

Check out the benchmarks from other users and analyze your results here [here](https://app.cybench.io/cybench/).

## Running Default Benchmarks using CyBench

- Download the selected benchmark package from the [release page](https://github.com/K2NIO/cybench-java-benchmarks/releases)
- Extract the files and run cybench.bat (on Windows) or cybench.sh (on Unix).
- Analyze results in your console, reports folder or [app.cybench.io](https://app.cybench.io/cybench/)
- **Otional:** manage your benchmarking run configuration located in `<cybench-home>/conf/cybench-launcher.properties`

#### CyBench launcher configuration 

| Property name        | Description           | Default value  |
| ------------- |-------------| -----:|
| **javaOptions**      | All the property fields that starts with name javaOptions will be used while benchmarking as JVM properties. | - |
| **javaToUsePath**      | Provide full path to java.exe to be used e.g. D:/jdk180_162/bin/java.exe  | - |
| **benchmarks**| Provide jar's with JMH benchmarks which shall be executed with CyBench. [more here](#adding-custom-benchmarks-for-execution)| - |
| **sendReport**| Choose if the report generated will be automatically uploaded. (true/false)  | true |
| **reportUploadStatus**| Define public or private property for the uploaded report visibility.  | public |
| **benchAccessToken** | By providing the "bench" token that you get after creating a workspace in CyBench UI, you can send reports to your private directory, which will be visible only to the users that you authorize. | - |
| **emailAddress** | Email property is used to identify report sender while sending reports to both private and public repositories | - |
| **reportName**| Choose the uploaded report name. E.g. | - |
| **benchmarkClasses**| Specify benchmarks by including fully qualified benchmark class names which are comma separated. For more information [more here](#execute-only-custom-benchmarks)| - |
| **numberOfBenchmarkForks**| Number of separate full executions of a benchmark (warm up+measurement), this is returned still as one primary score item. | 1 |
| **measurementIterations** | Number of measurements per benchmark operation, this is returned still as one primary score item. | 5 |
| **warmUpIterations**| Number of iterations executed for warm up.  |  1 |
| **warmUpSeconds**|  Number of seconds dedicated for each warm up iteration.  |  5  |
| **runThreadCount**| Number of threads for benchmark test execution. |  1 |
| **benchmarkMetadata**| A property which adds extra properties to the benchmarks report such as category or version or context. Configuration pattern is `<fully qualified benchmark class name>=<key1>:<value1>;<key2>:<value2>`. Example which adds category for class CollectionsBenchmarks: `io.cybench.launcher.CollectionsBenchmarks=category:Collections;`   |   -  |
| **userProperties**| User defined properties which will be added to benchmarks report section `environmentSettings->userDefinedProperties` as key/value strings. Configuration pattern:`<key1>:<value1>;<key2>:<value2>`. Example which adds a project name:`user.propname1=My Test Project;` |  -  |


### Adding Custom Benchmarks for Execution

Update CyBench Launcher configuration located in `<cybench-home>/conf/cybench-launcher.properties`:

* __required__: add or update property `benchmarks`, set path to jar file which contains your JMH benchmarks, this path will be added to the `CLASSPATH` of the JVM. Values must be semicolon separated!  

    Rule:
    ```properties
    benchmarks=<path to benchmark jar file1>;<path to benchmark jar file2>;
    ```
    
    Example:
    ```properties
    benchmarks=<jmh-benchmark-file>.jar;
    ```

* __optional__: register categories for your tests in order to have correct tests classification and better readability and comparison in CyBench portal. Values of different classes must be semicolon separated!

    Rule:
    ```properties
    benchmarkMetadata=<fully classified benchmark class name>=category:<category name>;\
      <fully classified benchmark class name>=category:<category name>;
    ```
       
    Example:
    ```properties
    benchmarkMetadata=io.cybench.launcher.CollectionsBenchmarks=category:Collections;
    ```
    
### Execute Only Custom Benchmarks

Update CyBench configuration in order to run specific JMH tests:
* add or update property `benchmarkClasses`, specify class names of tests which shall be executed (values must be comma separated).
    
    Rule:
    ```properties
    benchmarkClasses=<fully qualified class name, or class name>,<fully qualified class name, or class name>
    ```
    Example:
    ```properties
    benchmarkClasses=io.cybench.launcher.CollectionsBenchmarks,NumberBenchmarks
    ```
    

## Running User-defined Benchmarks using CyBench

### Add Your Benchmark to CyBench Launcher

Attachment and execution of user-defined benchmarks using CyBench launcher:
 * Build a `jar` file with your benchmarks containing `jmh` compiled classes and dependecies
 * Copy benchmark `jar` file to `<cybench-home>` (where `gocypher-benchmarks-client.jar` resides)
 * Configure CyBench Launcher to execute your benchmarks

**NOTE**:
* User defined benchmarks must be implemented using `JMH` framework
* Exported `jar` must contain `class` files generated by `JMH` framework (`jar` file must contain a folder `jmh_generated` with classes having `jmh` suffix ).
* If any files are created during tests (test data files, results files) run they must be deleted after the iteration when benchmark finishes

