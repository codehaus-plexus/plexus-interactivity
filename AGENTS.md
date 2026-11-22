# Agent guide for plexus-interactivity

`org.codehaus.plexus:plexus-interactivity` is the component behind interactive Maven prompts: the
archetype plugin's "Define value for property…" question comes from here. The `Prompter` API covers
plain questions, defaults, allowed-answer lists and password prompts, with `InputHandler` and
`OutputHandler` underneath. The root is a `pom` aggregator; the code lives in the one module,
`plexus-interactivity-api`.

## Build and test

- `mvn verify` builds the reactor. CI calls the shared `codehaus-plexus/.github` `maven.yml@master`
  workflow, which runs `mvn verify javadoc:javadoc` on JDK 8, 21 and 25 and on Linux, Windows and
  macOS; a push to master also deploys a snapshot. Site publishing is manual and not part of CI.
- Main code must use the Java 8 API (`javaVersion` 8 from the `plexus` parent).
- Unit tests use JUnit 5 and live in `plexus-interactivity-api/src/test`; use that module path for
  targeted test runs.
- `jline-reader` is an optional dependency: code outside `...interactivity.jline` must not import it.
- `sisu-maven-plugin` builds the component index, and the JAR sets
  `Automatic-Module-Name: org.codehaus.plexus.components.interactivity`; keep that name stable.
- Run `mvn spotless:apply` on JDK 17 to 21 before pushing; it also formats Markdown.

## Documentation updates

A change that users or contributors can notice ships with the documentation it affects, in the same
pull request. A refactor with no visible effect needs none.

- Behaviour of `Prompter`, `InputHandler` or `OutputHandler` (defaults, validation, password
  echo): the Javadoc of the interface and of its `Default*` or `JLine*` implementation, and the
  matching section of `README.md`, including its example code.
- The Java requirement, or how to build and release: `README.md`.
- There are no pages beyond `site.xml` under the root `src/site` or under
  `plexus-interactivity-api/src/site`; the README and the Javadoc are the user guide. Keep the
  menus in those two `site.xml` files in sync if a page is ever added.
- A new convention, build gate or test fixture: this file.

