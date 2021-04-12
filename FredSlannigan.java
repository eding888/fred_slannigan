package fredpack;


import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;



public class FredSlannigan {
	
	
	public static void main(String[] args) throws LoginException{
		JDA jda = JDABuilder.createDefault("Njk3ODQ0NjMzMTY1NjI3NDky.Xo9MdQ.d_xAoCq_TBWwaLlDVxmo-thBV_Q").addEventListeners(new Commands()).setMemberCachePolicy(MemberCachePolicy.ALL).setChunkingFilter(ChunkingFilter.ALL).enableIntents(GatewayIntent.GUILD_PRESENCES).enableIntents(GatewayIntent.GUILD_MEMBERS).enableIntents(GatewayIntent.GUILD_VOICE_STATES).enableCache(CacheFlag.VOICE_STATE).enableCache(CacheFlag.ACTIVITY).build();
		jda.getPresence().setActivity(Activity.playing("fred michanigan"));
		
	}
	
}
