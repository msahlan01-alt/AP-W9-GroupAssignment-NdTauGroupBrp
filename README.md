# 🎮 OOO Driver Transformation Simulator

**📌 Project Title**  
OOO Driver Transformation Simulator (Kamen Rider OOO Medal Scanner)

**👥 Group Members**  
[Muh. Sahlan Rahman](https://github.com/msahlan01-alt)ㅤㅤㅤㅤ- 2086022510038<br>
[Jason Silvester Djaya](https://github.com/Sonnn03)ㅤㅤ ㅤㅤ- 2086022510027<br>
[Christiano Waliliong](https://github.com/Chr1st1an0-UC) ㅤ ㅤㅤㅤ- 2086022510025<br>
[Intan Adhara Tangkelangi](https://github.com/Intan-afk)ㅤㅤ - (nim)

---

## 📖 Project Description  
A console‑based Java application developed for the **Kougami Foundation** that simulates the transformation belt mechanics of **Kamen Rider OOO**.  
Users can manage a collection of Core Medals, insert them into the Driver’s three slots (HEAD, ARMS, LEGS), and scan the current configuration.  
The system detects **official combos**, identifies **mixed forms**, and announces **Scanning Charges** when the same loadout is rescanned.  
The project serves as a classroom demonstration of **Object‑Oriented Programming**, **Java Collections**, **Generics**, and **custom Exception Handling**.

---

## 🎬 Scenario  
The **Kougami Foundation** has commissioned a console‑based training simulator for the **OOO Driver**.  
The simulator must fulfill the following requirements:

- 🏅 **Store many Core Medals** – The system maintains a collection of Core Medals that can be browsed and selected.
- 🎯 **Insert Medals into the Driver** – Medals can be placed into the three dedicated slots: HEAD, ARMS, and LEGS.
- ✅ **Validate Full Combo Formation** – Once all slots are filled, the system checks whether the three medals match one of the eight official combo rules.
- 🔀 **Identify Mixed Forms** – When the slots are correctly occupied but do not form a recognised combo, the system reports a Mixed Form.
- 🔄 **Support Scanning and Re‑scanning** – The belt can be scanned to trigger a transformation. Re‑scanning the same medal configuration without changes announces a Scanning Charge.
- 🛡️ **Safely Handle Invalid Input** – Custom exceptions prevent operations such as inserting a medal into the wrong slot, occupying an already filled slot, or scanning an incomplete belt. All error cases are caught and reported without crashing the program.

This scenario guided the design and implementation of the entire system, ensuring that all user interactions mirror the behaviour of the actual OOO Driver toy from the Kamen Rider series.

---

## ⚙️ How to Compile and Run the Program  

### ✅ Prerequisites  
- Java Development Kit (JDK) **17 or higher**  
- Gradle Wrapper (included – no separate Gradle installation needed)

### 🔨 Build & Run Commands  

| Platform | Command (run in project root) |
|----------|-------------------------------|
| Linux / macOS | `./gradlew build` <br> `./gradlew run` |
| Windows | `gradlew.bat build` <br> `gradlew.bat run` |

### 🧪 Run Unit Tests  
./gradlew test        # (or gradlew.bat test on Windows)

Test reports are generated in build/reports/tests/test/index.html – open this file in any browser to see detailed results.

## ✨ Features

### 🏅 Medal Management
- Insert, eject, and store Core Medals
- Three dedicated slots:
  - HEAD
  - ARMS
  - LEGS

### 🔥 Official Combos (8 Recognized)
- Tatoba  
- Gatakiriba  
- Latorartar  
- Sagohzo  
- Shauta  
- Tajadol  
- Putotyra  
- Burakawani  

### 🌀 Mixed Form Detection
When all slots are filled but no official combo matches, the system outputs:
MIXED FORM

### ⚡ Scanning Charge
Rescanning the exact same configuration triggers:
SCANNING CHARGE!

### 🔊 Sound Simulation
Each combo is associated with a sound effect (simulated via console output).

### 📈 Power Calculation
Total power is calculated based on:
- Medal Tier (I, II, III)
- Number of medals used

### 📦 Storage System
Storage supports the following actions:
- pressFront()
- pullBack()
- open()
- close()

### 🔄 Miring Scan (Tilt Scan)
The driver supports tilted scanning using:
scanMiring()

### 🚫 Cancel Mechanism
Supported across:
- Scanner
- Driver
- Storage

### ⛔ Exception Handling
Prevents invalid operations such as:
- Inserting medal into wrong slot
- Inserting into an occupied slot
- Scanning before all slots are filled

---

## 📚 Java Concepts Demonstrated

### 🗃️ Collections

| Class                  | Collection Used                  | Purpose                          |
|----------------------|----------------------------------|----------------------------------|
| MedalRepository<T>   | ArrayList<T>                     | Generic storage                  |
| OOODriver            | EnumMap<MedalType, CoreMedal>    | Slot-to-medal mapping            |
| ComboCatalogue       | MedalRepository<ComboRule>       | Combo definitions                |
| Storage              | MedalRepository<CoreMedal>       | Player inventory                 |
| TransformationResult | List<CoreMedal>                  | Transformation data              |

---

### 🧬 Generics

The class MedalRepository<T> is a generic repository:

- MedalRepository<CoreMedal> → stores medals  
- MedalRepository<ComboRule> → stores combo rules  

Benefits:
- Type safety
- Code reusability
- No duplication

---

### ⚠️ Exception Handling

| Exception              | Description |
|-----------------------|-------------|
| InvalidSlotException  | Wrong slot insertion |
| SlotOccupiedException | Slot already occupied |
| ScanNotReadyException | Scan before ready |

All exceptions extend RuntimeException and are handled safely in the main flow.

---

## 🏗️ Project Structure
```bash
src/
├── main/java/com/ooo/simulator/model/
│   ├── Main.java
│   ├── driver/
│   │   ├── OOODriver.java
│   │   └── OScanner.java
│   ├── engine/
│   │   ├── ComboCatalogue.java
│   │   ├── TransformationEngine.java
│   │   └── TransformationResult.java
│   ├── exception/
│   │   ├── InvalidSlotException.java
│   │   ├── ScanNotReadyException.java
│   │   └── SlotOccupiedException.java
│   ├── model/
│   │   ├── CoreMedal.java
│   │   ├── MedalType.java
│   │   ├── Tier.java
│   │   ├── ComboStatus.java
│   │   └── ComboRule.java
│   ├── repository/
│   │   └── MedalRepository.java
│   └── storage/
│       └── Storage.java
└── test/java/com/ooo/simulator/model/
    ├── ComboCatalogueTest.java
    ├── OOODriverTest.java
    ├── TransformationEngineTest.java
    ├── StorageTest.java
    └── OScannerTest.java
```
---

## ▶️ How to Run

### Build Project
```bash
./gradlew build
```

### Run Application
```bash
./gradlew run
```

### Run Tests
```bash
./gradlew test
```

---

## 🧪 Example Execution

Insert HEAD medal  
Insert ARMS medal  
Insert LEGS medal  
Scan...

Output:
Combo detected: TATOBA  
Power: 300  
Sound: Playing Tatoba theme...

---

## ⚙️ Design Highlights

- Clean OOP design (separation of concerns)
- Efficient slot mapping using EnumMap (O(1))
- Generic repository pattern
- Modular architecture (Driver, Engine, Storage)

---

## 🚧 Future Improvements

- Add persistent storage (save/load system)
- Real audio playback instead of console simulation
- GUI implementation (JavaFX / Swing)
- Combo rarity system
- Async scanning mechanism

---

## 📜 License

This project is created for educational purposes as part of an Advanced Programming course assignment.
Inspired by Kamen Rider OOO.
