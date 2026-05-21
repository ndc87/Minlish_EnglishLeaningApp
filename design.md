--------------------------------------------------------------------------------
# MinLish App UI/UX Design Specification
**Tech Stack:** Kotlin, Jetpack Compose, Material Design 3, MVVM.

  ## 1. Design Philosophy & Inspirations
  The goal is to combine the **scientific rigor of Anki** (Spaced Repetition System - SM-2) with the **engaging, gamified UI of Duolingo and Quizlet**.
- **Duolingo's trait:** Clean, rounded corners, progress bars, visual streak flames, and chunked learning (topics).
- **Anki's trait:** Flashcard flipping, granular spaced repetition buttons (Again, Hard, Good, Easy).
- **Quizlet's trait:** Elegant word list management and filtering.

---

## 2. Vocabulary Tab (`VocabScreen.kt`)
**Purpose:** A dictionary and deck management screen where users can browse, search, and manage their vocabulary.

### 2.1. Top App Bar & Overview
- **Search Bar:** A prominent search bar at the top to find specific words.
- **SRS Status Filters (Scrollable Row of Chips):**
  Allow users to filter words based on their SM-2 learning status:
    - 🟣 **All** (All words in DB)
    - 🔵 **New** (Words never reviewed)
    - 🟠 **Learning/Due** (Words that are due for review today)
    - 🟢 **Mastered** (Words with an interval > 21 days)

### 2.2. Main Content: Topic-based Grouping
Words must be visually grouped by Topics (e.g., TOEIC themes: "Contracts", "Marketing", "Warranties").
- **UI Component:** Use `LazyColumn` for virtual scrolling.
- **Grouping:** Use `stickyHeader` to display the Topic Name (e.g., "📁 Topic 1: Contracts").
- **Word Card Item (`VocabListItem`):**
    - **Left side:** The English Word (bold) and Part of Speech (POS).
    - **Right side:** A small status indicator (e.g., a green checkmark for Mastered, an orange clock for Due).
    - **Expansion:** Clicking a word expands it (using `AnimatedVisibility`) to show the Meaning, Example sentence, and a Pronunciation (Audio) icon.

### 2.3. Floating Action Button (FAB)
- A prominent `+` FAB at the bottom right to manually add a new word to a specific topic.

---

## 3. Learn Tab (`LearnScreen.kt` / `FlashcardScreen.kt`)
**Purpose:** The core Spaced Repetition engine where Active Recall happens. It must be distraction-free.

### 3.1. Header: Gamification & Progress
- **Progress Bar:** A horizontal progress bar showing how many cards are left in today's review session (Duolingo style).
- **Daily Streak:** A small 🔥 flame icon showing the current streak count at the top right.

### 3.2. Main Content: The Flashcard
- **Card UI:** A large, elevated Material 3 `Card` with rounded corners. It should take up most of the screen.
- **Animation:** Implement a **3D Flip Animation** (using `graphicsLayer { rotationY }`).
- **Front of Card:**
    - Displays ONLY the Target Word (large font) to force Active Recall.
- **Back of Card (Revealed on tap):**
    - Target Word.
    - Phonetic transcription & Audio Icon (to play pronunciation).
    - Meaning in Vietnamese.
    - Example sentence (Context-based learning) with the target word highlighted.

### 3.3. Footer: Evaluation Buttons (Anki Style)
*These buttons only appear AFTER the card is flipped.*
Map the SM-2 algorithm to 4 distinct, color-coded buttons. Show the estimated "Next Review Time" above each button.
- 🔴 **Again (Quên):** Resets the card (e.g., "< 1 min").
- 🟠 **Hard (Khó):** Short interval.
- 🟢 **Good (Nhớ):** Standard interval.
- 🔵 **Easy (Dễ):** Long interval (e.g., "4 days").

---

## 4. Color Palette & Typography (Material 3)
- **Primary Color:** A friendly, motivating color (e.g., Duolingo Green or Quizlet Blue).
- **Background:** Off-white or light gray to make the white flashcards pop.
- **Typography:** Sans-serif, bold headers, highly legible text for vocabulary. Provide adequate padding and line spacing.

## 5. Developer Instructions
Please generate the Jetpack Compose code for `VocabScreen` and `LearnScreen` adhering to this design specification. Ensure that the UI components are modular, reusable, and bind smoothly to the existing MVVM + Hilt + Room DB architecture.

--------------------------------------------------------------------------------