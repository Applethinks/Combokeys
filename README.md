# ComboKeys (Fabric 1.18.2)

A small client-side Minecraft Java mod that maps mouse-button + scroll-wheel combinations to actions.

## Default combos

| Hold | Scroll | Action |
|---|---|---|
| Right mouse button | Up | Open inventory |
| Right mouse button | Down | Close the current screen |
| Left mouse button | Up | Toggle first/third-person perspective |
| Left mouse button | Down | Drop one item |

## Build on GitHub

1. Create a GitHub repository named `ComboKeys`.
2. Upload the **contents** of this ZIP into the repository (not the ZIP file itself).
3. Open the **Actions** tab and run the `Build ComboKeys` workflow, or push a commit.
4. Download `combokeys-1.0.0.jar` from the workflow's Artifacts section.
5. Put the JAR in the `mods` folder for a Minecraft 1.18.2 Fabric profile with Fabric API installed.

## Build locally

Requires JDK 17 and Gradle 8.5 or compatible.

```bash
gradle build
```

The JAR will be created in `build/libs/`.

## Notes

- This is a starter implementation and has not been tested in your specific Mojo Launcher setup.
- Holding left/right click still performs the normal Minecraft click action; the combo is activated when you scroll while holding one of those buttons.
- This project is client-side only and is intended for Fabric 1.18.2.
