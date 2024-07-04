Why we use Maven Framework:

- Dependency management
- Test execution
- Build Lifecycle Management ( validate, compile, package, verify. install, deploy)
- Support CI/CD
- Parallel Execution

-------------------------------

- Notes:
- Get the dependency Tree of a project
    - `mvn dependency:tree `
- Resolving Conflicts using Maven Dependency Tree Verbose Mode
    - `mvn dependency:tree -Dverbose`
    - Ex: `org.junit.jupiter:junit-jupiter-api:jar:5.1.0:test - omitted for conflict with 5.2.0)`
- Filtering the Maven Dependency Tree
    - `-Dincludes`
    - The syntax of the filtering pattern is: `[groupId]:[artifactId]:[type]:[version]`
    - Ex: `mvn dependency:tree -Dverbose -Dincludes=org.junit.jupiter:junit-jupiter-api`
- Saving Dependency Tree to a File
    - `mvn dependency:tree -DoutputFile=dependency-tree.txt`
- `<dependencyManagement> `:
    - Use the <dependencyManagement> section in your pom.xml to specify the version of a dependency that should be used
      throughout the project. This way, even if different versions of the dependency are specified elsewhere, the
      version
      specified in <dependencyManagement> will be used.
- Exclude the unwanted version.
    - ` <exclusions>
      <exclusion>
      <groupId>groupId</groupId>
      <artifactId>artifactId</artifactId>
      </exclusion>
      </exclusions>`