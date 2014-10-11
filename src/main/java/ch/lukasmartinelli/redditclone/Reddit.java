package ch.lukasmartinelli.redditclone;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;

public class Reddit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private User user;
	private URL link;
	private Date createTime;
	private ArrayList<Comment> comments = new ArrayList<>();
	private ArrayList<UpVote> upVotes = new ArrayList<>();
	private ArrayList<DownVote> downVotes = new ArrayList<>();
	
	public Reddit(){
	}
	
	public Reddit(String titel, User user){
		this.title = titel;
		this.setUser(user);
	}
	
	public void setTitle(String titel){
		this.title = titel;
	}
	public String getTitle(){
		return title;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public URL getLink() {
		return link;
	}
	public void setLink(URL link) {
		this.link = link;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public void addComment(Comment comment){
		comments.add(comment);
	}
	public ArrayList<Comment> getComments(){
		return comments;
	}

	public ArrayList<UpVote> getUpVotes() {
		return upVotes;
	}
	public void addUpVote(UpVote upVotes) {
		this.upVotes.add(upVotes);
	}

	public ArrayList<DownVote> getDownVotes() {
		return downVotes;
	}
	public void addDownVote(DownVote downVotes) {
		this.downVotes.add(downVotes);
	}

	public int getVotesCount() {
		return upVotes.size() - downVotes.size();
	}
	public void removeVoteFromUser(User u) {
		if(u!=null){
			UpVote up = getUpVoteFromUser(u);
			DownVote dn = getDownVoteFromUser(u);
			if(up!=null) {
				if(upVotes.contains(up)) upVotes.remove(up);
			}
			if(dn!=null) {
				if(downVotes.contains(dn)) downVotes.remove(dn);
			}
		}
	}
	public UpVote getUpVoteFromUser(User u) {
		for(UpVote v: upVotes) {
			if(v.getUser().getLogin().equals(u.getLogin())){
				return v;
			}
		}
		return null;
	}
	public DownVote getDownVoteFromUser(User u) {
		for(DownVote v: downVotes) {
			if(v.getUser().getLogin().equals(u.getLogin())){
				return v;
			}
		}
		return null;
	}
	

	public void upVote() {
		User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("User");
		
		//if(u!=null) {
			this.removeVoteFromUser(u);
			addUpVote(new UpVote(u));
		//}

	}
	public void downVote() {
		User u = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("User");
		
		//if(u!=null) {
			this.removeVoteFromUser(u);
			addDownVote(new DownVote(u));
		//}
	}

}
