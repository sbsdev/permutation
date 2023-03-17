# Permutation

Implements generic permutation according to Kenneth H. Rosen, Discrete
Mathematics and Its Applications, 2nd edition (NY: McGraw-Hill, 1991),
pp. 282-284.

# Build

```
mvn -B package
```

# Release

```
mvn release:prepare
```

Perform a release without deploying. The deploy step is done in a gh action.

```
mvn release:perform -Darguments="-Dmaven.deploy.skip=true"
```

# License

LGPLv2.1+

# Authors

**Bernhard Wagner**

- http://github.com/bwagner
