# RadarGun - Performance Testing Framework

RadarGun is a Java framework for automated performance tests - just like jUnit for unit tests.

RadarGun performance tests consists of two parts:

1. **The Benchmark** Benchmarks have to be written with the [Java Microbenchmarking Harness (JMH)](http://openjdk.java.net/projects/code-tools/jmh/). They define the code segment (i.e., a sequence of instructions) that should be tested.
Since conducting statically significant performance measurements in Java is not as easy as it seems, we recommend the paper [DOs and DON’Ts of Conducting Performance Measurements in Java](http://d3s.mff.cuni.cz/~steinhauser/icpe2015tt.pdf). Moreover, there exists several tutorials on JHM in the web.
2. **The Assertions** Assertions define an interval in which a test execution is seen as sucessful or not. Since the execution time highly dependends on the executing machine, assertions are defined individually per machine. RadarGun picks then the right assertion. See [Declaration of Assertions](#declaration-of-assertions) for more information.

## Usage

RadarGun can be executed in two different ways: First, via the command line and, second, using its Java API.

### Execution via Command Line

The easiest way to execute RadarGun is via the command line: Just [download RadarGun](https://oss.sonatype.org/service/local/artifact/maven/redirect?r=snapshots&g=de.soeren-henning&a=radargun&v=LATEST), make sure to have a recent Java installed, and type the following. Do not forget to replace `<your_benchmarks.jar>` by your benchmarks jar file.

##### On Unix-like operating systems (such as Linux and macOS):
```shell
java -cp "RadarGun.jar:<your_benchmarks.jar>" radargun.Main
```

##### On Windows (classpaths must be separeted by `;`):
```shell
java -cp "RadarGun.jar;<your_benchmarks.jar>" radargun.Main
```

This will simply execute all JMH benchmarks. To make RadarGun comparing the benchmarks results with your predefined assertions, you have to tell RadarGun where they are.

**From file system:**
If you have placed your assertions on the filesystem, append `--assertions` with a comma-separated list of files or directories to the command.
A passed file has to be a YAML file containing assertions. When passing a directory, RadarGun considers all containing YAML files.
Of course, you can mix both files and directories.

**From classpaths:**
If your assertions are located in the classpath, for instance, directly in the benchmarks project, append `--cp-assertions` with a comma-separated list of files. Here, no directories are allowed for technical reasons. 


RadarGun provides some more options, for example, to configure its output: 

```
Usage: RadarGun [options]
  Options:
    --assertions
      Comma-sperated list of assertions in the filesystem (files or directories)
    --console-output
      Console output
      Default: true
    --cp-assertions
      Comma-sperated list of assertions in the classpath (must be files)
    --csv
      Enables CSV output to specified directory
    --exit-on-fail
      Enable exit on fail after all tests run
      Default: false
    --exit-on-fail-immediately
      Enable exit on fail of first test
      Default: false
    --help
      Print usage
    --jmh-output
      Enable JMH output
      Default: false
    --output-stream
      Output stream ('out' for System.out or 'err' for System.err)
      Default: System.out
```

### Using the Java API

To use RadarGun within your Java (or other JVM-based language) project, you have to add it to your project's build path. You can either download the sources directly or use a build or dependency management tool such as Apache Maven.

<!---
TODO: Add instructions for release
-->

RadarGun is currently only available as a so called *Snapshot*. However, this will change in a very near future. This version contains the latest changes and updates, but may be unstable. Therefore, it is not meant for productive usage. If you use Maven, please add the following depenency to the dependencies's section of your `pox.xml`:

```xml
<dependency>
  <groupId>de.soeren-henning</groupId>
  <artifactId>radargun</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```
If you did not add the Sonatype snapshot repository yet to your pom.xml, add also following lines to it, as otherwise Maven will not be able to find the needed artifacts:

```xml
<repositories>
  <repository>
    <id>sonatype.oss.snapshots</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
  </repository>
</repositories>
```

For other build tools, adding the dependencies is similar.

RadarGun uses the Pipe-And-Filter framework [TeeTime](http://teetime-framework.github.io). Thus, a good starting point to work with it, is to use its `radargun.Configuration`.

## Declaration of Assertions

Assertions are defined in YAML files in the following format:

```yaml
---
identifier: MacAddressIdentifier
parameters: [01:23:45:67:89:AB]
tests:
  myproject.benchmark.MyFirstBenchmark.run: [70, 90]
  myproject.benchmark.MySecondBenchmark.run: [6.4, 6.7]
  myproject.benchmark.MyThirdBenchmark.run: [1300, 1400]
```

First, it defines the machine by an identifier class and the parameters by which it will be created (see [Machine Identification](#machine-identification)). Afterwards, all assertions are declared by the fully qualified name of the benchmark and the lower and upper bound for the permitted benchmarks result.

A YAML file can contain multiple of such blocks (separated by a `---` line) and, moreover, RadarGun can handle multiple of YAML files. RadarGun picks the right assertions.

## Machine Identification

RadarGun provides the following machine identifers:

- `NetworkAddressIdentifier` Tests for a one or more network addresses, which can either be IP addresses or host names.
- `MacAddressIdentifier` Tests for one or more MAC addresses.
- `WindowsComputernameIdentifier` Tests for one or more computer names. Limited to Windows systems only.
- `WildcardIdentifier` Matches for all machines. It can be used when no machine distinction is necessary. (E.g., if there is only one machine.)
- `Dismiss Identifier` Matches for no machine. Normally, just used internally by RadarGun.

Of course, you can implement your own identifier. To do so, you just have to implement the `MachineIdentifier` interface. If your identifier is not located in the package `radargun.comparsion.machine.identification`, you have to define its fully qualified name in the assertions files.

## Build RadarGun by Yourself

RadarGun uses Apache Maven as build tool. To build RadarGun an existing Maven and JDK installation is required. Simply execute:

```shell
mvn install
```
