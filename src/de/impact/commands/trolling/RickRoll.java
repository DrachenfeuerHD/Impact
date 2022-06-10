package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class RickRoll extends Command {

    public RickRoll() {
        super("RickRoll", "RickRoll <Player>", "RickRoll someone", CommandCategory.TROLLING, "RR");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        playSound(target);

        ChatUtils.sendMessage(p, "Successfully rick-rolled §a" + target.getName());

    }

    private void pause() {
        try {
            TimeUnit.MILLISECONDS.sleep(150);
        } catch (InterruptedException ignored) {Thread.currentThread().interrupt();}
    }

    // This music is from another plugin, it is not my code.
    private void playSound(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), () -> {
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(1, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.A));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.A));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(1, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.sharp(0, Note.Tone.G));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.G));
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.D));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.BASS_DRUM, Note.natural(0, Note.Tone.C));
            pause();
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            pause();
            p.playNote(p.getLocation(), Instrument.BASS_GUITAR, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.STICKS, Note.natural(0, Note.Tone.D));
            pause();
            p.playNote(p.getLocation(), Instrument.PIANO, Note.natural(0, Note.Tone.F));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.PIANO, Note.sharp(0, Note.Tone.C));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(1, Note.Tone.E));
            p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.natural(0, Note.Tone.D));
        });
    }

}
