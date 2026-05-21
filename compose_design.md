# MinLish App - Jetpack Compose Design System & Refactoring Guide

## 1. Project Context & Tech Stack
**Project:** "MinLish" - An EdTech English Vocabulary App with Spaced Repetition (SM-2).
**Tech Stack:** Kotlin, Jetpack Compose, Material Design 3 (MD3).
**Goal:** Refactor and build the app's UI entirely in Jetpack Compose using Declarative UI principles. Avoid any XML layouts.

## 2. Core Layout Architecture (Compose Basics)
Do not use `ConstraintLayout` unless absolutely necessary. Rely on the core Compose layouts:
- **`Column`:** Use for vertical stacking (e.g., placing the Word, Pronunciation, and Meaning from top to bottom on a Flashcard).
- **`Row`:** Use for horizontal arrangements (e.g., placing the 4 SRS evaluation buttons: Again, Hard, Good, Easy side-by-side). Use `horizontalArrangement = Arrangement.SpaceEvenly` to distribute them nicely.
- **`Box`:** Use for overlapping elements, specifically for stacking the Front and Back of the flashcard to execute the Flip Animation.

## 3. Component Stylings & Modifiers
All styling must be done using the `Modifier` chain.
- **Spacing:** Use `dp` (density-independent pixels) exclusively. Standardize spacing with an 8dp baseline (e.g., `Modifier.padding(8.dp)`, `16.dp`, `24.dp`).
- **Surfaces & Containers:** Do not leave text floating. Wrap content inside MD3 `Surface` or `Card` components.
- **Corner Style:** Use `RoundedCornerShape(12.dp)` for Vocabulary List items and a larger `RoundedCornerShape(16.dp)` for the main Flashcard to give it a modern, friendly look.
- **Shadows/Elevation:** Apply subtle elevations using `CardDefaults.cardElevation(defaultElevation = 4.dp)` to create visual depth without being heavy.

## 4. Typography & Multimedia
- **Text Sizing:** Use `sp` (scalable pixels) for all `Text` composables to support accessibility scaling (e.g., `fontSize = 32.sp` for the main flashcard word, `16.sp` for meanings).
- **Alignment:** Use `textAlign = TextAlign.Center` for flashcard text to keep it perfectly centered.
- **Images/Icons:** Use the `Image` or `Icon` composable with `painterResource()`. Apply `ContentScale.Crop` for placeholder images.

## 5. Specific Screen Requirements

### A. Flashcard Screen (`FlashcardScreen.kt`)
- **Animation:** Implement a smooth 3D Flip using `Modifier.graphicsLayer { rotationY = ... }` powered by `animateFloatAsState`.
- **Active Recall Design:** The front of the card must ONLY show the target word. The back reveals the meaning, example, and a clickable Audio icon (using `MediaPlayer`).
- **Evaluation Footer:** 4 distinct colored buttons (Again=Red, Hard=Orange, Good=Green, Easy=Blue) that only appear *after* the card is flipped.

### B. Vocabulary List Screen (`VocabScreen.kt`)
- **Virtual Scrolling:** Must use `LazyColumn` for high performance when rendering hundreds of TOEIC words.
- **Topic Grouping:** Use `stickyHeader { }` to group words by topic (e.g., "Contracts", "Marketing").
- **List Item Expansion:** Use `AnimatedVisibility` so when a user taps a word, it smoothly expands to show the definition and example.

## 6. Developer Best Practices (Strict Enforcement)
- **Localization:** NO hardcoded strings in the UI. Extract all text to `strings.xml` and access them via `stringResource(R.string.key)`.
- **Accessibility (a11y):** Provide meaningful `contentDescription` for informative icons (like the Audio button). For purely decorative images, explicitly set `contentDescription = null`.
- **Preview:** Every UI component must have a `@Preview(showBackground = true)` function attached so the design can be validated in the Android Studio Design Pane without running the emulator.