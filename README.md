# Eco-Points Recycling Tracker

A command-line Java application for tracking household recycling and rewarding it with eco-points.

This project was built as part of a [Coursera course on Java app development (fundamentals, OOP and file I/O)](https://www.coursera.org/learn/java-app-development-project-fundamentals-oop-fileio). It practices object-oriented design, collections, input validation and file I/O with Java serialization.

## Features

- **Register households** with a unique ID, name and address. The join date is recorded automatically.
- **Log recycling events** for a household: the material type (plastic, glass, metal or paper) and its weight in kilograms. Invalid input is rejected and re-prompted.
- **Earn eco-points:** each event earns 10 points per kilogram (rounded down).
- **View households** and each household's recycling history, with total weight and total points.
- **Generate reports:** the household with the most points and the community's total recycled weight.
- **Persistent data:** households are saved to `households.ser` on exit and loaded again at startup.

## Project structure

```
src/
├── EcoPointsRecyclingTracker.java  # Entry point: menu loop, user input, save/load
├── Household.java                  # A household and its recycling events
└── RecyclingEvent.java             # One recycling event (MaterialType enum, weight, date, points)
```

## Getting started

### Requirements

- JDK 8 or later (developed with OpenJDK 27)

### Build and run

From the project root:

```bash
javac -d out src/*.java
java -cp out EcoPointsRecyclingTracker
```

You can also open the project in IntelliJ IDEA and run `EcoPointsRecyclingTracker`.

## Usage

```
=== Eco-Points Recycling Tracker ===
1. Register Household
2. Log Recycling Event
3. Display Households
4. Display Household Recycling Events
5. Generate Reports
6. Save and Exit
Choose an option:
```

Example session:

```
Choose an option: 1
Enter household ID: H001
Enter household name: Smith Family
Enter household address: 12 Green Street
Household registered successfully on 2026-10-07T09:30:00+02:00[Europe/Paris]

Choose an option: 2
Enter household ID: H001
Enter material type (plastic/glass/metal/paper): wood
Invalid material. Must be plastic, glass, metal or paper.
Enter material type (plastic/glass/metal/paper): glass
Enter weight in kilograms: 2.5
Recycling event logged! Points earned: 25
```

Choose option 6 to save your data. Closing the program any other way discards changes made in that session.
