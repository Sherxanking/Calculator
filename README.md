![Screenshot_2025-07-17-19-11-49-632_com example calculator](https://github.com/user-attachments/assets/688c2816-eeb6-49f0-897d-810db4977d52)A beautiful and feature-rich Android calculator app built with Jetpack Compose, featuring modern UI design, advanced operations, and multilingual support.

## ✨ Features

### 🎯 Core Calculator Functions
- **Basic Operations**: Addition (+), Subtraction (-), Multiplication (×), Division (÷)
- **Advanced Operations**: Percentage (%), Plus/Minus (±)
- **Smart Percent**: Xiaomi-style percentage calculation
- **Decimal Support**: Precise decimal calculations
- **Number Formatting**: Automatic comma separation for better readability

### 🎨 Modern UI/UX
- **iPhone-style Design**: Clean, modern interface with round buttons
- **Dark/Light Theme**: Toggle between dark and light modes
- **Responsive Layout**: Optimized for different screen sizes
- **Smooth Animations**: Fluid user interactions

### 📱 Multilingual Support
- **English** (Default)
- **Russian** (Русский)
- **Uzbek** (O'zbekcha)

### 📋 History & Clipboard
- **Calculation History**: View recent calculations
- **Clickable History**: Tap to reuse previous results
- **Copy to Clipboard**: One-tap copy with formatted numbers
- **Clear History**: Easy history management

### 🔧 Technical Features
- **Jetpack Compose**: Modern Android UI toolkit
- **MVVM Architecture**: Clean separation of concerns
- **Unit Tests**: Comprehensive test coverage
- **Material Design 3**: Latest design guidelines

<img width="1441" height="613" alt="image" src="https://github.com/user-attachments/assets/5a9834df-6e02-4dca-a146-88aedf2008df" />

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+
- Kotlin 1.8+

### Build Instructions
1. Clone the repository:
   ```bash
   git clone https://[github.com/sherxanking/calculator-app.git](https://github.com/Sherxanking/Calculator.git)
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run on your device or emulator

### APK Installation
1. Download the latest APK from releases
2. Enable "Install from unknown sources" on your device
3. Install the APK file

## 🛠️ Architecture

```
app/
├── src/main/java/com/example/calculator/
│   ├── Calculator.kt              # Main UI composable
│   ├── CalculatorViewModel.kt     # Business logic
│   ├── CalculatorState.kt         # State management
│   ├── CalculatorAction.kt        # User actions
│   ├── CalculatorOperation.kt     # Operation definitions
│   ├── CalculatorButton.kt        # Button component
│   └── MainActivity.kt            # Activity entry point
└── src/main/res/
    ├── values/                    # English strings
    ├── values-ru/                 # Russian strings
    └── values-uz/                 # Uzbek strings
```

## 🧪 Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## 📝 Usage

### Basic Operations
1. Enter first number
2. Select operation (+, -, ×, ÷)
3. Enter second number
4. Press = to calculate

### Advanced Features
- **Percentage**: Enter number, press %, then operation
- **Square Root**: Enter number, press √
- **Square**: Enter number, press x²
- **History**: Tap any history entry to reuse
- **Copy**: Press C to copy current result
- **Theme**: Use theme toggle button for dark/light mode

## 🌟 Key Features Explained

### Smart Percentage Calculation
The app implements Xiaomi-style percentage calculation:
- `100 - 25%` = `100 - 25` = `75`
- `100 + 25%` = `100 + 25` = `125`

### Number Formatting
- Large numbers are automatically formatted with commas
- Example: `1234567` displays as `1,234,567`
- Clipboard copies formatted numbers

### Multilingual Support
- Automatic language detection based on device settings
- Manual language switching available
- All UI elements and messages are localized

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Jetpack Compose team for the amazing UI toolkit
- Material Design team for design guidelines
- Android developer community for inspiration

## 📞 Contact

- **Developer**: [Alisher Kodirov]
- **Email**: [sherxanking@gmail.com]
- **Telegram messenger**: [https://t.me.alisherkodirov](https://t.me/AlisherKodirov)
- **GitHub**: [@sherxanking]

---

⭐ If you find this project helpful, please give it a star! 

- 
