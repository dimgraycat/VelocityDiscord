package ooo.foooooooooooo.velocitydiscord.config.definitions;

import ooo.foooooooooooo.velocitydiscord.config.Config;

import java.util.HashMap;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class MinecraftConfig {
  /// Placeholders available: `discord`
  public String discordChunkFormat = "<dark_gray>[<{discord_color}>Discord<dark_gray>]<reset>";

  /// Placeholders available: `role_color`, `display_name`, `username`, `nickname`
  ///
  /// `<insert>` tag allows you to shift right-click the username to insert `@username` in the chat
  public String usernameChunkFormat =
    "<{role_color}><insert:@{username}><hover:show_text:{display_name}>{nickname}</hover></insert><reset>";

  /// Placeholders available: {discord_chunk}, {username_chunk}, {attachments}, {message}
  public String messageFormat =
    "{discord_chunk} {role_prefix} {username_chunk}<dark_gray>: <reset>{message} {attachments}";

  /// Placeholders available: `url`, `attachment_color`
  public String attachmentFormat =
    "<dark_gray><click:open_url:{url}>[<{attachment_color}>Attachment<dark_gray>]</click><reset>";

  /// Placeholders available: `url`, `link_color`
  public Optional<String> linkFormat = Optional.of("""
    <click:open_url:"{url}"><hover:show_text:"Click to open {url}"><dark_gray>[</dark_gray><{link_color}>Link<dark_gray>]</hover></click>""");

  /// Placeholders available: `server`, `current_server`, `username`
  public Optional<String> playerJoinFormat = Optional.of("""
    <dark_gray>[<gray>{server}</gray>]<reset> <white>{username}</white><green> joined</green>""");

  /// Placeholders available: `server`, `current_server`, `username`
  public Optional<String> playerLeaveFormat = Optional.of("""
    <dark_gray>[<gray>{server}</gray>]<reset> <white>{username}</white><red> left</red>""");

  /// Placeholders available: `server`, `current_server`, `previous_server`, `username`
  public Optional<String> playerServerSwitchFormat = Optional.of("""
    <dark_gray>[<gray>{server}</gray>]<reset> <white>{username}</white><yellow> moved from {previous_server}</yellow>""");

  public boolean receivePlayerChatFromOtherServers = true;
  public boolean receivePlayerJoinFromOtherServers = true;
  public boolean receivePlayerLeaveFromOtherServers = true;
  public boolean receivePlayerServerSwitchFromOtherServers = true;

  public String discordColor = "#7289da";
  public String attachmentColor = "#4abdff";
  public String linkColor = "#4abdff";

  public HashMap<String, String> rolePrefixes = new HashMap<>();

  public void load(Config config) {
    if (config == null) return;

    this.discordChunkFormat = config.getOrDefault("discord_chunk", this.discordChunkFormat);
    this.usernameChunkFormat = config.getOrDefault("username_chunk", this.usernameChunkFormat);
    this.messageFormat = config.getOrDefault("message", this.messageFormat);
    this.attachmentFormat = config.getOrDefault("attachments", this.attachmentFormat);
    this.linkFormat = config.getDisableableStringOrDefault("links", this.linkFormat);
    this.playerJoinFormat = config.getDisableableStringOrDefault("player_join", this.playerJoinFormat);
    this.playerLeaveFormat = config.getDisableableStringOrDefault("player_leave", this.playerLeaveFormat);
    this.playerServerSwitchFormat =
      config.getDisableableStringOrDefault("player_server_switch", this.playerServerSwitchFormat);
    this.receivePlayerChatFromOtherServers =
      config.getOrDefault("receive_player_chat_from_other_servers", this.receivePlayerChatFromOtherServers);
    this.receivePlayerJoinFromOtherServers =
      config.getOrDefault("receive_player_join_from_other_servers", this.receivePlayerJoinFromOtherServers);
    this.receivePlayerLeaveFromOtherServers =
      config.getOrDefault("receive_player_leave_from_other_servers", this.receivePlayerLeaveFromOtherServers);
    this.receivePlayerServerSwitchFromOtherServers =
      config.getOrDefault(
        "receive_player_server_switch_from_other_servers",
        this.receivePlayerServerSwitchFromOtherServers
      );

    this.discordColor = config.getOrDefault("discord_color", this.discordColor);
    this.attachmentColor = config.getOrDefault("attachment_color", this.attachmentColor);
    this.linkColor = config.getOrDefault("link_color", this.linkColor);

    this.rolePrefixes = config.getMapOrDefault("role_prefixes", this.rolePrefixes);
  }

  public String getRolePrefix(String role) {
    return this.rolePrefixes.getOrDefault(role, "");
  }
}
