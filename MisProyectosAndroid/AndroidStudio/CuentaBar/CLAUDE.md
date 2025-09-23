# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

CuentaBar is a native Android application built with Java and the Android SDK. The project uses Gradle with Kotlin DSL for build configuration and follows standard Android project structure conventions.

**Package**: `com.koss.cuentabar`
**Min SDK**: 31 (Android 12)
**Target SDK**: 36
**Java Version**: 11

## Architecture

- **Single Activity Architecture**: The app currently uses `MainActivity` as the main entry point with EdgeToEdge display support
- **Standard Android Structure**: Follows typical Android project organization with `src/main`, `src/test`, and `src/androidTest` directories
- **Layout System**: Uses XML layouts with LinearLayout as the root container in the main activity
- **Resources**: Standard Android resource structure with strings, colors, themes, and drawable resources

## Build and Development Commands

### Building the App
```bash
./gradlew build                    # Build and test the project
./gradlew assembleDebug           # Build debug APK
./gradlew assembleRelease         # Build release APK
./gradlew installDebug            # Install debug build on connected device
```

### Testing
```bash
./gradlew test                    # Run unit tests
./gradlew testDebugUnitTest       # Run debug unit tests specifically
./gradlew connectedAndroidTest    # Run instrumented tests on connected devices
./gradlew connectedDebugAndroidTest # Run debug instrumented tests
```

### Project Management
```bash
./gradlew clean                   # Clean build directory
./gradlew check                   # Run all checks including tests
./gradlew tasks                   # List all available tasks
```

## Key Files and Structure

### Build Configuration
- `build.gradle.kts`: Root project build configuration
- `app/build.gradle.kts`: App module build configuration with dependencies and Android settings
- `gradle/libs.versions.toml`: Version catalog for dependency management
- `settings.gradle.kts`: Project settings and module inclusion

### Main Application
- `app/src/main/java/com/koss/cuentabar/MainActivity.java`: Main activity with EdgeToEdge support
- `app/src/main/AndroidManifest.xml`: App manifest with activity declarations
- `app/src/main/res/layout/activity_main.xml`: Main activity layout (LinearLayout with TextView)

### Dependencies
The project uses standard Android libraries:
- AndroidX AppCompat, Activity, ConstraintLayout
- Material Design Components
- JUnit for unit testing
- Espresso for UI testing

## Development Notes

- The project uses Gradle with Kotlin DSL (.kts files) for build configuration
- Version catalog pattern is implemented for dependency management in `libs.versions.toml`
- EdgeToEdge display is enabled in MainActivity for modern Android UI
- Standard Android testing setup with both unit tests and instrumented tests