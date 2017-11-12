package br.com.jonglee.RedditBot;

public class DataReddit {
	private String upvote;
	private String subreddit;
	private String threadTitle;
	private String linkComments;
	private String linkThread;
	/**
	 * @return the upvote
	 */
	public String getUpvote() {
		return upvote;
	}
	/**
	 * @param upvote the upvote to set
	 */
	public void setUpvote(String upvote) {
		this.upvote = upvote;
	}
	/**
	 * @return the subreddit
	 */
	public String getSubreddit() {
		return subreddit;
	}
	/**
	 * @param subreddit the subreddit to set
	 */
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	/**
	 * @return the threadTitle
	 */
	public String getThreadTitle() {
		return threadTitle;
	}
	/**
	 * @param threadTitle the threadTitle to set
	 */
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	/**
	 * @return the linkComments
	 */
	public String getLinkComments() {
		return linkComments;
	}
	/**
	 * @param linkComments the linkComments to set
	 */
	public void setLinkComments(String linkComments) {
		this.linkComments = linkComments;
	}
	/**
	 * @return the linkThread
	 */
	public String getLinkThread() {
		return linkThread;
	}
	/**
	 * @param linkThread the linkThread to set
	 */
	public void setLinkThread(String linkThread) {
		this.linkThread = linkThread;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UpVote: " + upvote + "\n\nSubReddit: " + subreddit + "\n\nThreadTitle: " + threadTitle
				+ "\n\nLinkThread: " + linkThread + "\n\nLinkComments: " + linkComments +"\n\n-----\n\n";
	}
}
