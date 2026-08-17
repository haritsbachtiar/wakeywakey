# WakeyWakey

An alarm clock app built with **Compose Multiplatform**, sharing UI and business logic across Android and iOS from a single Kotlin codebase.

> **Status** — Android: complete and working. iOS: in progress (shared code compiles; platform-specific alarm scheduling not yet implemented).

---

## What it does

Set alarms, name them, and get woken up. Three screens:

| Screen | What it does |
|---|---|
| **Alarm list** | All saved alarms with a live countdown to the next ring, a toggle to enable/disable, and swipe-to-delete |
| **Alarm detail** | Create or edit an alarm — time picker, custom name dialog, save-state handling, back-navigation guard against losing unsaved changes |
| **Alarm trigger** | Full-screen wake-up screen that appears over the lock screen, plays a looping sound, and dismisses back to the list |

---

## Why the alarm part is the interesting part

Most of this app is ordinary CRUD. The part worth reading is **getting an alarm to actually fire and show a full-screen UI on modern Android**, which is harder than it looks:

**The API changed under you.** Before Android 10 you could simply `startActivity()` from a `BroadcastReceiver`. From Android 10 onwards, background activity starts are blocked — so the same code silently does nothing. `AlarmReceiver` branches on the API level and takes a different route on each:

```kotlin
if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
    context?.startActivity(newIntent)          // pre-10: direct start still allowed
} else {
    notificationHandler.start(newIntent, ...)  // 10+: must go through a notification
}
```

**On Android 10+ the full-screen UI has to be requested, not taken.** The app posts a high-importance notification carrying a `setFullScreenIntent(pendingIntent, true)`. That is the only sanctioned way to say "this is urgent enough to take over the screen" — the system then decides whether to show the activity or fall back to a heads-up notification, depending on whether the device is locked and what the user has allowed.

**The notification channel importance is load-bearing.** A full-screen intent on a channel below `IMPORTANCE_HIGH` is quietly downgraded to a normal notification. Easy to get wrong, and it fails silently.

**Platform boundary.** `AlarmScheduler` is a plain interface in `commonMain`; the Android implementation wraps `AlarmManager` and is bound through Koin in `androidSpecificModule`. Shared code schedules alarms without knowing anything about `AlarmManager` — which is what makes the iOS target possible later.

---

## Architecture

```
composeApp/src/
├── commonMain/          ← shared across Android and iOS
│   └── org/example/project/
│       ├── alarm/
│       │   ├── data/            AlarmScheduler (interface), Realm data source, mappers
│       │   ├── domain/          AlarmDataSource contract
│       │   └── presentations/   ViewModel, State, Action, screens & components
│       ├── core/
│       │   ├── data/            Realm client
│       │   └── presentation/    theme
│       └── di/                  Koin modules
├── androidMain/         ← Android-only
│   ├── AlarmActivity            full-screen trigger screen
│   ├── data/AlarmReceiver       BroadcastReceiver, API-level routing
│   ├── data/NotificationHandler channel + full-screen intent
│   └── androidSpecificModule    binds AlarmScheduler to the AlarmManager impl
└── iosMain/             ← iOS entry point (WIP)
```

**Unidirectional data flow.** `AlarmsViewModel` exposes a single `StateFlow<AlarmsState>` and receives every user interaction as an `AlarmsAction`. State is built with `onStart { loadAlarms() }` and `stateIn(..., SharingStarted.WhileSubscribed(5_000L))`, so data loads when a collector appears and survives brief configuration changes without refetching.

**Expect/actual for platform differences.** Sound playback (`EnableAlarmSound`) and theming are declared in `commonMain` and implemented per platform.

---

## Tech stack

| | |
|---|---|
| **UI** | Compose Multiplatform 1.7, Material 3, Navigation Compose |
| **Language** | Kotlin 2.0.21, Coroutines & Flow, kotlinx-datetime |
| **DI** | Koin 4.0 (core, compose, compose-viewmodel, android) |
| **Persistence** | Realm Kotlin 2.3 |
| **Platform** | Android minSdk 24 / target 34; iOS via Kotlin/Native |
| **Testing** | kotlin-test, assertk, Compose UI test |

---

## Testing

`commonTest` covers the countdown formatting logic using a `FakeClock` so the assertions are deterministic rather than dependent on wall-clock time:

```kotlin
assertThat(remainingTime(24, 2)).isEqualTo("24h 2 mins")
```

---

## Running it

**Android**

```bash
./gradlew :composeApp:installDebug
```

Or open the project in Android Studio and run the `composeApp` configuration.

The app needs two runtime permissions on modern Android: **notifications** (Android 13+) and **exact alarm scheduling** (Android 12+). Both are requested on first launch — if alarms never fire, check that exact alarms are allowed in system settings.

**iOS**

Open `iosApp/iosApp.xcodeproj` in Xcode. The shared Compose UI builds and runs; alarm scheduling is not yet implemented on this platform.

---

## What I would do differently

- `NotificationHandler` calls `startActivity()` *and* posts a full-screen intent. Belt and braces, but on Android 10+ only the notification path is legitimate — the direct call is dead code that will start failing more loudly over time.
- The notification channel is named `active_run` / `reset_password`, left over from a template. User-visible on the settings screen, and worth renaming.
- `MediaPlayer` is created and released inside `AlarmActivity`. Moving it behind the `EnableAlarmSound` expect/actual would make the iOS port cleaner.
- Alarm scheduling does not yet survive a device reboot — that needs a `BOOT_COMPLETED` receiver to re-register pending alarms.

---

## Credits

Built to the **Snoozeloo** design brief from Philipp Lackner's Mobile Dev Campus challenge series. The spec and UI design come from the challenge; the implementation here is my own.
