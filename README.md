[![Git Releases Version](https://img.shields.io/github/release/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/releases)
[![Last Release](https://img.shields.io/github/release-date/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/releases)
[![Commit Activity](https://img.shields.io/github/commit-activity/m/Keffisor/JDAPlug)](https://github.com/Keffisor/JDAPlug/commits/master)
<br>
# JDAPlug
**JDAPlug** - Add a console and install/create plugins with a easy api to your bot. It's based on the Discord library [JDA](https://github.com/DV8FromTheWorld/JDA) and inspired on [Bukkit](https://dev.bukkit.org/).

# Features
- Console for executing commands directly on the bot.
- Install or create plugins for any bot using this expansion.
- Generate configuration yml files for plugins.
- Simple and easy-to-use API.
- Enhanced event management, improving upon the original JDA system.
- Integrated logging system.
- Reload plugin source code while the bot is running.
- Compatible with Java 8 through Java 21.

### Get started
You can start the jar file in the regular way for build your project with plugins.
```
@echo OFF
java -Xms512M -Xmx512M -jar JDAPlug.jar
PAUSE
```
In case you want to integrate it to already created bot project, you can wrap the jda instance into JDAPlug.
```
public static void main(String[] args) throws LoginException {
       JDA jda = new JDABuilder(AccountType.BOT).setToken(token).build(); //any call of jda
       JDAPlug.start(jda);  
}
```

### Creating plugins 
You will need to set up a plugin.yml on the jar without being on a package
```
name: Name of your plugin
main: your.main.class.of.your.plugin
# optional ⬇
author: Keffisor21
version: v2.3
```
Now you will have to create a class extending the PluginListener class
```
public class Main extends JavaPlugin {

	@Override
	protected void onEnable() {
		JDAPlug.getLogger().info("My plugin enabled");
	}
	
	@Override
	protected void onDisable() {
		JDAPlug.getLogger().info("My plugin disabled");
	}
}
```
You can use the api of events by the JDAPlug without using the ListenerAdapter
```
public class EventTest implements PluginListener {
	/*
	With the new system of events in JDAPlug you will be able to declare any event with a custom name, you can repeat in the same class
	any event and you can set the priority of execution of that event.
        */
	
	@EventHandler(priority = EventPriority.MONITOR) //set the priority of the event, if it's not set, it will be NORMAL by default.
  	public void test(MessageReceivedEvent e) {
            JDAPlug.getLogger().info(e.getMessage().getContentRaw());
  	}

	@EventHandler
	public void test2(MessageReceivedEvent e) {
            JDAPlug.getLogger().info(e.getAuthor().getAsTag());
	}

}
```
In case that you need to register a class with events you must call this function
```
    @Override
	protected void onEnable() {
		JDAPlug.registerEvent(new EventClass());
		JDAPlug.registerEvents(new Event1(), new Event2(), new Event3());
	}
```
After compiling the plugin, you have to move the jar into the plugins folder created by the Bot. You can reload the plugins with the command !reload o !rl and you can see the plugins installed with !plugins or !pl.

### Creating commands for Discord and the Console
```
CommandExecutor command = new CommandExecutor(new CommandData("principalcommand", "Some description for slash command"), "!", "principalcommand", "aliase1", "anotheraliase") {
	@Override
	protected void isExecuted(String[] args, CommandSender sender) {
		if(sender instanceof ConsoleCommand) {
			ConsoleCommand e = (ConsoleCommand)sender;
			JDAPlug.getLogger().info("Hey! This is just a test command for the console :D");
		}
		if(sender instanceof TextCommand) {
			TextCommand e = (TextCommand)sender;
			e.getChannel().sendMessage("Hey! This is just a test command without slash commands :D").queue();
		}
		if(sender instanceof SlashCommand) {
			SlashCommand e = (SlashCommand)sender;
			e.reply("Hey! This is just a test command by slash commands :D").queue();
		}
		sender.sendSenderMessage("This message will sent without caring about the type");
	}
  }; 
}

JDAPlug.registerEvent(command);
```
You have to use the class of CommandExecutor which you can set a slash command, prefix, a principal command or command aliases.
**The commands on the console doesn't have prefix, if you command is "!test" you have to execute as "test" on the console**

### Creating configs for plugins
```
public class Main extends JavaPlugin {
	public static FileConfiguration config;
	
	@Override
	protected void onEnable() {
		JDAPlug.registerEvent(new Event());		
		config = getConfig("config.yml");
 		
	}
	
	@Override
	protected void onDisable() {
		
	}
}
```
The config file is created into a directory with the name of the plugin in the directory of plugins. The config file **must** be created inside the jar without any package. The data that contains that config will be created into the directory of the plugin.
```
e.getChannel().sendMessage(Main.getInstance().config.getString("Message.NoPermission")).queue(); 
config.set("something", 1); //set data to the config
```
Works exactly the same as Bukkit. In addition, the yml files can have comments, will be not removed if the comment has a unique line only for them.
<h2><strong>Screenshots</strong></h2>
<img src="https://i.imgur.com/ftzRALM.png">
<img src="https://i.imgur.com/SCTW9Cu.png">
<img src="https://i.imgur.com/ZyRzR6f.png">
<h2><strong>Example</strong></h2>
<p>You can download an example of a plugin <a href="https://keffisor21.com/downloads/CommandCreator.jar">here</a></p>
<br>
<br>
<br>
