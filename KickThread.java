package fredpack;
import java.util.Random;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.audit.TargetType;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class KickThread extends Thread{
	MessageReceivedEvent event;
	Member target;
	private int serverIndex = 0;
	KickThread(MessageReceivedEvent event, Member target, int serverIndex){
		this.event = event;
		this.target = target;
		this.serverIndex = serverIndex;
	}
	
	public void run() {
		Random gen = new Random();
		int rand = gen.nextInt(6);
		switch(rand) {
		case 0:
			event.getChannel().sendMessage("https://tenor.com/view/kaiba-yugioh-button-smash-gif-11477965").queue();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/orbital-laser-gif-20334732").queue();
			try {
				Thread.sleep(1750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		case 1:
			event.getChannel().sendMessage("https://tenor.com/view/sylvester-stallone-running-rambo-gif-4922369").queue();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/saturday-weekend-sylvester-stallone-on-rage-gif-13405426").queue();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		case 2:
			event.getChannel().sendMessage("https://tenor.com/view/stewie-bitches-die-gun-family-guy-gif-3451886").queue();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/stewie-gun-sniper-gif-14401284").queue();
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		case 3:
			event.getChannel().sendMessage("https://tenor.com/view/clint-eastwood-cowboy-im-ready-gif-14491236").queue();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/cowboy-western-clint-eastwood-gun-gif-14191492").queue();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		case 4:
			event.getChannel().sendMessage("https://tenor.com/view/chopper-helicopter-flying-gif-8912042").queue();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/helicopter-gunner-full-metal-jacket-gif-19741409").queue();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		case 5:
			event.getChannel().sendMessage("https://tenor.com/view/flowers-roses-red-roses-bouquet-gif-15346480").queue();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("https://tenor.com/view/arnold-schwarzenegger-shotgun-hasta-la-vista-%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%E3%82%AC%E3%83%B3-gif-9487483").queue();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getGuild().kickVoiceMember(target).queue();
			break;
		}
	}
}
