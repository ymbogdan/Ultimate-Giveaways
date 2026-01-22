<div align="center">

# 🎰 Ultimate Giveaways

**The Ultimate Minecraft Giveaway Plugin with Animated Roulette System**

[![Minecraft](https://img.shields.io/badge/Minecraft-1.20%2B-brightgreen)](https://www.minecraft.net/)
[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.java.com/)
[![Paper](https://img.shields.io/badge/Paper-1.20.4-blue)](https://papermc.io/)
[![License](https://img.shields.io/badge/License-Proprietary-red)](LICENSE)

*Create epic giveaways with a stunning animated roulette system that keeps your players engaged!*

</div>

---

## 🚀 Features

<div align="center">

### ⚡ **Powerful & Intuitive**

</div>

| 🎁 | **Complete Giveaway System** | Start giveaways by simply holding an item in your hand |
|---|---|---|
| 📦 | **Multi-Stack Support** | Hold multiple items? They're all added to the prize pool! |
| 🎰 | **Animated Roulette** | Beautiful visual extraction that randomly displays participants |
| ⏱️ | **Extended Duration** | 9-second roulette for maximum suspense and excitement |
| 🚫 | **Exclusion System** | Permission-based automatic player exclusion |
| 🎨 | **Customizable Titles** | Fully configurable title messages in config.yml |
| 🔒 | **Secure & Safe** | One giveaway at a time with online participant checks |

---

## 📦 Requirements

- **Minecraft Version**: `1.20+`
- **Server Type**: Paper or Spigot
- **Java Version**: `17+`

---

## 🛠️ Installation

### Quick Setup

1. 📥 Download the latest release from the [Releases](../../releases) section
2. 📁 Drop the `.jar` file into your server's `plugins` folder
3. 🔄 Restart your server
4. ⚙️ (Optional) Customize titles in `config.yml`

### That's it! You're ready to go! 🎉

---

## 🎮 Commands

<div align="center">

| Command | Description | Permission |
|:--------|:------------|:-----------|
| `/giveaway start` | Start a giveaway with the item in your hand | `ultimate.giveaway.start` |
| `/giveaway reload` | Reload the plugin configuration | `ultimate.giveaway.reload` |

</div>

---

## 🔐 Permissions

<div align="center">

| Permission | Default | Description |
|:-----------|:--------|:------------|
| `ultimate.giveaway.start` | `op` | Allows players to start giveaways |
| `ultimate.giveaway.reload` | `op` | Allows players to reload the configuration |
| `ultimate.giveaway.excluded` | `false` | Automatically excludes players from giveaways |

</div>

---

## ⚙️ Configuration

### Customizable Titles

Edit `config.yml` to customize all giveaway messages:

```yaml
titles:
  start:
    title: "&b&lɢɪᴠᴇᴀᴡᴀʏ ꜱᴛᴀʀᴛᴇᴅ ʙʏ {starter}!"
    subtitle: "&f{item} &7ɪɴ ᴘʀɪᴢᴇ"
  roulette:
    title: "&bᴇxᴛʀᴀᴄᴛɪᴏɴ ɪɴ ᴘʀᴏɢʀᴇꜱꜱ..."
    subtitle: "&7{player}"
  winner:
    title: "&a&lᴡɪɴɴᴇʀ!"
    subtitle: "&f{winner}"
```

### Available Placeholders

| Placeholder | Description |
|:------------|:------------|
| `{starter}` | Name of the player who started the giveaway |
| `{item}` | Name of the prize item |
| `{player}` | Name of the player shown during roulette |
| `{winner}` | Name of the final winner |

---

## 🎯 How to Use

<div align="center">

### **Simple 5-Step Process**

</div>

1. 🎁 **Hold the item** you want to give away (you can hold multiple!)
2. 💬 **Run** `/giveaway start`
3. ✨ **Watch** as the item is removed from your inventory
4. ⏳ **Wait** 3 seconds, then enjoy the 9-second animated roulette
5. 🏆 **Celebrate** as a random winner is selected and receives the prize!

---

## 🎬 How It Works

```
┌─────────────────────────────────────────────────────────┐
│                    GIVEAWAY FLOW                        │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  1. Player holds item → /giveaway start                │
│     ↓                                                   │
│  2. Item removed → Title shown to all players          │
│     ↓                                                   │
│  3. 3-second countdown begins                           │
│     ↓                                                   │
│  4. 9-second animated roulette displays participants   │
│     ↓                                                   │
│  5. Random winner selected → Prize awarded! 🎉         │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 💻 Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/yourusername/UltimateGiveaways.git
cd UltimateGiveaways

# Build the project
mvn clean package

# Find the JAR in target/
```

### Project Structure

```
src/main/java/it/ymbogdan/ultimateGiveaways/
├── UltimateGiveaway.java          # Main plugin class
├── command/
│   └── GiveawayCommand.java       # Command handler
├── manager/
│   └── GiveawayManager.java       # Giveaway logic & roulette
└── util/
    ├── TitleUtil.java             # Title management
    ├── PlaceholderUtil.java       # Placeholder system
    └── MiniMessageUtil.java       # MiniMessage utilities
```

---

## 📝 Important Notes

- ⚠️ Only **one giveaway** can be active at a time
- 🚫 Players with `ultimate.giveaway.excluded` permission are **automatically excluded**
- 🎨 Plugin uses **Adventure API** for modern title support
- ✅ Exclusion checks happen **during roulette** for real-time updates

---

## 🐛 Bug Reports & Support

Found a bug? Have a suggestion? 

👉 [Open an Issue](../../issues) and let us know!

Please include:
- Minecraft version
- Server type (Paper/Spigot)
- Plugin version
- Detailed description of the issue

---

## 📄 License

This project is licensed under a proprietary license. All rights reserved.

---

<div align="center">

### ⭐ **Star this repo if you like the plugin!** ⭐

**Made with ❤️ by ymbogdan**

[⬆ Back to Top](#-ultimate-giveaways)

</div>
