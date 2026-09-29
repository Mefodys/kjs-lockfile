# Dependabot Kotlin/JS lockfile demo

This repository demonstrates the expected Kotlin/JS dependency-update flow.

1. Create a pull request that changes the version in `npm("nanoid", "5.1.5")`.
2. The GitHub Actions workflow detects the changed `npm(...)` declaration.
3. It runs `kotlinUpgradeYarnLock` and uploads the resulting lockfile patch as an artifact.
4. The check fails until `kotlin-js-store/yarn.lock` is added to the pull request.

The workflow intentionally has read-only permissions. It proves the detection and regeneration steps without granting a workflow permission to push to a pull-request branch.

To update the lockfile locally, run:

```bash
./gradlew kotlinUpgradeYarnLock
```
