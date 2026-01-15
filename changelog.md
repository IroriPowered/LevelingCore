v0.2.0
- Added new config options.
  - enableLevelChatMsgs ( default: false): Enables level up chat messages. 
  - enableXPChatMsgs ( default: true): Enables XP gain chat messages. 
  - enableLevelAndXPTitles ( default: true): Enables level up and XP gain title messages. 
  - enableSimplePartyXPShareCompat ( default: true): Enables compatibility with the Simple Party mod to share XP in parties.
- Added optional support for the Simple Party XP Share mod to share XP between party members.
- Added a fallback if the MultipleHUD mod is not installed, with a warning message and log to install it if want UI to work correctly with other mods.

v0.1.2
- Fixed a bug where the default.yml would have the wrong comment link for H2
- Fixed a bug where /removexp would ADD XP instead of removing it. Whoops.
- Moved all text messages to be translatable now.
  - Added pt-BR translation (machine translated, PRs encouraged to help improve it).
  - Added en-ES translation (machine translated, PRs encouraged to help improve it).
  - Added de-DE translation (machine translated, PRs encouraged to help improve it).
  - Added fr-FR translation (machine translated, PRs encouraged to help improve it).
  - Added ru-RU translation (machine translated, PRs encouraged to help improve it).
- Implemented XP bar in the UI using MultipleHUD mod, which is now required. https://www.curseforge.com/hytale/mods/multiplehud

v0.1.1
- Fixed a bug where all players would get XP and level messages.

v0.1.0
- Initial release with basic functionality that I could think of.