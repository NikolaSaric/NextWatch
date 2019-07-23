package com.NextWatch.beans;

public class UserMovieRelation {
	private boolean liked;
	private boolean disliked;
	private boolean watched;
	private boolean watchLater;

	public UserMovieRelation() {
	}

	public UserMovieRelation(boolean liked, boolean disliked, boolean watched, boolean watchLater) {
		this.liked = liked;
		this.disliked = disliked;
		this.watched = watched;
		this.watchLater = watchLater;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public boolean isDisliked() {
		return disliked;
	}

	public void setDisliked(boolean disliked) {
		this.disliked = disliked;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public boolean isWatchLater() {
		return watchLater;
	}

	public void setWatchLater(boolean watchLater) {
		this.watchLater = watchLater;
	}

}
