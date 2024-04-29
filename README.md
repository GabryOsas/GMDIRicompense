# GMDIRicompense
[Plugin creato da GMDIDevelopment](https://t.me/GMDIdevelopment)

Questo plugin implementa i rispettivi comandi:
- riscatta (ID)
- reload
# File di configurazione
```yaml
Ricompense:
  prova:
    Permission: "none" #Nessun permesso
    1:
      command: "give %player% stick"
    2:
      Material: LEATHER_CHESTPLATE
      Name: "&bProva"
      Model-Data: 1
      Amount: 5
      Lore:
        - "&dProva"
        - "&cProva"
      Flags:
        - HIDE_ENCHANTS
      Unbreakable: true
      Color: 255 0 0
Messaggi:
  Help-Message:
    - "&b&lRICOMPENSE"
    - "&e/ricompense riscatta (ID)"
    - "&e/ricompense reload"
    - "&7Plugin creato da &e@GMDIdevelopment"
  Ricompensa-Riscattata: "&aRicompensa &e%name% &ariscattata con successo!"
  Ricompensa-Non-Trovata: "&cRicompensa non trovata!"
  Ricompensa-Reload: "&aFile di configurazione ricaricato con successo!"
  Ricompensa-Permessi: "&cNon hai abbastanza permessi!"
```
[![SapnC.gif](https://s12.gifyu.com/images/SapnC.gif)](https://gifyu.com/image/SapnC)
