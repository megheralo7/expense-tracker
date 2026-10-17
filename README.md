# ApexLedger - Cross-Platform Financial Intelligence & Android Application Suite

ApexLedger is a financial intelligence and budget suite rewritten as a **Native Android (Kotlin / Jetpack Compose / AppWidget)** application architecture with real-time cloud synchronization and Gemini AI insights.

## Android Architecture (`/android/app`)

- **Jetpack Compose UI (Material 3)**:
  - `ComposeMainActivity.kt`: Native Jetpack Compose entrypoint with edge-to-edge system insets, M3 `NavigationBar`, and back-navigation (`BackHandler`).
  - `ui/screens/DashboardScreen.kt`: Net Available Balance, multi-currency switcher (USD, EUR, GBP, JPY, CAD, AUD), Income vs. Expense KPI metric cards, 1-Tap Quick-Log presets, and recent transaction feeds.
  - `ui/screens/TransactionsScreen.kt`: Filterable transactions ledger with IRS Schedule C tax deductibility categorization, search, add, and delete actions.
  - `ui/screens/BudgetsScreen.kt`: Category monthly budgets with 80% threshold warnings, real-time linear progress bars, and custom budget creation.
  - `ui/screens/SubscriptionsScreen.kt`: Recurring SaaS burn tracking, monthly/annual cost analysis, renewal schedule, and pause/active toggles.
  - `ui/screens/TaxScreen.kt`: Schedule C freelancer write-off audit, 50% meals vs 100% software deductions, and quarterly estimated tax computation.
  - `ui/screens/BankSyncScreen.kt`: Connected financial institutions (Chase, Amex, SVB) with simulated automated data refresh.
  - `ui/screens/CrossPlatformSyncScreen.kt`: Connected devices status (Android phone, tablet, web dashboard) and two-way AES-256 cloud sync.
  - `ui/screens/AiInsightsScreen.kt`: Gemini AI financial health hygiene score (88/100), detected spending leaks, and impact-ranked optimization recommendations.
- **Home Screen AppWidget**:
  - `QuickLogAppWidgetProvider.kt`: 2x2 home screen widget providing 1-tap rapid micro-expense logging (Coffee $4.50, Lunch $15.00, Rideshare $24.00, Fuel $35.00) directly from the launcher.
- **Theme & Adaptive Launcher Icons**:
  - `ui/theme/Theme.kt`, `Color.kt`: Dark emerald aesthetic following M3 guidelines with dynamic contrast.
  - `res/mipmap-anydpi-v26/ic_launcher.xml` & `ic_launcher_round.xml`: Custom adaptive launcher icon with vector foreground and gradient background.
- **State Management & Business Logic**:
  - `viewmodel/FinanceViewModel.kt`: Coroutine `StateFlow` architecture managing reactive transaction logs, budgets, subscriptions, bank connections, cloud devices, and multi-currency exchange rates.
  - `data/model/FinanceModels.kt`: Type-safe Kotlin data classes for all domain models.
- **Android Manifest & Resources**:
  - `AndroidManifest.xml`: Configured with deep links, App Links, AppWidget receiver, and permissions.
  - `res/values/strings.xml`, `colors.xml`, `themes.xml`, `res/layout/widget_quick_log.xml`.

## Web & Simulator Hub (`/src`)

- **Interactive Google Pixel 8 Pro Simulator**:
  - Real-time simulation of the Jetpack Compose app screens and the Android Home Screen AppWidget.
- **WebAPK PWA Direct Installation**:
  - Standalone installation on Android devices via Chrome.
- **Source Code Exporter**:
  - Export and download the complete native Android project bundle for Android Studio.
