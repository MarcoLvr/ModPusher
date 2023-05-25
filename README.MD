# MOD PUSHER v1.0.1 🔨

#### By PuppaBusters 👥

## Description ℹ️

This mod allows you to simply push your custom mods that are not on curseforge into your modpacks.

Everything you need is just a Name, an Url and the Hash.

## How to use 📝

1. Put the mod into your modpack from the curseforge launcher.
2. Start the modpack once.
3. Get the mod you want to push Hash value.
4. Go to your modpack folder and open the `config` folder.
5. Open the `modpusher/config.yml` file.
6. Add your mod to the list.

## How to get the Hash 📥

1. Open your modpack folder.
2. Open the `mods` folder.
3. Put there the mods you want and modpusher.
4. Run the command to get your hash (Use Java17) 🚀

```bash
java -jar modpusher.jar theModHasIWant.jar
```
This will output the hash of the mod that you need to add to the config.

## Config Example 📄

```yaml
packName: "EBLCraft-mon"
mods:
  - "url": "https://static.eblcraft.it/coeblmon/iris-mc1.19.2-1.6.2.jar"
    "hash": "c7b26dd5e13de30ddf7ecca16d5c9d41"
    "name": "Iris"
  - "url": "https://static.eblcraft.it/coeblmon/sodium-fabric-mc1.19.2-0.4.4+build.18.jar"
    "hash": "f864470ac732f505dd5c774010449540"
    "name": "Sodium"
```

