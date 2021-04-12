package fredpack;

import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.audit.TargetType;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.util.*;
public class BanishThread extends Thread{

	private MessageReceivedEvent event;
	private Member target;
	private int serverIndex = 0;
	BanishThread(MessageReceivedEvent event, Member target, int serverIndex){
		this.event = event;
		this.target = target;
		this.serverIndex = serverIndex;
	}
	
	public void run() {
		while(Commands.servers.get(serverIndex).get(7).equals("true")) {
			if(target.getVoiceState().inVoiceChannel()) {
				event.getGuild().kickVoiceMember(target).queue();
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}