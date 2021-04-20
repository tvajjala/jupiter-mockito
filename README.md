

## Architecture

Unlike JUnit4, JUnit5 promotes a better separation of concerns and comes with following modules.

* *JUnit Jupiter Module*
* *JUnit Platform Module*
* *JUnit Vintage Module*
* *junit-jupiter-migration-support:* - supports backward compatibility to select JUnit 4 Rules.

### JUnit5 Jupiter Module

This Aggregator dependency(`junit-jupiter`) Contains Three Child Dependencies
** API against which we write tests (`junit-jupiter-api`)
** Engine that understands it.(`junit-jupiter-engine`)
** Jupiter Parameters library, which helps to write parameterized tests (`junit-jupiter-params`)

TIP: If you are writing tests on JUnit5 this is the only dependency you need.

```shell script
+--- org.junit.jupiter:junit-jupiter:5.7.0
|    +--- org.junit.jupiter:junit-jupiter-api:5.7.0  <compile-time>
|    +--- org.junit.jupiter:junit-jupiter-params:5.7.0 <compile-time>
|    \--- org.junit.jupiter:junit-jupiter-engine:5.7.0 <runtime>
```

### JUnit5 Platform Module

It Contains Public APIs for configuring and launching test plans, This API typically used by IDEs and build tools.

If you are using *gradle* as build tool, you just need to add below line in `build.gradle`.

```groovy
test {
    useJUnitPlatform()
}
```

If you are using *maven* as build tool, add below surefire plugin in `pom.xml`

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
        </plugin>
    </plugins>
</build>
```


### JUnit Vintage Module

Add this dependency only If you want run existing (junit4) tests on Junit5 platform.

JUnit Vintage itself comprised of two modules:

* *junit-vintage-engine* is the test engine for running JUnit 3 and JUnit 4 tests on the JUnit Platform.
* *junit:junit* is the API for JUnit 3 and JUnit 4.

TIP: when you add `unit-vintage-engine` as dependency , it will pull junit4 dependencies.

```shell script
+--- org.junit.vintage:junit-vintage-engine:5.7.0
|    +--- org.apiguardian:apiguardian-api:1.1.0 <compile-time>
|    \--- junit:junit:4.13 <compile-time>
|         \--- org.hamcrest:hamcrest-core:1.3
|    +--- org.junit.platform:junit-platform-engine:1.7.0 <runtime>
```

```shell script
|    +--- org.junit:junit-bom:5.7.0
|    |    +--- org.junit.jupiter:junit-jupiter:5.7.0 (c) <1>
|    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.0 (c) <2>
|    |    +--- org.junit.jupiter:junit-jupiter-params:5.7.0 (c) <3>
|    |    +--- org.junit.platform:junit-platform-engine:1.7.0 (c) <4>
|    |    +--- org.junit.vintage:junit-vintage-engine:5.7.0 (c) <5>
|    |    \--- org.junit.platform:junit-platform-commons:1.7.0 (c) <6>
```


    <1> Simplified Aggregator Dependency
    <2> JUnit Jupiter API - which contains Annotation (@Test, @BeforeEach etc) to write test cases.
    <3> JUnit Jupiter Params - which helps to write parameterized tests using `@ParameterizedTest`
    <4> JUnit Platform Engine - Core Runtime Test Execution Engine.
    <5> JUnit Vintage Engine - Engine That Execute JUnit4 Tests.
    <6> JUnit Platform Commons - Internal Library used by JUnit5 framework.


Refer https://codergists.blogspot.com/2021/04/unit-testing-with-junit5.html for complete details





