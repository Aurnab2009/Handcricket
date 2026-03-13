# Hand Cricket (Java Console Game)

## Overview

This project is a **console-based Hand Cricket game written in Java**. It simulates the traditional hand cricket game played between two players, where a user competes against the computer. The game includes a toss system, batting and bowling phases, score tracking, and even a super over in case of a tie.

The program also contains a simple **pattern recognition mechanism** that attempts to predict the user's most frequently played number during batting.

---

## Features

* User vs Computer gameplay
* Toss system (Head or Tail)
* Choice to bat or bowl after winning the toss
* Customizable number of overs and wickets
* Automatic score tracking
* Pattern-based computer bowling during user batting
* Super Over if the match ends in a tie
* Input validation for incorrect ball inputs

---

## Game Rules

1. The user enters the **number of overs** and **number of wickets** for the match.
2. A **toss** is conducted by choosing either **Head or Tail**.
3. The toss winner chooses to **Bat or Ball first**.
4. Each ball:

   * The user enters a number between **1 and 6**.
   * The computer generates a number between **1 and 6**.
5. If both numbers match:

   * The batter is **out**.
6. If they do not match:

   * The number entered by the batter is added to the score.

The innings ends when:

* All wickets fall, or
* All overs are completed.

The second innings follows the same rules.

---

## Computer Intelligence

During the user's batting phase, the program records the frequency of each number entered by the user.

The computer sometimes uses this information to predict the user's most frequent number and attempt to get the user out.

This introduces a simple **probability-based strategy** instead of purely random gameplay.

---

## Super Over

If both teams finish with the same score:

* A **Super Over** is triggered.
* The match is reduced to:

  * **1 over**
  * **2 wickets**
* Scores reset to zero.
* The batting order alternates between super overs until a winner is determined.

---

## Program Structure

### Class

`Handcricket`

### Key Variables

| Variable         | Description                                       |
| ---------------- | ------------------------------------------------- |
| `user_score`     | Stores the total score of the user                |
| `computer_score` | Stores the total score of the computer            |
| `overs`          | Total overs in the match                          |
| `wickets`        | Maximum wickets allowed                           |
| `reader_input[]` | Tracks how many times the user inputs each number |

---

### Important Methods

#### `random_generator_six()`

Generates a random number between **1 and 6**.

---

#### `user_pattern_reader()`

Analyzes user input frequency and returns the number most frequently played by the user.

---

#### `toss_manager()`

Simulates the toss and determines whether the user wins or loses.

Returns:

* `1` → User wins toss
* `0` → User loses toss

---

#### `over_simulator_manager()`

Simulates an entire over (6 balls).

Handles:

* Runs
* Wickets
* Input validation
* Pattern-based computer prediction

Returns the runs and wickets of the over.

---

#### `bat()`

Controls the **user batting innings**.

Tracks:

* Overs completed
* Wickets fallen
* Runs scored

---

#### `ball()`

Controls the **computer batting innings**.

Tracks:

* Overs completed
* Wickets fallen
* Runs scored

---

## How to Run

### Compile

```
javac Handcricket.java
```

### Run

```
java Handcricket
```

---

## Example Gameplay

```
Enter the number of overs and the number of wickets
2
3

Choose Head or Tail
Head

You win
Choose to bat or to ball
Bat

User now batting
Computer : 3
Computer : 5
Wicket Fallen
...
```

---

## Requirements

* Java JDK 8 or later
* Terminal / Command Prompt

---

## Possible Future Improvements

* Better AI prediction model
* Real-time scoreboard display
* Difficulty levels

---

## Author
Aurnab

Created as a Java programming project to simulate the classic hand cricket game using basic object-oriented design and console interaction.
