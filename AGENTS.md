# Project context

## Goal

Build a Windows desktop application for learning Java desktop development and local database use. The working project name is NrcDocApp.

## Decisions made with the user

- Target Windows only.
- Use Java, matching the user's experience.
- Create the project in IntelliJ IDEA using the JavaFX generator and Maven build system.
- Use Oracle OpenJDK 27 as the development JDK.
- Use SQLite for the application's local data store. The database will be a single file, for example `nrcdocapp.db`.
- This is for learning only. Use fictional patient data, never real patient information.
- The app should run on another Windows PC without requiring Java or a database server to be installed there.
- Preferred distribution is a portable Windows app-image folder containing the launcher and bundled Java runtime; the user can copy or zip the whole folder. It is not a single standalone `.exe` file.
- The user wants design and implementation decisions discussed in chat before making project changes. Make changes when the user explicitly asks for them.

## Planned toolchain and distribution

- Use JavaFX for the graphical interface; let the IntelliJ generator create the initial configuration.
- Use `jpackage` (likely through Maven tooling) to create the portable Windows app image.

## Still to decide

- The app's exact purpose and features.
- Review the JavaFX generator's Maven configuration and decide whether to adjust it.
- Patient fields and the SQLite schema.
- Where the database file should live in the portable app setup.

## Current generated project state

- IntelliJ generated the Maven JavaFX project in the nested folder `myNrcDocApp/` under the repository root.
- IntelliJ project SDK is set to Oracle OpenJDK 27.
- Maven artifact/name and Java module/package are normalized to `nrcdocapp` / `sk.nrcdocapp`.
- JavaFX Controls and FXML dependencies use version 27; compiler source/target are 27.
- Template classes and FXML are named `NrcDocApp`, `MainController`, and `main-view.fxml`.
- The main window has a top navigation bar for HOME, TERAJŠIE OCHORENIE, ANAMNÉZA, OBJEKTÍVNY NÁLEZ, and ZÁVER. `main-view.fxml` contains the shared navigation and content host; each page has its own FXML in `src/main/resources/sk/nrcdocapp/views/` and controller in `sk.nrcdocapp.controller`.
- Loaded page roots are cached while switching, so in-memory form contents can remain when navigating away and back.
- Navigation clicks animate the selected button with a brief scale-down-and-back effect in `controller/MainController.java`.
- Navigation appearance is in `src/main/resources/sk/nrcdocapp/app.css`.
- HOME displays `src/main/resources/sk/nrcdocapp/images/homeImage.jpg` as a bundled classpath resource.
- The Terajšie ochorenie screen currently has male/female radio buttons, a digits-only age field with a gender-sensitive Slovak suffix, a generate button, an editable output area, and a clipboard copy button.
- The generated skeleton includes JavaFX Controls and FXML, plus a Maven Wrapper using Maven 3.8.5.
- No ControlsFX, BootstrapFX, or FormsFX dependency is currently present.
- The IntelliJ project directory is still named `myNrcDocApp` inside the repository; the user has not asked to move or rename this directory.

## Working with this context

- Keep this file concise and update it when the user makes or changes a project decision.
- Do not treat recommendations above as user-approved until confirmed.
