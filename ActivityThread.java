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
public class ActivityThread extends Thread{
	private List<Member> memberList = new ArrayList<Member>();
	private List<Boolean> memberStatus = new ArrayList<Boolean>();
	private int serverIndex = 0;
	private MessageReceivedEvent event;
	ActivityThread(MessageReceivedEvent event, int serverIndex){
		this.event = event;
		this.serverIndex = serverIndex;
	}
	
	public void run() {
		for(Member member: event.getGuild().getMembers()) {
			memberList.add(member);
			memberStatus.add(Boolean.TRUE);
		}
		event.getGuild().getDefaultChannel().sendMessage(String.valueOf(memberList.size())).queue();

		while(Commands.servers.get(serverIndex).get(5).equals("true")) {
			
			for(int i = 0; i < memberList.size(); i++) {
				if(memberList.get(i).getActivities().size() > 0 && memberStatus.get(i) == Boolean.FALSE ){
						memberStatus.set(i, Boolean.TRUE);
						String activity = memberList.get(i).getActivities().get(0).getName();
						event.getGuild().getDefaultChannel().sendMessage("**" + memberList.get(i).getEffectiveName() + "**" + " has just hopped on " + "**" + activity + "**").queue();
					
				}
			}
			
			for(int i = 0; i < memberList.size(); i++) {
				if(memberList.get(i).getActivities().size() == 0 && memberStatus.get(i) == Boolean.TRUE) {
					memberStatus.set(i, Boolean.FALSE);
				}
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
