# Automatically update the Kotlin/JS Yarn lockfile in a PR

This workflow updates `kotlin-js-store/yarn.lock` after an `npm(...)` declaration changes in a Gradle build script.

## Setup

1. Add [`.github/workflows/update-kotlin-js-lock.yml`](.github/workflows/update-kotlin-js-lock.yml) to the repository.
2. In GitHub, open **Settings → Actions → General → Workflow permissions** and select **Read and write permissions**.
3. Commit the workflow to the default branch.

## Resulting flow

1. A contributor or dependency-update tool changes a Kotlin/JS declaration, for example:

   ```kotlin
   implementation(npm("nanoid", "5.1.5"))
   ```

   to:

   ```kotlin
   implementation(npm("nanoid", "5.1.6"))
   ```

2. A pull request is opened or updated.
3. GitHub Actions detects a changed `npm(...)` line and runs:

   ```bash
   ./gradlew --no-daemon kotlinUpgradeYarnLock
   ```

4. If the task changes `kotlin-js-store/yarn.lock`, GitHub Actions commits that file to the PR branch as `github-actions[bot]`.

5. The next workflow run finds no further lockfile change and exits without another commit.

## Scope

The workflow only pushes to PR branches in the same repository. It does not push to forks.

Dependabot currently does not recognize Kotlin Gradle declarations such as `npm("name", "version")`, so it will not create those dependency-update PRs itself. The workflow is still useful after a manual change or with a tool that can create such a PR.
