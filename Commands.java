package fredpack;

import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.audit.TargetType;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Message.Attachment;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class Commands extends ListenerAdapter{

	private String prefix = "fred";
	
	public static List<List<String>> servers = new ArrayList<List<String>>();
	private static List<List<Object>> messages = new ArrayList<List<Object>>();
	private static List<List<File>> attachments = new ArrayList<List<File>>();
	private static List<List<Member>> blacklists = new ArrayList<List<Member>>();

	

	@Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
		if(servers.size() == 0) {
			List<String> newServer = new ArrayList<String>();
			newServer.add(event.getGuild().getId());
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			servers.add(newServer);
			
			List<Object> serverMessages = new ArrayList<Object>();
			messages.add(serverMessages);
			List<File> attachment = new ArrayList<File>();
			attachments.add(attachment);
			List<Member> blacklist = new ArrayList<Member>();
			blacklists.add(blacklist);
		}
		boolean contained = false;
		int serverIndex = 0;
		int x = 0;
		for(List<String> server: servers) {
			if(server.get(0).equals(event.getGuild().getId())) {
				contained = true;
				serverIndex = x;
			}
			x++;
		}
		if(contained == false) {
			List<String> newServer = new ArrayList<String>();
			newServer.add(event.getGuild().getId());
			newServer.add("false");
			newServer.add("talse");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			newServer.add("false");
			servers.add(newServer);
			List<Object> serverMessages = new ArrayList<Object>();
			messages.add(serverMessages);
			List<File> attachment = new ArrayList<File>();
			attachments.add(attachment);
			List<Member> blacklist = new ArrayList<Member>();
			blacklists.add(blacklist);
		}
		boolean whitelisted = true;
		for(Member member: blacklists.get(serverIndex)) {
			if(event.getMember().equals(member)) {
				whitelisted = false;
			}
		}
		
		if(whitelisted == false) {
			event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
			return;
			
		}
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw(); 
        
        if(message.getAttachments().size() > 0) {
        	File attachment = null;
        	try {
				attachment = message.getAttachments().get(0).downloadToFile().get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attachments.get(serverIndex).add(attachment);
			
        	messages.get(serverIndex).add("**" + message.getAuthor().getName() + ": " + "**");
        }
        else {
        	messages.get(serverIndex).add("**" + message.getAuthor().getName() + ": " + "**"  + content);
        	attachments.get(serverIndex).add(null);
        }
        
        
        

        if(messages.get(serverIndex).size() > 30) {
        	messages.get(serverIndex).remove(0);

        }
        if(attachments.get(serverIndex).size()>30) {
        	messages.get(serverIndex).remove(0);
        }
        if(content.toLowerCase().contains(prefix + " recorded messages")) {
        	String sendMessages = new String("");
        	for(int i = 0; i < messages.get(serverIndex).size(); i++) {
        		if(attachments.get(serverIndex).get(i) != null) {
        			if(!sendMessages.equals("")) event.getChannel().sendMessage(sendMessages + " \n").queue();
        			sendMessages = "";
        			event.getChannel().sendMessage(messages.get(serverIndex).get(i) + "\n").queue();
        			event.getChannel().sendFile(attachments.get(serverIndex).get(i)).queue();
        		}
        		else {
        			sendMessages = sendMessages + messages.get(serverIndex).get(i) + "\n";
        		}
        	
        	}
        	event.getChannel().sendMessage(sendMessages).queue();
        }
        
        if (content.toLowerCase().contains(prefix + " attack"))
        {
        	Random gen = new Random();
           	TextChannel channel = event.getGuild().getTextChannelsByName("gif-encyclopedia", false ).get(0);
           	
            List<Message> messages = channel.getHistory().retrievePast(99).complete();
            
            event.getChannel().sendMessage(messages.get(gen.nextInt(messages.size())).getContentDisplay()).queue();
            


        }
        if(content.toLowerCase().contains(prefix + " logs")) {
        	List<AuditLogEntry> logs = event.getGuild().retrieveAuditLogs().limit(15).complete();
        	String logsString = new String("");
        	for(AuditLogEntry log: logs) {

        		logsString = logsString + ( "**" + log.getUser().getName() + "**" + ": " + log.getType().name() + " at minute " + log.getTimeCreated().getMinute() + ", second " + log.getTimeCreated().getSecond() + "\n");

        	}
        	
        	event.getChannel().sendMessage(logsString).queue();
        	

        }
        if(content.toLowerCase().contains(prefix + " help")) {
        	String help = new String("");
        	help = help + "**fred awaken [member name]**: force moves member between channels 1 and 2.\n";
        	help = help + "**fred stop**: stops any awaken or muzzle processes.\n";
        	help = help + "**fred attack**: sends random message from gif-encyclopedia.\n";
        	help = help + "**fred logs**: sends recent audit logs.\n";
        	help = help + "**fred initialize reminders**: initializes default reminders.\n";
        	help = help + "**fred initialize reminders (custom message) & (HH:MM)24H Format**: initializes new custom reminder.\n";
        	help = help + "**fred view reminders**: views all custom reminders.\n";
        	help = help + "**fred clear reminders**: clears all custom reminders.\n";
        	help = help + "**fred disable reminders**: disables reminders.\n";
        	help = help + "**fred kick [member name]**: kicks a member.\n";
        	help = help + "**fred recorded messages**: lists recorded messages.\n";
        	help = help + "**fred enable announce**: enables game announcments.\n";
        	help = help + "**fred disable announce**: disables game announcments.\n";
        	help = help + "**fred muzzle [member name]**: muzzles target.\n";
        	help = help + "**fred blacklist [member name]**: blacklists target.\n";
        	help = help + "**fred clear**: clears blacklists.\n";
        	help = help + "**fred banish [member name]**: clears blacklists.\n";
        	
        	
        	event.getChannel().sendMessage(help).queue();
        }
        if(content.toLowerCase().contains(prefix + " awaken")) {
        	String name = content.substring(12);
        	
        	Member member = message.getMentionedMembers().get(0);
        	AwakenThread thread = new AwakenThread(event, member, serverIndex);
        	servers.get(serverIndex).set(1, "true");
        	thread.start();
        	

        }
        
        if(content.toLowerCase().contains(prefix + " stop")) {
        	servers.get(serverIndex).set(1, "false");
        	servers.get(serverIndex).set(6, "false");
        	servers.get(serverIndex).set(7, "false");
        }
        
        if(content.toLowerCase().contains(prefix + " clear")) {
        	blacklists.get(serverIndex).clear();
        	event.getChannel().sendMessage("Cleared.").queue();
        }
        
        
        if(content.toLowerCase().contains(prefix + " initialize reminders")) {
	        	String customMessage = content.substring(25);
	        	TimeCheckThread thread;
	        	if(customMessage.length() > 1) {
	        		thread = new TimeCheckThread(event, customMessage, customMessage.substring(customMessage.lastIndexOf("&") + 2), serverIndex);
	        		event.getChannel().sendMessage("Your reminder has been recorded. Make sure you have used 24 hour format.").queue();
	        		
	        	}
	        
	        		thread = new TimeCheckThread(event, serverIndex);
	        		event.getChannel().sendMessage("Reminders initialized.").queue();
	        
	        	
	        	thread.start();

        }
        if(content.toLowerCase().contains(prefix + " clear reminders")) {
        	event.getChannel().sendMessage("Custom reminders cleared.").queue();
        	servers.get(serverIndex).set(3, "true");
        }
        if(content.toLowerCase().contains(prefix + " view reminders")) {
        	servers.get(serverIndex).set(4, "true");
        }
        if(content.toLowerCase().contains(prefix + " disable reminders")) {
        	servers.get(serverIndex).set(2, "false");
        	event.getChannel().sendMessage("Reminders disabled.").queue();
        }
        
        if(content.toLowerCase().contains(prefix + " kick")) {
        	Member target = message.getMentionedMembers().get(0);
        	KickThread thread = new KickThread(event, target, serverIndex);
        	thread.start();
        }
        
        if(content.toLowerCase().contains(prefix + " enable announce")) {
	        	event.getChannel().sendMessage("Game announcements for all members has been enabled.").queue();
	        	ActivityThread thread = new ActivityThread(event, serverIndex);
	        	servers.get(serverIndex).set(5, "true");
	        	thread.start();
        	
        	
        	
        }
        
        if(content.toLowerCase().contains(prefix + " disable announce")) {

        	servers.get(serverIndex).set(5, "false");
        	event.getChannel().sendMessage("Announcements disabled.").queue();
        }
        
        if(content.toLowerCase().contains(prefix + " muzzle")) {
        	
			
        	servers.get(serverIndex).set(6, "true");
			Member target = message.getMentionedMembers().get(0);
			event.getChannel().sendMessage("https://tenor.com/view/so-quiet-why-so-quiet-heath-ledger-the-joker-gif-12263842").queue();
			MuzzleThread thread = new MuzzleThread(event, target, serverIndex);
			thread.start();
        }
        
        if(content.toLowerCase().contains(prefix + " banish")) {
        	
			
        	servers.get(serverIndex).set(7, "true");
			Member target = message.getMentionedMembers().get(0);
			event.getChannel().sendMessage("https://tenor.com/view/snap-thanos-infinity-war-avengers-gif-14356717").queue();
			BanishThread thread = new BanishThread(event, target, serverIndex);
			thread.start();
        }
        
        
        if(content.toLowerCase().contains(prefix + " blacklist")) {
        	Member target = message.getMentionedMembers().get(0);
        	blacklists.get(serverIndex).add(target);
        	
        	event.getChannel().sendMessage("Blacklisted.").queue();
        }
        

        
    }
    
}
	

    
