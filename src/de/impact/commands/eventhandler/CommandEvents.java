package de.impact.commands.eventhandler;

import de.impact.utils.ChatUtils;
import de.impact.utils.PrefixUtils;
import de.impact.utils.UserUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class CommandEvents implements Listener {

	private static final String HANDLER_NAME = "chatHandle";

	public static void registerPipelines() {

		for(var p : Bukkit.getOnlinePlayers()) {

			var pline = ((CraftPlayer)p).getHandle().playerConnection.networkManager.channel.pipeline();

			if(pline.toMap().containsKey(HANDLER_NAME))
				pline.remove(HANDLER_NAME);

			pline.addAfter("decoder", HANDLER_NAME, new MessageToMessageDecoder<Packet<?>>() {

				@Override
				protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) throws Exception {
					handleChat(p, packet, list);
				}
			});

		}

	}

	public static void removePipelines() {
		for(var p : Bukkit.getOnlinePlayers()) {

			var pline = ((CraftPlayer) p).getHandle().playerConnection.networkManager.channel.pipeline();

			if (pline.toMap().containsKey(HANDLER_NAME))
				pline.remove(HANDLER_NAME);
		}
	}

    @EventHandler
	public void onJoin(PlayerJoinEvent e) {

		var p = e.getPlayer();

		((CraftPlayer)p).getHandle().playerConnection.networkManager.channel.pipeline().addAfter("decoder", HANDLER_NAME, new MessageToMessageDecoder<Packet<?>>() {

			@Override
			protected void decode(ChannelHandlerContext ctx, Packet<?> packet, List<Object> list) {
				handleChat(p, packet, list);
			}
		});
	}

	private static void handleChat(Player p, Packet<?> packet, List<Object> list) {

		if(!(packet instanceof PacketPlayInChat chat) || chat.a().startsWith("/")) {
			list.add(packet);
			return;
		}

		if(!UserUtils.isLoggedIn(p.getUniqueId()) && UserUtils.isAllowedToLogin(p.getUniqueId()) && chat.a().toLowerCase().startsWith(PrefixUtils.getPrefix() + "login")) {
			UserUtils.login(p.getUniqueId());
			return;
		}

		if(!UserUtils.isLoggedIn(p.getUniqueId())) {
			list.add(packet);
			return;
		}

		handleCommand(chat.a(), p);
	}

	private static void handleCommand(String s, Player p) {

		for (var command : Command.getAllCommands()) {

			var cmd = command.isCommandValid(s);

			if(cmd == null) continue;

			var aliases = s.replaceFirst("(?i)"+cmd, "").trim().split(" ");

			command.onChat((aliases[0].equals("") ? new String[] {} : aliases), p);
			return;

		}

		ChatUtils.sendMessage(p, "This command is not available. Use help for help or Chat to chat");
	}

}
