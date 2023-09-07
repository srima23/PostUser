package com.example.learningspring.buisness;

import com.example.learningspring.entity.User;

	public class LoggedInUser {
	    private User loggedInUser;
	    
		public User getLoggedInUser() {
			return loggedInUser;
		}

		public void setLoggedInUser(User loggedInUser) {
			this.loggedInUser = loggedInUser;
		}

		public LoggedInUser() {
			super();
		}

		public LoggedInUser(User loggedInUser) {
			super();
			this.loggedInUser = loggedInUser;
		}
	}


