# Plexus Interactivity

[![Maven Central](https://img.shields.io/maven-central/v/org.codehaus.plexus/plexus-interactivity-api.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/org.codehaus.plexus/plexus-interactivity-api)
[![GitHub CI](https://github.com/codehaus-plexus/plexus-interactivity/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/codehaus-plexus/plexus-interactivity/actions/workflows/maven.yml?query=branch%3Amaster)
[![Reproducible Builds](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/jvm-repo-rebuild/reproducible-central/master/content/org/codehaus/plexus/plexus-interactivity/badge.json)](https://github.com/jvm-repo-rebuild/reproducible-central/blob/master/content/org/codehaus/plexus/plexus-interactivity/README.md)
[![License](https://img.shields.io/github/license/codehaus-plexus/plexus-interactivity.svg?label=License)](https://www.apache.org/licenses/LICENSE-2.0)

Asks the user a question on the console. This is what an interactive Maven plugin uses to prompt — the
archetype plugin's "Define value for property…" comes from here.

`Prompter` handles the common cases: a plain question, a question with a default, a question restricted to
a list of allowed answers, and a password prompt. `InputHandler` and `OutputHandler` are the lower-level
pieces underneath.

## Status

Maintained, quietly. The API is small and settled; expect dependency updates rather than new features.

## Using it

```xml
<dependency>
  <groupId>org.codehaus.plexus</groupId>
  <artifactId>plexus-interactivity-api</artifactId>
</dependency>
```

Check the badge above for the current version. Note the artifact is `plexus-interactivity-api`;
`plexus-interactivity` is the aggregator.

```java
@Inject
Prompter prompter;

String name = prompter.prompt( "Project name" );
String scope = prompter.prompt( "Scope", List.of( "compile", "test" ), "compile" );
String secret = prompter.promptForPassword( "Password" );
```

An empty answer returns the default. With a list of allowed answers, the prompt repeats after
"Invalid selection." until the answer is on the list, and it throws a `PrompterException` if input ends and
no default was given.

### Password echo

The default `InputHandler` reads a password like any other line, so the typed characters are echoed. To
mask them with `*`, add `org.jline:jline-reader` (an optional dependency of this artifact, so you declare
it yourself) and use the handler registered under the name `jline`, `JLineInputHandler`.

A plugin that prompts should check `MavenSession.getRequest().isInteractiveMode()` first, so it does not
hang a batch-mode build.

## Requirements

Java 8 or later. Building the project needs Maven 3.9.0 or later.

## Documentation

- [Project site](https://codehaus-plexus.github.io/plexus-interactivity/)
- [Javadoc](https://javadoc.io/doc/org.codehaus.plexus/plexus-interactivity-api)
- [Release notes](https://github.com/codehaus-plexus/plexus-interactivity/releases)

## Contributing

Conventions for contributors and agents are in [AGENTS.md](AGENTS.md); see also [CONTRIBUTING.md](https://github.com/codehaus-plexus/.github/blob/master/CONTRIBUTING.md). In short:
`mvn verify` builds, and run `mvn spotless:apply` before pushing or CI will fail on formatting.

Please report security vulnerabilities privately — see
[SECURITY.md](https://github.com/codehaus-plexus/.github/blob/master/SECURITY.md), not a public issue.
