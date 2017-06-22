# RadarGun

<Description>

## Usage

RadarGun can be executed in two different ways: First, via the command line and, second, using its Java API.

### Execution via Command Line

The easiest way to execute RadarGun is via the command line: Just download RadarGun, make sure to have a recent Java installed, and type the following. Do not forget to replace `<your_benchmarks.jar>` by your benchmarks jar file.

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
A passed file have to be a YAML file containing assertions. When passing a directory, RadarGun considers all containing YAML files.
Of course, you can mix both files and directories.

**From classpaths:**
If your assertions are located in the classpath, for instance, directly in the benchmarks project, append `--cp-assertions` with a comma-separated list of files. Here, no directories are allowed for technical reasons. 


RadarGun provides some more options, for example, to configure its output or 

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
      Output stream ('out' or 'err')
      Default: java.io.PrintStream@6e8cf4c6
```

<Execution generell>

<Execution via command line>

<Usage as Dependency>

<Declaration of Asertions>

<Machine Identification>
