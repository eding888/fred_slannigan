package fredpack;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;



public class TimeCheckThread extends Thread {
	private MessageReceivedEvent event;
	private String firstBlock = new String("07:10");
	private String secondBlock = new String("08:31");
	private String homeroom = new String("09:52");
	private String thirdBlock = new String("10:28");
	private String fourthBlock = new String("12:22");
	private String reset = new String("00:00");
	
	private boolean firstBlockDone = false;
	private boolean secondBlockDone = false;
	private boolean homeroomDone = false;
	private boolean thirdBlockDone = false;
	private boolean fourthBlockDone = false;
	
	private List<String> customStrings = new ArrayList<String>();
	private List<String> customDates = new ArrayList<String>();
	private int serverIndex = 0;
	
	TimeCheckThread(MessageReceivedEvent event, int serverIndex){
		this.event = event;
		this.serverIndex = serverIndex;
	}
	
	TimeCheckThread(MessageReceivedEvent event, String customAddition, String cutomDate, int serverIndex){
		this.event = event;
		customStrings.add(customAddition);
		customDates.add(cutomDate);
		this.serverIndex = serverIndex;
	}
	
	
	public void run() {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		while(Commands.servers.get(serverIndex).get(2).equals("true")) {
			LocalDate localdate = LocalDate.now();
			DayOfWeek day = localdate.getDayOfWeek();
			Date date = new Date();
			
			if(day.getValue() == 3) {
				firstBlock = "07:45";
				secondBlock = "08:20";
				thirdBlock = "08:55";
				fourthBlock = "09:30";
				homeroom = "";
			}
			
			if(Commands.servers.get(serverIndex).get(3).equals("true")) {
				for(int i = 0; i < customStrings.size(); i++) {
					customStrings.remove(i);
					customDates.remove(i);
				}
				Commands.servers.get(serverIndex).set(3, "false");
			}
			if(Commands.servers.get(serverIndex).get(4).equals("true")) {
				for(int i = 0; i < customStrings.size(); i++) {
					
					event.getChannel().sendMessage(customStrings.get(i)).queue();
				}
				Commands.servers.get(serverIndex).set(4, "false");
			}
			for(int i = 0; i < customStrings.size(); i++) {
				if(format.format(date).equals(customDates.get(i))) {
					event.getGuild().getDefaultChannel().sendMessage("**Custom Reminder: **" + customStrings.get(i).substring(0, customStrings.get(i).lastIndexOf("&"))).queue();
					customStrings.remove(i);
					customDates.remove(i);
				}
				
			}
			if(day.getValue() != 6 && day.getValue() != 7) {
				
				    if(format.format(date).equals(firstBlock) && firstBlockDone == false) {
				    	event.getGuild().getDefaultChannel().sendMessage("First block in 5 minutes!").queue();
				    	event.getGuild().getDefaultChannel().sendMessage("https://tenor.com/view/go-planet-of-the-apes-%E0%A4%9C%E0%A4%BE-gif-4833567").queue();
				    	firstBlockDone = true;
				    }
				    if(format.format(date).equals(secondBlock) && secondBlockDone == false) {
				    	event.getGuild().getDefaultChannel().sendMessage("Second block in 5 minutes!").queue();
				    	event.getGuild().getDefaultChannel().sendMessage("https://tenor.com/view/go-planet-of-the-apes-%E0%A4%9C%E0%A4%BE-gif-4833567").queue();
				    	secondBlockDone = true;
				    	
				    }
				    
				    if(format.format(date).equals(homeroom) && homeroomDone == false && !(homeroom.equals(""))) {
				    	event.getGuild().getDefaultChannel().sendMessage("Homeroom in 5 minutes!").queue();
				    	event.getGuild().getDefaultChannel().sendMessage("https://tenor.com/view/go-planet-of-the-apes-%E0%A4%9C%E0%A4%BE-gif-4833567").queue();
				    	homeroomDone = true;
				    }
				    
				    if(format.format(date).equals(thirdBlock) && thirdBlockDone == false) {
				    	event.getGuild().getDefaultChannel().sendMessage("Third block in 5 minutes!").queue();
				    	event.getGuild().getDefaultChannel().sendMessage("https://tenor.com/view/go-planet-of-the-apes-%E0%A4%9C%E0%A4%BE-gif-4833567").queue();
				    	thirdBlockDone = true;
				    }
				    
				    if(format.format(date).equals(fourthBlock) && fourthBlockDone == false) {
				    	event.getGuild().getDefaultChannel().sendMessage("Fourth block in 5 minutes!").queue();
				    	event.getGuild().getDefaultChannel().sendMessage("https://tenor.com/view/go-planet-of-the-apes-%E0%A4%9C%E0%A4%BE-gif-4833567").queue();
				    	fourthBlockDone = true;
				    }


				}
			
		    if(format.format(date).equals(reset)) {
		    	firstBlockDone = false;
		    	secondBlockDone = false;
		    	thirdBlockDone = false;
		    	homeroomDone = false;
		    	fourthBlockDone = false;
		    	
		    	firstBlock = "07:10";
				secondBlock = "08:31";
				thirdBlock = "10:28";
				fourthBlock = "12:22";
				homeroom = "09:52";
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
