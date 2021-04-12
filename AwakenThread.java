package fredpack;

import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.audit.TargetType;
import net.dv8tion.jda.api.entities.Activity;
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


public class AwakenThread extends Thread {
	private MessageReceivedEvent event;
	private Member member;
	private int serverIndex = 0;
	AwakenThread(MessageReceivedEvent event, Member member, int serverIndex){
		this.event = event;
		this.member = member;
		this.serverIndex = serverIndex;
	}
	
	public void run() {
		int awakening = 0;
		boolean one = true;
		while(awakening < 30 && Commands.servers.get(serverIndex).get(1).equals("true")) {
    		if(one) {
    			event.getGuild().moveVoiceMember(member, event.getGuild().getVoiceChannelsByName("1", false).get(0)).complete();
    			try {
					Thread.sleep(750);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			one = !one;
    		}
    		else {
    			event.getGuild().moveVoiceMember(member, event.getGuild().getVoiceChannelsByName("2", false).get(0)).complete();
    			try {
					Thread.sleep(750);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			one = !one;
    		}
    		
    		awakening++;
    		
	}
    	Member sender = event.getGuild().getMember(event.getAuthor());
    	event.getGuild().moveVoiceMember(member, sender.getVoiceState().getChannel()).queue();
    	
}
}